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
 * @author davys_000
 */
public class Cliente {

	private String cpf;
	private String nome;

	public boolean buscarCliente(String cpf) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ConexaoBD con;
		Cliente tempc;
		con = new ConexaoBD();
		tempc = con.buscaCliente(cpf);
		//System.out.println(tempc.getCpf());
		if (tempc != null) {
			this.cpf = tempc.getCpf();
			this.nome = tempc.getNome();
			return true;
		}
		return false;
	}

	public Vector obterVendas() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector vetorVendas;
		ConexaoBD con = new ConexaoBD();
		vetorVendas = con.buscaTodasVendasNaoAvaliadas(this.cpf);
		return vetorVendas;
	}

	public Vector obterAvVendas() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector vetorAvVendas;
		ConexaoBD con = new ConexaoBD();
		vetorAvVendas = con.buscaTodasAvVendas(this.cpf);
		return vetorAvVendas;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
