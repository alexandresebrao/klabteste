// modal-sales.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalSalesComponent } from './modal-sales.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

const route = [{component: ModalSalesComponent, path: ''}]

@NgModule({
  declarations: [ModalSalesComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(route), 
    HttpClientModule,
    FormsModule
  ],
})
export class ModalSalesModule { }
