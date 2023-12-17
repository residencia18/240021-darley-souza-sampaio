package veiculo;

class Moto extends Veiculo {
    public Moto(String modelo, String cor, int ano) {
        super(modelo, cor, ano);
    }

    @Override
    public void ligar() {
        System.out.println("Moto ligada");
    }

    public void empinar() {
        System.out.println("Moto empinando");
    }
}