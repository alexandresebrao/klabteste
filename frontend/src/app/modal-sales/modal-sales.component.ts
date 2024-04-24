import { HttpClient } from '@angular/common/http';
import { Component, Inject} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ProductsService } from '../products.service';
import { Sales } from '../sales';
import { of, Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  templateUrl: './modal-sales.component.html',
  styleUrl: './modal-sales.component.scss'
})
export class ModalSalesComponent {
  nome: string = '';
  quantidade: number = 0;
  produtoId: number = 0; 
  precoUnitario: number = 0;
  salesSubscription: Subscription;

  constructor(
    private http: HttpClient, 
    public dialogRef: MatDialogRef<ModalSalesComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private productService: ProductsService,
    private router: Router,
    private location: Location
  ) {
    this.produtoId = data.product.id;
    this.precoUnitario = data.product.preco;
  }

  cadastrarVenda() {
    const totalVenda = this.precoUnitario * this.quantidade;
    const venda = new Sales(this.nome, this.produtoId, this.quantidade, Number(totalVenda.toFixed(2)));
    
    this.productService.sales(venda).subscribe(resp => {
      console.log('resp', resp);
      this.closeModal();
    }, error => {
      console.error('Erro ao cadastrar venda:', error);
    });
  }
  
  closeModal() {
    
    this.nome = '';
    this.quantidade = 0;
    this.dialogRef.close();
  }
}
