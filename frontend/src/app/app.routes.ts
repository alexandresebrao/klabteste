import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductListingModule } from './product-listing/product-listing.module';
import { ModalSalesModule } from './modal-sales/modal-sales.module';
import { UpdateProductsModule } from './update-products/update-products.module';

export const routes: Routes = [
  { path: '', redirectTo: 'listing-products', pathMatch: 'full' },
  {path: '', loadChildren: () => import("./inicio/inicio.module").then(m => m.InicioModule)},
  {path: 'teste', loadChildren: () => import("./produtos/produtos.module").then(m => m.ProdutosModule)},
  { path: 'listing-products', loadChildren: () => import("./product-listing/product-listing.module").then(m => m.ProductListingModule) },
  { path: 'product-details/:id', loadChildren: () => import("./product-details/product-details.module").then(m => m.ProductDetailsModule) }
];
@NgModule({
  imports: [RouterModule.forRoot(routes),
     ProductListingModule,
      ModalSalesModule,
      UpdateProductsModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }