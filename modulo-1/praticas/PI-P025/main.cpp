#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <algorithm>
#include <fstream>
#include <memory>

using namespace std;

class Usuario;
class Tweet;

class Tweet
{
private:
    shared_ptr<Usuario> autor;
    string conteudo;
    time_t dataCriacao;

public:
    Tweet(shared_ptr<Usuario> autor, const string &conteudo)
    {
        this->autor = autor;
        this->conteudo = conteudo;
        this->dataCriacao = time(nullptr);
    }

    shared_ptr<Usuario> getAutor() const
    {
        return autor;
    }

    string getConteudo() const
    {
        return conteudo;
    }

    time_t getDataCriacao() const
    {
        return dataCriacao;
    }
};

class Usuario : public enable_shared_from_this<Usuario>
{
private:
    string nomeUsuario;
    string nome;
    vector<shared_ptr<Usuario>> seguidores;
    vector<shared_ptr<Usuario>> seguindo;
    vector<shared_ptr<Tweet>> tweets;

public:
    Usuario(const string &nomeUsuario, const string &nome)
    {
        this->nomeUsuario = nomeUsuario;
        this->nome = nome;
    }

    void postarTweet(const string &conteudo)
    {
        shared_ptr<Tweet> novoTweet = make_shared<Tweet>(shared_from_this(), conteudo);
        tweets.push_back(novoTweet);
    }

    void seguir(shared_ptr<Usuario> usuario)
    {
        seguindo.push_back(usuario);
        usuario->seguidores.push_back(shared_from_this());
    }

    vector<shared_ptr<Tweet>> getTweets() const
    {
        return tweets;
    }

    string getNomeUsuario() const
    {
        return nomeUsuario;
    }

    string getNome() const
    {
        return nome;
    }

    vector<shared_ptr<Usuario>> getSeguindo() const
    {
        return seguindo;
    }
};

class RedeSocial
{
private:
    vector<shared_ptr<Usuario>> usuarios;

public:
    void cadastrarUsuario(const string &nomeUsuario, const string &nome)
    {
        shared_ptr<Usuario> novoUsuario = make_shared<Usuario>(nomeUsuario, nome);
        usuarios.push_back(novoUsuario);
    }

    shared_ptr<Usuario> buscarUsuario(const string &nomeUsuario)
    {
        for (const shared_ptr<Usuario> &usuario : usuarios)
        {
            if (usuario->getNomeUsuario() == nomeUsuario)
            {
                return usuario;
            }
        }
        return nullptr;
    }

    vector<shared_ptr<Usuario>> listar_usuarios() const
    {
        return usuarios;
    }

    vector<shared_ptr<Tweet>> listar_tweets() const
    {
        vector<shared_ptr<Tweet>> all_tweets;
        for (const shared_ptr<Usuario> &usuario : usuarios)
        {
            const vector<shared_ptr<Tweet>> &user_tweets = usuario->getTweets();
            all_tweets.insert(all_tweets.end(), user_tweets.begin(), user_tweets.end());
        }

        sort(all_tweets.begin(), all_tweets.end(), [](const shared_ptr<Tweet> &tweet1, const shared_ptr<Tweet> &tweet2)
             { return tweet1->getDataCriacao() > tweet2->getDataCriacao(); });

        return all_tweets;
    }

    void salvarDados(const string &nomeArquivo)
    {
        ofstream arquivo(nomeArquivo);
        if (arquivo.is_open())
        {
            for (const shared_ptr<Usuario> &usuario : usuarios)
            {
                arquivo << "Nome de Usuário: " << usuario->getNomeUsuario() << endl;
                arquivo << "Nome: " << usuario->getNome() << endl;
                for (const shared_ptr<Tweet> &tweet : usuario->getTweets())
                {
                    arquivo << "Tweet: " << tweet->getConteudo() << endl;
                }
                for (const shared_ptr<Usuario> &seguido : usuario->getSeguindo())
                {
                    arquivo << "Segue: " << seguido->getNomeUsuario() << endl;
                }
                arquivo << "-------------------" << endl;
            }
            arquivo.close();
        }
        else
        {
            cout << "Erro ao abrir o arquivo para salvar dados." << endl
                 << endl;
        }
    }

