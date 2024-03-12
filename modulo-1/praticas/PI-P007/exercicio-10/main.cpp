#include <iostream>

using namespace std;

int main(void) {
    int number = 0, fib1 = 0, fib2 = 1, temp = 0;

    cout << "Digite um número inteiro: ";
    cin >> number;

    cout << "Sequência de Fibonacci(" << number << "): ";
    
    while (fib1 <= number) {
        cout << fib1 << " ";

        temp = fib1 + fib2;
        
        fib1 = fib2;
        fib2 = temp;
    }

    cout << endl;
    return 0;
}
