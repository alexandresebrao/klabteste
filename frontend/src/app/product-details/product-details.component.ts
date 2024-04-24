// product-details.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from '../products.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalSalesComponent } from '../modal-sales/modal-sales.component';

@Component({
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.scss'
})
export class ProductDetailsComponent implements OnInit {
  productId: number;
  product: any;

  constructor(private route: ActivatedRoute, private productService: ProductsService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.getProductDetails();
  }

  getProductDetails() {
    this.productService.getProductDetails(this.productId).subscribe(data => {
      this.product = data;
    });
  }

  openModal() {
    const dialogRef = this.dialog.open(ModalSalesComponent, {
      width: '50%',
      height: '300px',
      data: { product: this.product }
    });
    dialogRef.afterClosed().subscribe(result => {
      // Recarrega a página após fechar o modal
      window.location.reload();
    });
  }

}
