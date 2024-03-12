#include <iostream>

using namespace std;

int main() {
    bool aux;

    for (int number = 2; number <= 100; ++number) {
        aux = true;
        
        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0) {
                aux = false;
                break;
            }
        }
        if (aux) {
            cout << number << " ";
        }
    }

    cout << endl;

    return 0;
}
