export class Sales {
    comprador: string = "";
    produtoId: number = 0;
    quantidades: number = 0;
    total_venda:  number = 0;

    constructor(comprador: string, produtoId: number, quantidades: number, total_venda: number) {
        this.comprador = comprador;
        this.produtoId = produtoId;
        this.quantidades = quantidades;
        this.total_venda = total_venda;
      }
}
