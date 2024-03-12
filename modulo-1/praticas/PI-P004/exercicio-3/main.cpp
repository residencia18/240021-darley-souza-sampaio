#include <iostream>
#include <limits.h>
#include <limits>

using namespace std;

int main(void)
{
    // a
    cout << "INT MAX = " << INT_MAX << endl;
    cout << "INT MIN = " << INT_MIN << endl
         << endl;

    // b
    unsigned long int uli = ULONG_MAX;
    cout << "uli = " << uli << endl;

    cout << "Limite unsigned long int: " << numeric_limits<unsigned long int>::min() << " ... "
         << numeric_limits<unsigned long int>::max() << endl;

    // c
    long int li = uli;
    cout << "li = uli: " << li << endl;
    uli = li;
    cout << "Uli = li: " << uli << endl;

    // d
    li = LONG_MAX;
    cout << "Long Int Max: " << li << endl;

    cout << "Limite long int: " << numeric_limits<long int>::min() << " ... "
         << numeric_limits<long int>::max() << endl;

    //e
    

    return 0;
}