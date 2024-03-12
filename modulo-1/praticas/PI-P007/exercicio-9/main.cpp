#include <iostream>

using namespace std;

int main()
{
    int number, divisorsSum = 0;

    cout << "Digite um número inteiro: ";
    cin >> number;

    for (int i = 1; i < number; i++)
    {
        if (number % i == 0)
            divisorsSum += i;
    }
    (divisorsSum == number) ? cout << number << " é um número perfeito." << endl : cout << number << " não é um número perfeito." << endl;

    return 0;
}
