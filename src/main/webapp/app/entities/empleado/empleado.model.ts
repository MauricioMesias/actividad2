import { IDepartamento } from 'app/entities/departamento/departamento.model';

export interface IEmpleado {
  id: number;
  nombre?: string | null;
  apellido?: string | null;
  telefono?: string | null;
  correo?: string | null;
  departamento?: Pick<IDepartamento, 'id'> | null;
}

export type NewEmpleado = Omit<IEmpleado, 'id'> & { id: null };
