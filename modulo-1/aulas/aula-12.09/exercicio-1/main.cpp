#include <iostream>
#include <vector>
#include <unistd.h>

using namespace std;

struct Client
{
    string name;
    string cpf;
};

Client registerClient();
void showCustomers(const vector<Client> &);
void showMenu();
void showClient(const vector<Client> &);
void deleteClient(vector<Client> &);
void updateClient(vector<Client> &);

int main()
{
    vector<Client> customers;

    while (true)
    {
        int option;
        showMenu();
        cin >> option;

        switch (option)
        {
        case 1:
        {
            int amountCustomers;
            cout << "Insira a quantidade de clientes para cadastrar: ";
            cin >> amountCustomers;
            cout << endl;
            for (int i = 0; i < amountCustomers; i++)
            {
                Client client;
                client = registerClient();
                customers.push_back(client);

                cout << "Usuário " << client.name << " incluído no sistema!!" << endl
                     << endl;
                sleep(2);
            }
        }
        break;
        case 2:
        {
            showClient(customers);
        }
        break;
        case 3:
        {
            showCustomers(customers);
        }
        break;
        case 4:
        {

            deleteClient(customers);
            sleep(2);
            system("clear || cls");
        }
        break;
        case 5:
        {

            updateClient(customers);
            sleep(2);
            system("clear || cls");
        }
        break;
        case 6:
            break;
        default:
            cout << "Opção inválida!!!" << endl
                 << endl;
            sleep(3);
        }
    }

    return 0;
}

void showMenu()
{
    cout << "------ Menu ------" << endl
         << endl
         << "1 - Cadastrar Cliente" << endl
         << "2 - Mostrar Cliente" << endl
         << "3 - Mostrar Todos Clientes" << endl
         << "4 - Excluir Cliente" << endl
         << "5 - Atualizar Nome do Cliente" << endl
         << "6 - Sair" << endl
         << endl
         << "Selecione a opção: ";
}

Client registerClient()
{
    Client client;

    cout << "Nome do cliente: ";
    cin.ignore();
    getline(cin, client.name);

    cout << "CPF do cliente (XXX.XXX.XXX-XX): ";
    cin >> client.cpf;

    cout << endl;

    return client;
}

void showCustomers(const vector<Client> &customers)
{
    cout << "----- Todos os Clientes----- " << endl;
    for (const Client &client : customers)
    {
        cout << "Nome do Cliente: " << client.name << endl;
        cout << "CPF: " << client.cpf << endl
             << endl;
    }
}

void showClient(const vector<Client> &customers)
{
    string cpf;
    bool search = false;

    cout << endl;
    cout << "CPF do cliente: ";
    cin.ignore();
    getline(cin, cpf);
    cout << endl;

    for (const Client &client : customers)
    {
        if (client.cpf == cpf)
        {
            cout << "Nome do Cliente: " << client.name << endl;
            cout << "CPF: " << client.cpf << endl
                 << endl;
            search = true;
        }
    }
    if (search == false)
        cout << "Cliente não encontrado!!!" << endl
             << endl;
}

void deleteClient(vector<Client> &customers)
{
    string cpf;

    cout << endl;
    cout << "Digite o CPF do cliente: ";
    cin.ignore();
    getline(cin, cpf);

    for (auto it = customers.begin(); it != customers.end(); ++it)
    {
        if (it->cpf == cpf)
        {
            customers.erase(it);
            cout << "Cliente " << cpf << " excluído com sucesso!" << endl;
            return;
        }
    }
    cout << "Cliente " << cpf << " não encontrado." << endl;
}

void updateClient(vector<Client> &customers)
{
    string cpf, name;

    cout << endl;
    cout << "Digite o CPF do cliente: ";
    cin.ignore();
    getline(cin, cpf);

    for (auto it = customers.begin(); it != customers.end(); ++it)
    {
        if (it->cpf == cpf)
        {
            cout << "Digite o novo nome para o cliente: ";
            getline(cin, name);

            it->name = name;

            cout << "Nome do cliente alterado com sucesso!!!" << endl
                 << "Novo nome: " << name;
            return;
        }
        else
            cout << "Cliente " << cpf << " não encontrado." << endl;
    }
}
