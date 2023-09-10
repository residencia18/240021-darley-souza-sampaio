#include <iostream>
#include <string>

using namespace std;

enum class StatusLivro
{
    DISPONIVEL,
    INDISPONIVEL
};

struct Livro
{
    string titulo;
    string autor;
    string genero;
    string resumo;
    int numPaginas;
    int anoPublicacao;
    string isbn;
    double preco;
    StatusLivro status;

    void abrir()
    {
        if (status == StatusLivro::DISPONIVEL)
        {
            cout << "O livro '" << titulo << "' foi aberto para leitura." << endl;
        }
        else
        {
            cout << "O livro '" << titulo << "' não está disponível para leitura." << endl;
        }
    }

    void resumoLivro()
    {
        cout << "Resumo: " << endl
             << resumo << endl
             << endl;
    }

    void fechar()
    {
        cout << "O livro '" << titulo << "' foi fechado após a leitura." << endl;
    }

    double calcularPrecoComDesconto(double desconto)
    {
        return preco - (preco * desconto);
    }

    void exibirInformacoes()
    {
        cout << "Título: " << titulo << endl
             << "Autor: " << autor << endl
             << "Gênero: " << genero << endl
             << "Número de páginas: " << numPaginas << endl
             << "Ano de publicação: " << anoPublicacao << endl
             << "ISBN: " << isbn << endl
             << "Preço: $" << preco << endl;
    }

    void definirStatus(StatusLivro novoStatus)
    {
        status = novoStatus;
    }

    StatusLivro verificarStatus()
    {
        return status;
    }
};

int main()
{
    Livro meuLivro;

    meuLivro.titulo = "Dom Casmurro";
    meuLivro.autor = "Machado de Assis";
    meuLivro.genero = "Ficção";
    meuLivro.resumo = "Dom Casmurro é uma obra literária clássica escrita pelo renomado autor brasileiro Machado de Assis e publicada originalmente em 1899. O romance é narrado em primeira pessoa por Bento Santiago, também conhecido como Bentinho ou Dom Casmurro. A história é uma investigação da mente do protagonista enquanto ele relembra sua vida e seus relacionamentos, principalmente com sua amiga de infância e suposto amor, Capitu.";
    meuLivro.numPaginas = 250;
    meuLivro.anoPublicacao = 1899;
    meuLivro.isbn = "978-85-06-00037-3";
    meuLivro.preco = 29.99;
    meuLivro.definirStatus(StatusLivro::DISPONIVEL);

    meuLivro.exibirInformacoes();

    meuLivro.abrir();
    meuLivro.resumoLivro();
    meuLivro.fechar();

    double desconto = 0.1;
    double precoComDesconto = meuLivro.calcularPrecoComDesconto(desconto);
    cout << "Preço com desconto de " << (desconto * 100) << "%: $" << precoComDesconto << endl;

    return 0;
}
