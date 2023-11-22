# Atividade JB - P001
## Alunos: Ticiana H. S. Paternostro & Darley Sampaio

### Pesquisa:

1. **O que é uma classe em Java e qual é a diferença entre uma classe e um objeto? Dê 5 exemplos mostrando-os em C++ e em Java.** 

A classe não é exatamente um objeto, mas sim um modelo ou especificação que define um tipo de objeto. 

O que seria um objeto?

De uma forma muito simplificada, o objeto seria uma ferramenta de comunicação entre o usuário e o seu programa, que apresenta um agrupamento de dados e procedimentos definidos na classe.

Mas devemos observar que os objetos devem ser declarados logo após inicializar a classe, para que durante a descrição de suas características, eles possam ser reconhecidos como pertencentes à classe em que se está trabalhando.

Sobre derivação, quando se diz que uma classe deriva de outra, estamos dizendo que ela herda as propriedades e os métodos dessa classe (classe-mãe). Uma classe é identificada como filha de uma classe mãe através da palavra-chave extends.

Quando uma classe não é explicitamente derivada de outra classe, ela é implicitamente derivada da classe Object que é a classe-mãe original de todas as outras classes.
 
Resumindo: a classe é um modelo, um planejamento, tal como a maquete de uma casa. O objeto seria a classe materializada, ou seja, um objeto com os devidos atributos qualificados.

**Exemplos C++:**

- Classe carro em C++:
``` c++
class Carro {
public:
    string marca;
    int ano;

    void acelerar() {
        cout << "Carro acelerando!" << endl;
    }
};
``` 
- Objeto meuCarroda classe Carro em C++:
``` c++
Carro meuCarro;
meuCarro.marca = "Toyota";
meuCarro.ano = 2022;
meuCarro.acelerar();

- Classe Ponto em C++:

class Ponto {
public:
    int x, y;
};
``` 
- Objeto pontoAda classe Ponto em C++:
``` c++
Ponto pontoA;
pontoA.x = 10;
pontoA.y = 5;
``` 
**Exemplos Java:**

- Classe Livro em Java:
``` java
public class Livro {
    String titulo;
    String autor;

    void exibirDetalhes() {
        System.out.println("Livro: " + titulo + ", Autor: " + autor);
    }
}
``` 
- Objeto meuLivroda classe Livro em Java:
``` java
Livro meuLivro = new Livro();
meuLivro.titulo = "Dom Casmurro";
meuLivro.autor = "Machado de Assis";
meuLivro.exibirDetalhes();
``` 

- Classe Retangulo em Java:
``` java
public class Retangulo {
    int largura;
    int altura;
}
``` 

- Objeto meuRetanguloda classe Retangulo em Java:
``` java
Retangulo meuRetangulo = new Retangulo();
meuRetangulo.largura = 5;
meuRetangulo.altura = 3;
``` 


2. **Como você declara uma variável em Java e quais são os tipos de dados primitivos mais comuns? Faça um paralelo entre isso e a mesma coisa na linguagem C++** 

Variável é uma identificação de um espaço em memória, utilizado pelo nosso programa. Seguindo as convenções em linguagem de programação, toda variável é composta por: tipo de dados + identificação + valor atribuído.
A estrutura padrão para se declarar uma variável sempre é:

	 <Tipo> <nomeVariavel> <atribuicaoDeValorOpcional>

Os tipos de dados primitivos mais comuns em Java são:

- int: Representa números inteiros.
- duplo: Representa números de ponto flutuante de isolamento duplo.
- float: Representa números de ponto flutuante de precisão simples.
- char: Representa um caractere.
- booleano: Representa um valor lógico (verdadeiro ou falso).

Exemplos de variáveis ​​em Java e C++: 

- JAVA
``` java
int minhaVariavelInt;
double minhaVariavelDouble;
float minhaVariavelFloat;
char minhaVariavelChar;
boolean minhaVariavelBoolean;
``` 
- C++:
``` c++
int minhaVariavelInt;
double minhaVariavelDouble;
float minhaVariavelFloat;
char minhaVariavelChar;
bool minhaVariavelBool;
``` 
(Os tipos de dados primitivos são semelhantes nas duas linguagens, porém existem algumas diferenças nos nomes, por exemplo Em Java, o tipo booleano é chamado de boolean, e em C++ é chamado de bool)


3. **Explique o conceito de herança em Java e como você pode criar uma subclasse a partir de uma classe existente. Faça um paralelo com C++,apresentando 5 exemplos**

Herança é a capacidade de uma subclasse de ter acesso as propriedades da superclasse (também chamada classe base) relacionada a esta subclasse. Dessa forma os atributos e métodos de uma classe são propagados de cima para baixo em um diagrama de classes.
É um mecanismo da Orientação a Objeto, que permite criar novas classes a partir de classes já existentes, aproveitando as características existentes nas classes que serão estendidas.

