# springflix
aplicação de linha de comando sobre informações de séries de tv

### Tecnologias Utilizadas:
- Spring 
- JPA
- Jackson
- Maven
- PostegreSQL

### Recursos e Funcionalidades:
- modelagem das abstrações da aplicação
- consumo de API para buscar os dados referentes a série escolhida
- uso de funções lambdas
- uso de API de streams do Java
- Spring Data JPA para persistir dados no banco
- realizar vários tipos de consultas ao banco (PostgreSQL) sejam nativas da JPA ou derived queries

### Repositório Original:
O código original pode ser encontrado [aqui](https://github.com/iasminaraujoc/3355-java-screenmatch-com-jpa)

### Melhorias:
O projeto foi realizado durante o curso "Java: Persistência de dados e consultas com Spring Data JPA", porem realizei algumas melhorias que considerei necessárias:

- acrescentei mais opções de menu (mais métodos), para aumentar a interação com o usuário e possibilitar uma busca mais refinada
- realizei o tratamento de exceções e validei os resultados 
- renomeei métodos, classes e atributos que julguei estarem com legibilidade baixa/dubia
- uso de REGEX

### Executando a aplicação:
Pré-requisitos:
- certifique-se de ter o Java instalado na sua máquina
- instale o Apache Maven para gerenciar as dependências do projeto
- configure um banco de dados PostgreSQL e tenha as credenciais de acesso

Executar o projeto:
- clone o repositório
- configure as credenciais do banco de dados, seja através de variaveis de ambiente ou manualmente no arquivo application.properties
- compile o projeto com
```
mvn clean install
```
- rode a aplicação e veja o console

### Contribuindo com o projeto:
- Crie um fork do repositório
- Crie uma branch para suas alterações 
- Faça commit das alterações 
- Faça push para a branch
- Abra um novo Pull Request explicando na descrição quais foram as suas contribuições


Desenvolvido por [Mylena Verspeelt](https://www.linkedin.com/in/mylenaverspeelt/) 🌿🌿   

