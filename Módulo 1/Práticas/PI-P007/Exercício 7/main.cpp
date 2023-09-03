#include <iostream>

using namespace std;

int main(void)
{
    int height;

    cout << "Digite a altura: ";
    cin >> height;

    char character = 'A';
    int perLine = 1;

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < perLine; j++)
        {
            cout << character;
            character++;
            if (character == 'Z')
                character = 'A';
        }
        cout << endl;
        perLine++;
    }

    return 0;
}
