#include <iostream>

using namespace std;

int insere_meio(int *, int &, int);

int main(void)
{
    int vetor[100];
    int qtde = 6;

    for (int i = 0; i < qtde; i++)
        vetor[i] = i * 2;

    int elemento = 9;

    insere_meio(vetor, qtde, elemento);

    cout << "Novo valor de qtde: " << qtde << endl;
    cout << "Vetor resultante: ";

    for (int i = 0; i < qtde; i++)
        cout << vetor[i] << " ";

    cout << endl;

    return 0;
}

int insere_meio(int *vetor, int &qtde, int elemento)
{
    int posicao = 0;

    while (posicao < qtde && vetor[posicao] < elemento)
        posicao++;

    for (int i = qtde; i > posicao; i--)
        vetor[i] = vetor[i - 1];

    vetor[posicao] = elemento;

    qtde++;

    return qtde;
}