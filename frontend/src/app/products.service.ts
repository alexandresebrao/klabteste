import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private baseURL = 'http://localhost:8080/produtos';

  constructor(private httpClient: HttpClient) { }

  getProductsList(): Observable<Object[]> {
    return this.httpClient.get<Object[]>(this.baseURL);
  }

  getProductDetails(productId: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${productId}`);
  }
}
