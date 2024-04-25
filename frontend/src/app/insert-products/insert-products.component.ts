import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ModalSalesComponent } from '../modal-sales/modal-sales.component';
import { ProductsService } from '../products.service';
import { Products } from '../products';

@Component({
  templateUrl: './insert-products.component.html',
  styleUrl: './insert-products.component.scss'
})
export class InsertProductsComponent {
  nome: string;
  defeitos: number;
  quantidade: number;
  preco: any;
  constructor(
    private productService: ProductsService,
     private router: Router
  ) {}

  createdProduct() {
    const product = new Products(this.nome, this.quantidade,this.defeitos , Number(this.preco.toFixed(2)));
    this.productService.insertProducts(product).subscribe(resp => {
      this.router.navigate(['/listing-products']);
    }, error => {
      console.error('Erro ao criar produto:', error);
    });
  }
}
