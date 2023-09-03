#include <iostream>
#include <iomanip>
#include <vector>

using namespace std;

struct Student {
    int matricula;
    double nota1, nota2, nota3;
};

int main() {
    vector<Student> students;
    char continuar;

    do {
        Student student;

        cout << "Digite a matrícula do aluno (apenas números): ";
        cin >> student.matricula;
        
        cout << "Digite a primeira nota: ";
        cin >> student.nota1;

        cout << "Digite a segunda nota: ";
        cin >> student.nota2;

        cout << "Digite a terceira nota: ";
        cin >> student.nota3;

        students.push_back(student);

        cout << "\nDeseja cadastrar outro aluno? (S/N): ";
        cin >> continuar;
    } while (continuar == 'S' || continuar == 's');

    cout << "MATRICULA      NOTA1   NOTA2   NOTA3   MEDIA" << endl;
    cout << "=============================================" << endl;
    for (const Student & student : students) {
        double media = (student.nota1 + student.nota2 + student.nota3) / 3.0;
        cout << student.matricula << "      " << fixed << setprecision(2) << student.nota1 << "   " << student.nota2 << "    " << student.nota3 << "    " << media << endl;
    }

    cout << "\nPrograma encerrado." << endl;

    return 0;
}
