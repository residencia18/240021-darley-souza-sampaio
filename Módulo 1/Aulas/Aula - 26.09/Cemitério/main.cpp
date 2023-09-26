#include <iostream>
#include <vector>

using namespace std;

class Ham
{
private:
    string name;
    string deadDate;

public:
    void setName(string name)
    {
        this->name = name;
    }

    string getName()
    {
        return name;
    }

    void setDate(string _date)
    {
        this->deadDate = _date;
    }

    string getDate()
    {
        return deadDate;
    }
};

class Mausoleum
{
private:
    string location;
    vector<Ham> hams;

public:
    void setLocation(string location)
    {
        this->location = location;
    }

    string getLocation()
    {
        return location;
    }

    void setHamInMausoleum(Ham _ham)
    {
        hams.push_back(_ham);
    }

    void listHams()
    {
        for (auto it = hams.begin(); it != hams.end(); ++it)
        {
            cout << location << endl
                 << it->getName() << endl
                 << it->getDate() << endl
                 << endl;
        }
    }
};

int main(void)
{
    vector<Ham> hams;

    return 0;
}
