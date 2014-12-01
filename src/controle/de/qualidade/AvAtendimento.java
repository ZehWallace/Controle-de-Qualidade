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
class AvAtendimento extends Avaliacao {

	private String cpf_atendente;
	private String data_atendimento;
	private Atendimento atendimento;
	private String nome_atendente;
	private String nome_cliente;

	private final int probl_res;

	//cria nova avaliacao para inserir no banco
	AvAtendimento(String cpf_cliente, String cpf_atendente, String data_avaliacao, String data_atendimento, float nota, String sugestao, Atendimento atendimento, int probl_res) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		this.setCpf_cliente(cpf_cliente);
		this.setData(data_avaliacao);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.cpf_atendente = cpf_atendente;
		this.data_atendimento = data_atendimento;
		this.atendimento = atendimento;
		this.nome_atendente = null;
		this.nome_cliente = null;
		this.probl_res = probl_res;
		this.adicionarAvAtendimento();
	}

	AvAtendimento(String cpf_cliente, String cpf_atendente, String data_avaliacao, String data_atendimento, float nota, String sugestao, Atendimento atendimento, int probl_res, String nome_atendente) {
		this.setCpf_cliente(cpf_cliente);
		this.setData(data_avaliacao);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.cpf_atendente = cpf_atendente;
		this.data_atendimento = data_atendimento;
		this.atendimento = atendimento;
		this.nome_atendente = nome_atendente;
		this.nome_cliente = null;
		this.probl_res = probl_res;
	}

	AvAtendimento(String cpf_cliente, String cpf_atendente, String data_avaliacao, String data_atendimento, float nota, String sugestao, Atendimento atendimento, int probl_res, String nome_atendente, String nome_cliente) {
		this.setCpf_cliente(cpf_cliente);
		this.setData(data_avaliacao);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.cpf_atendente = cpf_atendente;
		this.data_atendimento = data_atendimento;
		this.atendimento = atendimento;
		this.nome_atendente = nome_atendente;
		this.nome_cliente = nome_cliente;
		this.probl_res = probl_res;
	}

	public void adicionarAvAtendimento() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con;
		con = new ConexaoBD();
		this.setCod_av(String.format("%05d", con.gerarCodigoAvaliacao()));
		con.inserirAvAtendimento(this);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(this.getData_splitted()).append("@");
		res.append(this.getNota()).append("@");
		res.append(nome_atendente).append("@");
		res.append(nome_cliente).append("@");
		res.append("");
		return res.toString();
	}

	public String getCpf_atendente() {
		return cpf_atendente;
	}

	public String getData_atendimento() {
		return data_atendimento;
	}

	public String getData_ini_splitted() {
		String d[] = this.data_atendimento.substring(0, 10).split("-");
		String aux = d[2] + "/" + d[1] + "/" + d[0];
		return aux;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public String getNome_atendente() {
		return nome_atendente;
	}

	public int getProbl_res() {
		return probl_res;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public void setCpf_atendente(String cpf_atendente) {
		this.cpf_atendente = cpf_atendente;
	}

	public void setData_atendimento(String data_atendimento) {
		this.data_atendimento = data_atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public void setNome_atendente(String nome_atendente) {
		this.nome_atendente = nome_atendente;
	}

	public void setNome_cliente(String nome_cliente) {
		this.nome_cliente = nome_cliente;
	}
}
