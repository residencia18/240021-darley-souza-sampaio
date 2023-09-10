#include <iostream>

using namespace std;

double convertTemp(float);
void inOut(); 

int main(void)
{
    inOut();
    
    return 0;
}

double convertTemp(double celsius)
{
    return (celsius * 9/5) + 32;
}

void inOut()
{
    double celsius, fahrenheit;

    cout << "Digite a temperatura em Celsius: ";
    cin >> celsius;

    fahrenheit = convertTemp(celsius);

    cout << "A temperatura em Fahrenheit é: " << fahrenheit << " graus Fahrenheit" << endl;

}