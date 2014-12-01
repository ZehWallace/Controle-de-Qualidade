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
		vetorAvOficina = con.buscaTodasAvOficinaIntervalo(data_ini, data_fim); //MUDAR
		return vetorAvOficina;
	}

	public void deletarHistorico(String data_ini, String data_fim) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		con.deletaavintervalo(data_ini, data_fim);
	}

	public float mediaAvVenda() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.mediaAvVenda();
	}

	public float mediaAvAtendimento() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.mediaAvAtendimento();
	}

	public float mediaAvOficina() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.mediaAvOficina();
	}

	public float qtdAvVenda() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.qtdAvVenda();
	}

	public float qtdAvAtendimento() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.qtdAvAtendimento();
	}

	public float qtdAvOficina() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.qtdAvOficina();
	}

	public float qtdVenda() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.qtdvenda();
	}

	public float qtdAtendimento() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.qtdAtendimento();
	}
	
	public float qtdServicos() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		ConexaoBD con = new ConexaoBD();
		return con.qtdServico();
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
