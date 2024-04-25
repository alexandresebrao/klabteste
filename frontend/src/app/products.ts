export class Products {    
    nome: string = "";
    quantidades: number = 0;
    defeitos: number = 0;
    preco:  number = 0;

    constructor(nome: string, quantidades: number, defeitos: number, preco: number) {
        this.nome = nome;
        this.quantidades = quantidades;
        this.defeitos = defeitos;
        this.preco = preco;
      }

}
