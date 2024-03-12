#include <iostream>
#include <cmath>


using namespace std;

class Forma
{
public:
    virtual double calcularArea() const = 0;
};

class Retangulo : public Forma
{
private:
    double largura;
    double altura;

public:
    Retangulo(double largura, double altura) : largura(largura), altura(altura) {}

    double getLargura()
    {
        return largura;
    };

    double getAltura()
    {
        return altura;
    };

    void setAltura(double _a)
    {
        this->altura = _a;
    }

    void setLargura(double _l)
    {
        this->largura = _l;
    }

    double calcularArea() const override
    {
        return largura * altura;
    }
};

class Circulo : public Forma
{
private:
    double raio;

public:
    Circulo(double raio) : raio(raio) {}

    double getRaio()
    {
        return raio;
    };

    void setRaio(double _r)
    {
        this->raio = _r;
    }

    double calcularArea() const override
    {
        return M_PI * raio * raio;
    }
};

int main()
{
    double largura, altura, raio;

    cout << "Digite a largura e altura do retangulo: ";
    cin >> largura >> altura;
    Retangulo retangulo(largura, altura);
    cout << "A area do retangulo e: " << retangulo.calcularArea() << endl;

    cout << "Digite o raio do circulo: ";
    cin >> raio;
    Circulo circulo(raio);
    cout << "A area do circulo e: " << circulo.calcularArea() << endl;

    return 0;
}
