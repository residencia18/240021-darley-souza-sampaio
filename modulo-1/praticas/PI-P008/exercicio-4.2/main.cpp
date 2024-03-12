#include <iostream>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <string>

using namespace std;

int main(void)
{
    srand(time(0));
    vector<string> strings;

    // a
    for (int i = 0; i < 10; i++)
    {
        string randomString;
        for (int j = 0; j < 10; j++)
        {
            char randomChar = 'a' + rand() % ('z' - 'a');
            randomString += randomChar;
        }
        randomString[0] = toupper(randomString[0]);
        sort(randomString.begin(), randomString.end());
        strings.push_back(randomString);
    }

    for (const string &str : strings)
        cout << str << endl;

    cout << endl;

    return 0;
}