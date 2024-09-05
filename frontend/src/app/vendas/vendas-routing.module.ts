import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VendasListComponent } from './vendas-list/vendas-list.component';
import { VendasComponent } from './vendas.component';

const routes: Routes = [
  {
    path: '',
    component: VendasComponent,
    children: [
      { path: '', component: VendasListComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class VendasRoutingModule {}
