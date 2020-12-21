# UserAPI

[![Maintainability](https://api.codeclimate.com/v1/badges/d90e0f308dca4ed4cb90/maintainability)](https://codeclimate.com/github/douglaslbittencourt/user-api)


Esta é uma API de CRUD de usuário. 


# Como usar 

+ Api publica:

	- Abra https://douglas-userapi.herokuapp.com/swagger-ui.html para consultar os endpoints. 

+ Com Docker e docker-compose: 

```sh
$ git clone https://github.com/douglaslbittencourt/user-api.git
$ cd user-api 
$ docker-compose up 
```

    - Abra localhost:8080/swagger-ui.html para consultar os endpoints. 

+ Sem Docker: 

```sh
$ git clone https://github.com/douglaslbittencourt/user-api.git
```
    - Confira se tem acesso ao banco de dados postgres.
    - Abra o projeto e altere o arquivo application.properties apontando os dados do seu banco postgres
    - Build Spring
    - Abra localhost:8080/swagger-ui.html para consultar os endpoints.