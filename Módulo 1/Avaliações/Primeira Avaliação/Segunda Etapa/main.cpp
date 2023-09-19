#include <iostream>
#include <vector>
#include <unistd.h>

using namespace std;

struct Date
{
    int day, month, year;
};

struct Hour
{
    int hour, minute;
};

struct Client
{
    string name;
    string CPF;
    Date birthDate;
    string CNH;
};

struct Car
{
    string name;
    string renavam;
    string carPlate;
    Date withdrawalDate, returnDate;
    Hour hourWithdrawDate, hourReturnDate;
    string concessionaire;
};

struct Rental
{
    char realized;
    Date withdrawalRentalDate, returnRentalDate;
    Hour hourWithdrawRentalDate, hourReturnRentalDate;
    Client clientData;
    Car carData;
};

Client registerClient();
Car registerCar();
Rental registerRental(vector<Client> &, vector<Car> &);
void clientMenu(vector<Client> &);
void carMenu(vector<Car> &);
void rentalMenu(vector<Rental> &, vector<Client> &, vector<Car> &);
bool cpfVerification(string);
bool plateVerification(string);
void catchDateClient(Date &);
void showDateClient(Date);
void showCustomers(const vector<Client> &);
void showClient(const vector<Client> &);
void deleteClient(vector<Client> &);
void updateClient(vector<Client> &);
void catchDateCar(Date &, Hour &);
void showDateCar(Date, Hour);
void showCars(const vector<Car> &);
void showCar(const vector<Car> &);
void deleteCar(vector<Car> &);
void updateCar(vector<Car> &);

int main()
{
    int option;
    vector<Client> customers;
    vector<Car> cars;
    vector<Rental> rentals;

    do
    {
        system("cls || clear");
        cout << " ------ Menu Geral -----" << endl
             << endl
             << "1 - Gestão de Clientes" << endl
             << "2 - Gestão de Carros" << endl
             << "3 - Gestão de Locação" << endl
             << "4 - Sair do Programa" << endl
             << endl
             << "Selecione o menu: ";

        cin >> option;

        switch (option)
        {
        case 1:
            clientMenu(customers);
            break;
        case 2:
            carMenu(cars);
            break;
        case 3:
            rentalMenu(rentals, cars, customers);
            break;
        case 4:
            break;
        default:
            cout << "Opção Inválida!!" << endl;
            sleep(2);
            break;
        }
    } while (option != 3);

    cout << "Programa Finalizado!!!!" << endl;
    sleep(2);

    return 0;
}

void clientMenu(vector<Client> &customers)
{
    system("clear || cls");
    int option;
    do
    {
        cout << " ----- Menu Cliente ----- " << endl
             << endl
             << "1 - Incluir Cliente" << endl
             << "2 - Exlcuir Cliente" << endl
             << "3 - Alterar Dados Cliente" << endl
             << "4 - Listar Clientes" << endl
             << "5 - Localizar Cliente" << endl
             << "6 - Sair" << endl
             << endl;

        cout << "Selecione a opção: ";

        cin >> option;

        switch (option)
        {
        case 1:
        {
            Client client;
            client = registerClient();
            customers.push_back(client);

            cout << "Usuário " << client.name << " incluído no sistema!!" << endl
                 << endl;
            sleep(2);
        }
        break;
        case 2:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
            {
                deleteClient(customers);
                sleep(2);
                system("clear || cls");
            }
        }
        break;
        case 3:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
                updateClient(customers);
        }
        break;
        case 4:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
                showCustomers(customers);
        }
        break;
        case 5:
        {
            if (customers.empty())
            {
                cout << "Não existe cliente cadastrado!!!" << endl;
                sleep(2);
            }
            else
                showClient(customers);
        }
        break;
        case 6:
            break;
        default:
            cout << "Opção inválida!!!" << endl
                 << endl;
            sleep(3);
        }
    } while (option != 6);
}

