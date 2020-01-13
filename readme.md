# API XPTO

Acesse a [documentação](https://web.postman.co/collections/715148-c353fd36-e925-4cd1-be5c-4d675072de9b?version=latest&workspace=e27e219d-abce-4db3-8359-575d3c74f15c)
Os desafios de **SQL** e **PLSQL** estão dentro do arquivo .zip com o nome **desafios_plsql**.
___
Esta é uma API de teste que tem por objetivo fornecer informações sobre as cidades brasileireis utilizando do padrão Rest.
As tecnologias utilizadas foram o [Spring Boot](https://spring.io/) a ide (STS)[https://spring.io/tools] e o banco de dados **H2**.
Na pasta **teste_postman** há um arquivo .Json que dentém de um relatório de testes da execução da aplicação.
Para poder realizar as **Requests** é necessário fazer uma autenticação no formato **Basic Auth**.
E para acessar o banco de dados utilize a url **http://localhost:8080/h2-console**.
Assim que acessado será aberto um modal solicitando um usuário e senha.
O usuário solicitado no modal mencionado e para executar os requests são os contidos na tabela abaixo:

Campos   | Valores
--------| ------
Usuário | admin
Senha | admin

**Obs.:** Para facilitar o seu trabalho utilize o [Postman](https://www.getpostman.com/). Nele há uma aba denominada de **Authorization**. E assim que acessa-la, no campo **type** opite por **Basic Auth**, preencha os dados de login na lateral direita e por fim pressione o botão **Preview Request**. Assim que feito tal procedimento a API estara autenticada.
Quanto ao banco de dados **H2**, assim que inserido o usuário **admin** e autenticado basta clicar sobre **Connect**, dessa forma você poderá acessar o banco.
___
## Métodos
##### POST | http://localhost:8080/city/upload 
-- Realizar upload de arquivo .csv para alimentar a base de dados.

##### GET | http://localhost:8080/city/capitals
-- Buscar as cidades capitais na base de dados.

##### GET | http://localhost:8080/city/max-min-cities-state
-- Buscar os estados com a maior e menor quantidade de cidades

##### GET | http://localhost:8080/city/quantity-cities-by-state
-- Buscar os estados com a contagem de cidades.

##### GET | http://localhost:8080/city/{ibge}
-- Buscar cidade por cógido IBGE.

##### GET | http://localhost:8080/city/cities-by-state/{uf}
-- Buscar lista de cidades por UF.

##### GET | http://localhost:8080/city/total
-- Buscar o total de registros da dabase de dados.

##### DELETE | http://localhost:8080/city/{ibge}
-- Deleta uma cidade por cógido IBGE.

##### POST | http://localhost:8080/city
-- Insere ou atualiza uma cidade.

##### GET | http://localhost:8080/city/quantity-column/{column}
-- Buscar a quantidade de registro a partir de uma coluna.

##### GET | http://localhost:8080/city/filter-column
-- Buscar objetos a partir de coluna (filtro).

##### GET | http://localhost:8080/city/distance
-- Buscar a maior distância entre as cidades em Km.