package empresaTransporte;

import java.io.Serializable;
import java.util.Date;

public class Jornada implements Serializable{
	private static final long serialVersionUID = -3570775618642863212L;
	private String codigo;
	private Veiculo veiculoAssociado;
	private Motorista motoristaAssociado;
	private Cobrador cobradorAssociado; // Pode ser nulo
	private Trajeto trajetoAssociado;
	private Date dataHoraInicio;
	private Date dataHoraTermino; // Pode ser nulo
	private int quantidadePassageiros;
	private boolean jornadaIniciada;

	public Jornada(String codigo, Veiculo veiculoAssociado, Motorista motoristaAssociado, Cobrador cobradorAssociado,
			Trajeto trajetoAssociado) {
		this.codigo = codigo;
		this.veiculoAssociado = veiculoAssociado;
		this.motoristaAssociado = motoristaAssociado;
		this.cobradorAssociado = cobradorAssociado;
		this.trajetoAssociado = trajetoAssociado;
		this.jornadaIniciada = false;
	}
	
	public Jornada() {

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Veiculo getVeiculoAssociado() {
		return veiculoAssociado;
	}

	public void setVeiculoAssociado(Veiculo veiculoAssociado) {
		this.veiculoAssociado = veiculoAssociado;
	}

	public Motorista getMotoristaAssociado() {
		return motoristaAssociado;
	}

	public void setMotoristaAssociado(Motorista motoristaAssociado) {
		this.motoristaAssociado = motoristaAssociado;
	}

	public Cobrador getCobradorAssociado() {
		return cobradorAssociado;
	}

	public void setCobradorAssociado(Cobrador cobradorAssociado) {
		this.cobradorAssociado = cobradorAssociado;
	}

	public Trajeto getTrajetoAssociado() {
		return trajetoAssociado;
	}

	public void setTrajetoAssociado(Trajeto trajetoAssociado) {
		this.trajetoAssociado = trajetoAssociado;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraTermino() {
		return dataHoraTermino;
	}

	public void setDataHoraTermino(Date dataHoraTermino) {
		this.dataHoraTermino = dataHoraTermino;
	}

	public boolean getJornadaIniciada() {
		return jornadaIniciada;
	}

	public void setJornadaIniciada(boolean jornadaIniciada) {
		this.jornadaIniciada = jornadaIniciada;
	}

	public int getQuantidadePassageiros() {
		return quantidadePassageiros;
	}

	public void setQuantidadePassageiros(int quantidadePassageiros) {
		this.quantidadePassageiros = quantidadePassageiros;
	}

	public void embarcarPassageiro() {
		this.quantidadePassageiros += 1;
	}

	public void desembarcarPassageiro() {
		if(quantidadePassageiros > 0)
			this.quantidadePassageiros -= 1;
		else
			System.out.println("Não possui passageiros para desembarcar.\n");
	}

	public void exibirInformacoes() {
		System.out.println("-----------------------------------------------");
		System.out.println("Código da Jornada: " + this.codigo);
		System.out.println("Veículo: ");
		this.veiculoAssociado.exibirInformacoes();
		System.out.println("Motorista: ");
		this.motoristaAssociado.exibirInformacoes();
		System.out.println("Cobrador: ");
		this.cobradorAssociado.exibirInformacoes();
		System.out.println("Trajeto: ");
		this.trajetoAssociado.exibirInformacoes();
		System.out.println("Início: " + dataHoraInicio);
		System.out.println("Término: " + dataHoraTermino);

		if (jornadaIniciada) {
			System.out.println("Jornada já iniciada.");
			if (quantidadePassageiros == 0) {
				System.out.println("Sem passageiros.\n");
			} else {
				System.out.println(this.quantidadePassageiros + " passageiros no veículo.\n");
			}
		} else
			System.out.println("Jornada não iniciada.");

		System.out.println("-----------------------------------------------");
	}

}
