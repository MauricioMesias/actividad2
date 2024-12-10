package com.aiep.actividad2.service;

import com.aiep.actividad2.domain.Jefe;
import com.aiep.actividad2.repository.JefeRepository;
import com.aiep.actividad2.service.dto.JefeDTO;
import com.aiep.actividad2.service.mapper.JefeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.aiep.actividad2.domain.Jefe}.
 */
@Service
@Transactional
public class JefeService {

    private static final Logger LOG = LoggerFactory.getLogger(JefeService.class);

    private final JefeRepository jefeRepository;

    private final JefeMapper jefeMapper;

    public JefeService(JefeRepository jefeRepository, JefeMapper jefeMapper) {
        this.jefeRepository = jefeRepository;
        this.jefeMapper = jefeMapper;
    }

    /**
     * Save a jefe.
     *
     * @param jefeDTO the entity to save.
     * @return the persisted entity.
     */
    public JefeDTO save(JefeDTO jefeDTO) {
        LOG.debug("Request to save Jefe : {}", jefeDTO);
        Jefe jefe = jefeMapper.toEntity(jefeDTO);
        jefe = jefeRepository.save(jefe);
        return jefeMapper.toDto(jefe);
    }

    /**
     * Update a jefe.
     *
     * @param jefeDTO the entity to save.
     * @return the persisted entity.
     */
    public JefeDTO update(JefeDTO jefeDTO) {
        LOG.debug("Request to update Jefe : {}", jefeDTO);
        Jefe jefe = jefeMapper.toEntity(jefeDTO);
        jefe = jefeRepository.save(jefe);
        return jefeMapper.toDto(jefe);
    }

    /**
     * Partially update a jefe.
     *
     * @param jefeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JefeDTO> partialUpdate(JefeDTO jefeDTO) {
        LOG.debug("Request to partially update Jefe : {}", jefeDTO);

        return jefeRepository
            .findById(jefeDTO.getId())
            .map(existingJefe -> {
                jefeMapper.partialUpdate(existingJefe, jefeDTO);

                return existingJefe;
            })
            .map(jefeRepository::save)
            .map(jefeMapper::toDto);
    }

    /**
     * Get all the jefes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<JefeDTO> findAll() {
        LOG.debug("Request to get all Jefes");
        return jefeRepository.findAll().stream().map(jefeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one jefe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JefeDTO> findOne(Long id) {
        LOG.debug("Request to get Jefe : {}", id);
        return jefeRepository.findById(id).map(jefeMapper::toDto);
    }

    /**
     * Delete the jefe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Jefe : {}", id);
        jefeRepository.deleteById(id);
    }
}
