#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>

using namespace std;

int main(void)
{
    srand(time(0));
    vector<char> stringA, stringB;

    // a
    for (int i = 0; i < 10; i++)
    {
        char randomCharA = 'a' + rand() % ('z' - 'a');
        stringA.push_back(randomCharA);
        char randomCharB = 'a' + rand() % ('z' - 'a');
        stringB.push_back(randomCharB);
    }

    // b
    stringA[0] = toupper(stringA[0]);
    stringB[0] = toupper(stringB[0]);

    // c
    sort(stringA.begin(), stringA.end());
    sort(stringB.begin(), stringB.end());

    for (char c : stringA)
        cout << c;

    cout << endl;

    for (char c : stringB)
        cout << c;
    
    cout << endl;

    return 0;
}