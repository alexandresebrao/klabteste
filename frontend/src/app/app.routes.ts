import { Routes } from '@angular/router';

export const routes: Routes = [
  {path: '', loadChildren: () => import("./inicio/inicio.module").then(m => m.InicioModule)},
  {path: 'produtos', loadChildren: () => import("./produtos/produtos.routes").then(m => m.PRODUTOS_ROUTES)},
  {path: 'vendas', loadComponent: () => import('./vendas/vendas-list/vendas-list.component').then(m => m.VendasListComponent)},
];
