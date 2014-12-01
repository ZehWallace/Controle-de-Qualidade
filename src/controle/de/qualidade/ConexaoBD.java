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

    public Vector buscaTodosServicosNaoAvaliados(String cpf) throws SQLException {
        String placa, data_serv;
        Vector res = new Vector();
        Vector serv_realizados;
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT * FROM (");
        ins.append(" SELECT DISTINCT a.placa, data_ini, data_fim FROM servicos_realizados a, (");
        ins.append(" SELECT * FROM servicos WHERE placa IN (");
        ins.append(" SELECT placa FROM veiculo WHERE cpf_cliente = '").append(cpf).append("'))");
        ins.append(" AS serv_cod WHERE a.placa = serv_cod.placa AND a.data_serv = serv_cod.data_ini ORDER BY a.placa)");
        ins.append(" AS servicos WHERE (placa, data_ini) NOT IN (SELECT placa, data_serv FROM av_oficina);");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        //adiciona ao vetor resultado os servicos em oficina ainda nao avaliados pelo cliente
        while (rs.next()) {
            placa = rs.getString(1);
            data_serv = rs.getString(2);

            ins = new StringBuilder();
            ins.append("SELECT l.cod_serv, nome_serv FROM lista_servicos l, (");
            ins.append(" SELECT * FROM servicos_realizados WHERE placa = '").append(placa).append("'");
            ins.append(" AND data_serv = '").append(data_serv).append("')");
            ins.append(" AS foo WHERE l.cod_serv = foo.cod_serv;");

            st = myConnection.createStatement();
            st.execute(ins.toString());
            ResultSet rsserv = st.getResultSet();
            //cria um vetor com todos os tipos de servicos realizados em um servico na oficina
            serv_realizados = new Vector();
            while (rsserv.next()) {
                TipoServRealizadoOficina sr = new TipoServRealizadoOficina(rsserv.getString(1), rsserv.getString(2));
                serv_realizados.add(sr);
            }

            ServicoOficina serv = new ServicoOficina(rs.getString(1), rs.getString(2), rs.getString(3), cpf, serv_realizados);
            res.add(serv);
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

    public void inserirAvOficina(AvOficina av) throws SQLException {
        StringBuilder ins = new StringBuilder();
        ins.append("INSERT INTO av_oficina VALUES (");
        ins.append("'").append(av.getCod_av()).append("'");
        ins.append(", '").append(av.getNota()).append("'");
        ins.append(", '").append(av.getProb_res()).append("'");
        if (av.getSugestao().length() > 0) {
            ins.append(", '").append(av.getSugestao()).append("'");
        } else {
            ins.append(", NULL");
        }
        ins.append(", '").append(av.getData()).append("'");
        ins.append(", '").append(av.getServ().getData_ini()).append("'");
        ins.append(", '").append(av.getServ().getPlaca()).append("');");
        st.execute(ins.toString());
    }
    //media venda
    public float mediaAvVenda() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT AVG(nota_venda) FROM av_venda;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getFloat(1);
    }
    //qtd de av. venda
    public int qtdAvVenda() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT COUNT(cod_av) FROM av_venda;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getInt(1);
    }
    //qtd de venda
    public int qtdvenda() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT COUNT(*) FROM venda;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getInt(1);
    }
    //media atendimento
    public float mediaAvAtendimento() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT AVG(nota_atend) FROM av_atendimento;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getFloat(1);
    }
    //qtd de av. atendimento
    public int qtdAvAtendimento() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT COUNT(cod_av) FROM av_atendimento;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getInt(1);
    }
    //qtd de atendimento
    public int qtdAtendimento() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT COUNT(*) FROM atendimento;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getInt(1);
    }
    //media oficina
    public float mediaAvOficina() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT AVG(nota_serv) FROM av_oficina;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getFloat(1);
    }
    //qtd de av. oficina
    public int qtdAvOficina() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT COUNT(cod_av) FROM av_oficina;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getInt(1);
    }
    //qtd de servições
    public int qtdServico() throws SQLException{
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT COUNT(*) FROM servicos;");
        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        
        rs.next();
        
        return rs.getInt(1);
    }
    
        //DELETE FROM av_venda WHERE data_av BETWEEN '<>' AND '<>';
        //DELETE FROM av_atendimento WHERE data_av BETWEEN '<>' AND '<>';
        //DELETE FROM av_oficina WHERE data_av BETWEEN '<>' AND '<>';
    
        //deletar em um intervalo
        public void deletaavintervalo(String data_ini, String data_fim) throws SQLException {
        StringBuilder delvenda = new StringBuilder();
        StringBuilder delatendimento = new StringBuilder();
        StringBuilder deloficina = new StringBuilder();
        delvenda.append("DELETE FROM av_venda ");
        delvenda.append("WHERE data_av BETWEEN '").append(data_ini).append("' AND '").append(data_fim).append("';");
        
        st.execute(delvenda.toString());
        
        delatendimento.append("DELETE FROM av_atendimento ");
        delatendimento.append("WHERE data_av BETWEEN '").append(data_ini).append("' AND '").append(data_fim).append("';");
        
        st.execute(delatendimento.toString());
        
        deloficina.append("DELETE FROM av_oficina ");
        deloficina.append("WHERE data_av BETWEEN '").append(data_ini).append("' AND '").append(data_fim).append("';");
        
        st.execute(deloficina.toString());
        
        
        
    }

        //SELECT cod_av, nota_venda, sugestao_venda, data_av, av.cpf_cliente, av.cpf_vendedor, av.data_venda, tipo_venda, descr_venda FROM av_venda av, venda v
    //WHERE av.data_venda BETWEEN '2014-11-03' AND '2015-10-30' AND av.cpf_cliente = v.cpf_cliente AND av.cpf_vendedor = v.cpf_vendedor AND av.data_venda = v.data_venda;
    public Vector buscaTodasAvVendasIntervalo(String datainit, String datafim) throws SQLException {
        Vector res = new Vector();
        AvVenda Av;
        Venda v;
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT cod_av, nota_venda, sugestao_venda, data_av, av.cpf_cliente, av.cpf_vendedor, av.data_venda, tipo_venda, descr_venda ");
        ins.append("FROM av_venda av, venda v ");
        ins.append(" WHERE av.data_av BETWEEN '").append(datainit).append("' AND '").append(datafim).append("'");
        ins.append(" AND av.cpf_vendedor = v.cpf_vendedor");
        ins.append(" AND av.data_venda = v.data_venda;");
        st.execute(ins.toString());

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            ResultSet rsnomecliente,rsnomefunc;
            st = myConnection.createStatement();
            st.execute("SELECT nome_cliente FROM cliente WHERE cpf_cliente = '" + rs.getString("cpf_cliente") + "';");
            //pega o nome do cliente
            rsnomecliente = st.getResultSet();
            rsnomecliente.next();
            st = myConnection.createStatement();
            st.execute("SELECT nome_vendedor FROM funcionario_vendedor  WHERE cpf_vendedor = '" + rs.getString("cpf_vendedor") + "';");
           //pega o nome do func.
            rsnomefunc = st.getResultSet();
            rsnomefunc.next();
            
            v = new Venda(rs.getString("cpf_vendedor"), rs.getString("cpf_cliente"), rs.getString("data_venda"), rs.getString("tipo_venda"), rs.getString("descr_venda"), rsnomecliente.getString(1));
            Av = new AvVenda(rs.getString("cod_av"), rs.getFloat("nota_venda"), rs.getString("sugestao_venda"), rs.getString("data_av"), rs.getString("cpf_cliente"), rs.getString("cpf_vendedor"),rs.getString("data_venda"),rsnomefunc.getString(1), rsnomecliente.getString(1), v);
            res.addElement(Av);
        }
        return res;
    }

    public Vector buscaTodasAvAtendimentoIntervalo(String datainit, String datafim) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Vector res = new Vector();
        AvAtendimento Av;
        Atendimento a;
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT cod_av, nota_atend, sugestao, data_av, av.cpf_cliente, av.cpf_atendente, a.data_ini, a.data_fim, probl_res, dresc_prob");
        ins.append(" FROM av_atendimento av, atendimento a");
        ins.append(" WHERE av.data_av BETWEEN '").append(datainit).append("' AND '").append(datafim).append("'");
        ins.append(" AND av.cpf_cliente = a.cpf_cliente");
        ins.append(" AND av.cpf_atendente = a.cpf_atendente");
        ins.append(" AND av.data_atend = a.data_ini;");
        st.execute(ins.toString());

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            ResultSet rsnome_cliente,rsnome_atendente;
            st = myConnection.createStatement();
            st.execute("SELECT nome_cliente FROM cliente WHERE cpf_cliente = '" + rs.getString("cpf_cliente") + "';");
            rsnome_cliente = st.getResultSet();
            rsnome_cliente.next();
            st = myConnection.createStatement();
            st.execute("SELECT nome_atendente FROM funcionario_atendente WHERE cpf_atendente = '" + rs.getString("cpf_atendente") + "';");
            rsnome_atendente = st.getResultSet();
            rsnome_atendente.next();
            
            a = new Atendimento(rs.getString("cpf_cliente"), rs.getString("cpf_atendente"), rs.getString("data_ini"), rs.getString("data_fim"), rs.getString("dresc_prob"), rsnome_cliente.getString(1));
            Av = new AvAtendimento(rs.getString("cpf_cliente"), rs.getString("cpf_atendente"), rs.getString("data_av"), rs.getString("data_ini"), rs.getFloat("nota_atend"), rs.getString("sugestao"), a, rs.getInt("probl_res"),rsnome_atendente.getString(1), rsnome_cliente.getString(1));
            res.addElement(Av);
        }
  
        return res;
    }

        //        SELECT v.placa, cpf_cliente, data_ini, data_fim, cod_av, nota_serv, prob_res, sugestao, data_av FROM veiculo v, (
    //              SELECT foo.placa, data_ini, data_fim, cod_av, nota_serv, prob_res, sugestao, data_av FROM servicos s, (
    //                  SELECT * FROM av_oficina WHERE data_serv BETWEEN '<>' AND '<>')
    //                      AS foo WHERE s.placa = foo.placa AND s.data_ini = foo.data_serv)
    //                          AS foo2 WHERE v.placa = foo2.placa;
    public Vector buscaTodasAvOficinaIntervalo(String datainit, String datafim) throws SQLException {
        String placa, data_serv;
        Vector res = new Vector();
        Vector serv_realizados;
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT v.placa, cpf_cliente, data_ini, data_fim, cod_av, nota_serv, prob_res, sugestao, data_av FROM veiculo v, (");
        ins.append(" SELECT foo.placa, data_ini, data_fim, cod_av, nota_serv, prob_res, sugestao, data_av FROM servicos s, (");
        ins.append(" SELECT * FROM av_oficina WHERE data_av BETWEEN '").append(datainit).append("' AND '").append(datafim).append("')");
        ins.append(" AS foo WHERE s.placa = foo.placa AND s.data_ini = foo.data_serv)");
        ins.append("AS foo2 WHERE v.placa = foo2.placa;");

        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        //adiciona ao vetor resultado os servicos em oficina ainda nao avaliados pelo cliente
        while (rs.next()) {
            placa = rs.getString(1);
            data_serv = rs.getString(2);

            ins = new StringBuilder();
            ins.append("SELECT cod_serv,c.cpf_cliente,nome_cliente,nome_serv,placa FROM cliente c, (");//
            ins.append("SELECT cod_serv,nome_serv,v.placa,cpf_cliente FROM veiculo v, (");//
            ins.append("SELECT l.cod_serv,nome_serv,placa FROM lista_servicos l, (");//
            ins.append("SELECT * FROM servicos_realizados WHERE placa = '").append(rs.getString(1)).append("' AND data_serv = '").append(rs.getString(3)).append("')");
            ins.append("AS foo WHERE l.cod_serv = foo.cod_serv)");
            ins.append(" AS foo2 WHERE v.placa = foo2.placa) ");
            ins.append("AS foo3 WHERE c.cpf_cliente = foo3.cpf_cliente");
            st = myConnection.createStatement();
            st.execute(ins.toString());
            ResultSet rsserv = st.getResultSet();
            //cria um vetor com todos os tipos de servicos realizados em um servico na oficina
            serv_realizados = new Vector();
            String nome_cliente = null;
            String cpf_cliente = null;
            while (rsserv.next()) {
                TipoServRealizadoOficina sr = new TipoServRealizadoOficina(rsserv.getString(1), rsserv.getString(4));
                nome_cliente = rsserv.getString(3);
                cpf_cliente = rsserv.getString(2);
                serv_realizados.add(sr);
            }
            ServicoOficina serv = new ServicoOficina(rs.getString(1), rs.getString(3), rs.getString(4),rs.getString(2) , serv_realizados);
            AvOficina av = new AvOficina(serv, rs.getInt("prob_res"), cpf_cliente, rs.getString("data_av"), rs.getFloat("nota_serv"), rs.getString("sugestao"), rs.getString("cod_av"),nome_cliente);
            res.add(av);
        }

        return res;
    }
    
                
        public Vector buscaTodasAvVendasFunc(String cpf_vendedor) throws SQLException {
        Vector res = new Vector();
        AvVenda Av;
        Venda v;
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT cod_av, nota_venda, sugestao_venda, data_av, av.cpf_cliente, av.cpf_vendedor, av.data_venda, tipo_venda, descr_venda ");
        ins.append("FROM av_venda av, venda v ");
        ins.append(" WHERE av.cpf_vendedor = '").append(cpf_vendedor).append("'");
        ins.append(" AND av.cpf_cliente = v.cpf_cliente");
        ins.append(" AND av.cpf_vendedor = v.cpf_vendedor");
        ins.append(" AND av.data_venda = v.data_venda;");
        st.execute(ins.toString());

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            ResultSet rsnomevendedor,rsnomecliente;
            st = myConnection.createStatement();
            st.execute("SELECT nome_vendedor FROM funcionario_vendedor WHERE cpf_vendedor = '" + rs.getString("cpf_vendedor") + "';");
            rsnomevendedor = st.getResultSet();
            rsnomevendedor.next();
            st = myConnection.createStatement();
            st.execute("SELECT nome_cliente FROM cliente WHERE cpf_cliente = '" + rs.getString("cpf_cliente") + "';");
            rsnomecliente = st.getResultSet();
            rsnomecliente.next();
            
            
            v = new Venda(rs.getString("cpf_vendedor"), rs.getString("cpf_cliente"), rs.getString("data_venda"), rs.getString("tipo_venda"), rs.getString("descr_venda"), rsnomevendedor.getString(1));
            Av = new AvVenda(rs.getString("cod_av"), rs.getFloat("nota_venda"), rs.getString("sugestao_venda"), rs.getString("data_av"), rs.getString("cpf_cliente"), rs.getString("cpf_vendedor"), rs.getString("data_venda"), rsnomevendedor.getString(1),rsnomecliente.getString(1), v);
            res.addElement(Av);
        }
        return res;
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
            ResultSet rsnomevendedor,rsnomecliente;
            st = myConnection.createStatement();
            st.execute("SELECT nome_vendedor FROM funcionario_vendedor WHERE cpf_vendedor = '" + rs.getString("cpf_vendedor") + "';");
            rsnomevendedor = st.getResultSet();
            rsnomevendedor.next();
            st = myConnection.createStatement();
            st.execute("SELECT nome_cliente FROM cliente WHERE cpf_cliente = '" + rs.getString("cpf_cliente") + "';");
            rsnomecliente = st.getResultSet();
            rsnomecliente.next();
            
            
            v = new Venda(rs.getString("cpf_vendedor"), rs.getString("cpf_cliente"), rs.getString("data_venda"), rs.getString("tipo_venda"), rs.getString("descr_venda"), rsnomevendedor.getString(1));
            Av = new AvVenda(rs.getString("cod_av"), rs.getFloat("nota_venda"), rs.getString("sugestao_venda"), rs.getString("data_av"), rs.getString("cpf_cliente"), rs.getString("cpf_vendedor"), rs.getString("data_venda"), rsnomevendedor.getString(1),rsnomecliente.getString(1), v);
            res.addElement(Av);
        }
        return res;
    }

    public Vector buscaTodasAvAtendimentoFunc(String cpf_atendente) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Vector res = new Vector();
        AvAtendimento Av;
        Atendimento a;
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT cod_av, nota_atend, sugestao, data_av, av.cpf_cliente, av.cpf_atendente, a.data_ini, a.data_fim, probl_res, dresc_prob");
        ins.append(" FROM av_atendimento av, atendimento a");
        ins.append(" WHERE av.cpf_atendente = '").append(cpf_atendente).append("'");
        ins.append(" AND av.cpf_cliente = a.cpf_cliente");
        ins.append(" AND av.cpf_atendente = a.cpf_atendente");
        ins.append(" AND av.data_atend = a.data_ini;");
        st.execute(ins.toString());

        ResultSet rs = st.getResultSet();

        while (rs.next()) {
            ResultSet rsnomeatendente,rsnomecliente;
            st = myConnection.createStatement();
            st.execute("SELECT nome_atendente FROM funcionario_atendente WHERE cpf_atendente = '" + rs.getString("cpf_atendente") + "';");
            rsnomeatendente = st.getResultSet();
            rsnomeatendente.next();
            st = myConnection.createStatement();
            st.execute("SELECT nome_cliente FROM cliente WHERE cpf_cliente = '" + rs.getString("cpf_cliente") + "';");
            rsnomecliente = st.getResultSet();
            rsnomecliente.next();
            
            a = new Atendimento(rs.getString("cpf_cliente"), rs.getString("cpf_atendente"), rs.getString("data_ini"), rs.getString("data_fim"), rs.getString("dresc_prob"), rsnomeatendente.getString(1));
            Av = new AvAtendimento(rs.getString("cpf_cliente"), rs.getString("cpf_atendente"), rs.getString("data_av"), rs.getString("data_ini"), rs.getFloat("nota_atend"), rs.getString("sugestao"), a, rs.getInt("probl_res"), rsnomeatendente.getString(1),rsnomecliente.getString(1));
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
            ResultSet rsnomeatendente,rsnomecliente;
            
            st = myConnection.createStatement();
            st.execute("SELECT nome_atendente FROM funcionario_atendente WHERE cpf_atendente = '" + rs.getString("cpf_atendente") + "';");
            rsnomeatendente = st.getResultSet();
            rsnomeatendente.next();
            st = myConnection.createStatement();
            st.execute("SELECT nome_cliente FROM cliente WHERE cpf_cliente = '" + rs.getString("cpf_cliente") + "';");
            rsnomecliente = st.getResultSet();
            rsnomecliente.next();
            
            a = new Atendimento(cpf_cliente, rs.getString("cpf_atendente"), rs.getString("data_ini"), rs.getString("data_fim"), rs.getString("dresc_prob"), rsnomeatendente.getString(1));
            Av = new AvAtendimento(cpf_cliente, rs.getString("cpf_atendente"), rs.getString("data_av"), rs.getString("data_ini"), rs.getFloat("nota_atend"), rs.getString("sugestao"), a, rs.getInt("probl_res"), rsnomeatendente.getString(1),rsnomecliente.getString(1));
            res.addElement(Av);
        }
        return res;
    }

    public Vector buscaTodasAvOficina(String cpf) throws SQLException {
        String placa, data_serv;
        Vector res = new Vector();
        Vector serv_realizados;
        StringBuilder ins = new StringBuilder();
        ins.append("SELECT serv.placa, serv.data_ini, serv.data_fim, foo.cod_av, foo.nota_serv, foo.prob_res, foo.sugestao, foo.data_av FROM servicos serv, (");
        ins.append(" SELECT * FROM av_oficina av WHERE placa IN (");
        ins.append(" SELECT v.placa FROM veiculo v WHERE cpf_cliente = '").append(cpf).append("')");
        ins.append(" ) AS foo WHERE serv.placa = foo.placa AND serv.data_ini = foo.data_serv;");

        st.execute(ins.toString());
        ResultSet rs = st.getResultSet();
        //adiciona ao vetor resultado os servicos em oficina ainda nao avaliados pelo cliente
        while (rs.next()) {
            placa = rs.getString(1);
            data_serv = rs.getString(2);

            ins = new StringBuilder();
            ins.append("SELECT l.cod_serv, nome_serv FROM lista_servicos l, (");
            ins.append(" SELECT * FROM servicos_realizados WHERE placa = '").append(placa).append("'");
            ins.append(" AND data_serv = '").append(data_serv).append("')");
            ins.append(" AS foo WHERE l.cod_serv = foo.cod_serv;");

            st = myConnection.createStatement();
            st.execute(ins.toString());
            ResultSet rsserv = st.getResultSet();
            
            ResultSet rsnomecliente;
            
            st = myConnection.createStatement();
            st.execute("SELECT nome_cliente FROM cliente WHERE cpf_cliente = '" + cpf + "';");
            rsnomecliente = st.getResultSet();
            rsnomecliente.next();
            
            
            //cria um vetor com todos os tipos de servicos realizados em um servico na oficina
            serv_realizados = new Vector();
            while (rsserv.next()) {
                TipoServRealizadoOficina sr = new TipoServRealizadoOficina(rsserv.getString(1), rsserv.getString(2));
                serv_realizados.add(sr);
            }
            ServicoOficina serv = new ServicoOficina(rs.getString(1), rs.getString(2), rs.getString(3), cpf, serv_realizados);
            AvOficina av = new AvOficina(serv, rs.getInt("prob_res"), cpf, rs.getString("data_av"), rs.getFloat("nota_serv"), rs.getString("sugestao"), rs.getString("cod_av"),rsnomecliente.getString(1));
            res.add(av);
        }

        return res;
    }

    private String rsnomefunc(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String rsnome_atendente(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
