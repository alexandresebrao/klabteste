import { Component } from '@angular/core';
import { ProductsService } from '../products.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './product-listing.component.html',
  styleUrl: './product-listing.component.scss'
})
export class ProductListingComponent  {
  products: any[] = [];
  constructor(private productService: ProductsService, private route: Router){}

  ngOnInit(): void {
    this.getProductsList();
  }

  private getProductsList() {
    this.productService.getProductsList().subscribe(resp => {
      this.products = resp;
    });
  }
}
