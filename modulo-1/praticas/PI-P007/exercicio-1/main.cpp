#include <iostream>

using namespace std;

int main(void)
{
    for(int i = 0; i <= 100; i++){
        ((i%3 == 0) && (i%5 != 0)) ? cout << "Fizz" << endl:
        ((i%5 == 0) && (i%3 != 0)) ? cout << "Buzz" << endl:
        ((i%3 == 0) && (i%5 == 0)) ? cout << "FizzBuzz" << endl:
        cout << i << endl;
    }

    return 0;
}