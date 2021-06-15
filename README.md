# app-transaction

### Descrição
 app-transaction é responsável pelo gerenciamento de contas e transações, contendo os seguintes recursos:

 * Criação de conta (Accounts)
 * Execução de Transações (Transactions) de crédito e débito

### Tecnologias Utilizadas
* Java 14
* Spring Boot
* JUnit 5
* H2 Database
* Maven
* Flyway Database Migrations  
* PostgreSQL

O projeto utiliza o banco H2 para os profiles dev e test, e para produção é utilizado o banco relacional PostgreSQL.
O projeto está containerizado em docker e para sua execução será necessário:
- OpenJDK 14 (https://adoptopenjdk.net/)
- Docker (https://docs.docker.com/engine/install/)
- Docker Compose (https://docs.docker.com/compose/install/)

### Execução do projeto

#### Desenvolvimento
- Clonar ou baixar o [projeto](https://github.com/vandsonlima/app-transactions.git) 
  e executar no diretório o comando
``` mvnw spring-boot:run -Dspring-boot.run.profiles=dev ```
 
#### Testes

* Clonar ou baixar o [projeto](https://github.com/vandsonlima/app-transactions.git) 
  e executar no diretório o comando ``` mvnw test ```

#### Produção
* Clonar ou baixar o [projeto](https://github.com/vandsonlima/app-transactions.git) 
  e executar no diretório o comando ``` docker-compose up --detach```

O comando acima irá executar os containers da aplicação e do banco de dados Postgresql seguindo os parametros passados no docker-compose.yaml

### Como acessar os recursos

* Acessar os endpoints a partir da url http://localhost:8080, utilizando o terminal através dos comandos abaixo, ou 
importando o comando abaixo na ferramenta de testes de API de sua preferência (e.g. insomnia, postman)
  
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
