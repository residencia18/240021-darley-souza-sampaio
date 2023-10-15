#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

class Tarefa;
class GerenciadorDeTarefas;
class Persistencia;

class Tarefa
{
private:
    string descricao;
    bool concluida;

public:
    Tarefa(string &descricao);
    string getDescricao();
    bool estaConcluida();
    void marcarConcluida();
};

Tarefa::Tarefa(string &descricao) : descricao(descricao), concluida(false) {}

string Tarefa::getDescricao()
{
    return descricao;
}

bool Tarefa::estaConcluida()
{
    return concluida;
}

void Tarefa::marcarConcluida()
{
    concluida = true;
}

class GerenciadorDeTarefas
{
private:
    vector<Tarefa> tarefas;

public:
    void adicionarTarefa(string &descricao);
    void listarTarefasPendentes();
    void marcarTarefaComoConcluida(int indice);
    vector<Tarefa> &getTarefas();
};

void GerenciadorDeTarefas::adicionarTarefa(string &descricao)
{
    tarefas.push_back(Tarefa(descricao));
}

void GerenciadorDeTarefas::listarTarefasPendentes()
{
    for (size_t i = 0; i < tarefas.size(); ++i)
    {
        if (!tarefas[i].estaConcluida())
        {
            cout << i << ": " << tarefas[i].getDescricao() << endl;
        }
        else
        {
            cout << i << ": " << tarefas[i].getDescricao() << " (Concluída)" << endl;
        }
    }
    cout << endl;
}

void GerenciadorDeTarefas::marcarTarefaComoConcluida(int indice)
{
    if (indice >= 0 && indice < int(tarefas.size()))
    {
        tarefas[indice].marcarConcluida();
    }
}

vector<Tarefa> &GerenciadorDeTarefas::getTarefas()
{
    return tarefas;
}

class Persistencia
{
public:
    static void salvarTarefas(GerenciadorDeTarefas &gerenciador);
    static void carregarTarefas(GerenciadorDeTarefas &gerenciador);
};

void Persistencia::salvarTarefas(GerenciadorDeTarefas &gerenciador)
{
    ofstream arquivo("tarefas.txt");
    for (Tarefa &tarefa : gerenciador.getTarefas())
    {
        arquivo << tarefa.getDescricao() << " " << tarefa.estaConcluida() << endl;
    }
}

void Persistencia::carregarTarefas(GerenciadorDeTarefas &gerenciador)
{
    ifstream arquivo("tarefas.txt");
    string descricao;
    bool concluida;
    
    while (arquivo >> descricao >> concluida)
    {
        Tarefa tarefa(descricao);
        if (concluida)
        {
            tarefa.marcarConcluida();
        }
        gerenciador.adicionarTarefa(descricao);
        if (concluida)
        {
            gerenciador.marcarTarefaComoConcluida(gerenciador.getTarefas().size() - 1);
        }
    }
}

int main()
{
    int opcao;
    GerenciadorDeTarefas gerenciador;
    Persistencia::carregarTarefas(gerenciador);

    do
    {
        cout << "1. Adicionar nova tarefa" << endl
             << "2. Marcar tarefa como concluída" << endl
             << "3. Listar tarefas" << endl
             << "4. Sair" << endl
             << endl
             << "Escolha uma opção:";

        cin >> opcao;
        cout << endl;

        switch (opcao)
        {
        case 1:
        {
            string descricao;
            cout << "Digite a descrição da nova tarefa: ";
            cin.ignore();
            getline(cin, descricao);
            gerenciador.adicionarTarefa(descricao);
        }
        break;
        case 2:
        {
            cout << "Digite o índice da tarefa a ser marcada como concluída: ";
            int indice;
            cin >> indice;
            gerenciador.marcarTarefaComoConcluida(indice);
        }
        break;

        case 3:
        {
            cout << "Tarefas pendentes:" << endl;
            gerenciador.listarTarefasPendentes();
        }
        break;
        case 4:
            Persistencia::salvarTarefas(gerenciador);
            break;
        default:
            cout << "Opção Inválida!!!" << endl
                 << endl;
            break;
        }
    } while (opcao != 4);

    return 0;
}