    void carregarDados(const string &nomeArquivo)
    {
        ifstream arquivo(nomeArquivo);
        if (arquivo.is_open())
        {
            string line;
            shared_ptr<Usuario> novoUsuario = nullptr;

            while (getline(arquivo, line))
            {
                if (line.find("Nome de Usuário: ") != string::npos)
                {
                    string nomeUsuario = line.substr(17);
                    shared_ptr<Usuario> usuarioExistente = buscarUsuario(nomeUsuario);
                    if (usuarioExistente != nullptr)
                    {
                        novoUsuario = usuarioExistente;
                    }
                    else
                    {
                        getline(arquivo, line);
                        if (line.find("Nome: ") != string::npos)
                        {
                            string nomeReal = line.substr(6);
                            novoUsuario = make_shared<Usuario>(nomeUsuario, nomeReal);
                            usuarios.push_back(novoUsuario);
                        }
                    }
                }
                else if (novoUsuario != nullptr)
                {
                    if (line.find("Tweet: ") != string::npos)
                    {
                        string conteudoTweet = line.substr(7);
                        novoUsuario->postarTweet(conteudoTweet);
                    }
                    else if (line.find("Segue: ") != string::npos)
                    {
                        string usuarioSeguindo = line.substr(7);
                        shared_ptr<Usuario> seguido = buscarUsuario(usuarioSeguindo);
                        if (seguido != nullptr)
                        {
                            novoUsuario->seguir(seguido);
                        }
                    }
                }
            }

            arquivo.close();
        }
        else
        {
            cout << "Arquivo de dados não encontrado. Nenhum dado foi carregado." << endl << endl;
        }
    }

};

int main()
{
    RedeSocial redeSocial;
    redeSocial.carregarDados("dados.txt");

    int opcao;

    do
    {
        cout << "=== Menu da Tweet ===" << endl
             << endl
             << "1. Registrar novo usuário" << endl
             << "2. Postar um tweet" << endl
             << "3. Seguir um usuário" << endl
             << "4. Ver feed de tweets" << endl
             << "5. Salvar dados em arquivo" << endl
             << "6. Sair" << endl
             << endl
             << "Escolha uma opção: ";
        cin >> opcao;

        switch (opcao)
        {
        case 1:
        {
            string nomeUsuario, nomeReal;

            cout << "Digite um nome de usuário único: ";
            cin.ignore();
            getline(cin, nomeUsuario);

            cout << "Digite seu nome real: ";
            cin.ignore();
            getline(cin, nomeReal);

            redeSocial.cadastrarUsuario(nomeUsuario, nomeReal);
            break;
        }
        case 2:
        {
            string nomeUsuario, conteudoTweet;

            cout << "Digite seu nome de usuário: ";
            cin.ignore();
            getline(cin, nomeUsuario);

            shared_ptr<Usuario> usuario = redeSocial.buscarUsuario(nomeUsuario);

            if (usuario != nullptr)
            {
                cout << "Digite o conteúdo do tweet: ";
                cin.ignore();
                getline(cin, conteudoTweet);

                usuario->postarTweet(conteudoTweet);
                cout << "Tweet postado com sucesso!" << endl
                     << endl;
            }
            else
            {
                cout << "Usuário não encontrado." << endl
                     << endl;
            }
            break;
        }
        case 3:
        {
            string nomeSeguidor, nomeSeguindo;
            cout << "Digite o nome de usuário do seguidor: ";
            cin.ignore();
            getline(cin, nomeSeguidor);

            cout << "Digite o nome de usuário a ser seguido: ";
            cin.ignore();
            getline(cin, nomeSeguindo);

            shared_ptr<Usuario>
                seguidor = redeSocial.buscarUsuario(nomeSeguidor);
            shared_ptr<Usuario> seguido = redeSocial.buscarUsuario(nomeSeguindo);
            if (seguidor != nullptr && seguido != nullptr)
            {
                seguidor->seguir(seguido);
                cout << "Agora " << seguidor->getNomeUsuario() << " está seguindo " << seguido->getNomeUsuario() << endl
                     << endl;
            }
            else
            {
                cout << "Usuário(s) não encontrado(s)." << endl
                     << endl;
            }
            break;
        }
        case 4:
        {
            string nomeUsuario;

            cout << "Digite o nome de usuário para ver o feed de tweets: ";
            cin.ignore();
            getline(cin, nomeUsuario);

            shared_ptr<Usuario> usuario = redeSocial.buscarUsuario(nomeUsuario);

            if (usuario != nullptr)
            {
                vector<shared_ptr<Tweet>> feed = usuario->getTweets();
                cout << "Feed de tweets para " << usuario->getNomeUsuario() << ":" << endl
                     << endl;
                for (const shared_ptr<Tweet> &tweet : feed)
                {
                    time_t tweetTime = tweet->getDataCriacao();
                    cout
                        << "Autor: " << tweet->getAutor()->getNomeUsuario() << endl
                        << "Data de Criação: " << ctime(&tweetTime)
                        << "Conteúdo: " << tweet->getConteudo() << endl
                        << "-------------------" << endl
                        << endl;
                }
                cout << endl;
            }
            else
            {
                cout << "Usuário não encontrado." << endl
                     << endl;
            }
            break;
        }
        case 5:
        {
            redeSocial.salvarDados("dados.txt");
            cout << "Dados salvos com sucesso." << endl
                 << endl;
            break;
        }
        case 6:
        {
            cout << "Saindo..." << endl
                 << endl;
            break;
        }
        default:
        {
            cout << "Opção inválida. Tente novamente." << endl
                 << endl;
        }
        }
    } while (opcao != 6);

    return 0;
}
