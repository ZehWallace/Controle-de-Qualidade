/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

/**
 *
 * @author Bruno
 */
public class TipoServRealizadoOficina {
	private String cod_serv;
	private String nome_serv;

	public TipoServRealizadoOficina(String cod_serv, String nome_serv) {
		this.cod_serv = cod_serv;
		this.nome_serv = nome_serv;
	}

	public String getCod_serv() {
		return cod_serv;
	}

	public String getNome_serv() {
		return nome_serv;
	}
	
	
}
