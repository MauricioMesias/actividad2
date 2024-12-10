import { IJefe } from 'app/entities/jefe/jefe.model';

export interface IDepartamento {
  id: number;
  nombre?: string | null;
  ubicacion?: string | null;
  monto?: number | null;
  jefe?: Pick<IJefe, 'id'> | null;
}

export type NewDepartamento = Omit<IDepartamento, 'id'> & { id: null };
