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
public class AvOficina extends Avaliacao {

	private ServicoOficina serv;
	private int prob_res;
	private String nome_cliente;

	//cria avaliacao para adicionar no BD
	public AvOficina(ServicoOficina s, int prob_res, String cpf_cliente, String data, float nota, String sugestao) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
		this.serv = s;
		this.prob_res = prob_res;
		this.setCpf_cliente(cpf_cliente);
		this.setData(data);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.nome_cliente = null;
		this.criarAvOficina();
	}

	public AvOficina(ServicoOficina s, int prob_res, String cpf_cliente, String data, float nota, String sugestao, String cod_av) {
		this.serv = s;
		this.prob_res = prob_res;
		this.setCpf_cliente(cpf_cliente);
		this.setData(data);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.nome_cliente = null;
		this.setCod_av(cod_av);
	}

	public AvOficina(ServicoOficina s, int prob_res, String cpf_cliente, String data, float nota, String sugestao, String cod_av, String nome_cliente) {
		this.serv = s;
		this.prob_res = prob_res;
		this.setCpf_cliente(cpf_cliente);
		this.setData(data);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.setCod_av(cod_av);
		this.nome_cliente = nome_cliente;
	}

	public void criarAvOficina() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con;
		con = new ConexaoBD();
		this.setCod_av(String.format("%05d", con.gerarCodigoAvaliacao()));
		con.inserirAvOficina(this);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(this.getServ().getPlaca()).append("@");
		res.append(this.getData_splitted()).append("@");
		res.append(this.getNota()).append("@");
		res.append(this.nome_cliente).append("@");
		res.append("");
		return res.toString();
	}

	public ServicoOficina getServ() {
		return serv;
	}

	public int getProb_res() {
		return prob_res;
	}
}
