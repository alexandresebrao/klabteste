import {AfterViewInit, Component, inject, OnInit, ViewChild} from "@angular/core";
import { Produtos } from "../../core/type/type";
import { HttpClient } from "@angular/common/http";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { MatSort } from "@angular/material/sort";
import { ProdutosService } from "../../core/services/produtos/produtos.service";

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

  constructor(private produtosService: ProdutosService) { }

  ngOnInit(): void {
    this.produtosService.listar().subscribe(
      data => {
        this.listaProdutos = new MatTableDataSource<Produtos>(data)
        this.listaProdutos.paginator = this.paginator
        this.listaProdutos.sort = this.sort
      }
    )
  }

}
