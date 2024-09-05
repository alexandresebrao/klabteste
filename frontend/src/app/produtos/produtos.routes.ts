import { Routes } from '@angular/router';
import { ProdutosComponent } from './produtos.component';

export const PRODUTOS_ROUTES: Routes = [
  {
    path: '',
    component: ProdutosComponent,
    children: [
      { path: '', loadComponent: () => import('./produtos-list/produtos-list.component').then(c => c.ProdutosListComponent) },
      { path: ':id', loadComponent: () => import('./produtos-detalhes/produtos-detalhes.component').then(c => c.ProdutosDetalhesComponent) },
      { path: 'edit/:id', loadComponent: () => import('./produtos-edit/produtos-edit.component').then(c => c.ProdutosEditComponent) }
    ]
  }
]
