#include <iostream>
#include <vector>

using namespace std;

struct Data
{
    int day, month, year;
};

struct voterRegistration
{
    string name;
    string registrationNumber;
    string zone, section;
    string county, UF;
    Data birthDate, emissionDate;
};

void catchDate(Data &);
void showDate(Data);
voterRegistration registerVoter(voterRegistration);

int main(void)
{
    int amountVoters;
    char answer;
    vector<voterRegistration> voters;

    do
    {
        voterRegistration voter;
        cout << "Quantos eleitores deseja cadastrar?: ";
        cin >> amountVoters;

        for (int i = 0; i < amountVoters; i++)
        {

            voter = registerVoter(voter);

            voters.push_back(voter);
        }
        cout << "Deseja cadastrar mais eleitores? (s/n): ";
        cin >> answer;
    } while (answer != 'n');

    cout << endl;

    for (const voterRegistration &voter : voters)
    {
        cout << "Nome do Eleitor: " << voter.name << endl;
        cout << "Data de Nascimento: ";
        showDate(voter.birthDate);
        cout << "Número de Inscrição: " << voter.registrationNumber << endl
             << "Zona/Seção: " << voter.zone << "/" << voter.section << endl
             << "Estado/UF: " << voter.county << "/" << voter.UF << endl
             << "Data de Emissão: ";
        showDate(voter.emissionDate);
        cout << endl;
    }

    return 0;
}

void catchDate(Data &date)
{
    cin >> date.day >> date.month >> date.year;
}

void showDate(Data date)
{
    cout << date.day << "/" << date.month << "/" << date.year << endl;
}

voterRegistration registerVoter(voterRegistration dataVoter)
{
    cout << "Nome do Eleitor: ";
    cin >> dataVoter.name;

    cout << "Data de nascimento(XX XX XXXX): ";
    catchDate(dataVoter.birthDate);

    cout << "Número de Inscrição: ";
    cin >> dataVoter.registrationNumber;

    cout << "Zona: ";
    cin >> dataVoter.zone;

    cout << "Seção: ";
    cin >> dataVoter.section;

    cout << "Estado: ";
    cin >> dataVoter.county;

    cout << "UF: ";
    cin >> dataVoter.UF;

    cout << "Data da emissao(XX XX XXXX): ";
    catchDate(dataVoter.emissionDate);

    return dataVoter;
}
