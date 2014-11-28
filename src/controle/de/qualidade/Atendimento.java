/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

/**
 *
 * @author Bruno
 */
public class Atendimento {

	private String cpf_cliente;
	private String cpf_atendente;
	private String data_ini;
	private String data_fim;
	private String descr_atend;
	private String nome_atendente;

	Atendimento(String cpf_cliente, String cpf_atendente, String data_ini, String data_fim, String descr_atend, String nome_atedente) {
		this.cpf_cliente = cpf_cliente;
		this.cpf_atendente = cpf_atendente;
		this.data_ini = data_ini;
		this.data_fim = data_fim;
		this.descr_atend = descr_atend;
		this.nome_atendente = nome_atedente;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(nome_atendente).append("@");
		res.append(data_ini);
		return res.toString();
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getCpf_atendente() {
		return cpf_atendente;
	}

	public void setCpf_atendente(String cpf_atendente) {
		this.cpf_atendente = cpf_atendente;
	}

	public String getData_ini() {
		return data_ini;
	}

	public void setData_ini(String data_ini) {
		this.data_ini = data_ini;
	}

	public String getData_fim() {
		return data_fim;
	}

	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}

	public String getDescr_atend() {
		return descr_atend;
	}

	public void setDescr_atend(String descr_atend) {
		this.descr_atend = descr_atend;
	}

	public String getNome_atendente() {
		return nome_atendente;
	}

	public void setNome_atendente(String nome_atendente) {
		this.nome_atendente = nome_atendente;
	}
}
