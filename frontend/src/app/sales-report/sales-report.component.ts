import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../products.service';

@Component({
  templateUrl: './sales-report.component.html',
  styleUrl: './sales-report.component.scss'
})
export class SalesReportComponent implements OnInit {
  sales: any[];
  showSkeleton: boolean = false;

  constructor(private productService: ProductsService,) { }

  ngOnInit(): void {
    this.getSalesReport();
  }

  getSalesReport(): void {
    this.productService.getSalesReport().subscribe(sales => {
      this.sales = sales;
      this.showSkeleton = true;
    });
  }

  getTotalSales(): number {
    return this.sales?.reduce((total, sale) => total + sale?.total_venda, 0);
  }

  getTotalQuantity(): number {
    return this.sales?.reduce((total, sale) => total + sale?.quantidades, 0);
  }

}
