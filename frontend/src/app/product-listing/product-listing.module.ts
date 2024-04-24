import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductListingComponent } from './product-listing.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatMenuModule } from '@angular/material/menu';
const route = [{component: ProductListingComponent, path: ''}]
@NgModule({
  declarations: [ProductListingComponent],
  imports: [CommonModule, RouterModule.forChild(route), HttpClientModule, FormsModule, MatMenuModule],
})
export class ProductListingModule {}
