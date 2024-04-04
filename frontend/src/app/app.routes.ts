import { Routes } from '@angular/router';

export const routes: Routes = [
  {path: '', loadChildren: () => import("./inicio/inicio.module").then(m => m.InicioModule)},
  {path: 'teste', loadChildren: () => import("./produtos/produtos.module").then(m => m.ProdutosModule)}
];
