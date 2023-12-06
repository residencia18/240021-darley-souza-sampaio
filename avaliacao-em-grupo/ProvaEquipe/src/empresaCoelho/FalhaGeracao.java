package empresaCoelho;
import java.time.LocalDate;

class FalhaGeracao extends Falha {
    public FalhaGeracao(Imovel imovel, String descricao, String previsao, LocalDate dataInicio) {
        super(imovel, descricao, previsao, dataInicio);
    }
}