void carMenu(vector<Car> &cars)
{
    system("clear || cls");
    vector<Car> cars;
    int option;
    do
    {
        cout << " ----- Menu Carros ----- " << endl
             << endl
             << "1 - Incluir Carro" << endl
             << "2 - Exlcuir Carro" << endl
             << "3 - Alterar Dados Carro" << endl
             << "4 - Listar Carros" << endl
             << "5 - Localizar Carro" << endl
             << "6 - Sair" << endl
             << endl;

        cout << "Selecione a opção: ";

        cin >> option;

        switch (option)
        {
        case 1:
        {
            Car car;
            car = registerCar();
            cars.push_back(car);

            cout << "Carro " << car.name << " incluído no sistema!!" << endl
                 << endl;
            sleep(2);
        }
        break;
        case 2:
        {
            if (cars.empty())
            {
                cout << "Não existe carro cadastrado!!!" << endl;
                sleep(2);
            }
            else
            {
                deleteCar(cars);
                sleep(2);
                system("clear || cls");
            }
        }
        break;
        case 3:
        {
            if (cars.empty())
            {
                cout << "Não existe carro cadastrado!!!" << endl;
                sleep(2);
            }
            else
                updateCar(cars);
        }
        break;
        case 4:
        {
            if (cars.empty())
            {
                cout << "Não existe carro cadastrado!!!" << endl;
                sleep(2);
            }
            else
                showCars(cars);
        }
        break;
        case 5:
        {
            if (cars.empty())
            {
                cout << "Não existe carro cadastrado!!!" << endl;
                sleep(2);
            }
            else
                showCar(cars);
        }
        break;
        case 6:
            break;
        default:
            cout << "Opção inválida!!!" << endl
                 << endl;
            sleep(3);
        }
    } while (option != 6);
}

void rentalMenu(vector<Rental> &rentals, vector<Car> &cars, vector<Client> &customers)
{
    system("clear || cls");
    int option;
    do
    {
        cout << " ----- Menu Carros ----- " << endl
             << endl
             << "1 - Incluir Locação" << endl
             << "2 - Exlcuir Locação" << endl
             << "3 - Alterar Dados Locação" << endl
             << "4 - Listar Locações" << endl
             << "5 - Sair" << endl
             << endl;

        cout << "Selecione a opção: ";

        cin >> option;

        switch (option)
        {
        case 1:
        {
            Rental rental;
            rental = registerRental(customers, cars);
            rentals.push_back(rental);

            cout << "Alocação incluído no sistema!!" << endl
                 << endl;
            sleep(2);
        }
        break;

        case 4:
        {
            if (rentals.empty())
            {
                cout << "Não alocações cadastradas!!!" << endl;
                sleep(2);
            }
            else
                showRentals(rentals);
        }
        break;
        case 6:
            break;
        default:
            cout << "Opção inválida!!!" << endl
                 << endl;
            sleep(3);
        }
    } while (option != 6);
}

void catchDateClient(Date &date)
{
    bool success = false;
    int day, month, year;
    do
    {
        cin >> day >> month >> year;

        if (month >= 1 && month <= 12)
        {

            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 10 || month == 12)
            {
                if (day >= 1 && day <= 31)
                {
                    date.day = day;
                    date.month = month;
                    date.year = year;
                    success = true;
                }
                else
                {
                    cout << "Dia inválido!!! Digite novamente: ";
                }
            }
            else if (month == 4 || month == 6 || month == 7 || month == 11)
            {
                if (day >= 1 && day <= 30)
                {
                    date.day = day;
                    date.month = month;
                    date.year = year;
                    success = true;
                }
                else
                {
                    cout << "Dia inválido!!! Digite novamente: ";
                }
            }
            else if (month == 2)
            {
                if (year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0)))
                {

                    if (day >= 1 && day <= 29)
                    {
                        date.day = day;
                        date.month = month;
                        date.year = year;
                        success = true;
                    }
                    else
                    {
                        cout << "Dia inválido!!! Digite novamente: ";
                    }
                }
                else
                {
                    if (day >= 1 && day <= 28)
                    {
                        date.day = day;
                        date.month = month;
                        date.year = year;
                        success = true;
                    }
                    else
                    {
                        cout << "Dia inválido!!! Digite novamente: ";
                    }
                }
            }
        }
        else
        {
            cout << "Mês Incorreto!!! Digite novamente: " << endl;
        }

    } while (success != true);
}

