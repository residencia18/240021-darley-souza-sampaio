#include <iostream>
#include <cmath>

using namespace std;

int digitCount(int number) {
    int count = 0;
    while (number != 0) {
        number /= 10;
        count++;
    }
    return count;
}

bool amstrongNumber(int number) {
    int originalNumber = number, sum = 0, digitNumbers = digitCount(number);

    while (number != 0) {
        int digito = number % 10;
        sum += pow(digito, digitNumbers);
        number /= 10;
    }

    return sum == originalNumber;
}

int main(void) {
    int number;

    cout << "Digite um número inteiro: ";
    cin >> number;

    (amstrongNumber(number)) ?
        cout << "É um número de Armstrong." << endl:
        cout << "Não é um número de Armstrong." << endl;

    return 0;
}
