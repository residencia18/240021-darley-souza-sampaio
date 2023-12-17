package veiculo2;


class Moto extends Veiculo {
    public Moto(String modelo, String cor, int ano, boolean tomada) {
        super(modelo, cor, ano, tomada);
    }

    @Override
    public void ligar() {
        System.out.println("Moto ligada");
    }

    public void empinar() {
        System.out.println("Moto empinando");
    }
}