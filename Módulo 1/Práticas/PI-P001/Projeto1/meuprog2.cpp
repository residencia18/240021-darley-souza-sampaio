#include <iostream>

using namespace std;

int main(void)
{
    int number_A = 0, number_B = 0;
    std::cout << "Digite o valor de A e B separados por espaço: ";
    cin >> number_A >> number_B;

    std::cout << "\n-----Resultado----\n" << std::endl;

    std::cout << "Soma =  " << number_A + number_B 
            << "\nSubtração A-B =  " << number_A - number_B 
            << "\nMultiplicação: =  " << number_A * number_B 
            << "\nDivisão A/B =  " << number_A / number_B 
            << "\nResto A/B =  " << number_A % number_B 
            << std::endl;

    return 0;
}
