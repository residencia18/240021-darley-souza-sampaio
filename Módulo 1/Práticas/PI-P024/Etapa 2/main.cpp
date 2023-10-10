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
};

int main(void)
{
    return 0;
}
