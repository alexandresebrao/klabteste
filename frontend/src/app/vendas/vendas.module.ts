import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import { VendasListComponent } from './vendas-list/vendas-list.component';
import { VendasRoutingModule } from './vendas-routing.module';
import { VendasComponent } from "./vendas.component";

// const route = [{component: VendasComponent, path: ''}]
@NgModule({
  declarations: [ VendasComponent],
  imports: [CommonModule, VendasRoutingModule  ],
})
export class VendasModule {}
