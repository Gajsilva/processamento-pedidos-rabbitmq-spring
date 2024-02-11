# Spring RabbitMQ Pedido Processamento

Este projeto demonstra um sistema simples de processamento de pedidos usando Spring Boot, RabbitMQ e Spring Data JPA.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- RabbitMQ
- Spring Web
- H2 Database (ou banco de dados de sua escolha)

## Configuração do Projeto

1. **Configuração do RabbitMQ:**
   - Certifique-se de ter o RabbitMQ instalado localmente ou utilize uma instância em nuvem.

2. **Configuração do Banco de Dados:**
   - Configure as propriedades do banco de dados no arquivo `src/main/resources/application.properties`. Exemplo usando H2 Database:
     ```properties
     spring.datasource.url=jdbc:h2:mem:testdb
     spring.datasource.driverClassName=org.h2.Driver
     spring.datasource.username=sa
     spring.datasource.password=password
     spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
     ```

3. **Configuração da Fila:**
   - Configure o nome da fila no arquivo `src/main/resources/application.properties`:
     ```properties
     fila.pedidos=pedidos
     ```

## Executando o Projeto

Execute a aplicação Spring Boot e interaja com ela através do endpoint REST.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues se encontrar problemas ou melhorias.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
