#include <iostream>
#include <string>

using namespace std;

string codificarString(const string&);
string decodificarString(const string&);

int main(void) {
    string password;
    cout << "Digite a string: ";
    getline(cin, password);

    string codificada = codificarString(password);
    cout << "String codificada: " << codificada << endl;

    string decodificada = decodificarString(codificada);
    cout << "String decodificada: " << decodificada << endl;

    return 0;
}

string codificarString(const string& password) {
    string result = password;

    for (char& c : result) {
        if (isalpha(c)) {
            if (c == 'z') {
                c = 'a';
            } else if (c == 'Z') {
                c = 'A';
            } else {
                c++;
            }
        }
    }

    return result;
}

string decodificarString(const string& password) {
    string result = password;

    for (char& c : result) {
        if (isalpha(c)) {
            if (c == 'a') {
                c = 'z';
            } else if (c == 'A') {
                c = 'Z';
            } else {
                c--;
            }
        }
    }

    return result;
}
