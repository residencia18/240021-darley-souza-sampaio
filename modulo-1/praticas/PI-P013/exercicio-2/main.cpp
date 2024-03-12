#include <iostream>
#include <string>

using namespace std;

// Definição da estrutura empregado
struct Empregado
{
    string nome;
    string sobrenome;
    int anoNascimento;
    string RG;
    int anoAdmissao;
    double salario;
};

void Reajusta_dez_porcento(Empregado[], int);

int main()
{
    int quantidadeEmpregados;
    cout << "Informe a quantidade de empregados (até 50): ";
    cin >> quantidadeEmpregados;

    if (quantidadeEmpregados <= 0 || quantidadeEmpregados > 50)
    {
        cout << "Quantidade de empregados inválida." << endl;
        return 1;
    }

    Empregado empregados[50];

    for (int i = 0; i < quantidadeEmpregados; i++)
    {
        cout << "Dados do empregado " << i + 1 << ":" << endl;
        cout << "Nome: ";

        cin >> empregados[i].nome;
        cout << "Sobrenome: ";

        cin >> empregados[i].sobrenome;
        cout << "Ano de Nascimento: ";
        cin >> empregados[i].anoNascimento;

        cout << "RG: ";
        cin >> empregados[i].RG;
        cout << "Ano de Admissao: ";

        cin >> empregados[i].anoAdmissao;
        cout << "Salario: ";

        cin >> empregados[i].salario;
    }

    cout << "\nDados dos empregados: " << endl;
    for (int i = 0; i < quantidadeEmpregados; i++)
    {
        cout << "Nome: " << empregados[i].nome << " " << empregados[i].sobrenome << endl
             << "Ano de Nascimento: " << empregados[i].anoNascimento << endl
             << "RG: " << empregados[i].RG << endl
             << "Ano de Admissao: " << empregados[i].anoAdmissao << endl
             << "Salario: " << empregados[i].salario << endl
             << endl;
    }

    Reajusta_dez_porcento(empregados, quantidadeEmpregados);

    cout << "\nDados dos empregados após o reajuste de 10%:" << endl;
    for (int i = 0; i < quantidadeEmpregados; i++)
    {
        cout << "Nome: " << empregados[i].nome << " " << empregados[i].sobrenome << endl
             << "Ano de Nascimento: " << empregados[i].anoNascimento << endl
             << "RG: " << empregados[i].RG << endl
             << "Ano de Admissao: " << empregados[i].anoAdmissao << endl
             << "Salario: " << empregados[i].salario << endl;
    }

    return 0;
}

void Reajusta_dez_porcento(Empregado vetor[], int quantidade)
{
    for (int i = 0; i < quantidade; i++)
        vetor[i].salario *= 1.10;
}
