import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { RouterModule } from '@angular/router';
import { UpdateProductsComponent } from './update-products.component';

const route = [{component: UpdateProductsComponent, path: ''}]

@NgModule({
  declarations: [UpdateProductsComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(route), 
    HttpClientModule, 
    FormsModule,
    MatCardModule,
    MatDialogModule
  ]
})
export class UpdateProductsModule { }
