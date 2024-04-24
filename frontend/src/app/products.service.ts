import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Sales } from "./sales";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private baseURL = 'http://localhost:8080/produtos';
  private baseURLVendas = 'http://localhost:8080/vendas';

  constructor(private httpClient: HttpClient) { }

  getProductsList(): Observable<Object[]> {
    return this.httpClient.get<Object[]>(this.baseURL);
  }

  getProductDetails(productId: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/${productId}`);
  }

  sales(sales: Sales): Observable<object> {
    return this.httpClient.post(`${this.baseURLVendas}`, sales);
  }

  // Nova função para atualizar a quantidade disponível para venda
  updateAvailableQuantity(productId: number, quantidadeVendida: number): Observable<object> {
    const body = {
      produtoId: productId,
      quantidades: quantidadeVendida
    };
    return this.httpClient.put(`${this.baseURL}/${productId}/quantidade`, body);
  }
}
