#include <iostream>
#include <climits>

using namespace std;

int main(void)
{
    int value = 0;

    // a
    cout << "Digite um número inteiro: ";
    cin >> value;

    cout << "O número " << value << (value > SHRT_MAX ? " é maior que um short int" : " este valor pertence ao intervalo dos short int") << endl;

    return 0;
}