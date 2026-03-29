package aula14;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Agenda {
	private LocalDate data;
	private LocalTime hora;
	private Cliente cliente;
	private ArrayList<Servicos> servico;
	private Funcionario funcionario;
	private float valor;
	
	
	public Agenda(LocalDate data, LocalTime hora, Cliente cliente, ArrayList<Servicos> servico, Funcionario funcionario, float valor) {
		this.data = data;
		this.hora = hora;
		this.cliente = cliente;
		this.servico = servico;
		this.funcionario = funcionario;
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Servicos> getServico() {
		return servico;
	}

	public void setServico(ArrayList<Servicos> servico) {
		this.servico = servico;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	


	
	
	
	
	
	

}
