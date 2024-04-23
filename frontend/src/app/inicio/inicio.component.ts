import {Component, inject} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { ProductsService } from "../products.service";
import { Router } from "@angular/router";

@Component({
  templateUrl: "inicio.component.html"
})
export class InicioComponent {
  httpClient = inject(HttpClient)
  products: any[] = [];
  constructor(private productService: ProductsService, private route: Router){}

  ngOnInit() {
    // this.httpClient.get("produtos").subscribe((value) =>{ console.log(value)})
    this.getProductsList();
  }

  private getProductsList(){
    this.productService.getProductsList().subscribe(resp => {
      this.products = resp;
    })
  }
}
