# Sistema de Gerenciamento de Empresa Coelho

Este sistema é destinado ao gerenciamento de clientes, imóveis, faturas e pagamentos para uma empresa fictícia chamada Empresa Coelho.

## Módulos

O sistema possui os seguintes módulos:

1. **Gerenciar Clientes**: Permite cadastrar, editar, excluir e listar clientes.
2. **Gerenciar Imóveis**: Permite cadastrar, editar, excluir e listar imóveis.
3. **Gerenciar Faturas**: Permite cadastrar e listar faturas, bem como listar faturas em aberto.
4. **Menu Pagamentos**: Permite registrar pagamentos de faturas e listar pagamentos e reembolsos realizados.

## Como Usar

Para utilizar o sistema, siga as instruções abaixo:

1. **Menu Principal**:
   Ao iniciar o programa, será exibido o menu principal com as opções disponíveis.

- Opção 1: Gerenciar Clientes
- Opção 2: Gerenciar Imóveis
- Opção 3: Gerenciar Faturas
- Opção 4: Menu Pagamentos
- Opção 5: Sair do Sistema


2. **Gerenciar Clientes**:
- Opção 1: Cadastrar Cliente
- Opção 2: Editar Cliente
- Opção 3: Excluir Cliente
- Opção 4: Listar Cliente
- Opção 5: Voltar ao Menu Principal

3. **Gerenciar Imóveis**:
- Opção 1: Cadastrar Imóvel
- Opção 2: Editar Imóvel
- Opção 3: Excluir Imóvel
- Opção 4: Listar Imóveis
- Opção 5: Voltar ao Menu Principal

4. **Gerenciar Faturas**:
- Opção 1: Cadastrar Fatura
- Opção 2: Listar Todas Faturas
- Opção 3: Listar Faturas em Aberto
- Opção 4: Voltar ao Menu Principal

5. **Menu Pagamentos**:
- Opção 1: Pagar Fatura
- Opção 2: Listar Pagamentos
- Opção 3: Voltar ao Menu Principal

## O que cada opção realiza:

### Clientes
- **Cadastrar Cliente**: Adiciona um novo cliente com nome e CPF únicos (necessitam ser válidos).
- **Editar Cliente**: Permite alterar o nome ou CPF de um cliente existente (necessitam ser válidos).
- **Excluir Cliente**: Remove um cliente do sistema.
- **Listar Cliente**: Exibe todos os clientes cadastrados e seus respectivos imóveis.

### Imóveis
- **Cadastrar Imóvel**: Registra um novo imóvel associado a um cliente pelo CPF, com matrícula única e endereço (matrícula é gerada automaticamente).
- **Editar Imóvel**: Permite alterar a matrícula (caso queira) ou endereço de um imóvel existente.
- **Excluir Imóvel**: Remove um imóvel do sistema.
- **Listar Imóveis**: Exibe todos os imóveis cadastrados e suas respectivas faturas.

### Faturas
- **Cadastrar Fatura**: Registra uma nova fatura para um imóvel, calculando o valor com base nas leituras de consumo de água(leituras são em Watt).
- **Listar Todas Faturas**: Exibe todas as faturas registradas para um imóvel.
- **Listar Faturas em Aberto**: Exibe as faturas não pagas de um imóvel.

### Pagamentos
- **Pagar Fatura**: Registra o pagamento de uma fatura, permitindo a aplicação de reembolso se necessário.
- **Listar Pagamentos**: Exibe todos os pagamentos registrados para uma fatura específica e reembolso caso tenha.

## Observações

- O sistema armazena os dados em um banco de dados na nuvem para preservar os dados entre execuções utilizando Hibernate. O modelo do banco de dados está na pasta lib do projeto.
Caso queira realizar mudanças e visualizar os dados, utilize um banco de dados próprio utilizando o modelo.

Para contribuir com o desenvolvimento deste sistema, sinta-se à vontade para fazer fork do repositório, enviar pull requests ou abrir issues para reportar problemas ou sugestões de melhorias.
