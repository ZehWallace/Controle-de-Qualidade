/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

import java.sql.SQLException;

/**
 *
 * @author Bruno
 */
public class Venda {

	Venda(String cpf_funcionario, String cpf_cliente, String data_venda, String tipo_venda, String descr_venda, String nome_func) throws SQLException {
		this.cpf_funcionario = cpf_funcionario;
		this.cpf_cliente = cpf_cliente;
		this.data_venda = data_venda;
		this.tipo_venda = tipo_venda;
		this.descr_venda = descr_venda;
		this.nome_func = nome_func;
	}

	public String getDescr_venda() {
		return descr_venda;
	}

	public void setDescr_venda(String descr_venda) {
		this.descr_venda = descr_venda;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public String getCpf_funcionario() {
		return cpf_funcionario;
	}

	public void setCpf_funcionario(String cpf_funcionario) {
		this.cpf_funcionario = cpf_funcionario;
	}

	public String getData_venda() {
		return data_venda;
	}

	public void setData_venda(String data_venda) {
		this.data_venda = data_venda;
	}

	public String getTipo_venda() {
		return tipo_venda;
	}

	public void setTipo_venda(String tipo_venda) {
		this.tipo_venda = tipo_venda;
	}

	public String getNome_func() {
		return nome_func;
	}

	public void setNome_func(String nome_func) {
		this.nome_func = nome_func;
	}
	private String nome_func;

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(tipo_venda).append("@");
		res.append(data_venda).append("@");

		res.append(nome_func);
		return res.toString();
	}

	private String cpf_cliente;
	private String cpf_funcionario;
	private String data_venda;
	private String tipo_venda;
	private String descr_venda;

}
