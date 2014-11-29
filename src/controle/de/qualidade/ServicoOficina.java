/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

import java.util.Vector;

/**
 *
 * @author Bruno
 */
public class ServicoOficina {

	private final String placa;
	private final String data_ini;
	private final String data_fim;
	private final String cpf_cliente;
	private final Vector tipo_servicos;

	public ServicoOficina(String placa, String data_ini, String data_fim, String cpf_cliente, Vector tipo_servicos) {
		this.placa = placa;
		this.data_ini = data_ini;
		this.data_fim = data_fim;
		this.cpf_cliente = cpf_cliente;
		this.tipo_servicos = tipo_servicos;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(placa).append("@");
		res.append(data_ini).append("@");
		return res.toString();
	}

	public String getPlaca() {
		return placa;
	}

	public String getData_ini() {
		return data_ini;
	}

	public String getData_fim() {
		return data_fim;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public Vector getTipo_servicos() {
		return tipo_servicos;
	}

}
