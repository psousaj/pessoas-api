# Pessoas-API

<h3 align="center">A simple API with SpringBoot to manage people and adresses</h3>

Esta é a coleção de requisições da Pessoas-API. Esta API foi desenvolvida para gerenciamento simples de pessoas e tem como objetivo estudo e aplicação de conhecimentos em java+springBoot. Todas as respostas são em json, e aqui mais a baixo tem alguns exemplos de requisição com corpo em json

Link para a documentação de requisições: <p align="center">
<a target="_blank" href="https://documenter.getpostman.com/view/24119108/2s935iskk9"/>Postman Documenter</a></p>

-----------------------------------------------------
### POST

## Cadastro multiplos enderecos
localhost:8080/pessoas/cadastro

Teste de cadastro de usuários com múltiplos endereços. Retorna status code: 201-CREATED e a pessoa cadastrada no corpo do response.

BODYraw

```json
{
   "nome":"José",
   "dataDeNascimento":"29/03/1999",
   "enderecos":[
        {
            "logradouro":"Rua Quadrado",
            "cep":"123456-89",
            "numero":"112",
            "cidade":"Juazeiro do Norte"
        },
        {
            "logradouro":"Rua Quadrado",
            "cep":"123456-89",
            "numero":"124",
            "cidade":"Juazeiro do Norte"
        }
   ]
}
```

-----------------------------------------------------
### POST

## Cadastro
localhost:8080/pessoas/cadastro

Teste simples de adiçao de uma pessoa com um único endereço no corpo da requisição.
BODYraw

```json
{
   "nome":"Testes da Cunha e Silva",
   "dataDeNascimento":"29/03/1997",
   "enderecos":[
        {
            "logradouro":"Rua Quadrado",
            "cep":"123456-89",
            "numero":"112",
            "cidade":"City of teste"
        }
   ]
}
```

-----------------------------------------------------
### GET

## Lista
localhost:8080/pessoas

Lista todos os registro de pessoas juntamente de todos os registros de cada endereço por pessoa.

-----------------------------------------------------
### GET

## Buscar
localhost:8080/pessoas/2

Busca especificamente por uma pessoa no banco de dados. É necessário saber o id da pessoa procurada, caso contrário retorna code:404-NOT FOUND.

-----------------------------------------------------
### PUT

## Alterar
localhost:8080/pessoas/atualizar/2

Altera os dados de um registro e o retorna no corpo do response.
BODYraw

```json
{
    "nome": "Testes da Silva",
    "dataDeNascimento": "29/06/1995",
    "enderecos": [
        {
            "logradouro": "Rua Bolota",
            "cep": "123456-78",
            "numero": 10,
            "cidade": "City of teste"
        }
    ]
}
```

-----------------------------------------------------
### POST

## Inserir Endereço
localhost:8080/pessoas/2/enderecos/adicionar

Insere endereços para a pessoa do id passado como parametro no URI. Novamente é necessáro saber o id do usuário a que deseja efetuar a adição de endereços, caso contrário recebe code:404.
BODYraw

```json
[
    {   
        "logradouro": "Rua Bolota",
        "cep": "63031-180",
        "numero": 964,
        "cidade": "Jua-City"
    }
]
```

-----------------------------------------------------
### GET

## Endereco Por pessoa
localhost:8080/pessoas/2/enderecos/listar

Lista todos os endereços da pessoa cujo id foi passado como parametro no URI.

-----------------------------------------------------
### DEL

## Delete
localhost:8080/pessoas/1

Deleta uma pessoa do banco de dados
BODYraw

```json
{
   "nome":"Teste",
   "dataDeNascimento":"12/02/1979",
   "endereco":{
      "logradouro":"Rua Teste",
      "cep":"123456-78",
      "numero":"1234",
      "cidade":"Las Vegas"
   }
}
```

-----------------------------------------------------
### PUT

## SetEndereco Principal
localhost:8080/pessoas/2/enderecos/favoritar?endereco=1

Determina qual será o endereço principal de uma pessoa por meio de dois parametros: pessoaId e enderecoId.

PARAMS endereco
BODYraw

```json
{
    "nome": "Testes da Silva",
    "dataDeNascimento": "29/06/1995",
    "enderecos": [
        {
            "logradouro": "Rua Bolota",
            "cep": "123456-78",
            "numero": 10,
            "cidade": "City of teste"
        }
    ]
}
```

<h3 align="left">Connect with me:</h3>
<p align="left">
<a href="https://twitter.com/psousaj" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/twitter.svg" alt="psousaj" height="30" width="40" /></a>
<a href="https://linkedin.com/in/psousaj" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="psousaj" height="30" width="40" /></a>
<a href="https://instagram.com/jp_filho_" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/instagram.svg" alt="jp_filho_" height="30" width="40" /></a>
</p>

<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a> <a href="https://www.linux.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/linux/linux-original.svg" alt="linux" width="40" height="40"/> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> </p>


