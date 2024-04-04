# KLAB FULLSTACK DESAFIO

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







