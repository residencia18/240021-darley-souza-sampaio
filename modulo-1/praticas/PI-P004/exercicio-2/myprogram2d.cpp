#include <iostream>
#include <iomanip>

using namespace std;

int main(void)
{
    char caractere;

    cout << "Insira o caractere: ";
    cin >> caractere;

    cout << "Caractere '" << caractere << "'" << endl;
    cout << "Decimal: " << (int)caractere << endl;
    cout << "Octal: " << oct << (int)caractere << endl;
    cout << "Hexadecimal: " << hex << (int)caractere << "\n"
         << endl;

    return 0;
}
