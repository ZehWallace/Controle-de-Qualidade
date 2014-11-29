/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author davys_000
 */
public class UICMenu extends javax.swing.JFrame {

	/**
	 * Creates new form MenuCliente
	 *
	 * @param tempc
	 * @throws java.sql.SQLException
	 */
	public UICMenu(Cliente tempc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.vendas_nao_avaliadas = new Vector();
		this.avaliacoes_vendas = new Vector();
		String temp;
		this.c = tempc;
		initComponents();
		temp = c.getCpf();
		this.numCPF.setText(temp.substring(0, 3) + "." + temp.substring(3, 6) + "." + temp.substring(6, 9) + "-" + temp.substring(9, 11));
		this.numCPF1.setText(temp.substring(0, 3) + "." + temp.substring(3, 6) + "." + temp.substring(6, 9) + "-" + temp.substring(9, 11));
		this.jLabel8.setText(c.getNome());
		this.jLabel10.setText(c.getNome());
		this.vendas_nao_avaliadas = c.obterVendas();
		this.avaliacoes_vendas = c.obterAvVendas();
		this.atendimentos_nao_avaliados = c.obterAtendimentos();
		this.avaliacoes_atendimentos = c.obterAvAtendimentos();
		this.servs_oficina_nao_avaliados = c.obterServicos();
		iniciaTables();
	}

	private void iniciaTables() throws SQLException {
		jTable1.setSelectionMode(SINGLE_SELECTION);
		jTable2.setSelectionMode(SINGLE_SELECTION);
		this.cria_model_criar_av_venda();
		this.cria_model_visualizar_av_venda();
		this.cria_model_criar_av_atendimento();
		this.cria_model_visualizar_av_atendimento();
		this.cria_model_criar_av_serv_oficina();
	}

