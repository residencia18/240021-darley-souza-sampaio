#include <iostream>
#include <iomanip>
#include <math.h>

using namespace std;
using std::setprecision;

int main(void)
{
    float imcCalculate, weight, height;
    
    cout << "Digite seu peso(xx.x): ";
    cin >> weight;

    cout << "Digite sua altura(x.xx): ";
    cin >> height;

    imcCalculate = weight/pow(height, 2);

    cout << "Seu IMC é: " << std::fixed << std::setprecision(2) << imcCalculate << endl;


    
    return 0;
}