#include <iostream>

using namespace std;

int main(void)
{
    int height;

    cout << "Digite a altura: ";
    cin >> height;

    char character = '*';
    int perLine = 1;

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < height; j++)
        {
            cout << " " << character << " ";
        }
        cout << endl;
    }

    cout << endl
         << endl;

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < perLine; j++)
        {
            cout << " " << character << " ";
        }
        cout << endl;
        perLine++;
    }

    cout << endl
         << endl;

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < height; j++)
        {
            if (i == 0 || i == height - 1 || j == 0 || j == height - 1)
            {
                cout << " " << character << " ";
            }
            else
            {
                cout << "   "; 
            }
        }
        cout << endl;
    }

    return 0;
}
