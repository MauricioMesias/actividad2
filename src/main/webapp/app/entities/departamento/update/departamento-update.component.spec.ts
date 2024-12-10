import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IJefe } from 'app/entities/jefe/jefe.model';
import { JefeService } from 'app/entities/jefe/service/jefe.service';
import { DepartamentoService } from '../service/departamento.service';
import { IDepartamento } from '../departamento.model';
import { DepartamentoFormService } from './departamento-form.service';

import { DepartamentoUpdateComponent } from './departamento-update.component';

describe('Departamento Management Update Component', () => {
  let comp: DepartamentoUpdateComponent;
  let fixture: ComponentFixture<DepartamentoUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let departamentoFormService: DepartamentoFormService;
  let departamentoService: DepartamentoService;
  let jefeService: JefeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [DepartamentoUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DepartamentoUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DepartamentoUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    departamentoFormService = TestBed.inject(DepartamentoFormService);
    departamentoService = TestBed.inject(DepartamentoService);
    jefeService = TestBed.inject(JefeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Jefe query and add missing value', () => {
      const departamento: IDepartamento = { id: 456 };
      const jefe: IJefe = { id: 5540 };
      departamento.jefe = jefe;

      const jefeCollection: IJefe[] = [{ id: 9689 }];
      jest.spyOn(jefeService, 'query').mockReturnValue(of(new HttpResponse({ body: jefeCollection })));
      const additionalJefes = [jefe];
      const expectedCollection: IJefe[] = [...additionalJefes, ...jefeCollection];
      jest.spyOn(jefeService, 'addJefeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ departamento });
      comp.ngOnInit();

      expect(jefeService.query).toHaveBeenCalled();
      expect(jefeService.addJefeToCollectionIfMissing).toHaveBeenCalledWith(
        jefeCollection,
        ...additionalJefes.map(expect.objectContaining),
      );
      expect(comp.jefesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const departamento: IDepartamento = { id: 456 };
      const jefe: IJefe = { id: 27153 };
      departamento.jefe = jefe;

      activatedRoute.data = of({ departamento });
      comp.ngOnInit();

      expect(comp.jefesSharedCollection).toContain(jefe);
      expect(comp.departamento).toEqual(departamento);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepartamento>>();
      const departamento = { id: 123 };
      jest.spyOn(departamentoFormService, 'getDepartamento').mockReturnValue(departamento);
      jest.spyOn(departamentoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ departamento });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: departamento }));
      saveSubject.complete();

      // THEN
      expect(departamentoFormService.getDepartamento).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(departamentoService.update).toHaveBeenCalledWith(expect.objectContaining(departamento));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepartamento>>();
      const departamento = { id: 123 };
      jest.spyOn(departamentoFormService, 'getDepartamento').mockReturnValue({ id: null });
      jest.spyOn(departamentoService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ departamento: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: departamento }));
      saveSubject.complete();

      // THEN
      expect(departamentoFormService.getDepartamento).toHaveBeenCalled();
      expect(departamentoService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepartamento>>();
      const departamento = { id: 123 };
      jest.spyOn(departamentoService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ departamento });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(departamentoService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareJefe', () => {
      it('Should forward to jefeService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(jefeService, 'compareJefe');
        comp.compareJefe(entity, entity2);
        expect(jefeService.compareJefe).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
