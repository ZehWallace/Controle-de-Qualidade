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

		//Cria um comando (statement) vinculado a conexao
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
		StringBuilder ins = new StringBuilder();
		//st.execute("SELECT * FROM venda WHERE cpf_cliente = '" + cpf + "'");
		ins.append("SELECT * FROM venda v WHERE cpf_cliente = '");
		ins.append(cpf).append("'");
		ins.append(" AND NOT EXISTS");
		ins.append(" (SELECT * FROM av_venda a WHERE v.data_venda = a.data_venda);");
		st.execute(ins.toString());
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			ResultSet rsnome;
			st = myConnection.createStatement();
			st.execute("SELECT nome_vendedor FROM funcionario_vendedor WHERE cpf_vendedor = '" + rs.getString("cpf_vendedor") + "';");
			rsnome = st.getResultSet();
			rsnome.next();
			v = new Venda(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rsnome.getString(1));
			res.addElement(v);
		}
		return res;
	}

	public Vector buscaTodosAtendimentosNaoAvaliados(String cpf) throws SQLException {
		Vector res = new Vector();
		StringBuilder ins = new StringBuilder();
		ins.append("SELECT * FROM atendimento a WHERE ");
		ins.append("cpf_cliente = '").append(cpf).append("'");
		ins.append("AND NOT EXISTS (SELECT * FROM av_atendimento av WHERE a.data_ini = av.data_atend);");
		st.execute(ins.toString());
		ResultSet rs = st.getResultSet();
		while (rs.next()) {
			ResultSet rsnome;
			st = myConnection.createStatement();
			st.execute("SELECT nome_atendente FROM funcionario_atendente WHERE cpf_atendente = '" + rs.getString("cpf_atendente") + "';");
			rsnome = st.getResultSet();
			rsnome.next();
			Atendimento a = new Atendimento(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rsnome.getString(1));
			res.addElement(a);
		}
		return res;
	}

	public int gerarCodigoAvaliacao() throws SQLException {
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
		ins.append("INSERT INTO av_venda VALUES (");
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
	}

	public void inserirAvAtendimento(AvAtendimento av) throws SQLException {
		StringBuilder ins = new StringBuilder();
		ins.append("INSERT INTO av_atendimento VALUES (");
		ins.append("'").append(av.getCod_av()).append("'");
		ins.append(", '").append(av.getCpf_atendente()).append("'");
		ins.append(", '").append(av.getCpf_cliente()).append("'");
		ins.append(", '").append(av.getNota()).append("'");
		ins.append(", '").append(av.getProbl_res()).append("'");
		if (av.getSugestao().length() > 0) {
			ins.append(", '").append(av.getSugestao()).append("'");
		} else {
			ins.append(", NULL");
		}
		ins.append(", '").append(av.getData_atendimento()).append("'");
		ins.append(", '").append(av.getData()).append("'");
		ins.append(");");

		st.execute(ins.toString());
	}

	public Vector buscaTodasAvVendas(String cpf_cliente) throws SQLException {
		Vector res = new Vector();
		AvVenda Av;
		Venda v;
		StringBuilder ins = new StringBuilder();
		ins.append("SELECT cod_av, nota_venda, sugestao_venda, data_av, av.cpf_cliente, av.cpf_vendedor, av.data_venda, tipo_venda, descr_venda ");
		ins.append("FROM av_venda av, venda v ");
		ins.append(" WHERE av.cpf_cliente = '").append(cpf_cliente).append("'");
		ins.append(" AND av.cpf_cliente = v.cpf_cliente");
		ins.append(" AND av.cpf_vendedor = v.cpf_vendedor");
		ins.append(" AND av.data_venda = v.data_venda;");
		st.execute(ins.toString());

		ResultSet rs = st.getResultSet();

		while (rs.next()) {
			ResultSet rsnome;
			st = myConnection.createStatement();
			st.execute("SELECT nome_vendedor FROM funcionario_vendedor WHERE cpf_vendedor = '" + rs.getString("cpf_vendedor") + "';");
			rsnome = st.getResultSet();
			rsnome.next();
			v = new Venda(rs.getString("cpf_vendedor"), rs.getString("cpf_cliente"), rs.getString("data_venda"), rs.getString("tipo_venda"), rs.getString("descr_venda"), rsnome.getString(1));
			Av = new AvVenda(rs.getString("cod_av"), rs.getFloat("nota_venda"), rs.getString("sugestao_venda"), rs.getString("data_av"), rs.getString("cpf_cliente"), rs.getString("cpf_vendedor"), rs.getString("data_venda"), rsnome.getString(1), v);
			res.addElement(Av);
		}
		return res;
	}

	public Vector buscaTodasAvAtendimento(String cpf_cliente) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Vector res = new Vector();
		AvAtendimento Av;
		Atendimento a;
		StringBuilder ins = new StringBuilder();
		ins.append("SELECT cod_av, nota_atend, sugestao, data_av, av.cpf_cliente, av.cpf_atendente, a.data_ini, a.data_fim, probl_res, dresc_prob");
		ins.append(" FROM av_atendimento av, atendimento a");
		ins.append(" WHERE av.cpf_cliente = '").append(cpf_cliente).append("'");
		ins.append(" AND av.cpf_cliente = a.cpf_cliente");
		ins.append(" AND av.cpf_atendente = a.cpf_atendente");
		ins.append(" AND av.data_atend = a.data_ini;");
		st.execute(ins.toString());

		ResultSet rs = st.getResultSet();

		while (rs.next()) {
			ResultSet rsnome;
			st = myConnection.createStatement();
			st.execute("SELECT nome_atendente FROM funcionario_atendente WHERE cpf_atendente = '" + rs.getString("cpf_atendente") + "';");
			rsnome = st.getResultSet();
			rsnome.next();
			a = new Atendimento(cpf_cliente, rs.getString("cpf_atendente"), rs.getString("data_ini"), rs.getString("data_fim"), rs.getString("dresc_prob"), rsnome.getString(1));
			Av = new AvAtendimento(cpf_cliente, rs.getString("cpf_atendente"), rs.getString("data_av"), rs.getString("data_ini"), rs.getFloat("nota_atend"), rs.getString("sugestao"), a, rs.getInt("probl_res"), rsnome.getString(1));
			res.addElement(Av);
		}
		return res;
	}
}
