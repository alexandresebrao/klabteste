import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListingModule } from './product-listing/product-listing.module';

export const routes: Routes = [
  { path: '', redirectTo: 'listing-products', pathMatch: 'full' },
  {path: '', loadChildren: () => import("./inicio/inicio.module").then(m => m.InicioModule)},
  {path: 'teste', loadChildren: () => import("./produtos/produtos.module").then(m => m.ProdutosModule)},
  { path: 'listing-products', loadChildren: () => import("./product-listing/product-listing.module").then(m => m.ProductListingModule) }
];
@NgModule({
  imports: [RouterModule.forRoot(routes), ProductListingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }