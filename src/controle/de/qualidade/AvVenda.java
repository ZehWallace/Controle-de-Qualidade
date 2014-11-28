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
	private String data_venda;
	private String cpf_vendedor;
	private Venda venda;
        private int o;

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

	public AvVenda(String cpfC, String cpfF, String data, String dataVenda, float nota, String sugestao) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.setCpf_cliente(cpfC);
		this.cpf_vendedor = cpfF;
		this.setData(data);
		this.data_venda = dataVenda;
		this.setNota(nota);
		this.setSugestao(sugestao);
		this.adicionarAvVenda();
		this.nome_vendedor = null;
	}

	//Carrega avaliação
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

	public void adicionarAvVenda() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		ConexaoBD con;
		con = new ConexaoBD();
		this.setCod_av(String.format("%05d", con.gerarCodigoAvVenda()));
		con.inserirAvVenda(this);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(this.venda.getTipo_venda()).append("@");
		res.append(this.getData()).append("@");
		res.append(nome_vendedor).append("@");
		res.append(this.getNota()).append("@");
		res.append("");
		return res.toString();
	}

	/**
	 * @return the data_venda
	 */
	public String getData_venda() {
		return data_venda;
	}

	/**
	 * @param data_venda the data_venda to set
	 */
	public void setData_venda(String data_venda) {
		this.data_venda = data_venda;
	}

}
