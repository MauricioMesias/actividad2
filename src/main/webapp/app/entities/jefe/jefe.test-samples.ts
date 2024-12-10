import { IJefe, NewJefe } from './jefe.model';

export const sampleWithRequiredData: IJefe = {
  id: 6527,
  nombre: 'unless trusting or',
  apellido: 'retract',
  telefono: 'fathom',
};

export const sampleWithPartialData: IJefe = {
  id: 12538,
  nombre: 'arrogantly',
  apellido: 'offset',
  telefono: 'miscalculate political tenderly',
};

export const sampleWithFullData: IJefe = {
  id: 9701,
  nombre: 'tank',
  apellido: 'analyse taut key',
  telefono: 'continually inconsequential',
};

export const sampleWithNewData: NewJefe = {
  nombre: 'fleck for welcome',
  apellido: 'before huzzah circa',
  telefono: 'humidity electric who',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
