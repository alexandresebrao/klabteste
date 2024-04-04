import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {Route, Router, RouterModule, Routes} from "@angular/router";
import {ProdutosComponent} from "./produtos.component";

const route = [{component: ProdutosComponent, path: ''}]
@NgModule({
  imports: [CommonModule, RouterModule.forChild(route)],
  declarations: [ProdutosComponent]
})
export class ProdutosModule {}