void showDateClient(Date date)
{
    cout << "Data de Nascimento: " << date.day << "/" << date.month << "/" << date.year << endl;
}

void catchDateCar(Date &date, Hour &hourDate)
{
    bool success = false;
    int day, month, year, hour, minute;
    do
    {
        cin >> day >> month >> year >> hour >> minute;

        if (month >= 1 && month <= 12)
        {

            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 10 || month == 12)
            {
                if (day >= 1 && day <= 31)
                {
                    if (hour >= 0 && hour <= 23)
                    {
                        if (minute >= 0 && minute <= 59)
                        {

                            date.day = day;
                            date.month = month;
                            date.year = year;
                            hourDate.hour = hour;
                            hourDate.minute = minute;
                            success = true;
                        }
                        else
                        {
                            cout << "Minutos errados!!! Digite novamente: ";
                        }
                    }
                    else
                    {
                        cout << "Hora incorreta!!! Digite novamente: ";
                    }
                }
                else
                {
                    cout << "Dia inválido!!! Digite novamente: ";
                }
            }
            else if (month == 4 || month == 6 || month == 7 || month == 11)
            {
                if (day >= 1 && day <= 30)
                {
                    if (hour >= 0 && hour <= 23)
                    {
                        if (minute >= 0 && minute <= 59)
                        {

                            date.day = day;
                            date.month = month;
                            date.year = year;
                            hourDate.hour = hour;
                            hourDate.minute = minute;
                            success = true;
                        }
                        else
                        {
                            cout << "Minutos errados!!! Digite novamente: ";
                        }
                    }
                    else
                    {
                        cout << "Hora incorreta!!! Digite novamente: ";
                    }
                }
                else
                {
                    cout << "Dia inválido!!! Digite novamente: ";
                }
            }
            else if (month == 2)
            {
                if (year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0)))
                {

                    if (day >= 1 && day <= 29)
                    {
                        if (hour >= 0 && hour <= 23)
                        {
                            if (minute >= 0 && minute <= 59)
                            {

                                date.day = day;
                                date.month = month;
                                date.year = year;
                                hourDate.hour = hour;
                                hourDate.minute = minute;
                                success = true;
                            }
                            else
                            {
                                cout << "Minutos errados!!! Digite novamente: ";
                            }
                        }
                        else
                        {
                            cout << "Hora incorreta!!! Digite novamente: ";
                        }
                    }
                    else
                    {
                        cout << "Dia inválido!!! Digite novamente: ";
                    }
                }
                else
                {
                    if (day >= 1 && day <= 28)
                    {
                        if (hour >= 0 && hour <= 23)
                        {
                            if (minute >= 0 && minute <= 59)
                            {

                                date.day = day;
                                date.month = month;
                                date.year = year;
                                hourDate.hour = hour;
                                hourDate.minute = minute;
                                success = true;
                            }
                            else
                            {
                                cout << "Minutos errados!!! Digite novamente: ";
                            }
                        }
                        else
                        {
                            cout << "Hora incorreta!!! Digite novamente: ";
                        }
                    }
                    else
                    {
                        cout << "Dia inválido!!! Digite novamente: ";
                    }
                }
            }
        }
        else
        {
            cout << "Mês Incorreto!!! Digite novamente: " << endl;
        }

    } while (success != true);
}

void showDateCar(Date date, Hour hourDate)
{
    cout << date.day << "/" << date.month << "/" << date.year << " às " << hourDate.hour << ":" << hourDate.minute << endl;
}

