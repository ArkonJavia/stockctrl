# stockctrl
SIBS API
Branch Principal = Master.

Instalar o postgres + command line tools;
configurar a pasta bin no PATH das variaveis de ambiente;
(coonfirmar que tem um postgres rodando compmgmt.msc)

psql -U postgres;
senha
CREATE DATABASE test;
CREATE USER admin WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES ON DATABASE test TO admin;
ALTER USER admin WITH SUPERUSER;
\q
d
(ver script para criação do banco dentro do projeto):

Criaçao do Projeto.
Utilizei o site https://start.spring.io com as seguintes definiçoes:
- Maven project, java, spring 3.0.4, jar, java8;
- Dependencias incluídas: spring web, spring data jpa, postgresql driver, spring boot devtools, spring actuator;
- nao foi encontrada a log4j, que foi adicionado e configurado posteriormente.


Para executar os testes, existe um outro arquivo dentro de resources com exemplos de requests.
Os endpoints de entrada basicos sao (POSTS) e respectivos bodyrequests são: 

	http://localhost:8080/orders/create

{
  "item": {
    "id": 1
  },
  "quantity": 6,
  "user": {
    "id": 1
  }
}
	
	http://localhost:8080/stock/create
{
  "quantity": 10,
  "item": {
    "id": 1
  }
}
