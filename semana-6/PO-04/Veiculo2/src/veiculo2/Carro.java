package veiculo2;

class Carro extends Veiculo {
    public Carro(String modelo, String cor, int ano, boolean tomada) {
        super(modelo, cor, ano, tomada);
    }

    @Override
    public void ligar() {
        System.out.println("Carro ligado");
    }

    public void abrirPorta() {
        System.out.println("Porta do carro aberta");
    }
}