bool cpfVerification(string CPF)
{
    if (CPF.size() == 14)
    {
        if (CPF[3] == '.' && CPF[7] == '.' && CPF[11] == '-')
            return true;
        else
            return false;
    }
    else
        return false;
}

bool plateVerification(string carPlate)
{
    if (carPlate.size() == 7)
    {
        if (carPlate[3] == '-')
            return true;
        else
            return false;
    }
    else
        return false;
}

Client registerClient()
{
    Client client;

    cout << "Nome do cliente: ";
    cin.ignore();
    getline(cin, client.name);

    do
    {
        bool cpfVerify;
        string cpf;

        cout << "CPF do cliente (XXX.XXX.XXX-XX): ";
        cin >> cpf;

        cpfVerify = cpfVerification(cpf);

        if (cpfVerify)
        {
            client.CPF = cpf;
            break;
        }
        else
        {
            cout << "CPF incorreto!! Digite novamente!!" << endl;
        }

    } while (true);

    cout
        << "Data de Nascimento (DD MM YYYY): ";
    catchDateClient(client.birthDate);

    cout << "CNH do cliente (XXXXXXXXXXX): ";
    cin >> client.CNH;

    cout << endl;

    return client;
}

Car registerCar()
{
    Car car;

    cout << "Nome do carro: ";
    cin.ignore();
    getline(cin, car.name);

    do
    {
        string renavam;

        cout << "Renavam do Carro (XXXXXXXXXXX): ";
        cin >> renavam;

        if (renavam.size() == 11)
        {
            car.renavam = renavam;
            break;
        }
        else
        {
            cout << "Renavam incorreto!! Digite novamente!!" << endl;
        }

    } while (true);

    do
    {
        bool carPlateVerify;
        string carPlate;

        cout << "Placa do Carro (XXX-XXX): ";
        cin >> carPlate;

        carPlateVerify = plateVerification(carPlate);

        if (carPlateVerify)
        {
            car.carPlate = carPlate;
            break;
        }
        else
        {
            cout << "Placa do carro incorreta!! Digite novamente!!" << endl;
        }

    } while (true);

    cout
        << "Data e Hora de Retirada (DD MM YYYY HH MM): ";
    catchDateCar(car.withdrawalDate, car.hourWithdrawDate);

    cout
        << "Data de Entrega (DD MM YYYY HH MM): ";
    catchDateCar(car.returnDate, car.hourReturnDate);

    cout << "Loja de Retirada: ";
    cin >> car.concessionaire;

    cout << endl;

    return car;
}

Rental registerRental(vector<Client> &customers, vector<Car> &cars)
{
    Rental rental;
    string CNH, carPlate;

    cout << endl;
    cout << "CNH do Passageiro: ";
    cin.ignore();
    getline(cin, CNH);

    if (CNH.size() == 11)
    {
        for (const Client &client : customers)
        {
            if (client.CNH == CNH)
            {
                rental.clientData = client;
            }
            else
            {
                cout << "Cliente não encontrado!!!";
            }
        }
    }
    else
    {
        cout << "CNH inválida!!!" << endl;
    }

    cout << "Placa do Carro: ";
    cin.ignore();
    getline(cin, carPlate);
    bool carPlateVerify = plateVerification(carPlate);

    if (carPlateVerify)
    {
        for (const Car &car : cars)
        {
            if (car.carPlate == carPlate)
                rental.carData = car;
            else
                cout << "Cliente não encontrado!!!";
        }
    }
    else
        cout << "CNH inválida!!!" << endl;

    rental.realized = 's';

    cout
        << "Data e Hora de Retirada (DD MM YYYY HH MM): ";
    catchDateCar(rental.withdrawalRentalDate, rental.hourWithdrawRentalDate);

    cout
        << "Data de Entrega (DD MM YYYY HH MM): ";
    catchDateCar(rental.returnRentalDate, rental.hourReturnRentalDate);

    cout << endl;

    return rental;
}

