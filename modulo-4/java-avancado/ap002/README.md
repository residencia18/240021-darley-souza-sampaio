# Rede Social - Documentação do Serviço de Autenticação e Recuperação de Senha

Este projeto contém os serviços de autenticação e recuperação de senha para uma rede social.

## Instruções de Utilização

### Requisitos

- Java 8 ou superior
- Maven
- PostgreSQL
- Postman (ou outra ferramenta para testar APIs)

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL com o nome `redeSocial`.
2. Configure as credenciais de acesso ao banco de dados no arquivo `application.properties`.

### Execução do Projeto

1. Clone este repositório.
2. Navegue até o diretório do projeto.
3. Execute o comando `mvn spring-boot:run` para iniciar o aplicativo ou inicie através da sua IDE.

O servidor estará rodando em `http://localhost:8080`.

## Endpoints

### Autenticação

#### Registro

Permite que usuários sejam cadastrados.

- **URL:** `POST http://localhost:8080/auth/register`
- **Body (JSON):**
  ```json
    {
    "login": "novousuario",
    "email": "novousuario@exemplo.com",
    "senha": "Senha@123",
    "role": "USER" // ou ADMIN
    }
  
- **Response (Exemplo):**
    `Response: Código de status 201 Created se o registro for bem-sucedido.`

#### Login

Permite que o usuário realize o login, emitindo um token para que tenha acesso aos endpoints.

- **URL:** `POST http://localhost:8080/auth/login`
- **Body (JSON):**
  ```json
  {
    "login": "exemplo@usuario.com",
    "senha": "senha123"
  }
  
- **Response (Exemplo):**
    ```json
    {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJleG1lbG8rdXN1YXJpb0Bjb21wYW55LmNvbSIsImlhdCI6MTYzMzUxMzQyMiwiZXhwIjoxNjMzNTEzNTQyfQ.55oP6okzdzvFj4xV7LsIw1U6VQ-zMBv1YyXYtJkxJ2A"
    }
    ```

### Recuperação de Senha

#### Solicitar Recuperação de Senha

Realiza a solicitação para mudar de senha, enviando um link com o token para o email da pessoa.

- **URL:** `PUT http://localhost:8080/recovery/{email}`
- **Response:** Código de status `200 OK` se o e-mail de recuperação for enviado com sucesso.

### Redefinição de Senha

#### Redefinir Senha

Redefine a senha da pessoa através do token recebido pelo email como parâmetro e a nova senha no corpo da requisição.

- **URL:** `PUT http://localhost:8080/reset-password?token={token}`
- **Body (JSON):**
  ```json
  {
    "senha": "Senha@123"
  }
- **Response:** Código de status `200 OK` se a senha for alterada com sucesso.



## Documentação da API pelo SWAGGER

Dados mais completos sobre os endpoits da API presente no link a seguir:
http://localhost:8080/swagger-ui/index.html#/
