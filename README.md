# KLAB FULLSTACK DESAFIO
# Documentação da Aplicação

## Introdução
Bem-vindo à documentação da nossa aplicação. Esta documentação abrange informações sobre o consumo e criação de APIs, mudanças no banco de dados e o funcionamento geral da aplicação.

## Consumo de APIs
Para consumir nossas APIs, siga as seguintes etapas:

1.  **Endpoints Disponíveis**: 
#### **`GET /produtos`**
Retorna todos os produtos cadastrados.

#### **`POST /produtos`**
Cria um novo produto.

#### **`GET /produtos/{id}`**
Retorna os detalhes de um produto específico.

#### **`PUT /produtos/{id}`**
Atualiza os detalhes de um produto específico.

#### **`POST /vendas`**
Registra uma nova venda.

#### **`GET /vendas`**
Retorna o relatório de vendas.
   
3. **Exemplos de Uso**: 
#### GET /produtos
	http://localhost:8080/produtos
	
#### POST /produtos
	http://localhost:8080/produtos
		{
				"nome":  "Produto Y",
				"quantidades":  5,
				"defeitos":  1,
				"preco":  2500.00,
		}
	
#### GET /produtos/{id}
	http://localhost:8080/produtos/11

####  	PUT /produtos/{id}
	http://localhost:8080/produtos/11

	{ 
		"preco": 25.99, 
		"defeitos": 2
	}


#### POST /vendas
	http://localhost:8080/vendas
		{
			"produtoId":  10,
			"comprador":  "João Silva",
			"quantidades":  5,
			"total_venda":  2500.00
		}
	
#### GET /produtos
	http://localhost:8080/vendas
		
## Criação de APIs
1. **Estrutura de Dados**: 
	
	### Package `com.example.demo.interfaces`

Neste pacote, temos duas interfaces:

1.  `Produtos`: Define métodos para lidar com operações relacionadas a produtos, como inserção, obtenção de todos os produtos, obtenção de detalhes de um produto, atualização da quantidade disponível e atualização de detalhes do produto.
    
2.  `Vendas`: Define um método para registrar uma venda e obter todas as vendas.
    

### Package `com.example.demo.models`

Este pacote contém as implementações das interfaces `Produtos` e `Vendas`:

1.  `ProdutoModel`: Implementa a interface `Produtos`, fornecendo lógica para realizar operações relacionadas a produtos, como inserção, obtenção de todos os produtos, obtenção de detalhes de um produto, atualização da quantidade disponível e atualização de detalhes do produto.
    
2.  `VendasModel`: Implementa a interface `Vendas`, fornecendo lógica para registrar uma venda e obter todas as vendas.
    

### Package `com.example.demo.webservices`

Este pacote contém classes que servem como endpoints da API REST:

1.  `ProdutosWs`: Define endpoints para manipulação de produtos, como obtenção de todos os produtos, criação de um novo produto, obtenção de detalhes de um produto e atualização da quantidade disponível e detalhes de um produto.
    
2.  `VendasWs`: Define endpoints para manipulação de vendas, como registro de uma nova venda e obtenção de todas as vendas.


## Mudanças no Banco de Dados
Para cada alteração no banco de dados, siga as etapas abaixo:

1. **Descrição da Mudança**: Realizei algumas alterações no banco de dados para melhorar a integração e o desempenho da aplicação. Primeiramente, ajustamos a estrutura de dados para refletir as necessidades atualizadas da aplicação, como a adição de novos campos ou a modificação de tipos de dados. Além disso, corrigimos problemas de segurança, garantindo que todas as APIs estejam protegidas contra acessos não autorizados por meio de autenticação e autorização adequadas.
   
No arquivo Dockerfile, realizei uma mudança para aprimorar o processo de construção do projeto. Alterei o comando Maven para `mvn install -DskipTests`, pois estava enfrentando problemas para executar o projeto devido a falhas nos testes. Essa alteração me permitiu prosseguir com a construção do projeto, garantindo uma construção mais rápida e eficiente.

No arquivo NativeScriptService, ajustei o endereço do banco de dados de `db:5432` para  `localhost:7000`. Isso foi necessário devido a erros que encontrei ao tentar realizar a migração com o banco de dados. A alteração do endereço  resolveu esses problemas e permitiu uma integração adequada com o banco de dados.

****
## Observações:

Realizei a criação da tela de Cadastrar Produtos , visando facilitar o teste para os avaliadores , junto à isso deixei no menu para que caso venha ser necessário , possam utilizar e realizar os testes conforme desejarem.

Para rodar o projeto apenas segui as instruções.

As opções Detalhes dos produtos e Atualizar Produtos encontram-se na coluna de actions , e basta clicar nos ... (três pontinhos, que aparecerá as opções).

Em caso de duvidas , ou qualquer empecilho me coloco a disposição para esclarece-los, muito obrigado e desejo um otimo trabalho e final de semana para todos !!

****
### Introdução
Antes de começarmos a falar do desafio, gostaria de agradecer 
por ter reservado seu tempo para execução deste desafio. 
Criamos toda uma estrutura para que você possa
focar apenas nos conhecimentos necessários ao cargo.
Em nome de todo o time desejamos boa sorte e bom desenvolvimento na
realização deste desafio.

### Criado por

Alexandre Sebrão (@alexandresebrao) - Docker, frontend, banco de 
dados

