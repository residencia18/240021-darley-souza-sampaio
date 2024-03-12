#include <iostream>
#include <math.h>
#include <iomanip>

using namespace std;

int main(void)
{
     double radius, area, diameter, circumference;

     cout << "Digite o radius do círculo: ";
     cin >> radius;

     diameter = 2 * radius;
     circumference = 2 * M_PI * radius;
     area = M_PI * pow(radius, 2);

     cout << "Diâmetro: " << diameter << endl;
     cout << "Circunferência: " << circumference << endl;
     cout << "Área: " << area << endl;

     return 0;
}