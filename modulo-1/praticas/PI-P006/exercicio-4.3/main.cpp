#include <iostream>
#include <iomanip>

using namespace std;

int main(void)
{
     double x, y;
     int quadrant;

     cout << "Digite o valor de x e y: ";
     cin >> x >> y;

     quadrant = (x > 0) ? ((y > 0) ? 1 : 4) : ((y > 0) ? 2 : 3);

     (quadrant == 1) ? cout << "O ponto está no primeiro quadrante." << endl:
     (quadrant == 2) ? cout << "O ponto está no segundo quadrante." << endl:
     (quadrant == 3) ? cout << "O ponto está no terceiro quadrante." << endl:
     (quadrant == 4) ? cout << "O ponto está no quarto quadrante." << endl:
     cout << "O ponto está no segundo quadrante." << endl;
     
     return 0;
}