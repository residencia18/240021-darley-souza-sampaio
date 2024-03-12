#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <memory>

using namespace std;

class Dependente;
class Evento;
class Pacote;
class Roteiro;
class Deslocamento;
class Pernoite;
class Persistencia;

class Cliente
{
private:
    string nome, cpf;
    int idade, idCliente;
    vector<Dependente> dependentes;
    vector<int> pacotesAlugados;

public:
    // Getters e Setters
    string getNome() const
    {
        return nome;
    }

    string getCpf() const
    {
        return cpf;
    }

    int getIdade() const
    {
        return idade;
    }

    int getIdCliente() const
    {
        return idCliente;
    }

    vector<Dependente> getDependentes() const
    {
        return dependentes;
    }

    void setNome(const string &novoNome)
    {
        nome = novoNome;
    }

    void setCpf(const string &novoCpf)
    {
        cpf = novoCpf;
    }

    void setIdade(int novaIdade)
    {
        idade = novaIdade;
    }

    void setIdCliente(int novoIdCliente)
    {
        idCliente = novoIdCliente;
    }

    void setDependentes(const vector<Dependente> &novosDependentes)
    {
        dependentes = novosDependentes;
    }

    void adicionaDependente(const Dependente &dependente)
    {
        dependentes.push_back(dependente);
    }

    void inserirCliente(vector<Cliente> &clientes);
    static bool clienteExiste(const vector<Cliente> &clientes, int idCliente);
    static void listarClientes(const vector<Cliente> &clientes);
    void alugarPacote(int idPacote);
    vector<int> getPacotesAlugados() const;
};

class Dependente
{
private:
    string nome, cpf;
    int idade, idDependente;

public:
    // Getters e Setters
    string getNome() const
    {
        return nome;
    }

    string getCpf() const
    {
        return cpf;
    }

    int getIdade() const
    {
        return idade;
    }

    int getIdDependente() const
    {
        return idDependente;
    }

    void setNome(const string &novoNome)
    {
        nome = novoNome;
    }

    void setCpf(const string &novoCpf)
    {
        cpf = novoCpf;
    }

    void setIdade(int novaIdade)
    {
        idade = novaIdade;
    }

    void setIdDependente(int novoIdDependente)
    {
        idDependente = novoIdDependente;
    }
};

class Evento
{
protected:
    int idEvento;
    string nome, local;

public:
    // Getters e Setters
    int getIdEvento() const
    {
        return idEvento;
    }

    string getNome() const
    {
        return nome;
    }

    string getLocal() const
    {
        return local;
    }

    void setIdEvento(int novoIdEvento)
    {
        idEvento = novoIdEvento;
    }

    void setNome(const string &novoNome)
    {
        nome = novoNome;
    }

    void setLocal(const string &novoLocal)
    {
        local = novoLocal;
    }
};

class Roteiro : public Evento
{
private:
    int duracao;
    string descricao;

public:
    int getDuracao() const
    {
        return duracao;
    }

    string getDescricao() const
    {
        return descricao;
    }

    void setDuracao(int novaDuracao)
    {
        duracao = novaDuracao;
    }

    void setDescricao(const string &novaDescricao)
    {
        descricao = novaDescricao;
    }

    static Roteiro inserirRoteiro();
};

class Pernoite : public Evento
{
private:
    int duracao;
    string descricao;

public:
    int getDuracao() const
    {
        return duracao;
    }

    string getDescricao() const
    {
        return descricao;
    }

    void setDuracao(int novaDuracao)
    {
        duracao = novaDuracao;
    }

    void setDescricao(const string &novaDescricao)
    {
        descricao = novaDescricao;
    }

    static Pernoite inserirPernoite();
};

class Deslocamento : public Evento
{
private:
    int duracao;
    string descricao;

public:
    int getDuracao() const
    {
        return duracao;
    }

    string getDescricao() const
    {
        return descricao;
    }

    void setDuracao(int novaDuracao)
    {
        duracao = novaDuracao;
    }

    void setDescricao(const string &novaDescricao)
    {
        descricao = novaDescricao;
    }

    static Deslocamento inserirDeslocamento();
};

