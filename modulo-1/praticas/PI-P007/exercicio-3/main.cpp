#include <iostream>

using namespace std;

int main(void)
{
    int number;

    cout << "Digite um número inteiro: ";
    cin >> number;

    for (int i = 1; i <= number; i++)
    {
        if(number%i == 0)
            cout << i << endl;
    }
    

    return 0;
}