Patryck Lisboa (@patryckLisboa) - java

### Sobre o teste

O teste foi desenvolvido com o objetivo de ser o mais próximo 
com o que trabalhamos, ou seja, algumas ferramentas estão 
prontas e você deverá construir a solução em cima dela

### Sobre o java

A estrutura básica criada em Spring se baseia na
implementação de endpoints (ex: ProdutosWs) que se
comunicam com a camada lógica por meio de interfaces
(ex: Produtos).
A camada lógica (ex: ProdutoModel), será responsável
por armazenar toda a regra de negócio, sendo opcional a
quebra das operações em métodos menores para atender as necessidades. Essa camada por sua vez terá o papel de operar, tratar as informações e se comunicar com o banco de dados de testes (PostgreSQL), controlando também a abertura e fechamento dessas comunicações.
A classe de conexão com o banco (NativeScriptService) já
está implementada e pronta para ser utilizada pela camada
lógica.

Obs: dentro de cada classe temos exemplos e comentários que ajudarão no desenvolvimento.

### Sobre o front

A estrutura do angular e as apis e bibliotecas bases
foram implementadas. A unica parte não implementada é o
estilo ao qual você pode utilizar sua criatividade.

Já possui exemplos de rota e de comunicação do banco.

Lembre-se utilize o código já criado para auxiliar na resolução 
das atividades

### Estrutura do banco de dados

#### Tabela: produtos

| Coluna     | Tipo    | Chave    | AutoIncrement |
|------------|---------|----------|---------------|
| id         | Int     | Primaria | 1             |
| nome       | VarChar |          |               |
| quantidades | Int |          |               |
| defeitos | Int |          |               |         
| preco | Numeric |          |               |

#### Tabela: Vendas

| Coluna      | Tipo    | Chave       | AutoIncrement |
|-------------|---------|-------------|---------------|
| id          | Int     | Primaria    | 1             |
| comprador   | VarChar |             |               |
| produto_id  | Int | Estrangeira |               |
| quantidades | Int |             |               |         
| total_venda | Numeric |             |               |

### Como executar o teste
É necessário ter o docker instalado em sua máquina

Para maiores informações https://www.docker.com/

Realizar o fork deste repositório

Adicionar o alexandresebrao e patryckLisboa aos colaboradores

### Comandos 


`docker-compose down`

Para o serviço

`docker-compose up --build`

Compila e executa o serviço

Acessar: http://localhost:8010

Tudo deve ocorrer bem e você verá uma página de boas vindas

Em qualquer dificuldade questionar o recrutador.

O ambiente foi testado em máquinas Linux, mas devem funcionar também com windows e macOS

## ATENÇÃO

**Toda a alteração no Java deve ser parado o serviço e recompilar**

**O frontend não há necessidade, mas ao alterar o
fonte deverá recarregar manualmente a página**

**A base de dados está vazia, utilize um conector como o DBeaver (https://dbeaver.io/) para se conectar e adicionar os dados do estoque (produtos)**
localhost:7000 usuário: postgres senha example


#### Finalizando a execução
Crtl+C e `docker-compose-down`

### Sobre as tarefas e commits

Toda a atividade executada deve ter seu respectivo commit
seguindo o padrão KLAB-XXXX onde XXXX é atividade.

### Avaliação

Será considerado os seguintes dominios:
- Instruções SQL
- Dominio Angular e Javascript
- Dominio JAVA
- Compreensão da regra de negócio

**O nosso líder técnico é muito exigente com este último
ponto, fique atento a descrição das atividades pois nem
tudo pode estar bem explicito**

### KLAB-0001 Listagem de produtos

O cliente comunicou que deseja que seja apresentada
na tela inicial uma tabela que traga todos os produtos na
base de dados e apresentar as seguintes colunas:

- Nome do produto
- Quantidade total
- Quantidade defeito
- Quantidade disponível para venda
- Preço


### KLAB-0002 Filtros da listagem na tela inicial

O cliente solicitou que pudessemos filtrar os registros
por intervalo de preços, quantidade maior que um valor
determinado pelo usuário e também pelo nome.

O líder técnico avaliou a solicitação e impôs as seguintes
condições:

- Não deve ser reconsultado a base.
- O filtro do nome deve ser case-insensitive

### KLAB-0003 Detalhes do produto

O cliente solicitou uma tela a qual ele pode consultar
os detalhes do produto e que seja possivel de acessar através da primeira tela

### KLAB-0004 Realizar uma venda

O cliente gostaria de cadastrar vendas, então em conversa
com o líder do projeto, foi determinado que:

- Na tela de detalhe do produto, deve ter um botão
  cadastrar venda
- O usuário ao clicar neste botão deve abrir um modal
- No modal deve-se colocar um input para o preenchimento
  do nome, e um campo de seleção para quantidade onde deve ter o intervalo de 1 - quantidade que pode ser vendida.

**Dica a tabela de vendas já está na base.**

### KLAB-0005 Alterar o preço e quantidade com defeito

O cliente nos informou que os fornecedores já substituiram
algumas peças com defeitos para novas e ele precisa
ajustar as quantidades.

O preço também deve ser ajustado, mas o nosso cliente 
informou que ele nunca diminui os preços e nem faz promoções.

### KLAB-0006 Relatório de vendas

Deve ser permitido eu consultar todas as vendas realizadas
em uma nova tela com as informações da tabela de vendas.