class Pacote
{
private:
    int idPacote;
    string nome;
    vector<shared_ptr<Evento>> eventos;
    vector<int> clientesQueAlugaram;

public:
    Pacote(int id, const string &nome) : idPacote(id), nome(nome) {}

    int getIdPacote() const
    {
        return idPacote;
    }

    string getNome() const
    {
        return nome;
    }

    void adicionarEvento(shared_ptr<Evento> evento)
    {
        eventos.push_back(evento);
    }

    void listarEventos() const;
    void clienteAlugou(int idCliente);
    vector<int> getClientesQueAlugaram() const;
    static bool pacoteExiste(const vector<Pacote> &pacotes, int idPacote);
    static void listarPacote(const vector<Pacote> &pacotes, int idPacote);
};

class Persistencia
{
public:
    void salvarDados(const string &nomeArquivo, const vector<string> &dados)
    {
        ofstream arquivo(nomeArquivo);

        if (arquivo.is_open())
        {
            for (const string &linha : dados)
            {
                arquivo << linha << endl;
            }

            arquivo.close();
            cout << "Dados salvos com sucesso." << endl
                 << endl;
        }
        else
        {
            cout << "Não foi possível abrir o arquivo para salvar." << endl
                 << endl;
        }
    }

    vector<string> carregarDados(const string &nomeArquivo)
    {
        vector<string> dadosCarregados;
        string linha;
        ifstream arquivo(nomeArquivo);

        if (arquivo.is_open())
        {
            while (getline(arquivo, linha))
            {
                dadosCarregados.push_back(linha);
            }

            arquivo.close();
            cout << "Dados carregados com sucesso." << endl
                 << endl;
        }
        else
        {
            cout << "Não foi possível abrir o arquivo para carregar." << endl
                 << endl;
        }

        return dadosCarregados;
    }
};

Roteiro Roteiro::inserirRoteiro()
{
    Roteiro roteiro;
    cout << "Nome do Roteiro: ";
    cin.ignore();
    getline(cin, roteiro.nome);

    cout << "Local do Roteiro: ";
    cin.ignore();
    getline(cin, roteiro.local);

    cout << "Descrição do Roteiro: ";
    cin.ignore();
    getline(cin, roteiro.descricao);

    cout << "Duração do Roteiro (minutos): ";
    cin >> roteiro.duracao;

    return roteiro;
}

Pernoite Pernoite::inserirPernoite()
{
    Pernoite pernoite;
    cout << "Nome da Pernoite: ";
    cin.ignore();
    getline(cin, pernoite.nome);

    cout << "Local da Pernoite: ";
    cin.ignore();
    getline(cin, pernoite.local);

    cout << "Descrição da Pernoite: ";
    cin.ignore();
    getline(cin, pernoite.descricao);

    cout << "Duração da Pernoite (minutos): ";
    cin >> pernoite.duracao;

    return pernoite;
}

Deslocamento Deslocamento::inserirDeslocamento()
{
    Deslocamento deslocamento;
    cout << "Nome do Deslocamento: ";
    cin.ignore();
    getline(cin, deslocamento.nome);

    cout << "Local do Deslocamento: ";
    cin.ignore();
    getline(cin, deslocamento.local);

    cout << "Descrição do Deslocamento: ";
    cin.ignore();
    getline(cin, deslocamento.descricao);

    cout << "Duração do Deslocamento (minutos): ";
    cin >> deslocamento.duracao;

    return deslocamento;
}

void Pacote::listarEventos() const
{
    cout << "Eventos no Pacote " << nome << " (ID: " << idPacote << "):" << endl;

    for (const shared_ptr<Evento> &evento : eventos)
    {
        cout << "Nome: " << evento->getNome() << endl;
        cout << "Local: " << evento->getLocal() << endl;
        cout << "ID do Evento: " << evento->getIdEvento() << endl;
        cout << endl
             << endl;
    }
}

bool Pacote::pacoteExiste(const vector<Pacote> &pacotes, int idPacote)
{
    for (const Pacote &pacote : pacotes)
    {
        if (pacote.getIdPacote() == idPacote)
        {
            return true;
        }
    }
    return false;
}

