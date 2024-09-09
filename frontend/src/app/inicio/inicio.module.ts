import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {InicioComponent} from "./inicio.component";
import {RouterModule} from "@angular/router";

const route = [{path: '', component: InicioComponent}]

@NgModule({
  imports: [CommonModule, RouterModule.forChild(route)],
  declarations: [InicioComponent]
})
export class InicioModule {}
