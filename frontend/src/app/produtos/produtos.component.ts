import {Component} from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { HttpserviceModule } from "../modules/httpservice.module";

@Component({
  templateUrl: "produtos.component.html",
  standalone: true,
  imports: [RouterOutlet]
})
export class ProdutosComponent {}
