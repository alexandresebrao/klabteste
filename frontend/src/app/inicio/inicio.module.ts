import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {InicioComponent} from "./inicio.component";
import {RouterModule} from "@angular/router";
import {HttpserviceModule} from "../modules/httpservice.module";

const route = [{path: '', component: InicioComponent}]

@NgModule({
  imports: [CommonModule, RouterModule.forChild(route), HttpserviceModule],
  declarations: [InicioComponent]
})
export class InicioModule {}
