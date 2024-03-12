#include <iostream>
#include <math.h>
#include <iomanip>

using namespace std;

int main(void)
{
     // a
     char ch1, ch2, ch3;

     // b
     cout << "Digite dois caracteres: ";
     cin >> ch1 >> ch2;

     // c
     ch3 = (int)ch1 - 1;

     cout << "Caractere: " << ch3 << endl
          << "Decimal: " << (int)ch3 << endl
          << "Octal: " << oct << (int)ch3 << endl
          << "Hexadecimal: " << hex << (int)ch3 << "\n"
          << endl;

     // d
     ch3 = (int)ch2 + 1;

     cout << "Caractere: " << ch3 << endl
          << "Decimal: " << (int)ch3 << endl
          << "Octal: " << oct << (int)ch3 << endl
          << "Hexadecimal: " << hex << (int)ch3 << "\n"
          << endl;

     //e
     (ch1 >= 'A' && ch1 <= 'Z') ? ch3 = 'A' : ch3 = ' ';
     cout << ch3 << endl;

     //f
     (ch2 >= 'a' && ch2 <= 'z') ? ch3 = 'a' : ch3 = ' ';
     cout << ch3 << endl;

     //g
     ((ch1 >= '0' && ch1 <= '9') || (ch2 >= '0' && ch2 <= '9')) ? ch3 = '1' : ch3 = ' ';
     cout << ch3 << endl;

     return 0;
}