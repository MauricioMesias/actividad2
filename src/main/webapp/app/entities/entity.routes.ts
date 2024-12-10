import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'actividad2App.adminAuthority.home.title' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'departamento',
    data: { pageTitle: 'actividad2App.departamento.home.title' },
    loadChildren: () => import('./departamento/departamento.routes'),
  },
  {
    path: 'empleado',
    data: { pageTitle: 'actividad2App.empleado.home.title' },
    loadChildren: () => import('./empleado/empleado.routes'),
  },
  {
    path: 'jefe',
    data: { pageTitle: 'actividad2App.jefe.home.title' },
    loadChildren: () => import('./jefe/jefe.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
