#include <iostream>

using namespace std;

int main(void)
{
    // a
    float temp[250] = {};

    for (int i = 0; i < 250; i++)
        temp[i] = 1 + 30.0 * ((rand() % 100) / 3000.0);

    for (int i = 0; i < 250; i++)
        cout << temp[i] << endl;

    // b
    float minTemp = 100, maxTemp = 0;
    for (int i = 0; i < 250; i++)
    {
        if (temp[i] >= maxTemp)
            maxTemp = temp[i];

        if (temp[i] <= minTemp)
            minTemp = temp[i];
    }

    cout << "Temperatura Mínima: " << minTemp << "\n"
         << "Temperatura Máxima: " << maxTemp << endl;

    // c
    float tempMedia = 0;
    for (int i = 0; i < 250; i++)
        tempMedia += temp[i];

    tempMedia /= 250;

    cout << "Temperatura Média: " << tempMedia << endl;

    // d

    for (int i = 0; i < 250; i++)
    {
        if (temp[i] >= tempMedia)
            temp[i] += 1;
        else
            temp[i] -= 2;
    }

    for (int i = 0; i < 250; i++)
        cout << temp[i] << endl;

    return 0;
}