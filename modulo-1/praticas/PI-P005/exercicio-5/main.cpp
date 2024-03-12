#include <iostream>
#include <cmath>

using namespace std;

int main(void)
{
    // a
    unsigned char geneticInformation;
    int countGen = 0, gene = 0;
    cout << "Informe a informação genética da planta: ";
    cin >> geneticInformation;

    // b

    for (int i = 0; i < 8; ++i)
    {
        if ((geneticInformation & (1 << i)) != 0)
        {
            countGen++;
        }
    }

    // c
    cout << "Informe o número do gene que deseja verificar (entre 1 e 8): ";
    cin >> gene;

    if (gene >= 1 && gene <= 8)
    {
        bool geneEstaPresente = (geneticInformation & (1 << (gene - 1))) != 0;
        if (geneEstaPresente)
        {
            cout << "O gene " << gene << " está presente nesta planta." << endl;
        }
        else
        {
            cout << "O gene " << gene << " não está presente nesta planta." << endl;
        }
    }
    else
    {
        cout << "Número de gene inválido. Deve ser um valor entre 1 e 8." << endl;
    }

    return 0;
}