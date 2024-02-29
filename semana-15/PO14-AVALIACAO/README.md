# Guia de Uso do Sistema

Este documento descreve como utilizar os endpoints disponíveis no sistema de leilões. Abaixo estão listados os endpoints para manipulação de concorrentes, leilões e lances, juntamente com os métodos HTTP suportados e os formatos de dados esperados.

## Endpoints de Concorrentes

### GET

- **Todos concorrentes:** `localhost:8080/concorrentes/`
- **Concorrente Específico:** `localhost:8080/concorrentes/id`

### POST

- **Inserir Concorrente:** `localhost:8080/concorrentes/`
    ```json
    {
        "nome": "",
        "cpf": "" // XXX.XXX.XXX-XX
    }
    ```

### PUT

- **Editar Concorrente:** `localhost:8080/concorrentes/id`
    ```json
    {
        "nome": "",
        "cpf": "" // XXX.XXX.XXX-XX
    }
    ```

### DELETE

- **Deletar Concorrente:** `localhost:8080/concorrentes/1`

## Endpoints de Leilão (Itens)

### GET

- **Todos leilões:** `localhost:8080/leilao/`
- **Leilão Específico:** `localhost:8080/leilao/id`

### POST

- **Inserir Leilão:** `localhost:8080/leilao/`
    ```json
    {
        "descricao": "",
        "valor": 0,
        "status": 0  // 1(finalizado) ou 0 (aberto)
    }
    ```

### PUT

- **Editar Leilão:** `localhost:8080/leilao/id`
    ```json
    {
        "descricao": "",
        "valor": 0,
        "status": 0 // 1(finalizado) ou 0 (aberto)
    }
    ```

### DELETE

- **Deletar Leilão:** `localhost:8080/leilao/id`

## Endpoints de Lances

### GET

- **Listar Lances:** `localhost:8080/lances/`
- **Lance Específico:** `localhost:8080/lances/id`
- **Todos lances por item:** `localhost:8080/lance/leilao=Id`
- **Todos lances por concorrente:** `localhost:8080/lance/concorrente=Id`

### POST

- **Inserir Lance:** `localhost:8080/lances/`
    ```json
    {
        "leilao": {
            "id": ,
            "descricao": "",
            "valor_minimo": ,
            "status": 0
        },
        "concorrente": {
            "id": ,
            "nome": "",
            "cpf": "" // XXX.XXX.XXX-XX
        },
        "valor": // valor do lance
    }
    ```

### PUT

- **Editar Lance:** `localhost:8080/lances/id`
    ```json
    {
        "leilao": {
            "id": ,
            "descricao": "",
            "valor_minimo": ,
            "status": 0
        },
        "concorrente": {
            "id": ,
            "nome": "",
            "cpf": "" // XXX.XXX.XXX-XX
        },
        "valor": // valor do lance
    }
    ```

### DELETE

- **Deletar Lance:** `localhost:8080/lances/id`

Certifique-se de substituir `id`, `descricao`, `valor`, `nome`, `cpf`, e `status` pelos valores correspondentes conforme necessário ao fazer solicitações para os endpoints.