**Exemplo 01:**

- JAVA
``` java
class Animal {
    void comer() {
        System.out.println("Animal comendo...");
    }
}

class Cachorro extends Animal {
    void latir() {
        System.out.println("Cachorro latindo...");
    }
}
``` 
- C++:
``` c++
class Animal {
public:
    void comer() {
        cout << "Animal comendo..." << endl;
    }
};

class Cachorro : public Animal {
public:
    void latir() {
        cout << "Cachorro latindo..." << endl;
    }
};
``` 
**Exemplo 02:**

- JAVA:
``` java
class Veiculo {
    void mover() {
        System.out.println("Veículo movendo...");
    }
}

class Carro extends Veiculo {
    void acelerar() {
        System.out.println("Carro acelerando...");
    }
}
``` 
- C++:
``` c++
class Veiculo {
public:
    void mover() {
        cout << "Veículo movendo..." << endl;
    }
};

class Carro : public Veiculo {
public:
    void acelerar() {
        cout << "Carro acelerando..." << endl;
    }
};
``` 
**Exemplo 03:**

- JAVA:
``` java
class Pessoa {
    String nome;
    int idade;

    void apresentar() {
        System.out.println("Eu sou " + nome + " e tenho " + idade + " anos.");
    }
}

class Estudante extends Pessoa {
    String curso;

    void estudar() {
        System.out.println(nome + " está estudando " + curso + ".");
    }
}
``` 
- C++:
``` c++
class Pessoa {
public:
    string nome;
    int idade;

    void apresentar() {
        cout << "Eu sou " << nome << " e tenho " << idade << " anos." << endl;
    }
};

class Estudante : public Pessoa {
public:
    string curso;

    void estudar() {
        cout << nome << " está estudando " << curso << "." << endl;
    }
};
``` 

**Exemplo 04:**

- JAVA: 
``` java
class Forma {
    void desenhar() {
        System.out.println("Desenhando uma forma...");
    }
}

class Circulo extends Forma {
    void desenharCirculo() {
        System.out.println("Desenhando um círculo...");
    }
}
``` 
- C++:
``` c++
class Forma {
public:
    void desenhar() {
        cout << "Desenhando uma forma..." << endl;
    }
};

class Circulo : public Forma {
public:
    void desenharCirculo() {
        cout << "Desenhando um círculo..." << endl;
    }
};
``` 

**Exemplo 05:**

- JAVA: 
``` java
class InstrumentoMusical {
    void tocar() {
        System.out.println("Tocando o instrumento musical...");
    }
}

class Violao extends InstrumentoMusical {
    void afinar() {
        System.out.println("Afinando o violão...");
    }
}
``` 
- C++: 

``` c++
class InstrumentoMusical {
public:
    void tocar() {
        cout << "Tocando o instrumento musical..." << endl;
    }
};

class Violao : public InstrumentoMusical {
public:
    void afinar() {
        cout << "Afinando o violão..." << endl;
    }
};
```



4. **Quando declaramos uma variável em Java, temos, na verdade, um ponteiro. Em C++ é diferente. Discorra sobre esse aspecto.** 

Em Java, quando você declara uma variável de um tipo de objeto (não um tipo primitivo), você está criando uma referência para um objeto. Essa referência funciona como um ponteiro para uma instância real do objeto no heap e possuimos acesso direto aos ponteiros de memória, onde a manipulação de objetos é feita por meio dessas referências.

**Exemplo em Java:**
``` java
    MinhaClasse obj = new MinhaClasse();
``` 
Em C++ a situação é um pouco diferente. Quando declaramos uma variável de um tipo de objeto, estamos alocando diretamente esse objeto na memória, não apenas uma referência a ele. Se quisermos usar um ponteiro para um objeto em C++, precisamos explicitamente declarar um ponteiro e alocar dinamicamente o objeto usando o operador.

**Exemplo em C++:**
``` c++
    MinhaClasse* ptrObj = new MinhaClasse();
``` 
Aqui, ptrObj é um ponteiro que contém o endereço de um objeto da classe MinhaClasse, que foi alocado dinamicamente no heap. Em C++ temos mais controle sobre a manipulação direta de ponteiros e gerenciamento de memória, mas também assumimos mais responsabilidades no código, como a necessidade de liberar a memória manualmente usando o operador delete quando não precisar mais do objeto.

Em resumo: Em Java, as variáveis ​​de objeto são referências gerenciadas automaticamente pelo sistema, enquanto em C++ trabalhamos explicitamente com ponteiros e alocação de memória.

