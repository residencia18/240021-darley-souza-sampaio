#include <iostream>
#include <iomanip>

using namespace std;
int main(void)
{
    char caracteres[10] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    for (int i = 0; i <= 9; i++)
    {
        cout << "Caractere '" << caracteres[i] << "'" << endl;
        cout << "Decimal: " << (int)caracteres[i] << endl;
        cout << "Octal: " << oct << (int)caracteres[i] << endl;
        cout << "Hexadecimal: " << hex << (int)caracteres[i] << "\n" << endl;
    }

    return 0;
}
