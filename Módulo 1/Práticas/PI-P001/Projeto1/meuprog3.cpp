#include <iostream>
#include <iomanip>

using namespace std;
using std::setprecision;

int main(void)
{
    float number_A = 0, number_B = 0;
    std::cout << "Digite o valor de A e B separados por espaço: ";
    cin >> number_A >> number_B;

    std::cout << "\n-----Resultado----\n" << std::endl;

    std::cout << std::fixed << std::setprecision(2) << "Soma =  " <<  number_A + number_B 
            << "\nSubtração A-B =  " << number_A - number_B 
            << "\nMultiplicação: =  " << number_A * number_B 
            << "\nDivisão A/B =  " << number_A / number_B  << std::endl;

    return 0;
}
