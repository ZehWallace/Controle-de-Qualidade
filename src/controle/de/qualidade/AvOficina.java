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

	private ServicoOficina s;
	private int prob_res;

	public AvOficina(ServicoOficina s, int prob_res, String cpf_cliente, String data, float nota, String sugestao) throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
		this.s = s;
		this.prob_res = prob_res;
		this.setCpf_cliente(cpf_cliente);
		this.setData(data);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.criarAvOficina();
	}

	public void criarAvOficina() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con;
		con = new ConexaoBD();
		this.setCod_av(String.format("%05d", con.gerarCodigoAvaliacao()));
		con.inserirAvOficina(this);
	}


	public ServicoOficina getS() {
		return s;
	}

	public int getProb_res() {
		return prob_res;
	}
}