void showCustomers(const vector<Client> &customers)
{
    cout << "----- Todos os Clientes----- " << endl;
    for (const Client &client : customers)
    {
        cout << "Nome do Cliente: " << client.name << endl
             << "CPF: " << client.CPF << endl;
        showDateClient(client.birthDate);
        cout << "CNH: " << client.CNH << endl
             << endl;
    }
}

void showCars(const vector<Car> &cars)
{
    cout << "----- Todos os Carros----- " << endl;
    for (const Car &car : cars)
    {
        cout << "Nome do Carro: " << car.name << endl
             << "Renavam: " << car.renavam << endl
             << "Placa do Carro: " << car.carPlate << endl;
        cout << "Retirada: ";
        showDateCar(car.withdrawalDate, car.hourWithdrawDate);
        cout << "Entrega: ";
        showDateCar(car.returnDate, car.hourReturnDate);
        cout << "Loja de Retirada: " << car.concessionaire << endl
             << endl;
    }
}

void showRentals(const vector<Rental> &rentals)
{
    cout << "----- Todos os Carros----- " << endl;
    for (const Rental &rental : rentals)
    {
        cout << "Nome do Cliente: " << rental.clientData.name << endl
             << "CNH do Cliente: " << rental.clientData.CNH << endl
             << "Nome do Carro: " << rental.carData.name << endl
             << "Placa do Carro: " << rental.carData.carPlate << endl
             << "Renavam do Carro: " << rental.carData.renavam << endl
             << "Loja de Retirada: " << rental.carData.concessionaire << endl
             << "Realizado: " << rental.realized << endl;
        cout << "Retirada: ";
        showDateCar(rental.withdrawalRentalDate, rental.hourWithdrawRentalDate);
        cout << "Entrega: ";
        showDateCar(rental.returnRentalDate, rental.hourReturnRentalDate);
    }
}

void showClient(const vector<Client> &customers)
{
    string CPF;
    bool cpfVerify;

    cout << endl;
    cout << "Digite o CPF do cliente (XXX.XXX.XXX-XX): ";
    cin.ignore();
    getline(cin, CPF);
    cpfVerify = cpfVerification(CPF);

    if (cpfVerify)
    {
        for (const Client &client : customers)
        {
            if (client.CPF == CPF)
            {
                cout << "Nome do Cliente: " << client.name << endl;
                cout << "CPF: " << client.CPF << endl;
                showDateClient(client.birthDate);
                cout << "CNH: " << client.CNH << endl
                     << endl;
            }
            else
            {
                cout << "Cliente não encontrado!!!";
            }
        }
    }
    else
    {
        cout << "CPF inválido!!!" << endl;
    }
    sleep(2);
}

void showCar(const vector<Car> &cars)
{
    string carPlate;

    cout << endl;
    cout << "Digite a placa do carro: (XXX-XXX): ";
    cin.ignore();
    getline(cin, carPlate);
    cout << endl;

    for (const Car &car : cars)
    {
        if (car.carPlate == carPlate)
        {
            cout << "Nome do Carro: " << car.name << endl
                 << "Renavam: " << car.renavam << endl
                 << "Placa do Carro: " << car.carPlate << endl;
            showDateCar(car.withdrawalDate, car.hourWithdrawDate);
            showDateCar(car.returnDate, car.hourReturnDate);
            cout << "Loja de Retirada: " << car.concessionaire << endl
                 << endl;
        }
        else
        {
            cout << "Carro não encontrado!!!" << endl;
        }
    }
}

void deleteClient(vector<Client> &customers)
{
    string CPF;
    bool cpfVerify;

    cout << endl;
    cout << "Digite o CPF do cliente (XXX.XXX.XXX-XX): ";
    cin.ignore();
    getline(cin, CPF);
    cpfVerify = cpfVerification(CPF);

    if (cpfVerify)
    {

        for (auto it = customers.begin(); it != customers.end(); ++it)
        {
            if (it->CPF == CPF)
            {
                customers.erase(it);
                cout << "Cliente " << CPF << " excluído com sucesso!" << endl;
                return;
            }
        }
        cout << "Cliente " << CPF << " não encontrado." << endl;
    }
    else
    {
        cout << "CPF inválido!!!" << endl;
    }
    sleep(2);
}

