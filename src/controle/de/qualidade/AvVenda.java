/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 COMENTARIO

 */
package controle.de.qualidade;

import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Bruno
 */
public class AvVenda extends Avaliacao {

	private String nome_vendedor;
	private String nome_cliente;
	private String data_venda;
	private String cpf_vendedor;
	private Venda venda;

	/**
	 * cria nova avaliacao para inserir no banco
	 */
	public AvVenda(String cpfC, String cpfF, String data, String dataVenda, float nota, String sugestao) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.setCpf_cliente(cpfC);
		this.setData(data);
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.cpf_vendedor = cpfF;
		this.data_venda = dataVenda;
		this.nome_vendedor = null;
		this.nome_cliente = null;
		this.adicionarAvVenda();

	}

	/**
	 * carrega avaliacao do banco de dados
	 */
	public AvVenda(String codigo, float nota, String sugestao, String data, String cpfC, String cpfF, String dataVenda, String nome_vendedor, Venda v) throws SQLException {
		this.setCod_av(codigo);
		this.setCpf_cliente(cpfC);
		this.cpf_vendedor = cpfF;
		this.setData(data);
		this.data_venda = dataVenda;
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.nome_vendedor = nome_vendedor;
		this.venda = v;
	}

	public AvVenda(String codigo, float nota, String sugestao, String data, String cpfC, String cpfF, String dataVenda, String nome_vendedor, String nome_cliente, Venda v) throws SQLException {
		this.setCod_av(codigo);
		this.setCpf_cliente(cpfC);
		this.cpf_vendedor = cpfF;
		this.setData(data);
		this.data_venda = dataVenda;
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.nome_vendedor = nome_vendedor;
		this.nome_cliente = nome_cliente;
		this.venda = v;
	}

	/**
	 * adiciona avaliacao ao banco de dados
	 */
	public void adicionarAvVenda() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ConexaoBD con;
		con = new ConexaoBD();
		this.setCod_av(String.format("%05d", con.gerarCodigoAvaliacao()));
		con.inserirAvVenda(this);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(this.venda.getTipo_venda()).append("@");
		res.append(this.getData_splitted()).append("@");
		res.append(this.getNota()).append("@");
		res.append(nome_vendedor).append("@");
		res.append(nome_cliente).append("@");
		res.append("");
		return res.toString();
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public String getCpf_vendedor() {
		return cpf_vendedor;
	}

	public void setCpf_vendedor(String cpf_vendedor) {
		this.cpf_vendedor = cpf_vendedor;
	}

	public String getData_venda() {
		return data_venda;
	}

	public void setData_venda(String data_venda) {
		this.data_venda = data_venda;
	}

	public String getNome_vendedor() {
		return nome_vendedor;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public String getData_venda_splitted() {
		String d[] = this.data_venda.split("-");
		String aux = d[2] + "/" + d[1] + "/" + d[0];
		return aux;
	}

}
