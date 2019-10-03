/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.CrearDeposito;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan
 */
public class MantDepositos extends javax.swing.JFrame {
public static String Orden;
    /**
     * Creates new form MantDepositos
     */
    public MantDepositos() {
        initComponents();
        DefaultTableModel tabla1= (DefaultTableModel) this.jTabledepositos.getModel();
        MantDepositos.jDateChooserFecha1.setDateFormatString("dd/MM/yyyy");
        MantDepositos.jDateChooserFecha2.setDateFormatString("dd/MM/yyyy");
        Date date = new Date(); 
        MantDepositos.jDateChooserFecha1.setDate(date); 
        MantDepositos.jDateChooserFecha2.setDate(date);
 
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
        jDateChooserFecha1 = new com.toedter.calendar.JDateChooser();
        jDateChooserFecha2 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        todos = new javax.swing.JRadioButton();
        conciliado = new javax.swing.JRadioButton();
        depositado = new javax.swing.JRadioButton();
        transito = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabledepositos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmdcancelar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Estado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cuenta = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);
        jPanel1.add(jDateChooserFecha1);
        jDateChooserFecha1.setBounds(570, 20, 152, 32);
        jPanel1.add(jDateChooserFecha2);
        jDateChooserFecha2.setBounds(570, 70, 152, 32);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));

        todos.setBackground(new java.awt.Color(204, 255, 255));
        todos.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        todos.setSelected(true);
        todos.setText("Todos");
        todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todosActionPerformed(evt);
            }
        });

        conciliado.setBackground(new java.awt.Color(204, 255, 255));
        conciliado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        conciliado.setText("Conciliado");
        conciliado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conciliadoActionPerformed(evt);
            }
        });

        depositado.setBackground(new java.awt.Color(204, 255, 255));
        depositado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        depositado.setText("Depositado");
        depositado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositadoActionPerformed(evt);
            }
        });

        transito.setBackground(new java.awt.Color(204, 255, 255));
        transito.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        transito.setText("Transito");
        transito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transitoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(todos)
                .addGap(18, 18, 18)
                .addComponent(depositado)
                .addGap(18, 18, 18)
                .addComponent(transito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(conciliado)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todos)
                    .addComponent(depositado)
                    .addComponent(transito)
                    .addComponent(conciliado))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(120, 10, 380, 50);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Zoom-icon.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(10, 10, 101, 49);

        jTabledepositos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Cuenta", "Descripcion", "Monto", "Estado", "Actualizar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTabledepositos);
        if (jTabledepositos.getColumnModel().getColumnCount() > 0) {
            jTabledepositos.getColumnModel().getColumn(0).setMinWidth(50);
            jTabledepositos.getColumnModel().getColumn(0).setMaxWidth(50);
            jTabledepositos.getColumnModel().getColumn(1).setMinWidth(80);
            jTabledepositos.getColumnModel().getColumn(1).setMaxWidth(80);
            jTabledepositos.getColumnModel().getColumn(2).setMinWidth(150);
            jTabledepositos.getColumnModel().getColumn(2).setMaxWidth(150);
            jTabledepositos.getColumnModel().getColumn(3).setMinWidth(210);
            jTabledepositos.getColumnModel().getColumn(3).setMaxWidth(210);
            jTabledepositos.getColumnModel().getColumn(4).setMinWidth(80);
            jTabledepositos.getColumnModel().getColumn(4).setMaxWidth(80);
            jTabledepositos.getColumnModel().getColumn(5).setMinWidth(80);
            jTabledepositos.getColumnModel().getColumn(5).setMaxWidth(80);
            jTabledepositos.getColumnModel().getColumn(6).setMinWidth(80);
            jTabledepositos.getColumnModel().getColumn(6).setMaxWidth(80);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 112, 720, 370);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("CUENTA BANCARIA:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 80, 140, 15);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Al");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(540, 80, 12, 15);

        cmdcancelar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cmdcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Login-icon.png"))); // NOI18N
        cmdcancelar.setText("Cancelar");
        cmdcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelarActionPerformed(evt);
            }
        });
        jPanel1.add(cmdcancelar);
        cmdcancelar.setBounds(600, 500, 132, 47);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Apps-system-software-update-icon.png"))); // NOI18N
        jButton3.setText("ACTUALIZAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(150, 500, 140, 50);

        Estado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Estado.setForeground(new java.awt.Color(0, 0, 255));
        Estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Depositado", "Transito", "Conciliado", "Anulado" }));
        jPanel1.add(Estado);
        Estado.setBounds(370, 500, 115, 47);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ESTADO:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(310, 510, 53, 47);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Actions-trash-empty-icon.png"))); // NOI18N
        jButton2.setText("ELIMINAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(10, 500, 130, 50);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Del:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(530, 30, 30, 15);

        jPanel1.add(cuenta);
        cuenta.setBounds(150, 70, 210, 30);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("M A N T E N I M I E N T O    D E    D E P O S I T O S   R E G I S T R A D O S");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(108, 108, 108))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        CrearDeposito carga = new CrearDeposito();
        Orden = "1";
        carga.llenarcombo();
        carga.cargardepositos();
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CrearDeposito carga1 = new CrearDeposito();
        carga1.cargardepositos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todosActionPerformed
        MantDepositos.todos.setSelected(true);
        MantDepositos.depositado.setSelected(false);
        MantDepositos.transito.setSelected(false);
        MantDepositos.conciliado.setSelected(false);     
    }//GEN-LAST:event_todosActionPerformed

    private void depositadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositadoActionPerformed
        MantDepositos.todos.setSelected(false);
        MantDepositos.depositado.setSelected(true);
        MantDepositos.transito.setSelected(false);
        MantDepositos.conciliado.setSelected(false);    
    }//GEN-LAST:event_depositadoActionPerformed

    private void transitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transitoActionPerformed
        MantDepositos.todos.setSelected(false);
        MantDepositos.depositado.setSelected(false);
        MantDepositos.transito.setSelected(true);
        MantDepositos.conciliado.setSelected(false); 
    }//GEN-LAST:event_transitoActionPerformed

    private void conciliadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conciliadoActionPerformed
        MantDepositos.todos.setSelected(false);
        MantDepositos.depositado.setSelected(false);
        MantDepositos.transito.setSelected(false);
        MantDepositos.conciliado.setSelected(true); 
    }//GEN-LAST:event_conciliadoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      int contador=0;
      
      for (int i = 0; i < MantDepositos.jTabledepositos.getRowCount(); i++) {

            if( MantDepositos.jTabledepositos.isCellSelected(i, 6)){ 
                contador = contador + 1;  
            }else{
                continue;
            }
      }   
        if (contador!=0){
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){  
                CrearDeposito crear = new CrearDeposito();
                crear.eliminardeposito();
                crear.cargardepositos();
            }
        }else{
            JOptionPane.showMessageDialog(null, "NO HAY UN REGISTRO SELECCIONADO PARA ELIMINAR", "Eliminacion", JOptionPane.WARNING_MESSAGE);   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            int contador=0;
      
      for (int i = 0; i < MantDepositos.jTabledepositos.getRowCount(); i++) {

            if( MantDepositos.jTabledepositos.isCellSelected(i, 6)){ 
                contador = contador + 1;  
            }else{
                continue;
            }
      }   
        if (contador!=0){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente actualizar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            CrearDeposito actualiza = new CrearDeposito();
            actualiza.ActualizarDepositos();
            actualiza.cargardepositos();
        }     
         }else{
            JOptionPane.showMessageDialog(null, "NO HAY REGISTRO SELECCIONADO PARA ACTUALIZAR", "Actualizacion", JOptionPane.WARNING_MESSAGE); 
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MantDepositos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MantDepositos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MantDepositos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MantDepositos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MantDepositos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> Estado;
    private javax.swing.JButton cmdcancelar;
    public static javax.swing.JRadioButton conciliado;
    public static javax.swing.JComboBox<String> cuenta;
    public static javax.swing.JRadioButton depositado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public static com.toedter.calendar.JDateChooser jDateChooserFecha1;
    public static com.toedter.calendar.JDateChooser jDateChooserFecha2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTabledepositos;
    public static javax.swing.JRadioButton todos;
    public static javax.swing.JRadioButton transito;
    // End of variables declaration//GEN-END:variables
}
