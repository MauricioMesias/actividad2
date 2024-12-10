import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IJefe } from 'app/entities/jefe/jefe.model';
import { JefeService } from 'app/entities/jefe/service/jefe.service';
import { IDepartamento } from '../departamento.model';
import { DepartamentoService } from '../service/departamento.service';
import { DepartamentoFormGroup, DepartamentoFormService } from './departamento-form.service';

@Component({
  standalone: true,
  selector: 'jhi-departamento-update',
  templateUrl: './departamento-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class DepartamentoUpdateComponent implements OnInit {
  isSaving = false;
  departamento: IDepartamento | null = null;

  jefesSharedCollection: IJefe[] = [];

  protected departamentoService = inject(DepartamentoService);
  protected departamentoFormService = inject(DepartamentoFormService);
  protected jefeService = inject(JefeService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: DepartamentoFormGroup = this.departamentoFormService.createDepartamentoFormGroup();

  compareJefe = (o1: IJefe | null, o2: IJefe | null): boolean => this.jefeService.compareJefe(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ departamento }) => {
      this.departamento = departamento;
      if (departamento) {
        this.updateForm(departamento);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const departamento = this.departamentoFormService.getDepartamento(this.editForm);
    if (departamento.id !== null) {
      this.subscribeToSaveResponse(this.departamentoService.update(departamento));
    } else {
      this.subscribeToSaveResponse(this.departamentoService.create(departamento));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepartamento>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(departamento: IDepartamento): void {
    this.departamento = departamento;
    this.departamentoFormService.resetForm(this.editForm, departamento);

    this.jefesSharedCollection = this.jefeService.addJefeToCollectionIfMissing<IJefe>(this.jefesSharedCollection, departamento.jefe);
  }

  protected loadRelationshipsOptions(): void {
    this.jefeService
      .query()
      .pipe(map((res: HttpResponse<IJefe[]>) => res.body ?? []))
      .pipe(map((jefes: IJefe[]) => this.jefeService.addJefeToCollectionIfMissing<IJefe>(jefes, this.departamento?.jefe)))
      .subscribe((jefes: IJefe[]) => (this.jefesSharedCollection = jefes));
  }
}
