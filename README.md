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
(ver script para criação do banco e carga inicial de dados dentro do projeto):

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

Fluxo básico de funcionamento:
Inserir usuário;
Cadastrar produto;
Castrar estoque;
Cadastrar ordem.

Assim que uma ordem é cadastrada, caso haja o produto em estoque, a ordem é gerada uma movimentação de estoque e é enviado um email ao usuário dizendo que sua ordem está completa;
Caso não haja itens o suficiente em estoque, a ordem é criada e fica em aberto;
Quando um novo estoque for cadastrado, ele verificará se existem ordens em aberto para aquele item. Caso sim, ele tentará completa-la, tendo como prioridade as mais antigas. Caso consiga completar alguma, um e-mail é enviado ao usuário.
