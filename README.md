# Pokemon
Pokedex, criação de uma aplicação rest de uma pokedex pessoal.


### Requisitos
Para compilar e rodar está aplicação você precisa:
* [Lombok](https://projectlombok.org/download)

* [MySQL](https://dev.mysql.com/downloads/installer/)

* [Java SE 14](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)

* [Maven](https://maven.apache.org/download.cgi)

### Clonar o projeto
Você pode clonar este repositório, pelo git usando:
```
https://github.com/Marcelocs19/Pokemon
```
### MySQL
```
1. create database pokemon;
```
```
2. create user 'user'@'%' identified by '123456';
```
```
3. grant all on pokemon.* to 'user'@'%';
```
```
4. use pokemon;
```

### Rodar a aplicação localmente
Existem varias jeitos para rodar uma aplicação Spring Boot no seu computador. Um jeito é executar a classe main RestauranteApplication pela sua IDE.

Ou você pode rodar a aplicação pela linha de comando usando:

```
mvn spring-boot:run
```

### Usuario Admin inseridos no banco
```
"Marcelo","marcelo@gmail.com", "123";

```

### Rotas
1. Método que cria um usuário:
Post - http://localhost:8080/usuarios
```
Postman
Adicionar no Body, um json com o nome, email, apelido e senha.
Exemplo:
http://localhost:8080/usuarios
Exemplo json:
{
    "nome": "Teste",
    "email":"teste@gmail.com",
    "apelido": "teste2",
    "senha": "1234"
}
```

2. Método para se logar na aplicação:
Post - http://localhost:8080/login
```
Postman
Adicionar no Body, um json com o apelido e senha.
Exemplo:
http://localhost:8080/login
Exemplo json:
{
    "apelido":"admin",
    "senha":"123"
}
```

3. Método para o usuário trocar de senha:
Post - http://localhost:8080/autorizacao/novaSenha
```
Postman
Adicionar no Body, um json com o apelido e senha.
Exemplo:
http://localhost:8080/login
Exemplo json:
{
    "email":"marcelo@gmail.com",
    "novaSenha":"teste"
}
```

4. Método para adicionar um pokemon a um usuário:
Post - http://localhost:8080/usuarios/pokemons/1

5. Método que lista os pokemons de um usuário:
Get - http://localhost:8080/usuarios/pokemons

6. Método que lista os usuários cadastrados:
Get - http://localhost:8080/usuarios

7. Método que atualiza o token de um usuário:
Post - http://localhost:8080/autorizacao/atualiza_token

8. Método que lista todos os Pokemons:
Get -  http://localhost:8080/pokemons

9. Método que busca um Pokemon por id:
Get - http://localhost:8080/pokemons/1

10. Método que busca um Pokemon por nome:
Get - http://localhost:8080/pokemons/nomes/Slowpoke

11. Método que busca os Pokemons por tipo:
Get - http://localhost:8080/pokemons/tipos?tipo1=Grama&tipo2=Venenoso

12. Método para inserir um novo Pokemon:
Post - http://localhost:8080/pokemons
```
Postman
Adicionar no Body, um json com o nome, tipo1, tipo2(não é obrigatório) e descricao.
Exemplo:
http://localhost:8080/pokemons
Exemplo json:
{
    "nome":"Pokemon Teste",
    "tipo1": "GRAMA",
    "tipo2": "VENENOSO",
    "descricao": "Pokemon de teste inserção"
}
```



## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
