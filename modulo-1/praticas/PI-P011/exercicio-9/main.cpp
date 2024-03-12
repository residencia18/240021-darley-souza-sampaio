#include <iostream>

using namespace std;

float calc_serie(int);

int main(void)
{
    float sum = calc_serie(3);
    cout << sum << endl;

    return 0;
}

float calc_serie(int N)
{
    float result = 1 / N;
    if (N == 1)
        return result;
    else if (N >= 2)
    {
        for (int i = 2; i <= N; i++)
        {
            result = result + (i / (N - (i - 1)));
        }
    }

    return result;
}