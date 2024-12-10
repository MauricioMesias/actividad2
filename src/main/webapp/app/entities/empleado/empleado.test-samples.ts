import { IEmpleado, NewEmpleado } from './empleado.model';

export const sampleWithRequiredData: IEmpleado = {
  id: 16684,
  nombre: 'above',
  apellido: 'who tuber',
  telefono: 'underneath shakily summary',
  correo: 'yippee switch',
};

export const sampleWithPartialData: IEmpleado = {
  id: 23466,
  nombre: 'huzzah pave save',
  apellido: 'and',
  telefono: 'outrank oh',
  correo: 'plus',
};

export const sampleWithFullData: IEmpleado = {
  id: 7927,
  nombre: 'apropos when',
  apellido: 'questionably about stint',
  telefono: 'surprisingly',
  correo: 'consequently',
};

export const sampleWithNewData: NewEmpleado = {
  nombre: 'violently',
  apellido: 'stuff tensely um',
  telefono: 'through rightfully',
  correo: 'lid woot',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
