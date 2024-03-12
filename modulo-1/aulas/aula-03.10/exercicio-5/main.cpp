#include <iostream>
#include <vector>

using namespace std;

class Cidade
{
private:
    string nome;

public:
    string getNome() const
    {
        return nome;
    }

    void setNome(const string &novoNome)
    {
        nome = novoNome;
    }

    void listaDados()
    {
        cout << "Nome: " << nome << endl;
    }
};

class Estado
{
private:
    string nome;
    string UF;
    vector<Cidade> cidades;

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

    Cidade getCidade(string nome)
    {
        for (Cidade cidade : cidades)
        {
            if (cidade.getNome() == nome)
            {

                return cidade;
            }
        }
    }

    void setCidade(Cidade cidade)
    {
        cidades.push_back(cidade);
    }

    void listaDados()
    {
        cout << "Estado: " << nome << endl
             << "UF: " << UF << endl;

        cout << "-----Cidades do Estado-----" << endl;
        for (Cidade cidade : cidades)
        {
            cidade.listaDados();
        }
    }
};

int main()
{
    vector<Estado> estados;
    vector<Cidade> cidades;

    Cidade cidade, cidade2, cidade3;
    cidade.setNome("Ilhéus");
    cidades.push_back(cidade);
    cidade2.setNome("Salvador");
    cidades.push_back(cidade2);
    cidade3.setNome("Feira de Santana");
    cidades.push_back(cidade3);

    Estado estado;
    estado.setNome("Bahia");
    estado.setUF("BA");
    estado.setCidade(cidade);
    estado.setCidade(cidade2);
    estado.setCidade(cidade3);

    estados.push_back(estado);

    cout << "-----Estados-----" << endl;
    for (Estado estado : estados)
    {
        estado.listaDados();
    }

    cout << endl
         << endl;

    cout << "-----Cidades-----" << endl;
    for (Cidade cidade : cidades)
    {
        cidade.listaDados();
    }

    return 0;
}
