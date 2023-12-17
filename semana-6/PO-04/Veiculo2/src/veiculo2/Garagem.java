package veiculo2;

import java.util.ArrayList;
import java.util.List;

class Garagem {
    private List<Veiculo> veiculos;
    private boolean temTomada;

    public Garagem(boolean temTomada) {
        this.veiculos = new ArrayList<>();
        this.temTomada = temTomada;
    }

    public boolean temTomada() {
        return temTomada;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
}

