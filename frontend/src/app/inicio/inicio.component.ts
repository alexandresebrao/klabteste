import {Component, inject} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import { ProdutosService } from "../core/services/produtos/produtos.service";

@Component({
  templateUrl: "inicio.component.html"
})
export class InicioComponent {
  httpClient = inject(HttpClient)

  ngOnInit() {
    this.httpClient.get("produtos").subscribe((value) => console.log(value))
  }
}
