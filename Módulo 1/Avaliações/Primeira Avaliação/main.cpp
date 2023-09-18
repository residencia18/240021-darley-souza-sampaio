#include <iostream>
#include <vector>
#include <unistd.h>

using namespace std;

struct Date
{
    int day, month, year;
};

struct Client
{
    string name;
    string CPF;
    Date birthDate;
    string CNH;
};

struct Car
{
    string renavan;
    string carPlate;
    Date withdrawalDate, deliveryDate;
    string concessionaire;
};

Client registerClient();
bool cpfVerification(string);
void catchDate(Date &);
void showDate(Date);
void showCustomers(const vector<Client> &);
void showMenu();
void showClient(const vector<Client> &);
void deleteClient(vector<Client> &);
void updateClient(vector<Client> &);

int main()
{
    vector<Client> customers;
    int option;
    do
    {
        showMenu();

        cin >> option;

        switch (option)
        {
        case 1:
        {
            Client client;
            client = registerClient();
            customers.push_back(client);

            cout << "Usuário " << client.name << " incluído no sistema!!" << endl
                 << endl;
            sleep(2);
        }
        break;
        case 2:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
            {
                deleteClient(customers);
                sleep(2);
                system("clear || cls");
            }
        }
        break;
        case 3:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
                updateClient(customers);
        }
        break;
        case 4:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
                showCustomers(customers);
        }
        break;
        case 5:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
                showClient(customers);
        }
        break;
        case 6:
            cout << "Programa Finalizado!!!" << endl;
            break;
        default:
            cout << "Opção inválida!!!" << endl
                 << endl;
            sleep(3);
        }
    } while (option != 6);

    return 0;
}

void catchDate(Date &date)
{
    cin >> date.day >> date.month >> date.year;
}

void showDate(Date date)
{
    cout << date.day << "/" << date.month << "/" << date.year << endl;
}
bool cpfVerification(string CPF)
{
    if (CPF[3] == '.' && CPF[7] == '.' && CPF[11] == '-')
        return true;
    else
        return false;
}

void showMenu()
{
    cout << " ----- Menu Cliente ----- " << endl
         << endl
         << "1 - Incluir Cliente" << endl
         << "2 - Exlcuir Cliente" << endl
         << "3 - Alterar Dados Cliente" << endl
         << "4 - Listar Clientes" << endl
         << "5 - Localizar Cliente" << endl
         << "6 - Sair" << endl
         << endl;

         cout << "Selecione a opção: ";
}

Client registerClient()
{
    Client client;

    cout << "Nome do cliente: ";
    cin.ignore();
    getline(cin, client.name);

    do
    {
        bool cpfVerify;
        string cpf;

        cout << "CPF do cliente (XXX.XXX.XXX-XX): ";
        cin >> cpf;

        cpfVerify = cpfVerification(cpf);

        if (cpfVerify)
        {
            client.CPF = cpf;
            break;
        }
        else
        {
            cout << "CPF incorreto!! Digite novamente!!" << endl;
        }

    } while (true);

    cout
        << "Data de Nascimento (DD MM YYYY): ";
    catchDate(client.birthDate);

    cout << "CNH do cliente (XXXXXXXXXXX): ";
    cin >> client.CNH;

    cout << endl;

    return client;
}

void showCustomers(const vector<Client> &customers)
{
    cout << "----- Todos os Clientes----- " << endl;
    for (const Client &client : customers)
    {
        cout << "Nome do Cliente: " << client.name << endl;
        cout << "CPF: " << client.CPF << endl;
        showDate(client.birthDate);
        cout << "CNH: " << client.CNH << endl
             << endl;
    }
}

void showClient(const vector<Client> &customers)
{
    string CPF;

    cout << endl;
    cout << "CPF do cliente: ";
    cin.ignore();
    getline(cin, CPF);
    cout << endl;

    for (const Client &client : customers)
    {
        if (client.CPF == CPF)
        {
            cout << "Nome do Cliente: " << client.name << endl;
            cout << "CPF: " << client.CPF << endl;
            showDate(client.birthDate);
            cout << "CNH: " << client.CNH << endl
                 << endl;
        }
        else
        {
            cout << "Cliente não encontrado!!!";
        }
    }
}

void deleteClient(vector<Client> &customers)
{
    string CPF;
    bool cpfVerify;

    cout << endl;
    cout << "Digite o CPF do cliente (XXX.XXX.XXX-XX): ";
    cin.ignore();
    getline(cin, CPF);
    cpfVerify = cpfVerification(CPF);

    if (cpfVerify)
    {

        for (auto it = customers.begin(); it != customers.end(); ++it)
        {
            if (it->CPF == CPF)
            {
                customers.erase(it);
                cout << "Cliente " << CPF << " excluído com sucesso!" << endl;
                return;
            }
        }
        cout << "Cliente " << CPF << " não encontrado." << endl;
    }
    else
    {
        cout << "CPF inválido!!!" << endl;
    }
    sleep(2);
}

void updateClient(vector<Client> &customers)
{
    string CPF;
    bool cpfVerify;

    cout << endl;
    cout << "Digite o CPF do cliente (XXX.XXX.XXX-XX): ";
    cin.ignore();
    getline(cin, CPF);
    cpfVerify = cpfVerification(CPF);

    if (cpfVerify)
    {
        int option;

        for (auto it = customers.begin(); it != customers.end(); ++it)
        {
            if (it->CPF == CPF)
            {
                cout << "Nome do Cliente: " << it->name << endl;
                cout << "CPF: " << it->CPF << endl;
                showDate(it->birthDate);
                cout << "CNH: " << it->CNH
                     << endl
                     << endl;

                cout << "1 - Alterar Nome" << endl
                     << "2 - Alterar CPF" << endl
                     << "3 - Alterar Data de Nascimento" << endl
                     << "4 - Alterar CNH" << endl
                     << "5 - Sair" << endl
                     << endl;
                cout << "Selecione a opção: ";
                cin >> option;

                switch (option)
                {
                case 1:
                {
                    string name;
                    cout << "Digite o novo nome para o cliente: ";

                    cin.ignore();
                    getline(cin, name);

                    it->name = name;

                    cout << "Nome do cliente alterado com sucesso!!!" << endl
                         << "Novo nome: " << name;
                }
                break;
                case 2:
                {

                    string CPF;
                    cout << "Digite o novo CPF para o cliente: ";
                    cin.ignore();
                    getline(cin, CPF);

                    it->CPF = CPF;

                    cout << "CPF do cliente alterado com sucesso!!!" << endl
                         << "Novo CPF: " << CPF;
                }
                break;
                case 3:
                {

                    cout << "Digite a nova data para o cliente: ";
                    catchDate(it->birthDate);

                    cout << "Data de Nascimento do cliente alterada com sucesso!!!" << endl
                         << "Nova data: " << endl;
                    showDate(it->birthDate);
                }
                break;
                case 4:
                {
                    string CNH;
                    cout << "Digite o novo CNH para o cliente: ";
                    cin.ignore();
                    getline(cin, CNH);

                    it->CNH = CNH;

                    cout << "CNH do cliente alterado com sucesso!!!" << endl
                         << "Novo CNH: " << CNH;
                }
                break;

                default:
                    cout << "Nenhum dado alterado!!";
                    break;
                }
            }
            else
            {
                cout << "Cliente não encontrado!!!";
            }
        }
    }
    else
    {
        cout << "CPF Inválido!!!" << endl;
    }
    sleep(2);
}
