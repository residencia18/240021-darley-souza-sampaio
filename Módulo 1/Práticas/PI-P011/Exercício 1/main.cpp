#include <iostream>

using namespace std;

void maxmin(int vetor[], int n, int &maximo, int &minimo);

int main(void)
{
    int vet[] = {0, 1, 22, 3, 4, 5, 6};
    int maximo, minimo, n = 7;

    maxmin(vet, n, maximo, minimo);

    cout << "Maior: " << maximo << endl
         << "Menor: " << minimo << endl;

    return 0;
}

void maxmin(int vetor[], int n, int &maximo, int &minimo)
{
    int max = vetor[0];
    int min = vetor[0];

    if (n <= 0)
    {
        maximo = 0;
        minimo = 0;
        return;
    }

    for (int i = 1; i < n; i++)
    {
        if (vetor[i] > max)
            max = vetor[i];
        if (vetor[i] < min)
            min = vetor[i];
    }

    maximo = max;
    minimo = min;
}