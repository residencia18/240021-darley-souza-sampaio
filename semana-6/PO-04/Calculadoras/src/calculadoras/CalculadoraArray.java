package calculadoras;

import java.util.List;

public class CalculadoraArray {
    Calculadora calculadora = new Calculadora();
	
    public <T extends Number> T somaLista(List<T> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("A lista está vazia");
        }

        T resultado = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            resultado = calculadora.soma(resultado, numeros.get(i));
        }

        return resultado;
    }

    public <T extends Number> T subtracaoLista(List<T> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("A lista está vazia");
        }

        T resultado = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            resultado = calculadora.subtracao(resultado, numeros.get(i));
        }

        return resultado;
    }

    public <T extends Number> T multiplicacaoLista(List<T> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("A lista está vazia");
        }

        T resultado = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            resultado = calculadora.multiplicacao(resultado, numeros.get(i));
        }

        return resultado;
    }

    public <T extends Number> T divisaoLista(List<T> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("A lista está vazia");
        }

        T resultado = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            resultado = calculadora.divisao(resultado, numeros.get(i));
        }

        return resultado;
    }

    public <T extends Number> T restoLista(List<T> numeros) {
        if (numeros.isEmpty()) {
            throw new IllegalArgumentException("A lista está vazia");
        }

        T resultado = numeros.get(0);
        for (int i = 1; i < numeros.size(); i++) {
            resultado = calculadora.resto(resultado, numeros.get(i));
        }

        return resultado;
    }

}