void deleteCar(vector<Car> &cars)
{
    string carPlate;
    bool carPlateVerify;

    cout << endl;
    cout << "Digite a placa do carro: (XXX-XXX): ";
    cin.ignore();
    getline(cin, carPlate);
    carPlateVerify = plateVerification(carPlate);

    if (carPlateVerify)
    {

        for (auto it = cars.begin(); it != cars.end(); ++it)
        {
            if (it->carPlate == carPlate)
            {
                cars.erase(it);
                cout << "Carro com placa " << carPlate << " excluído com sucesso!" << endl;
                return;
            }
        }
        cout << "Carro com placa " << carPlate << " não encontrado." << endl;
    }
    else
    {
        cout << "Placa inválido!!!" << endl;
    }
    sleep(2);
}

void updateClient(vector<Client> &customers)
{
    string CPF;
    bool cpfVerify;

    cout << endl;
    cout << "Digite o CPF do cliente (XXX.XXX.XXX-XX): ";
    cin.ignore();
    getline(cin, CPF);
    cpfVerify = cpfVerification(CPF);

    if (cpfVerify)
    {
        int option;

        for (auto it = customers.begin(); it != customers.end(); ++it)
        {
            if (it->CPF == CPF)
            {
                cout << "Nome do Cliente: " << it->name << endl;
                cout << "CPF: " << it->CPF << endl;
                showDateClient(it->birthDate);
                cout << "CNH: " << it->CNH
                     << endl
                     << endl;

                cout << "1 - Alterar Nome" << endl
                     << "2 - Alterar CPF" << endl
                     << "3 - Alterar Data de Nascimento" << endl
                     << "4 - Alterar CNH" << endl
                     << "5 - Sair" << endl
                     << endl;
                cout << "Selecione a opção: ";
                cin >> option;

                switch (option)
                {
                case 1:
                {
                    string name;
                    cout << "Digite o novo nome para o cliente: ";

                    cin.ignore();
                    getline(cin, name);

                    it->name = name;

                    cout << "Nome do cliente alterado com sucesso!!!" << endl
                         << "Novo nome: " << name << endl;
                }
                break;
                case 2:
                {

                    string CPF;
                    cout << "Digite o novo CPF para o cliente: ";
                    cin.ignore();
                    getline(cin, CPF);

                    it->CPF = CPF;

                    cout << "CPF do cliente alterado com sucesso!!!" << endl
                         << "Novo CPF: " << CPF;
                    cout << endl;
                }
                break;
                case 3:
                {

                    cout << "Digite a nova data para o cliente: ";
                    catchDateClient(it->birthDate);

                    cout << "Data de Nascimento do cliente alterada com sucesso!!!" << endl
                         << "Nova data: ";
                    showDateClient(it->birthDate);
                    cout << endl;
                }
                break;
                case 4:
                {
                    string CNH;
                    cout << "Digite o novo CNH para o cliente: ";
                    cin.ignore();
                    getline(cin, CNH);

                    it->CNH = CNH;

                    cout << "CNH do cliente alterado com sucesso!!!" << endl
                         << "Novo CNH: " << CNH << endl
                         << endl;
                }
                break;

                default:
                    cout << "Nenhum dado alterado!!";
                    break;
                }
            }
            else
            {
                cout << "Cliente não encontrado!!!";
            }
        }
    }
    else
    {
        cout << "CPF Inválido!!!" << endl;
    }
    sleep(2);
}

