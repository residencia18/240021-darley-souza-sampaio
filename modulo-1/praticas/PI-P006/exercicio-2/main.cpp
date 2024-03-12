#include <iostream>
#include <math.h>

using namespace std;

int main(void)
{
    // a
    int numberA = 0, numberB = 0, numberC = 0;

    // b
    cout << "Digite dois números inteiros para a e b: ";
    cin >> numberA >> numberB;

    // c e d
    numberC = 4 * numberA + numberB / 3 - 5;
    cout << "O resultado da expressão 4 * a + b / 3 - 5 é: " << numberC << endl;

    // e
    numberC = 4 * (numberA + numberB) / (3 - 5);
    cout << "O resultado da expressão 4 * (a + b) / (3 - 5) é: " << numberC << endl;

    /* f
        Em ambos os casos o compilador segue uma ordem de precedência. 4 * a + b / 3 - 5, multiplicaçao e divisão possuem precedência, então são
        calculados primeiro, depois soma e subtração. 4 * (a + b) / (3 - 5) os parenteses possuem precedência, então são calculados primeiro.
        Outro modo de realizar o cálculo seria modificando a ordem dos parenteses, por exemplo, 4 * ((a + b) / 3) - 5
    */
    numberC = 4 * ((numberA + numberB) / 3) - 5;
    cout << "O resultado da expressão 4 * (a + b) / (3 - 5) é: " << numberC << endl;

    //g
    cout << "O resultado da expressão a^2 + 2*b + c é: " << pow(numberA, 2) + 2 * numberB + numberC << endl;

    return 0;
}