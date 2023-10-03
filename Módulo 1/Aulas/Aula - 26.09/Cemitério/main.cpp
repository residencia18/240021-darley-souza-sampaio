#include <iostream>
#include <string.h>
#include <vector>
#include <fstream>
#include <sstream>

using namespace std;

void limpaTela()
{
    system("clear || cls");
};

class Data
{
    int dia, mes, ano;

public:
    string geraString()
    {
        string dataStr = to_string(dia);
        dataStr.append("/");
        dataStr.append(to_string(mes));
        dataStr.append("/");
        dataStr.append(to_string(ano));
        return dataStr;
    }
    void setDia(int _dia)
    {
        dia = _dia;
    }

    void setMes(int _mes)
    {
        mes = _mes;
    }

    void setAno(int _ano)
    {
        ano = _ano;
    }
};

class paciente
{
    int numMausoleu;
    string nome;
    Data dt_morte;

public:
    static paciente leDados()
    {
        paciente novoPaciente;
        cout << "Dados de um novo paciente" << endl;
        cout << "Digite o nome: " << endl;
        string nome;

        getline(cin, nome);
        novoPaciente.setNome(nome);
        cout << "Digite a data: " << endl;
        int dia, mes, ano;
        cin >> dia >> mes >> ano;

        Data dtMorte;
        dtMorte.setDia(dia);
        dtMorte.setMes(mes);
        dtMorte.setAno(ano);

        novoPaciente.setDtMorte(dtMorte);

        return novoPaciente;
    }

    void listaDados()
    {
        cout << "Paciente: " << getNome() << endl;
        cout << "Falecido em " << getDtMorte().geraString() << endl;
    }

    void setNome(string _nome)
    {
        nome = _nome;
    }

    string getNome()
    {
        return nome;
    }

    void setDtMorte(Data _dataMorte)
    {
        dt_morte = _dataMorte;
    }

    Data getDtMorte()
    {
        return dt_morte;
    }

    void setNumMausoleu(int _numero)
    {
        numMausoleu = _numero;
    }

    int getNumMausoleu()
    {
        return numMausoleu;
    }
};

class mausoleu
{
    static int contador;
    int id;
    string localizacao;
    vector<paciente> pacientes;

public:
    static int getContador()
    {
        return contador;
    }

    static void incContador()
    {
        contador++;
    }

    mausoleu()
    {
        id = mausoleu::getContador();
        mausoleu::incContador();
    }

    static mausoleu leNovo()
    {
        mausoleu novoMausoleu;
        cout << "Digite a localicacao do novo mausoleu: ";
        string loc;
        getline(cin, loc);
        novoMausoleu.setLocalizacao(loc);
        return novoMausoleu;
    }

    void listaDados()
    {
        cout << endl;
        cout << "Numero do mausoleu: " << getId() << endl;
        cout << getLocalizacao() << endl
             << endl;
        cout << "Lista de pacientes deste mausoleu: " << endl;
        for (paciente p : pacientes)
        {
            p.listaDados();
        }

        cout << endl << endl;
    }

    void setLocalizacao(string _localizacao)
    {
        localizacao = _localizacao;
    }

    string getLocalizacao()
    {
        return localizacao;
    }

    int getId()
    {
        return id;
    }

    void setId(int _id)
    {
        id = _id;
    }

    void recepciona(paciente _paciente)
    {
        pacientes.push_back(_paciente);
    }

    void listaPacientes()
    {
        for (paciente p : pacientes)
        {
            cout << p.getNome() << endl;
        }
    }
};

vector<mausoleu> mausoleus;
vector<paciente> pacientes;

class persistencia
{
public:
    static void salvaMausoleu(mausoleu novo)
    {
        ofstream outMausoleus;
        outMausoleus.open("mausoleus.txt", ios_base::app);
        if (outMausoleus.is_open())
        {
            outMausoleus << novo.getId() << endl;
            outMausoleus << novo.getLocalizacao() << endl;
            outMausoleus.close();
        }
    }

