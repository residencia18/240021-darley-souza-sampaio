#include <iostream>

using namespace std;

int main(void)
{
    int number, aux = 0, aux2, original;

    cout << "Digite um número inteiro: ";
    cin >> number;

    original = number;

    while (number != 0)
    {
        aux2 = number % 10;
        aux = aux * 10 + aux2;
        number /= 10;
    }

    (original == aux) ? cout << "É um palíndromo." << endl : cout << "Não é um palíndromo." << endl;

    return 0;
}