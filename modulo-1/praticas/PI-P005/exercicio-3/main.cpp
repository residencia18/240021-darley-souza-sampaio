#include <iostream>
#include <cctype> 
#include <cmath>

using namespace std;

int main(void)
{
    //a
    int numberA = 0, numberB = 0, numberC = 0;

    //b
    cout << "Digite os valores inteiros de A e B separados por espaço: ";
    cin >> numberA >> numberB;

    //c
    numberC = numberA + numberB;
    cout << "A soma de A e B em hexadecimal: " << hex << numberC << endl;

    //d
    numberC = numberA * numberB;
    cout << "A soma de A e B em hexadecimal: " << oct << numberC << endl;

    //e
    numberC = (numberA > numberB) ? numberA - numberB : numberB - numberA;
    cout << "O módulo da diferença entre numberA e numberB: " << numberC << endl;

    //f
    numberC = abs(numberA - numberB);
    cout << "O módulo da diferença entre numberA e numberB: " << numberC << endl;

    //g
    (numberB == 0) ? cout << "Não é possível a divisão por 0" << endl :
    cout << "A dívisão de A por B como inteiro: " << numberA/numberB << endl;

    //h
    (numberA%numberB == 0) ? cout << "A divisão de A por B é exata: " << numberA/numberB << endl :
    cout << "A dívisão de A por B não é exata: " << (float)numberA/numberB << endl;

    return 0;
}