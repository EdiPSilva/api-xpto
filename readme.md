# API XPTO

Acesse a [documenta��o](https://shorturl.at/kxWZ2)
___
Esta � uma API de teste que tem por objetivo fornecer informa��es sobre as cidades brasileireis utilizando do padr�o Rest.
Para poder realizar as **Requests** � necess�rio fazer uma autentica��o no formato **Basic Auth**.
Por tanto utilize as informa��es abaixo:

Campos   | Valores
--------| ------
Usu�rio | admin
Senha | admin

**Obs.:** Para facilitar o seu trabalho utilize o [Postman](https://www.getpostman.com/). Nele h� uma aba denominada de **Authorization**. E assim que acessa-la, no campo **type** opite por **Basic Auth**, preencha os dados de login na lateral direira e por fim pressione o bot�o **Preview Request**. Assim que feito tal procedimento a API estara autenticada.
___
## M�todos
##### POST | http://localhost:8080/city/upload 
-- Realizar upload de arquivo .csv para alimentar a base de dados.

##### GET | http://localhost:8080/city/capitals
-- Buscar as cidades capitais na base de dados.

##### GET | http://localhost:8080/city/max-min-cities-state
-- Buscar os estados com a maior e menor quantidade de cidades

##### GET | http://localhost:8080/city/quantity-cities-by-state
-- Buscar os estados com a contagem de cidades.

##### GET | http://localhost:8080/city/{ibge}
-- Buscar cidade por c�gido IBGE.

##### GET | http://localhost:8080/city/cities-by-state/{uf}
-- Buscar lista de cidades por UF.

##### GET | http://localhost:8080/city/total
-- Buscar o total de registros da dabase de dados.

##### DELETE | http://localhost:8080/city/{ibge}
-- Deleta uma cidade por c�gido IBGE.

##### POST | http://localhost:8080/city
-- Insere ou atualiza uma cidade.

##### GET | http://localhost:8080/city/quantity-column/{column}
-- Buscar a quantidade de registro a partir de uma coluna.

##### GET | http://localhost:8080/city/filter-column
-- Buscar objetos a partir de coluna (filtro).

##### GET | http://localhost:8080/city/distance
-- Buscar a maior dist�ncia entre as cidades em Km.