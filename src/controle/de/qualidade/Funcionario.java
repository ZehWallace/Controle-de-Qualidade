/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Bruno
 */
public class Funcionario {

	private String cpf;
	private String nome;

	public boolean buscarFuncionario(String cpf) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		Funcionario temp;
		temp = con.buscaFuncionario(cpf);
		if (temp != null) {
			this.cpf = temp.getCpf();
			this.nome = temp.getNome();
			return true;
		}
		return false;
	}

	//tem q arrumar pra buscar venda do funcionario, só copiei da parte do cliente este metodo!
	public Vector obterVendasFunc() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector vetorVendas;
		ConexaoBD con = new ConexaoBD();
		vetorVendas = con.buscaTodasVendasNaoAvaliadas(this.cpf);
		return vetorVendas;
	}

	public Vector obterAvVendasFunc() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector vetorAvVendas;
		ConexaoBD con = new ConexaoBD();
		vetorAvVendas = con.buscaTodasAvVendasFunc(this.cpf);
		return vetorAvVendas;
	}

	//tem q arrumar pra buscar venda do funcionario, só copiei da parte do cliente este metodo!
	public Vector obterAtendimentosFunc() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector vetorAtend;
		ConexaoBD con = new ConexaoBD();
		vetorAtend = con.buscaTodosAtendimentosNaoAvaliados(this.cpf);
		return vetorAtend;
	}

	public Vector obterAvAtendimentosFunc() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector vetorAvAtend;
		ConexaoBD con = new ConexaoBD();
		vetorAvAtend = con.buscaTodasAvAtendimentoFunc(this.cpf);
		return vetorAvAtend;
	}

	public Vector obterAvVendasGeral(String data_ini, String data_fim) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Vector vetorAvVendas;
		ConexaoBD con = new ConexaoBD();
		vetorAvVendas = con.buscaTodasAvVendasIntervalo(data_ini, data_fim);
		return vetorAvVendas;
	}

	public Vector obterAvAtendimentosGeral(String data_ini, String data_fim) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Vector vetorAvAtend;
		ConexaoBD con = new ConexaoBD();
		vetorAvAtend = con.buscaTodasAvAtendimentoIntervalo(data_ini, data_fim);
		return vetorAvAtend;
	}

	public Vector obterAvOficinaGeral(String data_ini, String data_fim) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Vector vetorAvOficina;
		ConexaoBD con = new ConexaoBD();
		vetorAvOficina = con.buscaTodasAvAtendimentoIntervalo(data_ini, data_fim); //MUDAR
		return vetorAvOficina;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
