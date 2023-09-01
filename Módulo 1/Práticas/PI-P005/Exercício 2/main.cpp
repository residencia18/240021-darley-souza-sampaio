#include <iostream>
#include <cctype> 

using namespace std;

int main(void)
{
    //a
    char ch1, ch2, ch3;

    //b
    cout << "Digite um caractere: ";
    cin >> ch1;
    
    //c
    cout << "Modo 1: ";
    (ch1 >= 'A' && ch1 <= 'Z') ? cout << "Maíuscula" << endl :
    (ch1 >= 'a' && ch1 <= 'z') ? cout << "Minúscula" << endl :
    (ch1 >= '0' && ch1 <= '9') ? cout << "Dígito" << endl :
    cout << "Outro tipo de caractere" << endl;    

    //d
    cout << "Modo 2: ";
    isupper(ch1) ? cout << "Maiúscula" << endl :
    islower(ch1) ? cout << "Minúscula" << endl :
    isdigit(ch1) ? cout << "Dígito" << endl :
    isalpha(ch1) ? cout << "Outro tipo de caractere alfabético" << endl :
    cout << "Outro tipo de caractere não alfabético" << endl;

    cout << "\n\n";

    //e
    ch2 = 81;
    cout << "Caractere '" << ch2 << "'" << endl
    << "Decimal: " << (int)ch2 << endl
    << "Octal: " << oct << (int)ch2 << endl
    << "Hexadecimal: " << hex << (int)ch2 << "\n" << endl;

    cout << "\n\n";

    //f
    ch3 = tolower(ch2);

    cout << "Caractere '" << ch3 << "'" << endl
    << "Decimal: " << (int)ch3 << endl
    << "Octal: " << oct << (int)ch3 << endl
    << "Hexadecimal: " << hex << (int)ch3 << "\n" << endl;

    return 0;
}