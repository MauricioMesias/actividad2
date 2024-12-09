import { IUser } from './user.model';

export const sampleWithRequiredData: IUser = {
  id: 17250,
  login: 'fA5',
};

export const sampleWithPartialData: IUser = {
  id: 10207,
  login: 'tUa@mz\\ksI\\F2',
};

export const sampleWithFullData: IUser = {
  id: 29562,
  login: 'F1J',
};
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
