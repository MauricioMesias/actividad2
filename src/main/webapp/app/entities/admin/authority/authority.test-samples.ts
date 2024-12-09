import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '1d0f055c-fe22-49d8-8b79-e347d96305bc',
};

export const sampleWithPartialData: IAuthority = {
  name: '3e665143-88d3-4a3e-b747-8afb35b1ebea',
};

export const sampleWithFullData: IAuthority = {
  name: '5c075641-e359-4056-9a3a-99d419aaba92',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