    static void recuperaMausoleus()
    {
        ifstream inMausoleus;
        inMausoleus.open("mausoleus.txt", ios_base::in);
        if (inMausoleus.is_open())
        {
            int id;
            string idstr, loc;
            while (inMausoleus.eof() == false)
            {
                getline(inMausoleus, idstr);
                getline(inMausoleus, loc);
                if (idstr.length() > 0)
                {
                    id = stoi(idstr);
                    mausoleu novoMausoleu;
                    novoMausoleu.setId(id);
                    novoMausoleu.setLocalizacao(loc);
                    mausoleus.push_back(novoMausoleu);
                }
            }
            inMausoleus.close();
        }
    }

    static void salvaPacientes(paciente novo)
    {
        ofstream outPacientes;
        outPacientes.open("pacientes.txt", ios_base::app);
        if (outPacientes.is_open())
        {
            outPacientes << novo.getNumMausoleu() << endl;
            outPacientes << novo.getNome() << endl;
            outPacientes << novo.getDtMorte().geraString() << endl;
            outPacientes.close();
        }
    }

    static void recuperaPacientes()
    {
        ifstream inPacientes;
        inPacientes.open("pacientes.txt", ios_base::in);
        if (inPacientes.is_open())
        {
            int _numMausoleu;
            string nome;
            string dataStr;
            while (inPacientes >> _numMausoleu)
            {
                inPacientes.ignore();
                getline(inPacientes, nome);
                getline(inPacientes, dataStr);

                Data dt_morte;

                stringstream dateStream(dataStr);
                char delimiter;
                int dia, mes, ano;

                if (dateStream >> dia >> delimiter >> mes >> delimiter >> ano)
                {
                    dt_morte.setDia(dia);
                    dt_morte.setMes(mes);
                    dt_morte.setAno(ano);
                }

                if (nome.length() > 0)
                {
                    paciente novoPaciente;
                    novoPaciente.setNumMausoleu(_numMausoleu);
                    novoPaciente.setNome(nome);
                    novoPaciente.setDtMorte(dt_morte);
                    pacientes.push_back(novoPaciente);

                    for (auto it = mausoleus.begin(); it != mausoleus.end(); it++)
                    {
                        if (it->getId() == _numMausoleu)
                            it->recepciona(novoPaciente);
                    }
                }
            }
            inPacientes.close();
        }
    }
};

int mausoleu::contador = 1;

int main()
{
    limpaTela();
    persistencia::recuperaMausoleus();
    persistencia::recuperaPacientes();
    int op;
    do
    {
        cout << "Opcoes" << endl;
        cout << "1. Incluir Mausoleu" << endl;
        cout << "2. Listar Mausoleus" << endl;
        cout << "3. Recepcionar paciente" << endl;
        cout << "0. Sair" << endl;
        cout << "Digite opcao: ";
        cin >> op;
        cin.ignore();
        if (op == 1)
        {
            limpaTela();
            cout << "------Cadastro de Mausoleu-------" << endl;
            mausoleu novo = mausoleu::leNovo();
            mausoleus.push_back(novo);
            persistencia::salvaMausoleu(novo);
            cout << "----Mausoleu criado!-----" << endl
                 << endl;
        };

        if (op == 2)
        {
            limpaTela();
            cout << "----Lista de Mausoleus---" << endl;
            for (mausoleu m : mausoleus)
            {
                m.listaDados();
            }
            cout << "--------FIM DA LISTA-----" << endl
                 << endl;
        };
        if (op == 3)
        {
            int _id;
            paciente novoPac = paciente::leDados();
            cout << "----Paciente criado!-----" << endl;
            cout << "Digite ID do mausoleu: ";
            cin >> _id;

            for (auto it = mausoleus.begin(); it != mausoleus.end(); it++)
            {
                if (it->getId() == _id)
                {
                    novoPac.setNumMausoleu(_id);
                    it->recepciona(novoPac);
                }
            }
            persistencia::salvaPacientes(novoPac);
        };
    } while (op != 0);
    return 0;
}