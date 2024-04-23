import { Component } from '@angular/core';
import { ProductsService } from '../products.service';
import { Router } from '@angular/router';

@Component({
  templateUrl: './product-listing.component.html',
  styleUrl: './product-listing.component.scss'
})
export class ProductListingComponent  {
  products: any[] = [];
  filteredProducts: any[] = [];
  minPrice: number;
  maxPrice: number;
  minQuantity: number;
  nameFilter: string = '';

  constructor(private productService: ProductsService, private route: Router){}

  ngOnInit(): void {
    this.getProductsList();
  }

  //Methods the product
  private getProductsList() {
    this.productService.getProductsList().subscribe(resp => {
      this.products = resp;
      this.applyFilters();
    });
  }

  //The apply filters in table
  private applyFilters() {
    this.filteredProducts = this.products.filter(product => {
      console.log('Filtros atuais:', this.minPrice, this.maxPrice, this.minQuantity, this.nameFilter);
      console.log('Produto:', product);
      const precoInRange = (!this.minPrice || product.preco >= this.minPrice) && (!this.maxPrice || product.preco <= this.maxPrice);
      const quantidadesInRange = (!this.minQuantity || product.quantidades >= this.minQuantity);
      const nomeMatch = (!this.nameFilter || product.nome.toLowerCase().includes(this.nameFilter.toLowerCase()));
      console.log('Preço no intervalo:', precoInRange);
      console.log('Quantidade no intervalo:', quantidadesInRange);
      console.log('Nome corresponde:', nomeMatch);
      return precoInRange && quantidadesInRange && nomeMatch;
    });
  }
  
  // Update filtered products upon detecting a change in the filters.
  updateFilters() {
    this.applyFilters();
  }

   // Navigate to the product details page
   viewDetails(productId: number) {
    // Assuming the route to the product details page is '/product-details/:id'
    this.route.navigate(['/product-details', productId]);
  }

}