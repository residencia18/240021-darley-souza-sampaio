#include <iostream>
#include <vector>
#include <string>

using namespace std;

class SuperAnimal
{
protected:
    string name;
    int age;

public:
    string sound();

    string getNameAnimal()
    {
        return name;
    }

    int getAgeAnimal()
    {
        return age;
    }

    void setNameAnimal(string _n)
    {
        this->name = _n;
    }

    void setAgeAnimal(int _a)
    {
        this->age = _a;
    }
};

class Dog : SuperAnimal
{
public:
    string sound()
    {
        return "AuAu!!!";
    }

    string getName()
    {
        return getNameAnimal();
    };

    int getAge()
    {
        return getAgeAnimal();
    }

    void setName(string _n)
    {
        this->setNameAnimal(_n);
    }

    void setAge(int _a)
    {
        this->setAgeAnimal(_a);
    }
};

int main()
{
    Dog dog;
    string nameAnimal, soundAnimal;
    int ageAnimal;

    dog.setName("Felpudo");
    dog.setAge(5);

    nameAnimal = dog.getName();
    ageAnimal = dog.getAge();
    soundAnimal = dog.sound();

    cout << "O animal de nome " << nameAnimal << " de idade " << ageAnimal << " faz o som " << soundAnimal << endl << endl;

    return 0;
}
