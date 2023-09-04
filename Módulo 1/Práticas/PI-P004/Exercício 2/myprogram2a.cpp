#include <iostream>

using namespace std;

int main(void)
{
    char caracteres[10] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    for (int i = 0; i <= 9; i++)
    {
        cout << "ASCII Caractere " << caracteres[i] << " = " << (int)caracteres[i] << endl;
    }
    
    return 0;
}

