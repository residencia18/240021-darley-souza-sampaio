package veiculo;

class Carro extends Veiculo {
    public Carro(String modelo, String cor, int ano) {
        super(modelo, cor, ano);
    }

    @Override
    public void ligar() {
        System.out.println("Carro ligado");
    }

    public void abrirPorta() {
        System.out.println("Porta do carro aberta");
    }
}