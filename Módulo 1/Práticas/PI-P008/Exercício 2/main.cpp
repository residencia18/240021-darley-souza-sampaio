#include <iostream>
using namespace std;

int main(void)
{
    int vector[100] = {};

    // a
    for (int i = 0; i < 100; i++)
        vector[i] = 1 + rand() % 20;

    for (int i = 0; i < 100; i++)
        cout << vector[i] << " ";

    cout << endl << endl;

    // b
    int vector2[20] = {};
    for (int i = 0; i < 100; i++)
    {
        for (int j = 1; j <= 20; j++)
        {
            if (vector[i] == j)
                vector2[j]++;
        }
    }

    for (int i = 1; i <= 20; i++)
        cout << "Number " << i << ": "<< vector2[i] << endl;
    cout << endl;

    //c
    int maiorNumber = 0, maiorIndice = 0;


    for (int i = 1; i <= 20; i++){
        if(vector2[i] >= maiorNumber){
            maiorNumber = vector2[i];
            maiorIndice = i;
        }
    }

    cout << "O número " << maiorIndice << " aparece mais vezes: " << maiorNumber << endl << endl;



    return 0;
}