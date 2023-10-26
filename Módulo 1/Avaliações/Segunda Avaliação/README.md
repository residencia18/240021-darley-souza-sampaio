## Atualização de Algoritmo para Implementação de Práticas em Polimorfismo, Classes Abstratas e Interfaces


### 1. Atualização da Classe Data:

A Classe Data foi atualizado o método compara. Esse método permite comparar datas e retornar valores de comparação (-1, 0 ou 1), de acordo com as relações de anterioridade, igualdade ou posterioridade entre as datas.

### 2. Introdução das Classes de Listas:

Introdução de quatro classes de listas, cada uma derivada da classe abstrata Lista. Cada classe de lista é especializada em um tipo de dado (Nomes, Datas, Salários e Idades).

### 3. Adição de Funcionalidades:

Foram implementados métodos virtuais nas classes de listas, conforme solicitado:

entradaDeDados(): Solicita e armazena os dados específicos de cada tipo de lista.

mostraMediana(): Calcula e exibe a mediana dos dados da lista.

mostraMenor(): Encontra e exibe o menor valor na lista.

mostraMaior(): Encontra e exibe o maior valor na lista.

listarEmOrdem(): Adicionado como método virtual nas classes de lista, permitindo a listagem em ordem específica.

### 4. Implementação dos Métodos nas Classes de Lista:

Cada classe de lista implementa os métodos acima, considerando as particularidades de seu tipo de dado. Por exemplo, a classe ListaDatas calcula a mediana de datas e ordena-as cronologicamente, enquanto a classe ListaSalarios calcula a mediana dos salários e os ordena em ordem crescente.

### 5. Entrada de Dados:

A entrada de dados, permite que o usuário especifique a quantidade de elementos a serem inseridos nas listas, tornando o código mais interativo.

## Compilação

Para compilar o código basta baixar o arquivo main.cpp e dentro da usa IDE compilar normalmente com as extensões devidas para a linguagem C++.
Se estiver utilizando o terminal basta utilizar o comando <strong>g++ main.cpp -o 'nome-arquivo-executavel'</strong>, após isso, <strong>./'nome-arquivo-executavel'</strong>.