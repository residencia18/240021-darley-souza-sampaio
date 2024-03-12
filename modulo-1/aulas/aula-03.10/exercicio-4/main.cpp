#include <iostream>
#include <vector>

using namespace std;

class Estado
{
private:
    string nome;
    string UF;

public:
    string getNome()
    {
        return nome;
    }

    void setNome(string novoNome)
    {
        nome = novoNome;
    }

    string getUF() const
    {
        return UF;
    }

    void setUF(string novoUF)
    {
        UF = novoUF;
    }

    void listaDados()
    {
        cout << "Estado: " << nome << endl
             << "UF: " << UF << endl
             << endl;
    }
};

class Cidade
{
private:
    string nome;
    Estado *estado;

public:
    string getNome() const
    {
        return nome;
    }

    void setNome(const string &novoNome)
    {
        nome = novoNome;
    }

    Estado *getEstado() const
    {
        return estado;
    }

    void setEstado(Estado *novoEstado)
    {
        estado = novoEstado;
    }

    void listaDados()
    {
        cout << "Cidade: " << nome << endl
             << "Estado: " << estado->getNome() << endl
             << "UF: " << estado->getUF() << endl
             << endl;
    }
};

int main()
{
    vector<Estado> estados;
    vector<Cidade> cidades;

    Estado estado;
    estado.setNome("Bahia");
    estado.setUF("BA");

    estados.push_back(estado);

    Cidade cidade;
    cidade.setNome("Ilhéus");
    cidade.setEstado(&estado);

    cidades.push_back(cidade);

    for (Estado estado : estados)
    {
        estado.listaDados();
    }

    cout << endl
         << endl;

    for (Cidade cidade : cidades)
    {
        cidade.listaDados();
    }

    return 0;
}
