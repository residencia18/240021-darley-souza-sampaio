#include <iostream>
#include <math.h>
#include <iomanip>

using namespace std;

int main(void)
{
     // a, b e c
     int numberA = 0, numberB = 0, result;

     cout << "Digite dois valores inteiros: ";
     cin >> numberA >> numberB;

     (numberA > numberB) ? (cout << numberA << " é maior", (numberA%2 == 0) ? cout << " e é par" : cout << " e é impar" << endl) : 
     (numberB > numberA) ? (cout << numberB << " é maior", (numberB%2 == 0) ? cout << " e é par" : cout << " e é impar" << endl):
     cout << "Estes números são iguais" << endl;

     





     return 0;
}