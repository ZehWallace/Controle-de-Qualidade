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
public class Funcionario {
    private String cpf;
    private String nome;
    
    public boolean buscarFuncionario(String cpf) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		ConexaoBD con = new ConexaoBD();
		Funcionario temp;
		temp = con.buscaFuncionario(cpf);
		if(temp!=null){
			this.cpf = temp.getCpf();
			this.nome = temp.getNome();
			return true;
		}
		return false;
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
