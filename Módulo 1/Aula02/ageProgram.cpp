#include <iostream>
#include <string>

using namespace std;

int main(void)
{
    string nome;
    int anoNascimento;
    std::cout << "Digite seu nome:";

    cin >> nome;
    std::cout << "Bom dia" << nome << ". Tenha um bom curso";

    cout << nome << ", voce nasceu em que ano?: ";
    cin >> anoNascimento;

    cout << "Considerando que estamos em 2023, ou você tem " << 2023 - anoNascimento << " anos, ou está próximo de fazer" << std::endl;
    return 0;
}