	private void cria_model_criar_av_venda() {
		int i;
		//magica
		String rows[] = vendas_nao_avaliadas.toString().replace("[", "").replace("]", "").split(",");
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : rows) {
			row = row.trim();  //UPDATE
			Vector<String> data = new Vector<String>();
			data.addAll(Arrays.asList(row.split("@")));
			dataVector.add(data);
		}
		Vector<String> header = new Vector<String>(2);
		header.add("Tipo da Compra");
		header.add("Data");
		header.add("Vendedor");
		criar_av_venda_model = new DefaultTableModel(dataVector, header);
	}

	private void cria_model_visualizar_av_venda() {
		//magica
		String rows[] = avaliacoes_vendas.toString().replace("[", "").replace("]", "").split(",");
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : rows) {
			row = row.trim();  //UPDATE
			Vector<String> data = new Vector<String>();
			data.addAll(Arrays.asList(row.split("@")));
			dataVector.add(data);
		}
		Vector<String> header = new Vector<String>(2);
		header.add("Tipo da Compra");
		header.add("Data Avaliação");
		header.add("Vendedor");
		header.add("Nota");
		visualizar_av_venda_model = new DefaultTableModel(dataVector, header);
	}

	private void cria_model_criar_av_atendimento() {
		//magica
		String rows[] = atendimentos_nao_avaliados.toString().replace("[", "").replace("]", "").split(",");
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : rows) {
			row = row.trim();  //UPDATE
			Vector<String> data = new Vector<String>();
			data.addAll(Arrays.asList(row.split("@")));
			dataVector.add(data);
		}
		Vector<String> header = new Vector<String>(2);
		header.add("Nome Atendente");
		header.add("Data Atendimento");
		criar_av_atendimento_model = new DefaultTableModel(dataVector, header);
	}

	private void cria_model_visualizar_av_atendimento() {
		//magica
		String rows[] = avaliacoes_atendimentos.toString().replace("[", "").replace("]", "").split(",");
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : rows) {
			row = row.trim();  //UPDATE
			Vector<String> data = new Vector<String>();
			data.addAll(Arrays.asList(row.split("@")));
			dataVector.add(data);
		}
		Vector<String> header = new Vector<String>(2);
		header.add("Atendente");
		header.add("Data Avaliacao");
		header.add("Nota");
		visualizar_av_atendimentos_model = new DefaultTableModel(dataVector, header);
	}

	private void cria_model_criar_av_serv_oficina() {
		//magica
		String rows[] = servs_oficina_nao_avaliados.toString().replace("[", "").replace("]", "").split(",");
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		for (String row : rows) {
			row = row.trim();  //UPDATE
			Vector<String> data = new Vector<String>();
			data.addAll(Arrays.asList(row.split("@")));
			dataVector.add(data);
		}
		Vector<String> header = new Vector<String>(2);
		header.add("Placa");
		header.add("Data");
		criar_av_serv_oficina_model = new DefaultTableModel(dataVector, header);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cpfLalbel = new javax.swing.JLabel();
        numCPF = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        comboLabel = new javax.swing.JLabel();
        select = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int vColIndex){
                return false;
            }
        };
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cpfLalbel1 = new javax.swing.JLabel();
        numCPF1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        comboLabel1 = new javax.swing.JLabel();
        select1 = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int vColIndex){
                return false;
            }
        };
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jLabel1.setText("Nome:");
        jPanel8.add(jLabel1);

        jLabel8.setForeground(new java.awt.Color(0, 153, 51));
        jLabel8.setText("nome");
        jPanel8.add(jLabel8);

        cpfLalbel.setText("CPF:");
        cpfLalbel.setAlignmentY(0.8F);
        jPanel8.add(cpfLalbel);

        numCPF.setForeground(new java.awt.Color(0, 153, 51));
        numCPF.setText("cpf");
        jPanel8.add(numCPF);

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 395, 30));

        comboLabel.setText("Selecione a Avaliação:");
        jPanel6.add(comboLabel);

        select.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"", "Compras", "Oficina", "Atendimento"}));
        select.setToolTipText("Selecione o que você deseja avaliar...");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel6.add(select);

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 395, 30));

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jPanel7.add(jLabel2);

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 395, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Avaliar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 273, 395, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 400, 170));

        jTabbedPane1.addTab("Fazer Avaliação", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        jLabel9.setText("Nome:");
        jPanel1.add(jLabel9);

        jLabel10.setForeground(new java.awt.Color(0, 153, 51));
        jLabel10.setText("nome");
        jPanel1.add(jLabel10);

        cpfLalbel1.setText("CPF:");
        cpfLalbel1.setAlignmentY(0.8F);
        jPanel1.add(cpfLalbel1);

        numCPF1.setForeground(new java.awt.Color(0, 153, 51));
        numCPF1.setText("cpf");
        jPanel1.add(numCPF1);

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 395, 30));

        comboLabel1.setText("Selecione a Avaliação:");
        jPanel9.add(comboLabel1);

        select1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"", "Compras", "Oficina", "Atendimento"}));
        select1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select1ActionPerformed(evt);
            }
        });
        jPanel9.add(select1);

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 395, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Visualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton2);

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 273, 395, 40));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 400, 170));

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jPanel11.add(jLabel3);

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 395, 30));

        jTabbedPane1.addTab("Visualizar Avaliação", jPanel5);

        jPanel2.setLayout(new java.awt.GridLayout(5, 0));
        jPanel2.add(jPanel12);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel14.setText("Deseja voltar ao menu inicial?");
        jPanel29.add(jLabel14);

        jPanel2.add(jPanel29);

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton9.setText("Confirmar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton9);

        jPanel2.add(jPanel30);

        jTabbedPane1.addTab("Sair", jPanel2);

        getContentPane().add(jTabbedPane1);

        setSize(new java.awt.Dimension(416, 381));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
		// TODO add your handling code here:
		int opc;

		opc = select.getSelectedIndex();

		if (opc == 0) {

		} else if (opc == 1) {
			jTable1.setModel(criar_av_venda_model);
		} else if (opc == 2) {
			jTable1.setModel(criar_av_serv_oficina_model);
		} else if (opc == 3) {
			jTable1.setModel(criar_av_atendimento_model);
		}
    }//GEN-LAST:event_selectActionPerformed

    private void select1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select1ActionPerformed
		// TODO add your handling code here:
		int opc;

		opc = select1.getSelectedIndex();

		if (opc == 0) {

		} else if (opc == 1) {
			System.out.println("asjdasd");
			jTable2.setModel(visualizar_av_venda_model);
		} else if (opc == 2) {

		} else if (opc == 3) {
			jTable2.setModel(visualizar_av_atendimentos_model);
		}
    }//GEN-LAST:event_select1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
		new UILogin().setVisible(true);
		this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		int row = jTable1.getSelectedRow();
		int opc = select.getSelectedIndex();
		jLabel2.setText("");
		if (vendas_nao_avaliadas.size() > 0 && opc == 1 && row != -1) {
			Venda v = (Venda) vendas_nao_avaliadas.get(row);
			new UIAvVenda(c, v).setVisible(true);
			this.dispose();
		} else if (servs_oficina_nao_avaliados.size() > 0 && opc == 2 && row != -1) {
			ServicoOficina s = (ServicoOficina) servs_oficina_nao_avaliados.get(row);
			new UIAvOficina(c, s).setVisible(true);
			this.dispose();
		} else if (atendimentos_nao_avaliados.size() > 0 && opc == 3 && row != -1) {
			Atendimento a = (Atendimento) atendimentos_nao_avaliados.get(row);
			new UIAvAtendimento(c, a).setVisible(true);
			this.dispose();
		} else {
			jLabel2.setText("Nenhum item selecionado!");
		}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		int row = jTable2.getSelectedRow();
		int opc = select1.getSelectedIndex();
		jLabel3.setText("");
		if (avaliacoes_vendas.size() > 0 && opc == 1 && row != -1) {
			AvVenda av = (AvVenda) avaliacoes_vendas.get(row);
			new UIVisAvVenda(c, av).setVisible(true);
		} else if (avaliacoes_atendimentos.size() > 0 && opc == 3 && row != -1) {
			AvAtendimento av = (AvAtendimento) avaliacoes_atendimentos.get(row);
			new UIVisAvAtendimento(c, av).setVisible(true);
		} else {
			jLabel3.setText("Nenhum item selecionado!");
		}
    }//GEN-LAST:event_jButton2ActionPerformed

	/**
	 * @param args the command line arguments
	 */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel comboLabel;
    private javax.swing.JLabel comboLabel1;
    private javax.swing.JLabel cpfLalbel;
    private javax.swing.JLabel cpfLalbel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel numCPF;
    private javax.swing.JLabel numCPF1;
    private javax.swing.JComboBox select;
    private javax.swing.JComboBox select1;
    // End of variables declaration//GEN-END:variables
    private Cliente c;
	private TableModel criar_av_venda_model;
	private TableModel visualizar_av_venda_model;
	private TableModel criar_av_atendimento_model;
	private TableModel visualizar_av_atendimentos_model;
	private TableModel criar_av_serv_oficina_model;
	private Vector vendas_nao_avaliadas;
	private Vector avaliacoes_vendas;
	private Vector atendimentos_nao_avaliados;
	private Vector avaliacoes_atendimentos;
	private Vector servs_oficina_nao_avaliados;
}