void Pacote::listarPacote(const vector<Pacote> &pacotes, int idPacote)
{
    for (const Pacote &pacote : pacotes)
    {
        if (pacote.getIdPacote() == idPacote)
        {
            cout << "Pacote: " << pacote.getNome() << " (ID: " << pacote.getIdPacote() << ")" << endl;
            pacote.listarEventos();
            cout << endl;
            return;
        }
    }
    cout << "Pacote não encontrado." << endl
         << endl;
}

void Cliente::alugarPacote(int idPacote)
{
    pacotesAlugados.push_back(idPacote);
}

vector<int> Cliente::getPacotesAlugados() const
{
    return pacotesAlugados;
}

void Cliente::inserirCliente(vector<Cliente> &clientes)
{
    char resposta;
    Cliente cliente;
    vector<Dependente> novosDependentes;

    cout << "Digite o nome do Cliente: ";
    cin.ignore();
    getline(cin, cliente.nome);

    cout << "Digite a idade do cliente:";
    cin >> cliente.idade;

    cout << "Digite o CPF do cliente:";
    cin.ignore();
    getline(cin, cliente.cpf);

    cout << "O cliente possui algum dependente? (s/n): ";
    cin >> resposta;

    if (resposta == 's')
    {
        int quantidadeDependentes;
        cout << "Quantos dependentes: ";
        cin >> quantidadeDependentes;

        do
        {
            Dependente dependente;
            string nomeDependente, cpfDependente;
            int idadeDependente;

            cout << "Nome do dependente: ";
            cin.ignore();
            getline(cin, nomeDependente);

            cout << "Idade do dependente: ";
            cin >> idadeDependente;

            cout << "CPF do dependente: ";
            cin.ignore();
            getline(cin, cpfDependente);

            dependente.setNome(nomeDependente);
            dependente.setIdade(idadeDependente);
            dependente.setCpf(cpfDependente);

            novosDependentes.push_back(dependente);
            quantidadeDependentes--;
        } while (quantidadeDependentes > 0);

        cliente.setDependentes(novosDependentes);
    }

    cliente.setIdCliente(clientes.size() + 1);
    clientes.push_back(cliente);
}

void Cliente::listarClientes(const vector<Cliente> &clientes)
{
    cout << "----- Lista de Clientes -----" << endl
         << endl;

    for (const Cliente &c : clientes)
    {
        cout << "ID: " << c.getIdCliente() << endl
             << "Nome: " << c.getNome() << endl
             << "Idade: " << c.getIdade() << endl
             << "Cpf: " << c.getCpf() << endl
             << endl;

        if (!c.getDependentes().empty())
        {
            vector<Dependente> listaDependentes = c.getDependentes();
            cout << "Dependentes:" << endl;

            for (const Dependente &d : listaDependentes)
            {
                cout << "Nome: " << d.getNome() << endl
                     << "Idade: " << d.getIdade() << endl
                     << "CPF: " << d.getCpf() << endl
                     << endl;
            }
        }
        else
        {
            cout << endl;
        }
    }
}

bool Cliente::clienteExiste(const vector<Cliente> &clientes, int idCliente)
{
    for (const Cliente &cliente : clientes)
    {
        if (cliente.getIdCliente() == idCliente)
        {
            return true;
        }
    }
    return false;
}

int proximoIdPacote = 1;

