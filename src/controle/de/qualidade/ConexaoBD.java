package controle.de.qualidade;

import java.sql.*;
import java.util.Vector;

public class ConexaoBD {

	private Connection myConnection;
	private Statement st;

	// Construtor
	public ConexaoBD() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
			Class.forName("org.postgresql.Driver").newInstance();

			myConnection = DriverManager.getConnection("jdbc:postgresql:"
					+ "//localhost/ControleDeQualidade?user=postgres&password=postgres");

			//Cria um comando (statement) vinculado aa conexao
			st = myConnection.createStatement();
	}

	public Cliente buscaCliente(String cpf) throws SQLException {
		ResultSet rs;
		st.execute("SELECT * FROM cliente WHERE cpf_cliente = '" + cpf + "'");
		rs = st.getResultSet();
		if (rs.next()) {
			Cliente c = new Cliente();
			c.setCpf(rs.getString(1));
			c.setNome(rs.getString(2));
			return c;
		}
		return null;
	}

	public Funcionario buscaFuncionario(String cpf) throws SQLException {
		ResultSet rs;
		st.execute("SELECT * FROM funcionario_vendedor WHERE cpf_vendedor = '" + cpf + "'");
		rs = st.getResultSet();

		if (rs.next()) {
			Funcionario f = new Funcionario();
			f.setCpf(rs.getString(1));
			f.setNome(rs.getString(2));
			return f;
		} else {
			st.execute("SELECT * FROM funcionario_atendente WHERE cpf_atendente = '" + cpf + "'");
			rs = st.getResultSet();
		}

		if (rs.next()) {
			Funcionario f = new Funcionario();
			f.setCpf(rs.getString(1));
			f.setNome(rs.getString(2));
			return f;
		}

		return null;
	}

	public Vector buscaTodasVendasNaoAvaliadas(String cpf) throws SQLException {
		Vector res = new Vector();
		Venda v;
		StringBuilder strb = new StringBuilder();
		//st.execute("SELECT * FROM venda WHERE cpf_cliente = '" + cpf + "'");
		strb.append("SELECT * FROM venda v WHERE cpf_cliente = '");
		strb.append(cpf).append("'");
		strb.append(" AND NOT EXISTS");
		strb.append(" (SELECT * FROM av_venda a WHERE v.data_venda = a.data_venda);");
		st.execute(strb.toString());
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			ResultSet rsnome;
			st = myConnection.createStatement();
			st.execute("SELECT nome_vendedor FROM funcionario_vendedor WHERE cpf_vendedor = '" + rs.getString("cpf_vendedor") + "';");
			rsnome = st.getResultSet();
			rsnome.next();
			//System.out.println(rsnome.getString(1));
			v = new Venda(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rsnome.getString(1));

			//Armazena o novo filme no vetor
			res.addElement(v);
			//rsnome = null;
		}
		return res;
	}

	public int gerarCodigoAvVenda() throws SQLException {
		int count = 0;
		st.execute("SELECT * FROM av_venda");
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			count++;
		}
		st = myConnection.createStatement();
		st.execute("SELECT * FROM av_oficina");
		rs = st.getResultSet();
		while (rs.next()) {
			count++;
		}
		st = myConnection.createStatement();
		st.execute("SELECT * FROM av_atendimento");
		rs = st.getResultSet();
		while (rs.next()) {
			count++;
		}
		return count;
	}

	public void inserirAvVenda(AvVenda av) throws SQLException {
		StringBuilder ins = new StringBuilder();
		StringBuilder append = ins.append("INSERT INTO av_venda VALUES (");
		ins.append("'").append(av.getCod_av()).append("',");
		System.out.println(av.getCod_av());
		ins.append("'").append(av.getNota()).append("',");
		if (av.getSugestao().length() > 0) {
			ins.append("'").append(av.getSugestao()).append("',");
		} else {
			ins.append("NULL ,");
		}
		ins.append("'").append(av.getData()).append("',");
		ins.append("'").append(av.getCpf_cliente()).append("',");
		ins.append("'").append(av.getCpf_vendedor()).append("',");
		ins.append("'").append(av.getData_venda()).append("'");
		ins.append(");");
		System.out.println(ins.toString());
		st.execute(ins.toString());

//		ins = new StringBuilder();
		//UPDATE venda SET venda_avaliada = true WHERE cpf_vendedor = '00000000000' AND cpf_cliente = '22222222222' AND data_venda = '1961-06-16';
//		ins.append("UPDATE venda SET status = true WHERE cpf_vendedor = ");
//		ins.append("'").append(av.getCpf_vendedor()).append("'");
//		ins.append(" AND cpf_cliente = '").append(av.getCpf_cliente()).append("'");
//		ins.append(" AND data_venda = '").append(av.getData_venda()).append("';");
//		st.execute(ins.toString());
	}

	public Vector buscaTodasAvVendas(String cpf_cliente) throws SQLException {
		Vector res = new Vector();
		AvVenda Av;
		Venda v;
		StringBuilder strb = new StringBuilder();
		strb.append("SELECT cod_av, nota_venda, sugestao_venda, data_av, av.cpf_cliente, av.cpf_vendedor, av.data_venda, tipo_venda, descr_venda ");
		strb.append("FROM av_venda av, venda v ");
		strb.append(" WHERE av.cpf_cliente = '").append(cpf_cliente).append("'");
		strb.append(" AND av.cpf_cliente = v.cpf_cliente");
		strb.append(" AND av.cpf_vendedor = v.cpf_vendedor");
		strb.append(" AND av.data_venda = v.data_venda;");
		st.execute(strb.toString());
		
		ResultSet rs = st.getResultSet();

		//SELECT cod_av, nota_venda, sugestao_venda, data_av, av.cpf_cliente, av.cpf_vendedor, av.data_venda, tipo_venda, descr_venda 
		//FROM av_venda av, venda v 
		//WHERE av.cpf_cliente = '22222222222' AND  av.cpf_cliente = v.cpf_cliente AND av.cpf_vendedor = v.cpf_vendedor AND av.data_venda = v.data_venda;
		while (rs.next()) {
			ResultSet rsnome;
			st = myConnection.createStatement();
			st.execute("SELECT nome_vendedor FROM funcionario_vendedor WHERE cpf_vendedor = '" + rs.getString("cpf_vendedor") + "';");
			rsnome = st.getResultSet();
			rsnome.next();
			//System.out.println(rsnome.getString(1));
			v = new Venda(rs.getString("cpf_vendedor"), rs.getString("cpf_cliente"), rs.getString("data_venda"), rs.getString("tipo_venda"), rs.getString("descr_venda"), rsnome.getString(1));
			Av = new AvVenda(rs.getString("cod_av"), rs.getFloat("nota_venda"), rs.getString("sugestao_venda"), rs.getString("data_av"), rs.getString("cpf_cliente"), rs.getString("cpf_vendedor"), rs.getString("data_venda"), rsnome.getString(1), v);
//			st = myConnection.createStatement();
//			st.execute("");
//			//SELECT * FROM venda WHERE 
			//Armazena o novo filme no vetor
			res.addElement(Av);
			//rsnome = null;
		}
		return res;
	}
}


//INSERT INTO av_venda ('cod', 'nota', 'sugestao', 'dataAv', 'cpfC', 'cpfV', 'dataV');
