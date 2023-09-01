#include <iostream>
#include <cmath>

using namespace std;

int main(void)
{
    // a
    double numberA, numberB, numberC, delta, x, x1, x2, px;

    cout << "Digite os valores de A, B e C: ";
    cin >> numberA >> numberB >> numberC;

    delta = pow(numberB, 2) - (4 * numberA * numberC);

    if (delta > 0)
    {
        x1 = (-numberB + sqrt(delta)) / (2 * numberA);
        x2 = (-numberB - sqrt(delta)) / (2 * numberA);
        cout << "Existem duas raizes reais, sendo elas x1 = " << x1 << " e " << x2 << "\n" << endl;
    }
    else if (delta == 0)
    {
        x1 = -numberB / (2 * numberA);
        cout << "Existem somente uma raiz real, sendo ela x1 = " << x1 << "\n"
             << endl;
    }
    else
    {
        cout << "O polinômio não possui raízes reais.\n"
             << endl;
    }

    //b
    cout << "Agora digite um valor para x: ";
    cin >> x;

    px = (numberA * pow(x,2)) + (numberB * x) + numberC;
    cout << "O valor de p(x) é: " << px << endl;

    return 0;
}