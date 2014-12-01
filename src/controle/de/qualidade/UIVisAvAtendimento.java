/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.de.qualidade;

import java.awt.Color;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author davys_000
 */
public class UIVisAvAtendimento extends javax.swing.JFrame {

	private AvAtendimento av;
	private Cliente c;

	/**
	 * Creates new form UIVisAvAtendimento
	 *
	 * @param c
	 * @param av
	 */
	public UIVisAvAtendimento(Cliente c, AvAtendimento av) {
		this.av = av;
		this.c = c;
		Color orange = new Color(204, 102, 0);
		initComponents();
		this.setResizable(false);
		jLabel15.setText(c.getNome());
		String dIni = av.getAtendimento().getData_ini();
		String dFim = av.getAtendimento().getData_fim();
		Date data = new Date();
		Timestamp dataIni = Timestamp.valueOf(dIni);
		Timestamp dataFim = Timestamp.valueOf(dFim);

		System.out.println(dataIni);
		System.out.println(dataFim);

		long x = dataIni.getTime();
		long y = dataFim.getTime();

		Long tempo = (long) ((y - x) * 1.66666667 * 0.00001);

		jTextArea1.setEditable(false);
		jTextArea2.setEditable(false);
		jLabel13.setText(av.getNome_atendente());
		jLabel3.setText(av.getData_ini_splitted());
		if (tempo < 60) {
			jLabel11.setText(tempo.toString() + " minuto(s)");
		} else if (tempo >= 60) {
			Integer dias = 0;
			if (tempo >= 1440) {
				while (tempo >= 1440) {
					dias++;
					tempo -= 1440;
				}
			}
			Integer horas = 0;
			while (tempo >= 60) {
				horas++;
				tempo -= 60;
			}
			if (dias > 0) {
				jLabel11.setText(dias.toString() + "dia(s), " + horas.toString() + "hora(s) e " + tempo.toString() + " minuto(s)");
			} else {
				jLabel11.setText(horas.toString() + "hora(s) e " + tempo.toString() + " minuto(s)");
			}
		}
		jLabel5.setText(av.getData_splitted());
		int probl_res = av.getProbl_res();
		if (probl_res == 1) {
			//jLabel9.setForeground(Color.green);
			jLabel9.setText("Sim");
		} else if (probl_res == 2) {
			jLabel9.setForeground(orange);
			jLabel9.setText("Parcialmente");
		} else if (probl_res == 3) {
			jLabel9.setForeground(Color.red);
			jLabel9.setText("Não");
		}
		float nota = av.getNota();
		jLabel7.setText("" + nota);
		if (nota > 6) {
			//jLabel7.setForeground(Color.green);
		} else if (nota <= 6 && nota >= 4) {
			jLabel7.setForeground(orange);
		} else {
			jLabel7.setForeground(Color.red);
		}
		jTextArea2.setText(av.getSugestao());
		jTextArea1.setText(av.getAtendimento().getDescr_atend());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Avaliação de Atendimento");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 40));

        jPanel3.setLayout(new java.awt.GridLayout(4, 0));

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel14.setText("Cliente:");
        jPanel14.add(jLabel14);

        jLabel15.setText("jLabel15");
        jPanel14.add(jLabel15);

        jPanel3.add(jPanel14);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel12.setText("Atendente:");
        jPanel12.add(jLabel12);

        jLabel13.setText("José Felisberto da Silva");
        jPanel12.add(jLabel13);

        jPanel3.add(jPanel12);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel2.setText("Data do Atendimento:");
        jPanel6.add(jLabel2);

        jLabel3.setText("01/01/1849");
        jPanel6.add(jLabel3);

        jPanel3.add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel4.setText("Data da Avaliação:");
        jPanel7.add(jLabel4);

        jLabel5.setText("02/02/1850");
        jPanel7.add(jLabel5);

        jPanel3.add(jPanel7);

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 400, 110));

        jPanel4.setLayout(new java.awt.GridLayout(3, 0));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Tempo:");
        jPanel11.add(jLabel10);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("2 minuto");
        jPanel11.add(jLabel11);

        jPanel4.add(jPanel11);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Problema Resolvido:");
        jPanel10.add(jLabel8);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 0));
        jLabel9.setText("Sim");
        jPanel10.add(jLabel9);

        jPanel4.add(jPanel10);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Nota:");
        jPanel9.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setText("7.5");
        jPanel9.add(jLabel7);

        jPanel4.add(jPanel9);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 234, 400, 78));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sugestão"));

        jTextArea2.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 312, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1);

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 400, 78));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Descrição"));

        jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 400, 78));

        setSize(new java.awt.Dimension(416, 507));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
