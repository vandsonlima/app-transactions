# app-transaction

### Descrição
 app-transaction é responsável pelo gerenciamento de contas e transações, contendo os seguintes recursos:

 * Consulta de contas (Accounts/id)
 * Criação de contas (Accounts)
 * Execução de Transações (Transactions) de crédito e débito

### Tecnologias Utilizadas
* Java 14
* Spring Boot
* JUnit 5
* MockMvc
* H2 Database
* Maven
* Flyway Database Migrations  
* PostgreSQL

O projeto utiliza o banco H2 para os profiles dev e test, e para produção é utilizado  o banco relacional PostgreSQL.
O projeto está containerizado em docker e para sua execução será necessário:
- OpenJDK 14 (https://adoptopenjdk.net/)
- Docker (https://docs.docker.com/engine/install/)
- Docker Compose (https://docs.docker.com/compose/install/)

### Execução do projeto

#### Desenvolvimento
- Clonar ou baixar o [projeto](https://github.com/vandsonlima/app-transactions.git)
  e no diretório do projeto **executar o comando**
``` mvnw spring-boot:run -Dspring-boot.run.profiles=dev ```
 
#### Testes

* Clonar ou baixar o [projeto](https://github.com/vandsonlima/app-transactions.git) 
  e no diretório do projeto **executar o comando** ``` mvnw test ```
  
  
#### Produção
* Clonar ou baixar o [projeto](https://github.com/vandsonlima/app-transactions.git) 
  e no diretório do projeto **executar o comando** ``` docker-compose up --detach```
 
* O comando acima irá executar os containers de build e execução (multi-stage) e do banco de dados Postgresql. 

### Como acessar os recursos

* Acessar os endpoints a partir da url **http://localhost:8080**, e **executar os comandos abaixo via terminal**, ou 
importar o comando abaixo na ferramenta de testes de API de sua preferência (e.g. insomnia, postman)
  
- GET /v1/accounts/{account_id}
```  
  curl --request GET --url http://localhost:8080/v1/accounts/1 
```
- POST /v1/accounts/
```
  curl --request POST \
  --url http://localhost:8080/v1/accounts \
  --header 'Content-Type: application/json' \
  --data {
	"document_number":"12934567812"}  
  ```

- POST /v1/transactions/
```
curl --request POST \
  --url http://localhost:8080/v1/transactions \
  --header 'Content-Type: application/json' \
  --data '{
	"account_id": 1,
	"operation_type_id": 1,
	"amount": 100
}'
```
