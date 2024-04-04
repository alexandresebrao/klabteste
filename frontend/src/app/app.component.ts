import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HttpserviceModule} from "./modules/httpservice.module";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpserviceModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
}
