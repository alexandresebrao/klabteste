import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
import { RouterModule } from '@angular/router';
import { InsertProductsComponent } from './insert-products.component';
import { MatDialogModule } from '@angular/material/dialog';

const route = [{ component: InsertProductsComponent, path: '' }]

@NgModule({
  declarations: [InsertProductsComponent],
  imports: [CommonModule, 
    RouterModule.forChild(route),
     MatDialogModule, HttpClientModule, FormsModule, MatMenuModule]
})
export class InsertProductsModule { }
