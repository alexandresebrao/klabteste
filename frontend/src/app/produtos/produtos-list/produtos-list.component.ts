import { Component } from '@angular/core';
import { TabelaComponentComponent } from '../../shared/tabela-component/tabela-component.component';

@Component({
  selector: 'app-produtos-list',
  standalone: true,
  imports: [TabelaComponentComponent],
  templateUrl: './produtos-list.component.html',
  styleUrls: ['./produtos-list.component.scss'],
})
export class ProdutosListComponent {
  columns = ['id', 'name', 'price'];

  data = [
    { id: 1, name: 'Product 1', price: 100 },
    { id: 2, name: 'Product 2', price: 200 },
    { id: 3, name: 'Product 3', price: 300 }
  ];
}