int main()
{
    vector<Cliente> clientes;
    vector<Pacote> pacotes;

    while (true)
    {
        cout << "=== Menu ===" << endl
             << endl
             << "1. Cadastrar Cliente" << endl
             << "2. Listar Clientes" << endl
             << "3. Criar Pacote de Viagem" << endl
             << "4. Listar Pacotes de Viagem" << endl
             << "5. Alugar Pacote de Viagem" << endl
             << "6. Listar Pacotes Alugados por Cliente" << endl
             << "7. Sair" << endl
             << "Selecione: ";

        int escolha;
        cin >> escolha;

        switch (escolha)
        {
        case 1:
        {
            Cliente cliente;
            cliente.inserirCliente(clientes);
            cout << "Cliente cadastrado com sucesso!" << endl << endl;
            break;
        }

        case 2:
        {
            if (clientes.empty())
            {
                cout << "Nenhum cliente cadastrado ainda." << endl << endl;
            }
            else
            {
                Cliente::listarClientes(clientes);
            }
            break;
        }

        case 3:
        {
            int idPacote;
            cout << "ID do Pacote: ";
            cin >> idPacote;

            if (Pacote::pacoteExiste(pacotes, idPacote))
            {
                cout << "Um pacote com o ID " << idPacote << " já existe." << endl << endl;
            }
            else
            {
                string nomePacote;
                cout << "Nome do Pacote: ";
                cin.ignore();
                getline(cin, nomePacote);

                Pacote pacote(idPacote, nomePacote);

                while (true)
                {
                    cout << "1. Roteiro" << endl
                         << "2. Pernoite" << endl
                         << "3. Deslocamento" << endl
                         << "4. Sair" << endl
                         << "Selecione: ";

                    int escolhaEvento;
                    cin >> escolhaEvento;

                    if (escolhaEvento == 4)
                    {
                        break;
                    }

                    shared_ptr<Evento> evento;

                    switch (escolhaEvento)
                    {
                    case 1:
                        evento = make_shared<Roteiro>(Roteiro::inserirRoteiro());
                        break;
                    case 2:
                        evento = make_shared<Pernoite>(Pernoite::inserirPernoite());
                        break;
                    case 3:
                        evento = make_shared<Deslocamento>(Deslocamento::inserirDeslocamento());
                        break;
                    default:
                        cout << "Opção inválida." << endl << endl;
                        break;
                    }

                    pacote.adicionarEvento(evento);
                    cout << "Evento adicionado com sucesso." << endl << endl;
                }

                pacotes.push_back(pacote);
                cout << "Pacote criado com sucesso." << endl << endl;
            }

            break;
        }

        case 4:
        {
            int idPacote;
            cout << "ID do Pacote: ";
            cin >> idPacote;
            Pacote::listarPacote(pacotes, idPacote);
            break;
        }

        case 5:
        {
            if (clientes.empty())
            {
                cout << "Nenhum cliente cadastrado ainda." << endl << endl;
            }
            else if (pacotes.empty())
            {
                cout << "Nenhum pacote cadastrado ainda." << endl << endl;
            }
            else
            {
                int idCliente;
                cout << "ID do Cliente: ";
                cin >> idCliente;

                if (!Cliente::clienteExiste(clientes, idCliente))
                {
                    cout << "Cliente não encontrado." << endl << endl;
                }
                else
                {
                    int idPacote;
                    cout << "ID do Pacote: ";
                    cin >> idPacote;

                    if (!Pacote::pacoteExiste(pacotes, idPacote))
                    {
                        cout << "Pacote não encontrado." << endl << endl;
                    }
                    else
                    {
                        for (Cliente &c : clientes)
                        {
                            if (c.getIdCliente() == idCliente)
                            {
                                c.alugarPacote(idPacote);
                                cout << "Pacote alugado com sucesso!" << endl << endl;
                            }
                        }
                    }
                }
            }
            break;
        }

        case 6:
        {
            if (clientes.empty())
            {
                cout << "Nenhum cliente cadastrado ainda." << endl << endl;
            }
            else if (pacotes.empty())
            {
                cout << "Nenhum pacote cadastrado ainda." << endl << endl;
            }
            else
            {
                int idCliente;
                cout << "ID do Cliente: ";
                cin >> idCliente;

                if (!Cliente::clienteExiste(clientes, idCliente))
                {
                    cout << "Cliente não encontrado." << endl << endl;
                }
                else
                {
                    for (const Cliente &c : clientes)
                    {
                        if (c.getIdCliente() == idCliente)
                        {
                            vector<int> pacotesAlugados = c.getPacotesAlugados();

                            if (pacotesAlugados.empty())
                            {
                                cout << "Este cliente não alugou nenhum pacote ainda." << endl << endl;
                            }
                            else
                            {
                                cout << "Pacotes alugados pelo cliente:" << endl << endl;

                                for (int idPacote : pacotesAlugados)
                                {
                                    Pacote::listarPacote(pacotes, idPacote);
                                }
                            }
                        }
                    }
                }
            }
            break;
        }

        case 7:
        {
            return 0;
        }

        default:
            cout << "Opção inválida. Por favor, selecione uma opção válida." << endl << endl;
        }
    }

    return 0;
}
