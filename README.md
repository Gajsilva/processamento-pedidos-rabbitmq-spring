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
   - Adicione um usuário no RabbitMQ via terminal:
     ```bash
     sudo rabbitmqctl add_user seu_usuario sua_senha
     sudo rabbitmqctl set_user_tags seu_usuario administrator
     sudo rabbitmqctl set_permissions -p / seu_usuario ".*" ".*" ".*"
     ```

2. **Configuração do Banco de Dados:**
   - Configure as propriedades do banco de dados no arquivo `src/main/resources/application.properties`. Exemplo usando H2 Database:
   
   ```properties
   server.servlet.context-path=/pedidos
   # Configuração do Data Source
   spring.datasource.url=jdbc:h2:file:./h2-dadosLocal
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=sa

   # Configuração do Console H2
   spring.h2.console.enabled=true
   spring.h2.console.settings.trace=false
   spring.h2.console.settings.web-allow-others=false
   spring.h2.console.path=/h2-console

   # Configuração do JPA
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.jpa.defer-datasource-initialization=true
   spring.jpa.show-sql=true
   logging.level.org.hibernate.SQL=DEBUG

   # Configuração do RabbitMQ
   spring.rabbitmq.host=seu_host_rabbitmq
   spring.rabbitmq.port=5672
   spring.rabbitmq.username=seu_usuario_rabbitmq
   spring.rabbitmq.password=sua_senha_rabbitmq

3. **Configuração da Fila:**
   - Configure o nome da fila no arquivo `src/main/resources/application.properties`:
     ```properties
     fila.pedidos=pedidos
     ```

## Executando o Projeto

Execute a aplicação Spring Boot e interaja com ela através do endpoint REST.

## Endpoints

- **POST /api/pedidos/criarPedidos:**
  Cria vários pedidos. Corpo da requisição deve conter uma lista de pedidos no formato JSON.

- **POST /api/pedidos/criarPedido:**
  Cria um pedido. Corpo da requisição deve conter um pedido no formato JSON.

- **GET /api/pedidos/{pedidoId}:**
  Obtém um pedido específico pelo ID.

- **GET /api/pedidos/buscarPorDescricao:**
  Busca pedidos por descrição. Parâmetro de consulta `descricao` é necessário.

- **DELETE /api/pedidos/{pedidoId}:**
  Deleta um pedido específico pelo ID.

- **GET /api/pedidos/listarOrdenadosPorData:**
  Lista todos os pedidos ordenados por data de criação.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou abrir issues se encontrar problemas ou melhorias.

## Licença

Este projeto é licenciado sob a [MIT License](LICENSE).
