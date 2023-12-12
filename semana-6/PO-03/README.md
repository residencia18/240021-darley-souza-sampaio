## Questões

#### 1. O que é uma exceção em Java e qual é o propósito de usá-las em programas?
---
- Basicamente exceção é uma ação ou função utilizada para tratar erros do programa, com a finalidade de que o mesmo não seja interrompido. Ou seja, tratamos os erros em tempo de execução e informamos ao usuário uma mensagem para que o mesmo corrija sem a necessidade de parar o programa.

#### 2. Pesquise sobre a diferença entre exceções verificadas e não verificadas em Java. Dê exemplos de cada uma.
---
- Exceções verificadas: verificadas pelo compilador e usadas para indicar condições excepcionais que estão fora do controle do programa.

- Exceções não verificadas: ocorrem em tempo de execução que não são verificados pelo compilador, para indicar erros de lógica, condições imprevisíveis ou no próprio ambiente de execução.

#### 3. Como você pode lidar com exceções em Java? Quais são as palavras-chave e as práticas comuns para tratamento de exceções?
---
- Normalmente, eu, tenho o costume de criar classes próprias para cada exceção sendo estendidas. Utilizo um try-catch para obter as validações em tempo de execução e encontrar a exceção ideal, gerando ela com um throw new.

#### 4. O que é o bloco "try-catch" em Java? Como ele funciona e por que é importante usá-lo ao lidar com exceções?
---
- try-catch é um bloco de declaração de teste para determinada área do algoritmo, ele testa um trecho em tempo de execução (try) e específica uma resposta se uma exceção for gerada, tratando a mesma(catch). É importante usá-lo para que o programa em si não seja interrompido.

#### 5. Quando é apropriado criar suas próprias exceções personalizadas em Java e como você pode fazer isso? Dê um exemplo de quando e por que você criaria uma exceção personalizada.
---
- Cria-se exceções personalizadas quando as exceções existentes da própria linguagem não forem suficientes. Pode-se realizar a criação, como dito na questão 3, utilizo classes estendidas para criação de exceções e faço o teste com try-catch para gerar. Um exemplo de exceção personalizada, é a quando não encontramos um CPF especificado pelo usuário  em um sistema, ao percorrer o banco de dados ou mesmo uma lista e não encontramos, geramos uma exceção de CPF não encontrado e retornamos a mensagem ao usuário.
