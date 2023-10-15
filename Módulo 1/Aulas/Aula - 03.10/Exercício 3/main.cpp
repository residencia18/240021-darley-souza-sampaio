#include <iostream>
#include <cmath>
#include <string>

using namespace std;

class ItemBiblioteca
{
protected:
    string titulo;
    string autor;
    int copiasDisponiveis;

public:
    ItemBiblioteca(string t, string a, int c)
    {
        titulo = t;
        autor = a;
        copiasDisponiveis = c;
    }
};

class Livro : protected ItemBiblioteca
{
private:
    int numeroPaginas;

public:
    Livro(string t, string a, int c, int n) : ItemBiblioteca(t, a, c)
    {
        this->numeroPaginas = n;
    }

    void listaDados()
    {
        cout << "---- Livro ----" << endl
             << "Nome: " << titulo << endl
             << "Autor: " << autor << endl
             << "Páginas: " << numeroPaginas << endl
             << endl;
    }
};

class DVD : protected ItemBiblioteca
{
private:
    string duracao;

public:
    DVD(string t, string a, int c, string d) : ItemBiblioteca(t, a, c)
    {
        this->duracao = d;
    }

    void listaDados()
    {
        cout << "---- DVD ----" << endl
             << "Nome: " << titulo << endl
             << "Autor: " << autor << endl
             << "Cópias disponíveis: " << copiasDisponiveis << endl
             << "Duração: " << duracao << endl
             << endl;
    }
};

int main()
{

    return 0;
}
