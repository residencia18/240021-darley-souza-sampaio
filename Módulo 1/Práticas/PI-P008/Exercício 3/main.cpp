#include <iostream>
#include <string>
#include <sstream>

using namespace std;

bool lapYear(int year)
{
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
}

bool validDate(int day, int month, int year)
{
    if (month < 1 || month > 12 || day < 1 || day > 31)
        return false;

    if (month == 2)
    {
        if (lapYear(year))
            return day <= 29;
        else
            return day <= 28;
    }
    else if (month == 4 || month == 6 || month == 9 || month == 11)
        return day <= 30;

    return true;
}

int main()
{
    string date;
    string months[12] = {"janeiro", "fevereiro", "março", "abril", "maio", "junho",
                         "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"};
    int day, month, year;
    char delimiter;

    cout << "Digite uma data no formato dd/mm/aaaa: ";
    cin >> date;

    stringstream ss(date);
    ss >> day >> delimiter >> month >> delimiter >> year;

    if (ss && ss.peek() == EOF)
    {
        if (validDate(day, month, year))
            cout << day << " de " << months[month - 1] << " de " << year << endl;
        else
            cout << "Data inválida." << endl;
    }
    else
        cout << "Formato de data inválido." << endl;

    return 0;
}
