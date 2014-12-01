/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davys_000
 */
public class UIAvAtendimento extends javax.swing.JFrame {

	private Cliente c;
	private Atendimento a;

	/**
	 * Creates new form UIAvAtendimento
	 */
	public UIAvAtendimento(Cliente c, Atendimento a) {
		this.c = c;
		this.a = a;
		initComponents();
		this.setResizable(false);
		this.jTextArea2.setEditable(false);
		this.jTextArea2.setText(a.getDescr_atend());
		this.jLabel3.setText(a.getData_ini() + " - " + a.getData_fim());

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelTitulo.setText("Avaliação do Atendimento");
        jPanel1.add(labelTitulo);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 50));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));

        jTextArea2.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        jTextArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 89, 400, 130));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tempo na Oficina"));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 0));

        jLabel3.setText("2 dia");
        jLabel3.setToolTipText("");
        jPanel3.add(jLabel3);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 400, 40));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Nota*"));
        jPanel4.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setText("0");
        jPanel2.add(jLabel1);

        jSlider1.setPaintLabels(true);
        jSlider1.setToolTipText("Dê sua nota ao serviço feito pela oficina.");
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jPanel2.add(jSlider1);

        jLabel4.setText("10");
        jPanel2.add(jLabel4);

        jPanel4.add(jPanel2);

        jLabel5.setText("5.0");
        jPanel5.add(jLabel5);

        jPanel4.add(jPanel5);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 400, 80));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sugestões"));

        jTextArea3.setColumns(20);
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setToolTipText("Deixe suas sugetões, críticas e elogios. Sua opinião é muito importante.");
        jScrollPane2.setViewportView(jTextArea3);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 400, 200));

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 614, 400, 40));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Problema Resolvido?*"));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Sim");
        jRadioButton1.setToolTipText("Seu problema foi resolvido :D");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel9.add(jRadioButton1);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Parcialmente");
        jRadioButton2.setToolTipText("Seu problema foi parcialmente resolvido : |");
        jPanel9.add(jRadioButton2);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Não");
        jRadioButton3.setToolTipText("Seu problema não foi resolvido :(");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel9.add(jRadioButton3);

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 400, 54));

        setSize(new java.awt.Dimension(416, 693));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
		float nota = (float) jSlider1.getValue() / 10;
		jLabel5.setText(String.valueOf(nota));
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		try {
			new UICMenu(c).setVisible(true);
			this.dispose();
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(UIAvOficina.class.getName()).log(Level.SEVERE, null, ex);
		}

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		int probl_res = -1;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String data = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		if (jRadioButton1.isSelected()) {
			probl_res = 1;
		} else if (jRadioButton2.isSelected()) {
			probl_res = 2;
		} else if (jRadioButton3.isSelected()) {
			probl_res = 3;
		} else {
			jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Problema Resolvido?*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(255, 0, 0)));
			return;
		}
		try {
			new AvAtendimento(c.getCpf(), a.getCpf_atendente(), data, a.getData_ini(), jSlider1.getValue() / 10, jTextArea3.getText(), a, probl_res);
			new UIAvSucesso(c).setVisible(true);
			this.dispose();
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
			Logger.getLogger(UIAvVenda.class.getName()).log(Level.SEVERE, null, ex);
			new UIAvNaoSucesso(c).setVisible(true);
			this.dispose();
		}

    }//GEN-LAST:event_jButton1ActionPerformed
	
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
