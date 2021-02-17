<div align="center">

  
  <h2> Entrega em grupo - Projeto Java</h2>
</div>

<div align="center">
  <h3>Projeto Java</h3>
  <a href="#-Introduction">Introdução</a> |
  <a href="#Tecnologias">Tecnologias</a> |
   <a href="#-Uml-used">UML</a> |
  <a href="#-Routes-used">Rotas</a> |
  <a href="#-Services-used">Serviços</a> |
  <a href="#-Routes-used">Controllers</a> |
    <a href="#-Dependecias-used">Dependências</a> |
</div>

---

# 👨🏻‍💻 Introdução

## Objetivo
Disponibilizar as funcionalidades de cadastro de usuários, cadastro de contas, cadastro de planos de contas, lançamentos de crédito, débito e transferências, extratos. 

Todos os dados devem ser armazenados em um banco de dados utilizando Spring Data JPA.



## Esse repositório possui

Aplicação Banco Carcará:

- Api 


## Projeto

Esse projeto foi realizado no treinamento da [Gama Academy](https://www.gama.academy/) coordenado pelo professor Gleyson Sampaio

# Tecnologias utilizadas

- Java
- Spring Data JPA

# Dependências
- spring-boot-starter-web
- spring-boot-starter-security
- javax.persistence-api
- spring-boot-starter-data-jpa
- com.h2database
- mysql-connector-java
- io.jsonwebtoken
- springfox-swagger2
- springfox-swagger-ui

# UML
<div align="center">
  <img  src="https://i.imgur.com/oYjRrHV.png"/>
  

</div>

# Documentação Swagger
[Clique aqui para acessar online](https://bancocarcara-api.herokuapp.com/swagger-ui.html)


# Rotas

## Auth

POST: /auth/refresh_token

##  Dashboard

GET /dashboard

## Lançamento

POST /lancamentos

## Plano-conta

GET /planoconta
<br>
POST /planoconta
<br>
PUT /planoconta/{id}
<br>
GET/planoconta/param

## Usuário

POST /user
<br>
GET /user/{id}
<br>
POST /user/login

# Models

- [Conta.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/model/Conta.java)
- [Lancamento.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/model/Lancamento.java)
- [PlanoConta.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/model/PlanoConta.java)
- [Usuario.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/model/Usuario.java)

- [ContaService.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/service/ContaService.java)


# Services

- [ContaService.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/service/ContaService.java)
- [DashboardService.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/service/DashboardService.java)
- [LancamentoService.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/service/LancamentoService.java)
- [UserDetailsServiceImpl.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/service/UserDetailsServiceImpl.java)
- [PlanoContaService.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/service/PlanoContaService.java)
- [UsuarioService.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/service/UsuarioService.java)

# Controllers

- [DashboardController.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/controller/DashboardController.java)
- [LancamentoController.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/controller/LancamentoController.java)
- [UsuarioController.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/controller/UsuarioController.java)
- [AuthResource.java.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/controller/AuthResource.java)
- [PlanoContaController.java](https://github.com/GabrielBotelhoGit/GrupoCarcara-Api/blob/main/src/main/java/academy/gama/desafio/controller/PlanoContaController.java)



# 💻 Made by
- Wesley Vicente `(Development)`  [LinkedIn](https://www.linkedin.com/in/wesleyvicen/)
- Renan Alencar `(Development)`  [LinkedIn](https://www.linkedin.com/in/renancostaalencar/)
- Gabriel Botelho `(Development)`  [LinkedIn](https://www.linkedin.com/in/gabriel-carreiras-botelho-867158151/)
- Guilherme Cruz `(Development)`  [LinkedIn](https://www.linkedin.com/in/guilherme-p-cruz/)
- Bárbara Rodrigues `(Development)`  [LinkedIn](https://www.linkedin.com/in/b%C3%A1rbara-rodrigues-49924697/)
- Lucas Reis Caputo `(Development)`  [LinkedIn](https://www.linkedin.com/in/lucascaputo/)
