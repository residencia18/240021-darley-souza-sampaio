#include <iostream>
#include <vector>

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
            }
            cout << endl;
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
        }
        break;
        case 5:
            break;
        default:
            cout << "Opção inválida!!!" << endl
                 << endl;
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
         << "5 - Sair" << endl
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

    return client;
}

void showCustomers(const vector<Client> &customers)
{
    cout << endl;
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
        }
    }
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
