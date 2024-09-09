import {AfterViewInit, Component, inject, OnInit, ViewChild} from "@angular/core";
import { Produtos } from "../../core/type/type";
import { HttpClient } from "@angular/common/http";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { MatSort } from "@angular/material/sort";

@Component({
  templateUrl: "produtos.component.html",
  styleUrls: ['./produtos.component.scss']
})
export class ProdutosComponent implements OnInit {

  listaProdutos: MatTableDataSource<Produtos>
  displayedColumns: string[] = [
    'nomeProduto',
    'quantidadeTotal',
    'quantidadeDefeitos',
    'quantidadeDisponivelVenda',
    'preco'
  ]

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  httpClient = inject(HttpClient)

  ngOnInit(): void {
    this.httpClient.get<Produtos[]>('produtos').subscribe(
      data => {
        this.listaProdutos = new MatTableDataSource<Produtos>(data)
        this.listaProdutos.paginator = this.paginator
        this.listaProdutos.sort = this.sort
      }
    )
  }

}
