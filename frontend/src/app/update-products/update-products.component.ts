import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from '../products.service';

@Component({
  templateUrl: './update-products.component.html',
  styleUrls: ['./update-products.component.scss']
})
export class UpdateProductsComponent implements OnInit {
  productId: number;
  productName: String;
  newPrice: number;
  newDefects: number;
  existingPrice: number; // Adicione esta propriedade para armazenar o valor existente
  errorMessage: string;

  constructor(
    private route: ActivatedRoute, 
    private productService: ProductsService, 
    public dialogRef: MatDialogRef<UpdateProductsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {
    console.log('DATA', this.data);
    this.productId = this.data.product.id;
    this.existingPrice = this.data.product.preco;
    this.productName = this.data.product.nome;
  }

  updateProduct(): void {
    // Verifique se o novo preço é menor do que o existente
    if (this.newPrice < this.existingPrice) {
      this.errorMessage = 'O novo preço não pode ser menor do que o preço existente.';
      return; // Não envie a solicitação de atualização se houver um erro
    }

    const details = {
      preco: this.newPrice,
      defeitos: this.newDefects
    };
    
    this.productService.updateProductDetails(this.productId, details).subscribe(
      response => {
        console.log('Detalhes do produto atualizados com sucesso:', response);
        this.closeModal();
      },
      error => {
        console.error('Erro ao atualizar os detalhes do produto:', error);
      }
    );
  }

  closeModal(): void {
    this.newPrice = null;
    this.newDefects = null;
    this.errorMessage = null; // Limpe a mensagem de erro ao fechar o modal
    this.dialogRef.close();
  }
}
