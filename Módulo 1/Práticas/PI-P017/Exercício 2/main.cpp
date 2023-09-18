#include <iostream>
#include <math.h>

using namespace std;

class Ponto
{
private:
    float pointX, pointY;

public:
    Ponto()
    {
        this->pointX = 0.0;
        this->pointY = 0.0;
    }

    void setPoints(float newPointX, float newPointY)
    {
        pointX = newPointX;
        pointY = newPointY;
    }

    void getPoints()
    {
        cout << "Coordenadas: (" << pointX << "," << pointY << ")" << endl;
    }

    float distanceCalculator()
    {
        float distance;

        distance = sqrt(pow(pointX, 2) + pow(pointY, 2));

        return distance;
    }
};

int main(void)
{
    Ponto p1;
    p1.setPoints(3, 4);
    double distanciaP1 = p1.distanceCalculator();
    cout << "Distância: " << distanciaP1 << endl
         << endl;

    Ponto p2;
    p2.setPoints(-2, 7);
    p2.setPoints(1, 1);
    double distanciaP2 = p2.distanceCalculator();
    cout << "Distância: " << distanciaP2 << endl
         << endl;

    Ponto p3;
    Ponto p4;
    p3.setPoints(0, 3);
    p4.setPoints(4, 0);
    double distancia_p3 = p3.distanceCalculator();
    double distancia_p4 = p4.distanceCalculator();
    cout << "Distância P3: " << distancia_p3 << endl;
    cout << "Distância P4: " << distancia_p4 << endl
         << endl;

    Ponto pontos[3];
    pontos[0].setPoints(2, 2);
    pontos[1].setPoints(-1, 5);
    pontos[2].setPoints(0, 0);

    for (int i = 0; i < 3; ++i)
    {
        double distancia = pontos[i].distanceCalculator();
        cout << "Distância do ponto " << i + 1 << " até a origem: " << distancia << endl;
    }
    cout << endl;

    Ponto p5;
    p5.getPoints();
    p5.setPoints(8, -3);
    cout << "Novas coordenadas: ";
    p5.getPoints();

        return 0;
}