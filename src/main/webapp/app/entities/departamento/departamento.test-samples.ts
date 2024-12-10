import { IDepartamento, NewDepartamento } from './departamento.model';

export const sampleWithRequiredData: IDepartamento = {
  id: 20944,
  nombre: 'black pick coincide',
  ubicacion: 'reassuringly oddly pluck',
  monto: 15488.8,
};

export const sampleWithPartialData: IDepartamento = {
  id: 30294,
  nombre: 'where although especially',
  ubicacion: 'bump gladly focused',
  monto: 17839.92,
};

export const sampleWithFullData: IDepartamento = {
  id: 28360,
  nombre: 'gleefully',
  ubicacion: 'readjust gorgeous',
  monto: 14372.43,
};

export const sampleWithNewData: NewDepartamento = {
  nombre: 'badly conjecture',
  ubicacion: 'faithfully aw',
  monto: 3154.86,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
