import { NgModule } from "@angular/core";
import { HttpserviceModule } from "../modules/httpservice.module";
import { CommonModule } from "@angular/common";
import { ProdutosService } from "./services/produtos/produtos.service";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    HttpserviceModule
  ],
  declarations: [],
  providers: [ProdutosService]
})
export class CoreModule {}
