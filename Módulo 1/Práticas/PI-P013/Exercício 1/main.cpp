#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <iomanip>

using namespace std;

struct Passageiro
{
    int numAssento;
    string data;
    string cpf;
    string nome;
    int idade;
};

struct Viagem
{
    string data;
    string horario;
    vector<Passageiro> passageiros;
};

int main(void)
{
    vector<Viagem> viagens(10);
    double valorPassagem = 80.0;

    while (true)
    {
        cout << "Menu: " << endl
             << "1. Vender passagem" << endl
             << "2. Total arrecadado para uma viagem" << endl
             << "3. Total arrecadado em um determinado mês" << endl
             << "4. Nome do passageiro de uma poltrona em uma viagem" << endl
             << "5. Horário de viagem mais rentável" << endl
             << "6. Média de idade dos passageiros" << endl
             << "0. Sair" << endl
             << endl;

        int opcao;

        cout << "Selecione a opção: ";
        cin >> opcao;
        cout << endl;

        switch (opcao)
        {
        case 0:
            return 0;
        case 1:
        {
            int viagemNumero, poltrona;
            string dataHora, cpf, nome;
            int idade;

            cout << "Número da viagem (1 a 10): ";
            cin >> viagemNumero;
            cout << "Número da poltrona (1 a 40): ";
            cin >> poltrona;
            cout << "Data e hora da viagem (DD/MM/YYYY-HH:MM): ";
            cin >> dataHora;

            size_t pos = dataHora.find("-");
            string data = dataHora.substr(0, pos);
            string hora = dataHora.substr(pos + 1);

            cout << "CPF do passageiro: ";
            cin >> cpf;
            cout << "Nome do passageiro: ";
            cin >> nome;
            cout << "Idade do passageiro: ";
            cin >> idade;

            if (viagemNumero >= 1 && viagemNumero <= 10 && poltrona >= 1 && poltrona <= 40)
            {
                viagens[viagemNumero - 1].data = data;
                viagens[viagemNumero - 1].horario = hora;
                viagens[viagemNumero - 1].passageiros.push_back({poltrona, dataHora, cpf, nome, idade});
                cout << "Passagem vendida com sucesso!" << endl
                     << endl;
            }
            else
            {
                cout << "Viagem ou poltrona inválida!" << endl
                     << endl;
            }
            break;
        }
        case 2:
        {
            int viagemNumero;
            cout << "Número da viagem (1 a 10): ";
            cin >> viagemNumero;

            if (viagemNumero >= 1 && viagemNumero <= 10)
            {
                double totalArrecadado = viagens[viagemNumero - 1].passageiros.size() * valorPassagem;
                cout << "Total arrecadado para a viagem " << viagemNumero << ": R$" << totalArrecadado << endl
                     << endl;
            }
            else
            {
                cout << "Viagem inválida!" << endl
                     << endl;
            }
            break;
        }
        case 3:
        {
            string mes;
            cout << "Digite o mês (exemplo: 01 para Janeiro): ";
            cin >> mes;

            double totalArrecadado = 0.0;
            for (const auto &viagem : viagens)
            {
                if (viagem.data.find(mes) != string::npos)
                {
                    totalArrecadado += viagem.passageiros.size() * valorPassagem;
                }
            }

            cout << "Total arrecadado em " << mes << ": R$" << totalArrecadado << endl
                 << endl;
            break;
        }
        case 4:
        {
            int viagemNumero, poltrona;
            cout << "Número da viagem (1 a 10): ";
            cin >> viagemNumero;
            cout << "Número da poltrona (1 a 40): ";
            cin >> poltrona;

            if (viagemNumero >= 1 && viagemNumero <= 10 && poltrona >= 1 && poltrona <= 40)
            {
                for (const auto &passageiro : viagens[viagemNumero - 1].passageiros)
                {
                    if (passageiro.numAssento == poltrona)
                    {
                        cout << "Nome do passageiro da poltrona " << poltrona << " na viagem " << viagemNumero << ": " << passageiro.nome << endl
                             << endl;
                        break;
                    }
                }
            }
            else
            {
                cout << "Viagem ou poltrona inválida!" << endl
                     << endl;
            }
            break;
        }
        case 5:
        {
            map<string, double> horarioArrecadacao;

            for (const auto &viagem : viagens)
            {
                double arrecadacao = viagem.passageiros.size() * valorPassagem;
                string chave = viagem.data + "-" + viagem.horario;
                horarioArrecadacao[chave] += arrecadacao;
            }

            string horarioMaisRentavel;
            double maxArrecadacao = 0.0;

            for (const auto &kv : horarioArrecadacao)
            {
                if (kv.second > maxArrecadacao)
                {
                    maxArrecadacao = kv.second;
                    horarioMaisRentavel = kv.first;
                }
            }

            size_t pos = horarioMaisRentavel.find("-");
            string dataMaisRentavel = horarioMaisRentavel.substr(0, pos);
            string horarioMaisRentavelFinal = horarioMaisRentavel.substr(pos + 1);

            cout << "Data e Hora das Vendas: " << dataMaisRentavel << " " << horarioMaisRentavelFinal << " (arrecadação: R$" << maxArrecadacao << ")" << endl
                 << endl;
            break;
        }
        case 6:
        {
            int totalIdades = 0;
            int totalPassageiros = 0;

            for (const auto &viagem : viagens)
            {
                for (const auto &passageiro : viagem.passageiros)
                {
                    totalIdades += passageiro.idade;
                    totalPassageiros++;
                }
            }

            double mediaIdade = static_cast<double>(totalIdades) / totalPassageiros;
            cout << "Média de idade dos passageiros: " << fixed << setprecision(2) << mediaIdade << " anos" << endl;
            break;
        }
        default:
            cout << "Opção inválida. Tente novamente." << endl;
        }
    }

    return 0;
}
