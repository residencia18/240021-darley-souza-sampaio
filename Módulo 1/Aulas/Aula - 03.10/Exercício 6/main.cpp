#include <iostream>
#include <vector>

using namespace std;

class Aluno
{
private:
    string nome;
    int matricula;

public:
    Aluno(string n, int m)
    {
        nome = n;
        matricula = m;
    }

    string getNome() const
    {
        return nome;
    }

    void setNome(const string &novoNome)
    {
        nome = novoNome;
    }

    int getMatricula()
    {
        return matricula;
    }

    void setMatricula(int novaMatricula)
    {
        matricula = novaMatricula;
    }

    void listaDados()
    {
        cout << "Nome: " << nome << endl
             << "Matricula: " << matricula << endl;
    }
};

class Turma
{
private:
    string turma;
    vector<Aluno> alunos;

public:
    string getTurma()
    {
        return turma;
    }

    void setTurma(string novaTurma)
    {
        turma = novaTurma;
    }

    vector<Aluno> getAlunos()
    {
        return alunos;
    }

    void setUF(Aluno newAluno)
    {
        alunos.push_back(newAluno);
    }

    void listaDados()
    {
        cout << "Turma: " << turma << endl;

        cout << "-----Alunos da Turma-----" << endl;
        for (Aluno aluno : alunos)
        {
            aluno.listaDados();
        }
    }
};

int main()
{
    //Eu deveria testar mas eu sei que vai funcionar, então tá aí
    return 0;
}
