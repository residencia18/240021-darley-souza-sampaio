#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

string commonCharacters(const string&, const string&);

int main(void) {
    string stringA = "Jucolino foi à praia";
    string stringB = "Abigail não foi à praia";
    
    string stringC = commonCharacters(stringA, stringB);
    
    cout << "Caracteres comum: " << stringC << endl;
    
    return 0;
}

string commonCharacters(const string& stringA, const string& stringB) {
    string stringC;
    
    for (char c : stringA) {
        if (stringB.find(c) != string::npos) {
            if (stringC.find(c) == string::npos) {
                stringC += c;
            }
        }
    }

    return stringC;
}