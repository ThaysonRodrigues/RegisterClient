<p align="center">
  <img src="https://rocketseat-cdn.s3-sa-east-1.amazonaws.com/theme-docs.svg" alt="A illustration of file that is the @rocketseat/gatsby-theme-docs logo" width="100">
</p>

<h2 align="center">
  RegisterClient API
</h2>

<p align="center">
  API rest developed in java for customer registration, a project used to solve a challenge.
</p>

#### Our API is available at the following URL:
```
API: https://register-client-challenge.herokuapp.com/api/clientes
Documentation: https://register-client-challenge.herokuapp.com/swagger-ui.html
```

## ⚡️ Project Details
*This project has the following characteristics:*
* Project created with Spring Boot and Java 8
* Project created with Spring Boot and Java 8
* Heroku Cloud implementation
* Documentation of endpoints with Swagger
* Implementation of the database in Docker

## 🚀 How to run the application
The first step to run the application is to make a copy of the project, to do this run the following command in Git Bash:
```
git clone https://github.com/ThaysonRodrigues/RegisterClient.git
```

Make sure you have Maven installed and added to your operating system's PATH, as well as Docker. To perform the next step enter the main project folder and run the following docker command, it will create an image of the database.

```
docker-compose up
```

The third step is to compile the project and start it.

```
mvn spring-boot:run
Access endpoints via url: http://localhost:8080
```

It is also possible to compile the project to run in a production environment, to do this run the following command at the root of the project.

```
mvn clean install
```
## :star: Testing the API

### Register
To register a new customer just access localhost:8080/api/clientes, passing the basic information, name, cpf and address in the payload. Below is an example of payload.
```
Method: POST
localhost:8080/api/clientes

{
	"nome": "João da Silva",
	"cpf": "36725262074",
	"endereco": "Rua sem nome"
}
```

### Query
To consult a customer, simply access x with the get method, and a customer can be consulted individually by identifying it in the URL.

```
Method: GET
localhost:8080/api/clientes/1

{
  "data": [
    {
      "id": 2,
      "nome": "Maria",
      "cpf": "72261349009",
      "endereco": "Rua Sem Nome"
    }
  ],
  "errors": []
}

Method: GET
localhost:8080/api/clientes

{
  "data": [
    {
      "id": 2,
      "nome": "Maria",
      "cpf": "72261349009",
      "endereco": "Rua São Paulo"
    },
    {
	    "nome": "João da Silva",
	    "cpf": "36725262074",
	    "endereco": "Rua sem nome"
    }
  ],
  "errors": []
}

```

### Update
To update the customer, simply send the new information along with the identifier.

```
{
	"id": 2,
	"nome": "Regina Ferreira",
	"cpf": "11667454650",
	"endereco": "Otávio da Silva Frade"
}
```

### Delete
To delete a customer's registration simply access the delete method sent the identifier.
```
Method: DELETE
localhost:8080/api/clientes
```

## :ledger: Documentation
Use the Swagger interface to access the endpoint documentation, it is available at the URL.
```
http://localhost:8080/swagger-ui.html
```