void updateCar(vector<Car> &cars)
{
    string carPlate;
    bool carPlateVerify;

    cout << endl;
    cout << "Digite a placa do carro (XXX-XXX): ";
    cin.ignore();
    getline(cin, carPlate);
    carPlateVerify = plateVerification(carPlate);

    if (carPlateVerify)
    {
        int option;

        for (auto it = cars.begin(); it != cars.end(); ++it)
        {
            if (it->carPlate == carPlate)
            {
                cout << "Nome do Carro: " << it->name << endl
                     << "Renavam: " << it->renavam << endl
                     << "Placa do Carro: " << it->carPlate << endl;
                showDateCar(it->withdrawalDate, it->hourWithdrawDate);
                showDateCar(it->returnDate, it->hourReturnDate);
                cout << "Loja de Retirada: " << it->concessionaire << endl
                     << endl;

                cout << "1 - Alterar Nome do Carro" << endl
                     << "2 - Alterar Renavam" << endl
                     << "3 - Alterar Placa do Carro" << endl
                     << "4 - Alterar Data de Retirada" << endl
                     << "5 - Alterar Data de Entrega" << endl
                     << "6 - Alterar Loja de Retirada" << endl
                     << "7 - Sair" << endl
                     << endl;
                cout << "Selecione a opção: ";
                cin >> option;

                switch (option)
                {
                case 1:
                {
                    string name;
                    cout << "Digite o novo nome para o carro: ";

                    cin.ignore();
                    getline(cin, name);

                    it->name = name;

                    cout << "Nome do carro alterado com sucesso!!!" << endl
                         << "Novo nome: " << name << endl;
                }
                break;
                case 2:
                {
                    string renavam;
                    do
                    {
                        cout << "Digite o novo renavam (XXXXXXXXXXX): ";
                        cin.ignore();
                        getline(cin, renavam);

                        if (renavam.size() == 11)
                        {
                            it->renavam = renavam;

                            cout << "Renavam do carro alterado com sucesso!!!" << endl
                                 << "Novo renavam do carro: " << renavam << endl;
                        }
                        else
                        {
                            cout << "Renavam incorreto!! Digite novamente!!" << endl;
                        }
                    } while (renavam.size() != 11);
                }
                break;
                case 3:
                {
                    bool carPlateVerify;
                    string carPlate;

                    do
                    {
                        cout << "Digite a nova placa do carro: (XXX-XXX): ";
                        cin >> carPlate;

                        carPlateVerify = plateVerification(carPlate);

                        if (carPlateVerify)
                        {

                            it->carPlate = carPlate;

                            cout << "Placa do carro alterada com sucesso!!!" << endl
                                 << "Nova placa do carro: " << carPlate << endl;
                        }
                        else
                            cout << "Placa Inválida!!!";
                    } while (carPlateVerify != true);
                }
                break;
                case 4:
                {

                    cout << "Digite a nova data de retirada (DD MM YYYY HH MM): ";
                    catchDateCar(it->withdrawalDate, it->hourWithdrawDate);

                    cout << "Data de retirada alterada com sucesso!!!" << endl
                         << "Nova data: ";
                    showDateCar(it->withdrawalDate, it->hourWithdrawDate);
                    cout << endl;
                }
                break;
                case 5:
                {

                    cout << "Digite a nova data de entrega (DD MM YYYY HH MM): ";
                    catchDateCar(it->returnDate, it->hourReturnDate);

                    cout << "Data de entrega alterada com sucesso!!!" << endl
                         << "Nova data: ";
                    showDateCar(it->returnDate, it->hourReturnDate);
                    cout << endl;
                }
                break;
                case 6:
                {
                    string concessionaire;
                    cout << "Digite a nova loja de retirada: ";
                    cin.ignore();
                    getline(cin, concessionaire);

                    it->concessionaire = concessionaire;

                    cout << "Loja de retirada alterada com sucesso!!!" << endl
                         << "Novo loja: " << concessionaire;
                    cout << endl;
                }
                break;

                default:
                    cout << "Nenhum dado alterado!!" << endl;
                    break;
                }
            }
            else
            {
                cout << "Carro não encontrado!!!" << endl;
            }
        }
    }
    else
    {
        cout << "Placa do carro inválida!!!" << endl;
    }
    sleep(2);
}
