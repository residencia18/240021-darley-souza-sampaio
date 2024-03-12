#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Student
{
    string name;
    float grade1, grade2, media;
};

void bubbleSort(vector<Student> &students)
{
    int n = students.size();
    bool swapped;

    do
    {
        swapped = false;
        for (int i = 0; i < n - 1; i++)
        {
            if (students[i].name > students[i + 1].name)
            {
                swap(students[i], students[i + 1]);
                swapped = true;
            }
        }
    } while (swapped);
}

int main()
{
    vector<Student> students;
    char choice;

    do
    {
        cout << "\nMenu de Opções:" << endl
             << "1. Inserir Alunos" << endl
             << "2. Deletar Aluno" << endl
             << "3. Modificar Nota Aluno" << endl
             << "4. Mostrar Alunos" << endl
             << "5. Sair" << endl
             << "Escolha uma opção: ";
        cin >> choice;

        switch (choice)
        {
        case '1':
        {
            char answer;
            do
            {
                Student student;
                int numStudents;

                cout << "Informe a quantidade de alunos a cadastrar: ";
                cin >> numStudents;

                cout << endl
                     << endl;

                for (int i = 0; i < numStudents; i++)
                {
                    cout << "Nome do aluno: ";
                    cin >> student.name;

                    cout << "Nota 1: ";
                    cin >> student.grade1;

                    cout << "Nota 2: ";
                    cin >> student.grade2;

                    student.media = (student.grade1 + student.grade2) / 2;

                    students.push_back(student);
                    cout << "Aluno inserido com sucesso!" << endl
                         << endl;
                }
                bubbleSort(students);

                cout << "Deseja incluir mais alunos (s/n)?: ";
                cin >> answer;
            } while (answer == 's');
            break;
        }
        case '2':
        {
            string name;
            cout << "Digite o nome do aluno que deseja excluir: ";
            cin >> name;

            auto it = find_if(students.begin(), students.end(), [&](const Student &student)
                              { return student.name == name; });

            if (it != students.end())
            {
                students.erase(it);
                cout << "Aluno excluído com sucesso!" << endl;
            }
            else
            {
                cout << "Aluno não encontrado." << endl;
            }

            break;
        }
        case '3':
        {
            string name;
            int grade;
            bool found = false;

            cout << "Digite o nome do aluno: ";
            cin >> name;

            for (Student &student : students)
            {
                if (student.name == name)
                {
                    cout << "Digite a nota a ser alterada (1 ou 2): ";
                    cin >> grade;

                    if (grade == 1)
                    {
                        cout << "Digite a nova nota 1 para " << student.name << ": ";
                        cin >> student.grade1;
                        found = true;
                        cout << "Nota 1 alterada com sucesso!" << endl;
                        student.media = (student.grade1 + student.grade2) / 2;
                    }
                    else if (grade == 2)
                    {
                        cout << "Digite a nova nota 2 para " << student.name << ": ";
                        cin >> student.grade2;
                        found = true;
                        cout << "Nota 2 alterada com sucesso!" << endl;
                        student.media = (student.grade1 + student.grade2) / 2;
                    }
                    else
                    {
                        cout << "Opção de nota inválida. Use 1 ou 2." << endl;
                    }
                }
            }

            if (!found)
            {
                cout << "Aluno não encontrado." << endl;
            }

            break;
        }
        case '4':
        {

            for (const Student &student : students)
            {
                cout << "Aluno: " << student.name << endl
                     << "Nota 1: " << student.grade1 << endl
                     << "Nota 2: " << student.grade2 << endl
                     << "Media: " << student.media << endl
                     << endl;
            }
            break;
        }
        case '5':
            cout << "Saindo do programa..." << endl;
            break;
        default:
            cout << "Opção inválida. Por favor, escolha uma opção válida." << endl;
            break;
        }
    } while (choice != '5');

    return 0;
}
