#include <iostream>
#include <cmath>

using namespace std;

int main(void)
{
    // a
    double x = 0, y = 0, z = 0;

    // b
    cout << "Digite os valores flutuantes de A e B separados por espaço: ";
    cin >> x >> y;

    // c
    cout << "O ponto (" << x << ", " << y << ") está " << (y > ((5 * x) + 2) ? "acima" : (y < ((5 * x) + 2) ? "abaixo" : "na")) << " da curva." << endl;

    // d
    cout << "A distância euclidiana do ponto (" << x << ", " << y << ") ao centro de coordenadas é: " << sqrt(pow(x, 2) + pow(y, 2)) << endl;

    // e
    cout << "O produto entre x e y em notação científica é: " << scientific << x * y << endl;

    return 0;
}