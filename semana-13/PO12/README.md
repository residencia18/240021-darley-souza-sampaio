# Sistema de Consulta de Vencedores de Corridas de Fórmula 1

Este sistema é uma aplicação SpringBoot que disponibiliza consultas aos dados de vencedores de corridas de Fórmula 1 a partir de um arquivo CSV fornecido. Abaixo estão listadas as consultas disponíveis e seus respectivos endpoints.

### Como usar o sistema

1. **Listar todos os vencedores**

   Endpoint: `/todos`

2. **Listar os vencedores brasileiros**

   Endpoint: `/brasileiros`

3. **Listar o Top 5 vencedores (em ordem decrescente)**

   Endpoint: `/top5`

4. **Listar o Top 10 vencedores (em ordem decrescente)**

   Endpoint: `/top10`

5. **Listar o número de vitórias por país (em ordem decrescente)**

   Endpoint: `/porpais`

6. **Listar a média de vitórias por país**

   Endpoint: `/mediaporpais`
   
### Como executar o sistema

Para executar o sistema, é necessário ter o ambiente de desenvolvimento configurado para Java e Spring Boot. Siga as etapas abaixo:

#### Sistema Linux

1. Clone este repositório em seu ambiente local:

   ```
   git clone <URL_DO_REPOSITÓRIO>
   ```

2. Navegue até o diretório do projeto:

   ```
   cd Formula 1
   ```

3. Execute o projeto Spring Boot:

   ```
   ./mvnw spring-boot:run
   ```

4. Após a execução bem-sucedida, você pode acessar as consultas utilizando os endpoints mencionados acima.

#### Sistema Windows(IDE's)

1. Clone este repositório em seu ambiente local:

   ```
   git clone <URL_DO_REPOSITÓRIO>
   ```

2. Navegue até o diretório do projeto:

   ```
   Importe o projeto Maven
   ```

3. Execute o projeto Spring Boot:

   ```
   Executo o arquivo principal do projeto
   ```

4. Após a execução bem-sucedida, você pode acessar as consultas utilizando os endpoints mencionados acima.

### Requisitos do sistema

- Java JDK 8 ou superior
- Maven
- Spring Boot

Certifique-se de ter esses requisitos instalados e configurados corretamente em seu sistema antes de executar o projeto.

### Observação

Certifique-se de que o arquivo CSV fornecido esteja corretamente formatado e disponível no diretório apropriado para que a aplicação possa acessá-lo e realizar as consultas corretamente.
