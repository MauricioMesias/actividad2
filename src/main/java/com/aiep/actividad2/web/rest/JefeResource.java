package com.aiep.actividad2.web.rest;

import com.aiep.actividad2.repository.JefeRepository;
import com.aiep.actividad2.service.JefeService;
import com.aiep.actividad2.service.dto.JefeDTO;
import com.aiep.actividad2.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.aiep.actividad2.domain.Jefe}.
 */
@RestController
@RequestMapping("/api/jefes")
public class JefeResource {

    private static final Logger LOG = LoggerFactory.getLogger(JefeResource.class);

    private static final String ENTITY_NAME = "jefe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JefeService jefeService;

    private final JefeRepository jefeRepository;

    public JefeResource(JefeService jefeService, JefeRepository jefeRepository) {
        this.jefeService = jefeService;
        this.jefeRepository = jefeRepository;
    }

    /**
     * {@code POST  /jefes} : Create a new jefe.
     *
     * @param jefeDTO the jefeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jefeDTO, or with status {@code 400 (Bad Request)} if the jefe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<JefeDTO> createJefe(@Valid @RequestBody JefeDTO jefeDTO) throws URISyntaxException {
        LOG.debug("REST request to save Jefe : {}", jefeDTO);
        if (jefeDTO.getId() != null) {
            throw new BadRequestAlertException("A new jefe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        jefeDTO = jefeService.save(jefeDTO);
        return ResponseEntity.created(new URI("/api/jefes/" + jefeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, jefeDTO.getId().toString()))
            .body(jefeDTO);
    }

    /**
     * {@code PUT  /jefes/:id} : Updates an existing jefe.
     *
     * @param id the id of the jefeDTO to save.
     * @param jefeDTO the jefeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jefeDTO,
     * or with status {@code 400 (Bad Request)} if the jefeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jefeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JefeDTO> updateJefe(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody JefeDTO jefeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Jefe : {}, {}", id, jefeDTO);
        if (jefeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jefeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jefeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        jefeDTO = jefeService.update(jefeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jefeDTO.getId().toString()))
            .body(jefeDTO);
    }

    /**
     * {@code PATCH  /jefes/:id} : Partial updates given fields of an existing jefe, field will ignore if it is null
     *
     * @param id the id of the jefeDTO to save.
     * @param jefeDTO the jefeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jefeDTO,
     * or with status {@code 400 (Bad Request)} if the jefeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the jefeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the jefeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JefeDTO> partialUpdateJefe(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody JefeDTO jefeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Jefe partially : {}, {}", id, jefeDTO);
        if (jefeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jefeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jefeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JefeDTO> result = jefeService.partialUpdate(jefeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jefeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /jefes} : get all the jefes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jefes in body.
     */
    @GetMapping("")
    public List<JefeDTO> getAllJefes() {
        LOG.debug("REST request to get all Jefes");
        return jefeService.findAll();
    }

    /**
     * {@code GET  /jefes/:id} : get the "id" jefe.
     *
     * @param id the id of the jefeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jefeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JefeDTO> getJefe(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Jefe : {}", id);
        Optional<JefeDTO> jefeDTO = jefeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jefeDTO);
    }

    /**
     * {@code DELETE  /jefes/:id} : delete the "id" jefe.
     *
     * @param id the id of the jefeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJefe(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Jefe : {}", id);
        jefeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
