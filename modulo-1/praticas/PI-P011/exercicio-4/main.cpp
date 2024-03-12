#include <iostream>

using namespace std;

void sumSub(int &, int &);

int main(void) {
    int value1 = 25, value2 = 5;

    sumSub(value1, value2);

    cout << "Soma: " << value1 << endl << "Subtração: " << value2 << endl;

    return 0;
}

void sumSub(int &value1, int &value2){
    int X = value1, Y = value2;
    value1 = X + Y;
    value2 = X - Y;
}