import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, shareReplay } from 'rxjs';
import { Produtos } from '../../type/type';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  private listaProdutos$?: Observable<Produtos[]>

  constructor(private httpClient: HttpClient) { }

  listar(): Observable<Produtos[]> {
    if(!this.listaProdutos$) {
      this.listaProdutos$ = this.requestProdutos().pipe(shareReplay(1))
    }

    return this.listaProdutos$
  }

  private requestProdutos(): Observable<Produtos[]> {
    return this.httpClient.get<Produtos[]>('produtos')
  }
}
