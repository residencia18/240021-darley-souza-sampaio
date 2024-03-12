#include <iostream>
#include <vector>

using namespace std;

vector<int>* searchPosition(const string & , char , int &);

int main(void) {
    string texto;
    char letra;

    cout << "Digite uma string de caracteres: ";
    cin >> texto;

    cout << "Digite uma letra: ";
    cin >> letra;

    int tamanho;
    vector<int>* posicoes = searchPosition(texto, letra, tamanho);

    if (tamanho > 0) {
        cout << "A letra '" << letra << "' foi encontrada nas posições: ";
        for (int i = 0; i < tamanho; i++) {
            cout << (*posicoes)[i];
            if (i < tamanho - 1) {
                cout << ", ";
            }
        }
        cout << endl;
    } else {
        cout << "A letra '" << letra << "' não foi encontrada na string." << endl;
    }

    return 0;
}

vector<int>* searchPosition(const string& str, char letra, int& tamanho) {
    vector<int>* posicoes = new vector<int>();
    tamanho = 0;

    for (int i = 0; i < str.length(); i++) {
        if (str[i] == letra) {
            posicoes->push_back(i);
            tamanho++;
        }
    }

    return posicoes;
}