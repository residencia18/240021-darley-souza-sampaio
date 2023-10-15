#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Cliente;
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

public:
    // Getters
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

    // Setters
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

    void setDependentes(vector<Dependente> novosDependentes)
    {
        dependentes = novosDependentes;
    }

    void adicionaDependente(Dependente dependente)
    {
        dependentes.push_back(dependente);
    }

    void inserirCliente(vector<Cliente> &clientes)
    {
        char resposta;
        Cliente cliente;
        vector<Dependente> novosDependentes;

        cout << "Digite o nome do Cliente: ";
        cin >> nome;

        cout << "Digite a idade do cliente:";
        cin >> idade;

        cout << "Digite o CPF do cliente:";
        cin >> cpf;

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
                cin >> nomeDependente;

                cout << "Idade do dependente: ";
                cin >> idadeDependente;

                cout << "CPF do dependente: ";
                cin >> cpfDependente;

                dependente.setNome(nomeDependente);
                dependente.setIdade(idadeDependente);
                dependente.setCpf(cpfDependente);

                novosDependentes.push_back(dependente);
                quantidadeDependentes--;
            } while (quantidadeDependentes);

            cliente.setNome(nome);
            cliente.setIdade(idade);
            cliente.setCpf(cpf);
            cliente.setDependentes(novosDependentes);

            clientes.push_back(cliente);
        }
        else
        {
            cliente.setNome(nome);
            cliente.setIdade(idade);
            cliente.setCpf(cpf);

            clientes.push_back(cliente);
        }
    }

    void listarClientes(vector<Cliente> clientes)
    {
        cout << "----- Lista de Clientes -----" << endl
             << endl;
        for (Cliente &c : clientes)
        {
            cout << "Nome: " << c.getNome() << endl
                 << "Idade: " << c.getCpf() << endl
                 << "Cpf: " << c.getCpf() << endl;
            if (c.getDependentes().size() != NULL)
            {
                vector<Dependente> listaDependentes = c.getDependentes();
                cout << "Dependentes: " << endl;
                for (Dependente d : listaDependentes)
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
};

class Dependente
{
private:
    string nome, cpf;
    int idade, idDependente;

public:
    // Getters
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

    // Setters
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

class ClienteDependente
{
private:
    int idCliente, idDependente;

public:
    ClienteDependente(int clienteId, int dependenteId)
        : idCliente(clienteId), idDependente(dependenteId)
    {
    }

    // Getters
    int getIdCliente() const
    {
        return idCliente;
    }

    int getIdDependente() const
    {
        return idDependente;
    }
};

class Evento
{
private:
    int idEvento;
    string nome, local;
    vector<Evento> *eventos;

public:
    // Getters
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

    vector<Evento> *getEventos() const
    {
        return eventos;
    }

    // Setters
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

    void setEventos(vector<Evento> *novosEventos)
    {
        eventos = novosEventos;
    }
};

class Roteiro : public Evento
{
private:
    int duracao;
    string descricao;

public:
    // Getters
    int getDuracao() const
    {
        return duracao;
    }

    string getDescricao() const
    {
        return descricao;
    }

    // Setters
    void setDuracao(int novaDuracao)
    {
        duracao = novaDuracao;
    }

    void setDescricao(const string &novaDescricao)
    {
        descricao = novaDescricao;
    }

    void inserirRoteiro(vector<Evento> &eventos)
    {
        Roteiro roteiro;
        string nomeRoteiro, localRoteiro, descricaoRoteiro;
        int duracaoRoteiro;

        cout << "Nome do Roteiro";
        cin >> nomeRoteiro;

        cout << "Local do Roteiro";
        cin >> localRoteiro;

        cout << "Descricao Roteiro";
        cin >> descricaoRoteiro;

        cout << "Duração do Roteiro";
        cin >> duracaoRoteiro;

        roteiro.setNome(nomeRoteiro);
        roteiro.setLocal(localRoteiro);
        roteiro.setDescricao(descricaoRoteiro);
        roteiro.setDuracao(duracaoRoteiro);

        eventos.push_back(roteiro);
    }
};

class Pernoite : public Evento
{
private:
    int duracao;
    string descricao;

public:
    // Getters
    int getDuracao() const
    {
        return duracao;
    }

    string getDescricao() const
    {
        return descricao;
    }

    // Setters
    void setDuracao(int novaDuracao)
    {
        duracao = novaDuracao;
    }

    void setDescricao(const string &novaDescricao)
    {
        descricao = novaDescricao;
    }

    void inserirPernoite(vector<Evento> &eventos)
    {
        Pernoite pernoite;
        string nomePernoite, localPernoite, descricaoPernoite;
        int duracaoPernoite;

        cout << "Nome Pernoite";
        cin >> nomePernoite;

        cout << "Local Pernoite";
        cin >> localPernoite;

        cout << "Descricao Pernoite";
        cin >> descricaoPernoite;

        cout << "Duração Pernoite";
        cin >> duracaoPernoite;

        pernoite.setNome(nomePernoite);
        pernoite.setLocal(localPernoite);
        pernoite.setDescricao(descricaoPernoite);
        pernoite.setDuracao(duracaoPernoite);

        eventos.push_back(pernoite);
    }
};

class Deslocamento : public Evento
{
private:
    int duracao;
    string descricao;

public:
    // Getters
    int getDuracao() const
    {
        return duracao;
    }

    string getDescricao() const
    {
        return descricao;
    }

    // Setters
    void setDuracao(int novaDuracao)
    {
        duracao = novaDuracao;
    }

    void setDescricao(const string &novaDescricao)
    {
        descricao = novaDescricao;
    }

    void inserirDeslocamento(vector<Evento> &eventos)
    {
        Pernoite deslocamento;
        string nomeDeslocamento, localDeslocamento, descricaoDeslocamento;
        int duracaoDeslocamento;

        cout << "Nome Deslocamento";
        cin >> nomeDeslocamento;

        cout << "Local Deslocamento";
        cin >> localDeslocamento;

        cout << "Descricao Deslocamento";
        cin >> descricaoDeslocamento;

        cout << "Duração Deslocamento";
        cin >> duracaoDeslocamento;

        deslocamento.setNome(nomeDeslocamento);
        deslocamento.setLocal(localDeslocamento);
        deslocamento.setDescricao(descricaoDeslocamento);
        deslocamento.setDuracao(duracaoDeslocamento);

        eventos.push_back(deslocamento);
    }
};

class Persistencia
{
    
};

int main(void)
{
    return 0;
}
