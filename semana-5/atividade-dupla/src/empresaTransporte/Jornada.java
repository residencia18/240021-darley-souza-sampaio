package empresaTransporte;

import java.util.Date;

public class Jornada {
	private Veiculo veiculoAssociado;
	private Motorista motoristaAssociado;
	private Cobrador cobradorAssociado; // Pode ser nulo
	private Trajeto trajetoAssociado;
	private Date dataHoraInicio;
	private Date dataHoraTermino; // Pode ser nulo

	public Jornada(Veiculo veiculoAssociado, Motorista motoristaAssociado, Cobrador cobradorAssociado,
			Trajeto trajetoAssociado, Date dataHoraInicio) {
		this.veiculoAssociado = veiculoAssociado;
		this.motoristaAssociado = motoristaAssociado;
		this.cobradorAssociado = cobradorAssociado;
		this.trajetoAssociado = trajetoAssociado;
		this.dataHoraInicio = dataHoraInicio;
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

}
