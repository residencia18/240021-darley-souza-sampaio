#include <iostream>
#include <vector>

using namespace std;

class Persistencia;
class Usuario;
class Cliente;
class Funcionario;
class Aluguel;
class Veiculo;
class Date;

class Date
{
private:
    int dia, mes, ano;

public:
    int getDia() const { return dia; }
    int getMes() const { return mes; }
    int getAno() const { return ano; }

    void setDia(int dia) { this->dia = dia; }
    void setMes(int mes) { this->mes = mes; }
    void setAno(int ano) { this->ano = ano; }

    void mostrarData() { cout << getDia() << "/" << getMes() << "/" << getAno() << endl; }
};

class Usuario
{
protected:
    int id;
    string cpf, nome, endereco, telefone;

public:
    int getId() { return id; }
    string getNome() { return nome; }
    string getCpf() { return cpf; }
    string getEndereco() { return endereco; }
    string getTelefone() { return telefone; }

    void setId(int id) { this->id = id; }
    void setNome(string nome) { this->nome = nome; }
    void setEndereco(string endereco) { this->endereco = endereco; }
    void setTelefone(string telefone) { this->telefone = telefone; }
    void setCpf(string cpf) { this->cpf = cpf; }
};

class Cliente : public Usuario
{
private:
    int id;
    string habilitacao;
    vector<Aluguel> historicoAlugueis;

public:
    int getId() { return id; }
    string getHabilitacao() { return habilitacao; }
    vector<Aluguel> getHistoricoAlugueis() { return historicoAlugueis; }

    void getId(int id) { this->id = id; }
    void setHabilitacao(string habilitacao) { this->habilitacao = habilitacao; }
    void setHistoricoAlugueis(Aluguel historico) { historicoAlugueis.push_back(historico); }

    float cotar_aluguel(Veiculo veiculo, Date dataInicio, Date dataFim)
    {
        for (Aluguel historico : historicoAlugueis)
        {
            if (dataInicio.getAno() >= historico.getDateInicio().getAno() &&
                dataInicio.getMes() >= historico.getDateInicio().getMes() &&
                dataInicio.getDia() >= historico.getDateInicio().getDia() &&
                dataFim.getAno() <= historico.getDateFim().getAno() &&
                dataFim.getMes() <= historico.getDateFim().getMes() &&
                dataFim.getDia() <= historico.getDateFim().getDia())
            {
                return -1.0;
            }
        }

        int dias = dataFim.getDia() - dataInicio.getDia();
        float precoTotal = dias * veiculo.getPrecoPorDia();

        return precoTotal;
    }

    Aluguel solicitar_aluguel(Veiculo veiculo, Date dataInicio, Date dataFim)
    {
        Aluguel aluguel;
        aluguel.setVeiculo(veiculo);
        aluguel.setDateInicio(dataInicio);
        aluguel.setDateFim(dataFim);

        return aluguel;
    }

    void devolver_veiculo(Aluguel aluguel, Date dataDevolucao)
    {
        aluguel.setDateDevolucao(dataDevolucao);
        historicoAlugueis.push_back(aluguel);
    }
};

class Funcionario : public Usuario
{
protected:
    int id;
    vector<Aluguel> historicoAlugueis;

public:
    int getId() { return id; }
    vector<Aluguel> getHistoricoAlugueis() { return historicoAlugueis; }

    void setId(int id) { this->id = id; }
    void setHistoricoAlugueis(Aluguel historico) { historicoAlugueis.push_back(historico); }

    void finalizar_aluguel(Aluguel aluguel, Date dataDevolucao) {}
};

class Veiculo
{
protected:
    int id;
    string identificador, marca, modelo;
    int anoFabricacao;
    float precoPorDia;

public:
    int getId() { return id; }
    string getIdentificador() { return identificador; }
    string getMarca() { return marca; }
    string getModelo() { return modelo; }
    int getAnoFabricacao() { return anoFabricacao; }
    float getPrecoPorDia() { return precoPorDia; }

    void setId(int id) { this->id = id; }
    void setIdetificador(string identificador) { this->identificador = identificador; }
    void setMarca(string marca) { this->marca = marca; }
    void setModelo(string modelo) { this->modelo = modelo; }
    void setAnoFabricacao(int ano) { this->anoFabricacao = ano; }
    void setPrecoPorDia(float preco) { this->precoPorDia = preco; }
};

class Aluguel
{
protected:
    int id;
    string identificador;
    Veiculo veiculo;
    Cliente cliente;
    Funcionario funcionario;
    Date dataInicio, dataFim, dataDevolucao;
    float desconto, adicional;

public:
    int getId() { return id; }
    string getIdentificador() { return identificador; };
    Veiculo getVeiculo() { return veiculo; }
    Cliente getCliente() { return cliente; }
    Funcionario getFuncionario() { return funcionario; }
    Date getDateInicio() { return dataInicio; }
    Date getDateFim() { return dataFim; }
    Date getDateDevolcao() { return dataDevolucao; }
    float getDesconto() { return desconto; }
    float getAdicional() { return adicional; }

    void setId(int id) { this->id = id; }
    void setIdentificador(string identificador) { this->identificador = identificador; }
    void setVeiculo(Veiculo veiculo) { this->veiculo = veiculo; }
    void setCliente(Cliente cliente) { this->cliente = cliente; }
    void setFuncionario(Funcionario funcionario) { this->funcionario = funcionario; }
    void setDateInicio(Date dataInicio) { this->dataInicio = dataInicio; }
    void setDateFim(Date dataFim) { this->dataFim = dataFim; }
    void setDateDevolucao(Date dataDevolucao) { this->dataDevolucao = dataDevolucao; }
    void setDesconto(float desconto) { this->desconto = desconto; }
    void setAdicional(float adicional) { this->adicional = adicional; }

    float calcular_valor_final();
};

class FuncionarioCliente
{
    // class n para n entre funcionario e cliente
};

class FuncionarioVeiculo
{
    // class n para n entre funcionario e veiculo
};

class ClienteVeiculo
{
    // class n para n entre cliente e veiculo
};

int main()
{

    return 0;
}
