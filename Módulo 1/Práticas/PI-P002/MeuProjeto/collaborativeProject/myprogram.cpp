#include <iostream>
#include <iomanip>
#include <math.h>

using namespace std;
using std::setprecision;

int main(void)
{
    float imcCalculate, weight, height;
    
    cout << "Digite seu peso(xx.x) e sua altura(x.xx), com um espaço entre eles: ";
    cin >> weight >> height;


    imcCalculate = weight/pow(height, 2);

    cout << "Seu IMC é: " << fixed << setprecision(2) << imcCalculate << endl;


    
    return 0;
}