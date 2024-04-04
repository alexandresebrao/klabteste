import {Component, inject} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Component({
  templateUrl: "inicio.component.html"
})
export class InicioComponent {
  httpClient = inject(HttpClient)
  ngOnInit() {
    this.httpClient.get("produtos").subscribe((value) => console.log(value))
  }
}
