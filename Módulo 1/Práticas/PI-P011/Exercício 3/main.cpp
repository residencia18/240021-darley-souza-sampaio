#include <iostream>

using namespace std;

void sortValues(float &, float &, float &, float &);

int main(void) {
    float value1 = 4.3, value2 = 25.2, value3 = 12.3, value4 = 1.25;

    cout << "Valores originais: " << value1 << " " << value2 << " " << value3 << " " << value4 << endl;

    sortValues(value1, value2, value3, value4);

    cout << "Valores ordenados: " << value1 << " " << value2 << " " << value3 << " " << value4 << endl;

    return 0;
}


void sortValues(float &a, float &b, float &c, float &d) {
    if (a > b) swap(a, b);
    if (a > c) swap(a, c);
    if (a > d) swap(a, d);
    if (b > c) swap(b, c);
    if (b > d) swap(b, d);
    if (c > d) swap(c, d);
}
