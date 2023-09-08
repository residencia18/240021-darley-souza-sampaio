#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

int main(void)
{
    srand(time(0));

    // a

    int matrix[640][480];

    ofstream arquivo("imagem_tons_de_cinza.pgm");
    if (!arquivo)
    {
        cerr << "Erro ao abrir o arquivo de imagem." << endl;
        return 1;
    }

    arquivo << "P2" << endl;
    arquivo << 640 << " " << 480 << endl;
    arquivo << "255" << endl;

    for (int i = 0; i < 640; i++)
    {
        for (int j = 0; j < 480; j++)
        {
            matrix[i][j] = rand() % 256;
            arquivo << matrix[i][j] << " ";
        }
        arquivo << endl;
    }

    arquivo.close();

    // b
    vector<int> histogram(256, 0);

    for (int i = 0; i < 640; i++)
    {
        for (int j = 0; j < 480; j++)
            histogram[matrix[i][j]]++;
    }

    ofstream histogramFile("histograma.txt");
    if (!histogramFile)
    {
        cerr << "Erro ao criar o arquivo de histograma." << endl;
        return 1;
    }

    for (int i = 0; i < 256; i++)
    {
        histogramFile << i << " " << histogram[i] << endl;
    }

    return 0;
}