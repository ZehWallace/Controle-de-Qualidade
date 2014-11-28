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
public abstract class Avaliacao {
	private float nota;
	private String cod_av;
	private String sugestao;
	private String data;
	
	private String cpf_cliente;

	/**
	 * @return the nota
	 */
	public float getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(float nota) {
		this.nota = nota;
	}

	/**
	 * @return the cod_av
	 */
	public String getCod_av() {
		return cod_av;
	}

	/**
	 * @param cod_av the cod_av to set
	 */
	public void setCod_av(String cod_av) {
		this.cod_av = cod_av;
	}

	/**
	 * @return the sugestao
	 */
	public String getSugestao() {
		return sugestao;
	}

	/**
	 * @param sugestao the sugestao to set
	 */
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the cpf_cliente
	 */
	public String getCpf_cliente() {
		return cpf_cliente;
	}

	/**
	 * @param cpf_cliente the cpf_cliente to set
	 */
	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

//	/**
//	 * @return the data_venda
//	 */
//	public String getData_venda() {
//		return data_venda;
//	}
//
//	/**
//	 * @param data_venda the data_venda to set
//	 */
//	public void setData_venda(String data_venda) {
//		this.data_venda = data_venda;
//	}
}
