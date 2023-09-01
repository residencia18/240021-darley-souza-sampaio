#include <iostream>
#include <math.h>
#include <iomanip>

using namespace std;

int main(void)
{
     // a
     double x = 0, y = 0, z;

     // b
     cout << "Digite dois valores flutuantes: ";
     scanf("%lf %lf", &x, &y);

     // c
     z = x + y;
     printf("Soma: %.3lf\n", z);

     // d
     z = (x + y) / 2;
     printf("Média: %.3lf\n", z);

     // e
     z = x * y;
     printf("Produto: %.3lf\n", z);

     // f
     (x > y) ?  z = x : z = y;
     printf("Maior: %.3lf\n", z);

     // f
     (x < y) ?  z = x : z = y;
     printf("Menor: %.3lf\n", z);

     return 0;
}