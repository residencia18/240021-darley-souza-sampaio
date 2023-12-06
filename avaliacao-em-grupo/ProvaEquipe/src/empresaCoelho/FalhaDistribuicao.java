package empresaCoelho;
import java.time.LocalDate;

class FalhaDistribuicao extends Falha {
    public FalhaDistribuicao(Imovel imovel, String descricao, String previsao, LocalDate dataInicio) {
        super(imovel, descricao, previsao, dataInicio);
    }
}
