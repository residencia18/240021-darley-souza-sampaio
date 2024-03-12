#include <iostream>
#include <math.h>

using namespace std;

struct Ponto
{
    double pointX, pointY;
};

double distanceCalculator(const Ponto &, const Ponto &);

int main(void)
{
    Ponto pointA, pointB;

    cout << "Digite as distâncias do ponto A (X Y): ";
    cin >> pointA.pointX >> pointA.pointY;

    cout << "Digite as distâncias do ponto B (X Y): ";
    cin >> pointB.pointX >> pointB.pointY;

    double distance = distanceCalculator(pointA, pointB);

    cout << "A distância entre os pontos é: " << distance << endl;

    return 0;
}

double distanceCalculator(const Ponto &point1, const Ponto &point2)
{
    double dx = point1.pointX - point2.pointX;
    double dy = point1.pointY - point2.pointY;
    return sqrt(dx * dx + dy * dy);
}