#include <iostream>
#include <iomanip>

using namespace std;
using std::setprecision;

int main(void)
{
    float avaliation1[15] = {}, avaliation2[15] = {};

    // a
    for (int i = 0; i < 15; i++)
        avaliation1[i] = ((float)rand() / RAND_MAX) * 10.0;

    // b

    for (int i = 0; i < 15; i++)
        avaliation2[i] = ((float)rand() / RAND_MAX) * 10.0;

    for (int i = 0; i < 15; i++)
        cout << fixed << setprecision(2) << "Aluno " << i + 1 << ": " << endl
             << "Avaliação 1: " << avaliation1[i] << endl
             << "Avaliação 2: " << avaliation2[i] << endl
             << endl;

    // c
    for (int i = 0; i < 15; i++)
    {
        if (avaliation1[i] > avaliation2[i])
            cout << "Aluno " << i + 1 << " Piorou" << endl;
        else if (avaliation1[i] < avaliation2[i])
            cout << "Aluno " << i + 1 << " Melhorou" << endl;
        else
            cout << "Aluno " << i + 1 << " Manteve a nota" << endl;
    }
    cout << endl;

    // d
    float avaliationMed[15] = {};

    for (int i = 0; i < 15; i++)
        avaliationMed[i] = (avaliation1[i] + avaliation2[i]) / 2;

    for (int i = 0; i < 15; i++)
        cout << "Média Aluno " << i + 1 << ": " << avaliationMed[i] << endl;

    return 0;
}