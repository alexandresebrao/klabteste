import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {ProdutosComponent} from "./produtos.component";
import { HttpserviceModule } from "../../modules/httpservice.module";

const route = [{component: ProdutosComponent, path: ''}]
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(route),
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    HttpserviceModule
  ],
  declarations: [ProdutosComponent]
})
export class ProdutosModule {}
