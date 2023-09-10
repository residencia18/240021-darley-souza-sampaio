#include <iostream>

using namespace std;

int factorial(int);

int main(void)
{
    int vet[3] = {5, 10, 12};

    for (int i = 0; i < 3; i++)
        cout << "Fatorial de " << vet[i] << " é: " << factorial(vet[i]) << endl;
    
    return 0;
}

int factorial(int number)
{
    if (number == 0)
        return 1;
    else
        return number * factorial(number - 1);
}