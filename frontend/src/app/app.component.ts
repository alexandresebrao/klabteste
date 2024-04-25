import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { HttpserviceModule } from "./modules/httpservice.module";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpserviceModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';

  constructor(private router: Router) { }

  navigateToCreatedProduct(): void {
    this.router.navigate(['/insert-products']);
  }

  navigateToListingProducts(): void {
    this.router.navigate(['/listing-products']);
  }

  navigateToSalesReport(): void {
    this.router.navigate(['/sales-report']);
  }

}
