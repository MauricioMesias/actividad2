export interface IJefe {
  id: number;
  nombre?: string | null;
  apellido?: string | null;
  telefono?: string | null;
}

export type NewJefe = Omit<IJefe, 'id'> & { id: null };
