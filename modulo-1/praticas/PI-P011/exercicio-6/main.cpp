#include <iostream>

using namespace std;

int *intercala(int *, int, int *, int);

int main(void)
{
    int vet1[100], vet2[100], *vet3;


    vet1[0] = 0;
    for (int i = 1; i < 30; i++)
    {
        if (i % 2 == 0)
                vet1[i] = i;
            else
                vet2[i] = i;
    }

    vet3 = intercala(vet1, 15, vet2, 15);

    for (int i = 0; i < 100; i++)
    {
        if (vet3[i])
            cout << vet3[i] << " ";
    }

    cout << endl;

    return 0;
}

int *intercala(int *vet1, int tam1, int *vet2, int tam2)
{
    int tamNew = tam1 + tam2;
    int *vet = new int[100];

    vet[0] = vet1[0];

    for (int i = 1; i <= tamNew; i++)
    {
        if(vet1[i] || vet2[i])
        {

            if (i % 2 == 0)
                vet[i] = vet1[i];
            else
                vet[i] = vet2[i];
        }
    }

    return vet;
}
