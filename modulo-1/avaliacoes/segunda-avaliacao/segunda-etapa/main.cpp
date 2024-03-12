#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Data
{
    int dia, mes, ano;

public:
    static int compara(Data d1, Data d2)
    {
        if (d1.ano < d2.ano || (d1.ano == d2.ano && d1.mes < d2.mes) || (d1.ano == d2.ano && d1.mes == d2.mes && d1.dia < d2.dia))
            return -1;
        else if (d1.ano == d2.ano && d1.mes == d2.mes && d1.dia == d2.dia)
            return 0;
        else
            return 1;
    }

    Data(int _dia, int _mes, int _ano)
    {
        dia = _dia;
        mes = _mes;
        ano = _ano;
    }

    string toString()
    {
        string ret = "";
        ret.append(to_string(dia));
        ret.append("/");
        ret.append(to_string(mes));
        ret.append("/");
        ret.append(to_string(ano));
        return ret;
    }
};

class Lista
{
public:
    virtual void entradaDeDados() = 0;
    virtual void mostraMediana() = 0;
    virtual void mostraMenor() = 0;
    virtual void mostraMaior() = 0;
    virtual void listarEmOrdem() = 0;
    virtual void mostrarNElementos(int nElementos) = 0;
};

class ListaNomes : public Lista
{
    vector<string> lista;

public:
    void entradaDeDados()
    {
        int numNomes;
        cout << "Quantidade de nomes para inserir na lista: ";
        cin >> numNomes;
        cin.ignore();

        for (int i = 0; i < numNomes; i++)
        {
            string nome;
            cout << i + 1 << "°"
                 << " nome: ";
            getline(cin, nome);
            lista.push_back(nome);
        }
    }

    void mostraMediana()
    {
        cout << "Dados Nomes: " << endl;
        sort(lista.begin(), lista.end());
        int tamanho = lista.size();
        if (tamanho % 2 == 0)
        {
            string mediana1 = lista[tamanho / 2 - 1];
            string mediana2 = lista[tamanho / 2];
            cout << "Mediana: " << mediana1 << " ou " << mediana2 << endl;
        }
        else
        {
            string mediana = lista[tamanho / 2];
            cout << "Mediana: " << mediana << endl;
        }
    }

    void mostraMenor()
    {
        sort(lista.begin(), lista.end());
        cout << "Menor: " << lista[0] << endl;
    }

    void mostraMaior()
    {
        sort(lista.begin(), lista.end());
        cout << "Maior: " << lista[lista.size() - 1] << endl;
    }

    void listarEmOrdem() override
    {
        cout << "Listagem em ordem alfabética: " << endl;
        sort(lista.begin(), lista.end());
        for (const string &nome : lista)
        {
            cout << nome << endl;
        }
    }

    void mostrarNElementos(int nElementos) override
    {
        cout << "Primeiros " << nElementos << " nomes: " << endl;
        for (int i = 0; i < min(nElementos, static_cast<int>(lista.size())); i++)
        {
            cout << lista[i] << endl;
        }
    }
};

class ListaDatas : public Lista
{
    vector<Data> lista;

public:
    void entradaDeDados()
    {
        int numElementos;
        cout << "Quantidade de datas para inserir na lista: ";
        cin >> numElementos;

        for (int i = 0; i < numElementos; i++)
        {
            int dia, mes, ano;
            cout << i + 1 << "°"
                 << " data (DD MM YYYY): ";
            cin >> dia >> mes >> ano;
            Data data(dia, mes, ano);
            lista.push_back(data);
        }
    }

    void mostraMediana()
    {
        cout << "Dados Datas: " << endl;
        sort(lista.begin(), lista.end(), [](const Data &a, const Data &b)
             { return Data::compara(a, b) < 0; });

        int tamanho = lista.size();

        if (tamanho % 2 == 0)
        {
            Data mediana1 = lista[tamanho / 2 - 1];
            Data mediana2 = lista[tamanho / 2];
            cout << "Mediana: " << mediana1.toString() << " ou " << mediana2.toString() << endl;
        }
        else
        {
            Data mediana = lista[tamanho / 2];
            cout << "Mediana: " << mediana.toString() << endl;
        }
    }

    void mostraMenor()
    {
        sort(lista.begin(), lista.end(), [](const Data &a, const Data &b)
             { return Data::compara(a, b) < 0; });
        cout << "Menor: " << lista[0].toString() << endl;
    }

    void mostraMaior()
    {
        sort(lista.begin(), lista.end(), [](const Data &a, const Data &b)
             { return Data::compara(a, b) < 0; });
        int tamanho = lista.size();
        cout << "Maior: " << lista[tamanho - 1].toString() << endl;
    }

