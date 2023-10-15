#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <ctime>

using namespace std;

class Livro;
class Usuario;
class Biblioteca;

class Livro
{
private:
    string nome;
    string autor;
    int copias;

public:
    Livro() {}

    Livro(const string &nome, const string &autor, int copias)
        : nome(nome), autor(autor), copias(copias) {}

    const string &getNome() const { return nome; }
    const string &getAutor() const { return autor; }
    int getCopias() const { return copias; }

    void setNome(const string &nome) { this->nome = nome; }
    void setAutor(const string &autor) { this->autor = autor; }
    void setCopias(int copias) { this->copias = copias; }
};

class Usuario
{
private:
    string nome;
    string cpf;

public:
    Usuario() {}

    Usuario(const string &nome, const string &cpf)
        : nome(nome), cpf(cpf) {}

    const string &getNome() const { return nome; }
    const string &getCpf() const { return cpf; }

    void setNome(const string &nome) { this->nome = nome; }
    void setCpf(const string &cpf) { this->cpf = cpf; }
};

class Biblioteca
{
private:
    static vector<Livro> livros;
    static vector<Usuario> usuarios;
    static unordered_map<string, vector<pair<Livro, time_t>>> emprestimos;

public:
    static void adicionarLivro(const Livro &livro);
    static const vector<Livro> &getLivros();

    static void cadastrarUsuario(const Usuario &usuario);
    static const vector<Usuario> &getUsuarios();

    static void emprestarLivro(const Usuario &usuario, const Livro &livro);
    static void listarLivrosEmprestados(const Usuario &usuario);
};

vector<Livro> Biblioteca::livros;
vector<Usuario> Biblioteca::usuarios;
unordered_map<string, vector<pair<Livro, time_t>>> Biblioteca::emprestimos;

void Biblioteca::adicionarLivro(const Livro &livro)
{
    livros.push_back(livro);
}

const vector<Livro> &Biblioteca::getLivros()
{
    return livros;
}

void Biblioteca::cadastrarUsuario(const Usuario &usuario)
{
    usuarios.push_back(usuario);
}

const vector<Usuario> &Biblioteca::getUsuarios()
{
    return usuarios;
}

void Biblioteca::emprestarLivro(const Usuario &usuario, const Livro &livro)
{
    for (Livro &l : livros)
    {
        if (l.getNome() == livro.getNome() && l.getCopias() > 0)
        {
            time_t now = time(0);
            emprestimos[usuario.getCpf()].push_back({livro, now});
            l.setCopias(l.getCopias() - 1);
            cout << "Livro emprestado com sucesso." << endl
                 << endl;
            return;
        }
    }
    cout << "O livro não está disponível para empréstimo." << endl
         << endl;
}

void Biblioteca::listarLivrosEmprestados(const Usuario &usuario)
{
    cout << "Livros emprestados para: " << usuario.getNome() << endl;
    if (emprestimos.find(usuario.getCpf()) != emprestimos.end())
    {
        for (const auto &emprestimo : emprestimos.at(usuario.getCpf()))
        {
            const Livro &livro = emprestimo.first;
            time_t dataHora = emprestimo.second;

            struct tm *tm_info = localtime(&dataHora);
            char dataHoraStr[80];
            strftime(dataHoraStr, 80, "%d/%m/%Y %H:%M:%S", tm_info);

            cout << "Nome: " << livro.getNome() << endl
                 << "Autor: " << livro.getAutor() << endl
                 << "Data do Empréstimo: " << dataHoraStr << endl
                 << endl;
        }
    }
    else
    {
        cout << "Nenhum livro emprestado para " << usuario.getNome() << endl
             << endl;
    }
}

int main()
{
    Usuario usuarioEncontrado;
    bool encontrado = false;

    while (true)
    {
        cout << "1. Adicionar Livro" << endl
             << "2. Cadastrar Usuário" << endl
             << "3. Listar Livros" << endl
             << "4. Listar Livros Emprestados para um Usuário" << endl
             << "5. Emprestar Livro" << endl
             << "6. Sair" << endl
             << endl
             << "Selecione: ";

        int opcao;
        cin >> opcao;

        if (opcao == 1)
        {
            Livro livro;
            string nome, autor;
            int copias;

            cout << "Nome do Livro: ";
            cin >> nome;
            livro.setNome(nome);

            cout << "Autor do Livro: ";
            cin >> autor;
            livro.setAutor(autor);

            cout << "Número de cópias do Livro: ";
            cin >> copias;
            livro.setCopias(copias);

            Biblioteca::adicionarLivro(livro);
            cout << "Livro adicionado com sucesso!" << endl
                 << endl;
        }
        else if (opcao == 2)
        {
            Usuario usuario;
            string nome, cpf;

            cout << "Nome do Usuário: ";
            cin >> nome;
            usuario.setNome(nome);

            cout << "CPF do Usuário: ";
            cin >> cpf;
            usuario.setCpf(cpf);

            Biblioteca::cadastrarUsuario(usuario);
            cout << "Usuário cadastrado com sucesso!" << endl
                 << endl;
        }
        else if (opcao == 3)
        {
            const vector<Livro> &livros = Biblioteca::getLivros();
            cout << "----- Lista de Livros -----" << endl << endl;

            for (const Livro &livro : livros)
            {
                cout << "Nome: " << livro.getNome() << endl
                     << "Autor: " << livro.getAutor() << endl
                     << "Número de cópias: " << livro.getCopias() << endl
                     << endl;
            }
        }
        else if (opcao == 4)
        {
            string cpf;
            cout << "Digite o CPF do Usuário: ";
            cin >> cpf;

            for (const Usuario &usuario : Biblioteca::getUsuarios())
            {
                if (usuario.getCpf() == cpf)
                {
                    usuarioEncontrado = usuario;
                    encontrado = true;
                    break;
                }
            }

            if (encontrado)
            {
                Biblioteca::listarLivrosEmprestados(usuarioEncontrado);
            }
            else
            {
                cout << "Usuário não encontrado." << endl
                     << endl;
            }
        }
        else if (opcao == 5)
        {
            string cpf;
            cout << "Digite o CPF do Usuário: ";
            cin >> cpf;

            string nomeLivro;
            cout << "Digite o nome do livro que deseja emprestar: ";
            cin >> nomeLivro;

            Livro livroParaEmprestar;
            for (const Livro &livro : Biblioteca::getLivros())
            {
                if (livro.getNome() == nomeLivro)
                {
                    livroParaEmprestar = livro;
                    break;
                }
            }

            if (!livroParaEmprestar.getNome().empty())
            {
                Biblioteca::emprestarLivro(usuarioEncontrado, livroParaEmprestar);
            }
            else
            {
                cout << "Livro não encontrado na biblioteca." << endl
                     << endl;
            }
        }
        else if (opcao == 6)
        {
            break;
        }
        else
        {
            cout << "Opção inválida. Tente novamente." << endl
                 << endl;
        }
    }

    return 0;
}
