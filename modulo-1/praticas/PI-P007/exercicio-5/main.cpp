#include <iostream>

using namespace std;

int main() {
    int number = 0, random = 0;

    random = rand() % 100 + 1;

    do{
        cout << "Digite um número inteiro: ";
        cin >> number;

        (number > random) ? cout << " Alto " << endl :
        (number < random) ? cout << " Baixo " << endl :
        cout << " Correto " << endl;


    }while(number != random);

    return 0;
}