    void listarEmOrdem() override
    {
        cout << "Listagem em ordem cronológica: " << endl;
        sort(lista.begin(), lista.end(), [](const Data &a, const Data &b)
             { return Data::compara(a, b) < 0; });

        for (Data &data : lista)
        {
            cout << data.toString() << endl;
        }
    }

    void mostrarNElementos(int nElementos) override
    {
        cout << "Primeiras " << nElementos << " datas: " << endl;
        for (int i = 0; i < min(nElementos, static_cast<int>(lista.size())); i++)
        {
            cout << lista[i].toString() << endl;
        }
    }
};

class ListaSalarios : public Lista
{
    vector<float> lista;

public:
    void entradaDeDados()
    {
        int numElementos;
        cout << "Quantidade de salários para inserir na lista: ";
        cin >> numElementos;

        for (int i = 0; i < numElementos; i++)
        {
            float salario;
            cout << i + 1 << "°"
                 << " salário: ";
            cin >> salario;
            lista.push_back(salario);
        }
    }

    void mostraMediana()
    {
        cout << "Dados Salários: " << endl;
        sort(lista.begin(), lista.end());
        int tamanho = lista.size();
        if (tamanho % 2 == 0)
        {
            float mediana1 = lista[tamanho / 2 - 1];
            float mediana2 = lista[tamanho / 2];
            cout << "Mediana: " << (mediana1 + mediana2) / 2 << endl;
        }
        else
        {
            float mediana = lista[tamanho / 2];
            cout << "Mediana: " << mediana << endl;
        }
    }

    void mostraMenor()
    {
        cout << "Menor: " << *min_element(lista.begin(), lista.end()) << endl;
    }

    void mostraMaior()
    {
        cout << "Maior: " << *max_element(lista.begin(), lista.end()) << endl;
    }

    void listarEmOrdem() override
    {
        cout << "Listagem em ordem crescente: " << endl;
        sort(lista.begin(), lista.end());
        for (const float &salario : lista)
        {
            cout << salario << endl;
        }
    }

    void mostrarNElementos(int nElementos) override
    {
        cout << "Primeiros " << nElementos << " salários: " << endl;
        for (int i = 0; i < min(nElementos, static_cast<int>(lista.size())); i++)
        {
            cout << lista[i] << endl;
        }
    }
};

class ListaIdades : public Lista
{
    vector<int> lista;

public:
    void entradaDeDados()
    {
        int numElementos;
        cout << "Quantidade de idades para inserir na lista: ";
        cin >> numElementos;

        for (int i = 0; i < numElementos; i++)
        {
            int idade;
            cout << i + 1 << "°"
                 << " idade: ";
            cin >> idade;
            lista.push_back(idade);
        }
    }

    void mostraMediana()
    {
        cout << "Dados Idades: " << endl;
        sort(lista.begin(), lista.end());
        int tamanho = lista.size();
        if (tamanho % 2 == 0)
        {
            int mediana1 = lista[tamanho / 2 - 1];
            int mediana2 = lista[tamanho / 2];
            cout << "Mediana: " << (mediana1 + mediana2) / 2 << endl;
        }
        else
        {
            int mediana = lista[tamanho / 2];
            cout << "Mediana: " << mediana << endl;
        }
    }

    void mostraMenor()
    {
        cout << "Menor: " << *min_element(lista.begin(), lista.end()) << endl;
    }

    void mostraMaior()
    {
        cout << "Maior: " << *max_element(lista.begin(), lista.end()) << endl;
    }

    void listarEmOrdem() override
    {
        cout << "Listagem em ordem crescente: " << endl;
        sort(lista.begin(), lista.end());
        for (const int &idade : lista)
        {
            cout << idade << endl;
        }
    }

    void mostrarNElementos(int nElementos) override
    {
        cout << "Primeiras " << nElementos << " idades: " << endl;
        for (int i = 0; i < min(nElementos, static_cast<int>(lista.size())); i++)
        {
            cout << lista[i] << endl;
        }
    }
};

int main()
{
    int nElementos;
    vector<Lista *> listaDeListas;

    ListaNomes listaNomes;
    listaNomes.entradaDeDados();
    listaDeListas.push_back(&listaNomes);

    ListaDatas listaDatas;
    listaDatas.entradaDeDados();
    listaDeListas.push_back(&listaDatas);

    ListaSalarios listaSalarios;
    listaSalarios.entradaDeDados();
    listaDeListas.push_back(&listaSalarios);

    ListaIdades listaIdades;
    listaIdades.entradaDeDados();
    listaDeListas.push_back(&listaIdades);

    cout << endl << "Quantos primeiros elementos de cada lista quer mostrar?: ";
    cin >> nElementos;

    cout << endl;


    for (Lista *l : listaDeListas)
    {
        l->mostraMediana();
        l->mostraMenor();
        l->mostraMaior();
        l->listarEmOrdem();
        l->mostrarNElementos(nElementos);
        cout << endl;
    }
}
