/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.CrearUsuarios;
import Clases.conectar;
import static Interfaces.Usuarios.txtcontraseña;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Interfaces.Permisos;
/**
 *
 * @author Juan
 */
public class Usuarios extends javax.swing.JFrame {
  public static Integer listacliente3;
  Connection conectar;
  PreparedStatement eliminarusuario;
  String codigo;
  public static Integer listavalida;

    /**
     * Creates new form Usuarios
     */
    public Usuarios() {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        txtusuario = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        cmb_tipousuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        txtcelular = new javax.swing.JTextField();
        cmb_estado = new javax.swing.JComboBox<>();
        lbl_foto = new javax.swing.JLabel();
        txtcontraseña = new javax.swing.JPasswordField();
        txtrecontraseña = new javax.swing.JPasswordField();
        accesos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btn_agregar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_listar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        btn_regresar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO DE USUARIOS");
        setMaximumSize(new java.awt.Dimension(885, 520));
        setMinimumSize(new java.awt.Dimension(885, 520));
        setPreferredSize(new java.awt.Dimension(885, 520));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("R E G I S T R O   Y   C O N S U L T A   D E   U S U A R I O S   D E L   S I S T E M A ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel1)
                .addContainerGap(209, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 11, 860, 41);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        txtcodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtcodigo.setEnabled(false);
        txtcodigo.setMaximumSize(new java.awt.Dimension(187, 25));
        txtcodigo.setMinimumSize(new java.awt.Dimension(187, 25));
        txtcodigo.setPreferredSize(new java.awt.Dimension(187, 25));
        jPanel2.add(txtcodigo);
        txtcodigo.setBounds(170, 30, 95, 25);

        txtnombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtnombre.setEnabled(false);
        txtnombre.setMaximumSize(new java.awt.Dimension(187, 25));
        txtnombre.setMinimumSize(new java.awt.Dimension(187, 25));
        txtnombre.setPreferredSize(new java.awt.Dimension(187, 25));
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        jPanel2.add(txtnombre);
        txtnombre.setBounds(170, 60, 187, 24);

        txtapellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtapellido.setEnabled(false);
        txtapellido.setMaximumSize(new java.awt.Dimension(187, 25));
        txtapellido.setMinimumSize(new java.awt.Dimension(187, 25));
        txtapellido.setPreferredSize(new java.awt.Dimension(187, 25));
        txtapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellidoActionPerformed(evt);
            }
        });
        jPanel2.add(txtapellido);
        txtapellido.setBounds(170, 100, 187, 28);

        txtusuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtusuario.setEnabled(false);
        txtusuario.setMaximumSize(new java.awt.Dimension(187, 25));
        txtusuario.setMinimumSize(new java.awt.Dimension(187, 25));
        txtusuario.setPreferredSize(new java.awt.Dimension(187, 25));
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        jPanel2.add(txtusuario);
        txtusuario.setBounds(170, 140, 187, 25);

        txtcorreo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtcorreo.setEnabled(false);
        txtcorreo.setMaximumSize(new java.awt.Dimension(187, 25));
        txtcorreo.setMinimumSize(new java.awt.Dimension(187, 25));
        txtcorreo.setPreferredSize(new java.awt.Dimension(187, 25));
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });
        jPanel2.add(txtcorreo);
        txtcorreo.setBounds(470, 60, 120, 25);

        txtdireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdireccion.setEnabled(false);
        txtdireccion.setMaximumSize(new java.awt.Dimension(187, 25));
        txtdireccion.setMinimumSize(new java.awt.Dimension(187, 25));
        txtdireccion.setPreferredSize(new java.awt.Dimension(187, 25));
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });
        jPanel2.add(txtdireccion);
        txtdireccion.setBounds(170, 260, 420, 25);

        cmb_tipousuario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cmb_tipousuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "SUPERVISOR", "USUARIO" }));
        cmb_tipousuario.setEnabled(false);
        cmb_tipousuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_tipousuarioActionPerformed(evt);
            }
        });
        jPanel2.add(cmb_tipousuario);
        cmb_tipousuario.setBounds(470, 190, 120, 28);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel2.setText("ID USUARIO:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(32, 33, 67, 13);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel3.setText("NOMBRE:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(30, 70, 47, 13);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel4.setText("APELLIDOS:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(30, 110, 63, 13);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel5.setText("DIRECCION:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(32, 266, 63, 13);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel6.setText("E-MAIL:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(380, 70, 41, 13);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel7.setText("TELEFONO:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(380, 110, 57, 13);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel8.setText("CELULAR:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(380, 150, 50, 13);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setText("TIPO USUARIO:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(380, 190, 81, 13);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel10.setText("ESTADO:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(380, 230, 70, 13);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel11.setText("CONTRASEÑA:");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(30, 180, 74, 13);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel12.setText("NOMBRE USUARIO:");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(32, 150, 98, 13);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel13.setText("REPETIR-CONTRASEÑA:");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(30, 220, 123, 10);

        txttelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txttelefono.setEnabled(false);
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        jPanel2.add(txttelefono);
        txttelefono.setBounds(470, 110, 120, 21);

        txtcelular.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtcelular.setEnabled(false);
        txtcelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcelularActionPerformed(evt);
            }
        });
        jPanel2.add(txtcelular);
        txtcelular.setBounds(470, 150, 120, 24);

        cmb_estado.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cmb_estado.setEnabled(false);
        cmb_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_estadoActionPerformed(evt);
            }
        });
        jPanel2.add(cmb_estado);
        cmb_estado.setBounds(470, 230, 120, 20);

        lbl_foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/user_permisos.png"))); // NOI18N
        jPanel2.add(lbl_foto);
        lbl_foto.setBounds(570, 0, 290, 220);

        txtcontraseña.setText("jPasswordField1");
        txtcontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraseñaActionPerformed(evt);
            }
        });
        jPanel2.add(txtcontraseña);
        txtcontraseña.setBounds(170, 170, 190, 30);

        txtrecontraseña.setText("jPasswordField2");
        txtrecontraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrecontraseñaActionPerformed(evt);
            }
        });
        jPanel2.add(txtrecontraseña);
        txtrecontraseña.setBounds(170, 210, 190, 30);

        accesos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        accesos.setText("A C C E S O S");
        accesos.setEnabled(false);
        accesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accesosActionPerformed(evt);
            }
        });
        jPanel2.add(accesos);
        accesos.setBounds(630, 250, 190, 40);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 60, 860, 305);

        jPanel3.setBackground(java.awt.Color.white);
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(null);

        btn_agregar.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Misc-New-Database-icon.png"))); // NOI18N
        btn_agregar.setText("NUEVO");
        btn_agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_agregar);
        btn_agregar.setBounds(10, 10, 90, 72);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Developmer Folder.png"))); // NOI18N
        jButton2.setText("EDITAR");
        jButton2.setEnabled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(120, 10, 110, 72);

        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Misc-Delete-Database-icon.png"))); // NOI18N
        btn_eliminar.setText("ELIMINAR");
        btn_eliminar.setEnabled(false);
        btn_eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_eliminar);
        btn_eliminar.setBounds(250, 10, 120, 72);

        btn_listar.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        btn_listar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Product-sale-report-icon.png"))); // NOI18N
        btn_listar.setText("LISTAR");
        btn_listar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_listar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_listar);
        btn_listar.setBounds(390, 10, 120, 72);

        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Save-icon.png"))); // NOI18N
        btn_guardar.setText("GUARDAR");
        btn_guardar.setEnabled(false);
        btn_guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_guardar);
        btn_guardar.setBounds(540, 10, 130, 72);

        btn_regresar.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        btn_regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Graficos/Perspective-Button-Shutdown-icon.png"))); // NOI18N
        btn_regresar.setText("SALIR");
        btn_regresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_regresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });
        jPanel3.add(btn_regresar);
        btn_regresar.setBounds(700, 10, 140, 72);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 371, 860, 100);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_regresarActionPerformed

    private void btn_listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listarActionPerformed
        BuscarU list = new BuscarU();
        list.setVisible(true);
    }//GEN-LAST:event_btn_listarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        Activar();   
        Limpiar();
          CrearUsuarios ch = new CrearUsuarios();        
        this.txtcodigo.setText(ch.buscarultimoregistro().toString());
         this.btn_guardar.setEnabled(true);
                 this.jButton2.setEnabled(false);
                 this.accesos.setEnabled(false);
                         this.btn_eliminar.setEnabled(false);
        txtnombre.requestFocus();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        try {
            if ((this.txtcontraseña.getText()).equals(this.txtrecontraseña.getText()))
            {
            if ((this.txtcodigo.getText().trim().length()==0) || (this.txtnombre.getText().trim().length()==0) || (this.txtapellido.getText().trim().length()==0)|| (this.txtusuario.getText().trim().length()==0)|| (this.txtcontraseña.getText().trim().length()==0) ) {
                JOptionPane.showMessageDialog(null, "Los Campos Con Aterisco No pueden Estar En Blanco");
            }else{    
                CrearUsuarios user = new CrearUsuarios();
                user.guardarusuario();
                Limpiar();
                CrearUsuarios ch = new CrearUsuarios();        
                this.txtcodigo.setText(ch.buscarultimoregistro().toString());
                txtnombre.requestFocus();// TODO a
                }
            }else
            {JOptionPane.showMessageDialog(null, "Contraseñas no coinciden");}
            }catch(Exception ex) {
       JOptionPane.showMessageDialog(null,"Error  = " +ex);
       }
        

    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
      
        try {                  
            codigo =Usuarios.txtcodigo.getText();
            conectar conexcio = new conectar(); 
            conexcio.conexion();
            eliminarusuario=conexcio.con.prepareStatement("DELETE FROM usuarios WHERE idUsuarios = ?");      
            eliminarusuario.setString(1, codigo);
            eliminarusuario.execute();
            JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Eliminar Error:" +ex);
        }
        
        Limpiar();
        CrearUsuarios ch = new CrearUsuarios(); 
         this.btn_guardar.setEnabled(true);
                 this.jButton2.setEnabled(false);
                         this.btn_eliminar.setEnabled(false);
       this.txtcodigo.setText(ch.buscarultimoregistro().toString());
       txtnombre.requestFocus();
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
              CrearUsuarios user = new CrearUsuarios();
        user.actualizarusuario();
        Limpiar();
         CrearUsuarios ch = new CrearUsuarios();  
                 Limpiar();
                 this.btn_guardar.setEnabled(true);
                 this.jButton2.setEnabled(false);
                  this.accesos.setEnabled(false);
                         this.btn_eliminar.setEnabled(false);
       this.txtcodigo.setText(ch.buscarultimoregistro().toString());
       txtnombre.requestFocus();
       Desactivar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
               CrearUsuarios ch = new CrearUsuarios();        
       this.txtcodigo.setText(ch.buscarultimoregistro().toString());
       txtnombre.requestFocus();// TODO add your handling code here:
       Desactivar();
               
    }//GEN-LAST:event_formWindowOpened

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txtapellido.requestFocus();
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellidoActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txtusuario.requestFocus();
    }//GEN-LAST:event_txtapellidoActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txtcontraseña.requestFocus();
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void txtcontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseñaActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txtrecontraseña.requestFocus();
    }//GEN-LAST:event_txtcontraseñaActionPerformed

    private void txtrecontraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrecontraseñaActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txtdireccion.requestFocus();
    }//GEN-LAST:event_txtrecontraseñaActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txtcorreo.requestFocus();
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txttelefono.requestFocus();
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        txtcelular.requestFocus();
    }//GEN-LAST:event_txttelefonoActionPerformed

    private void txtcelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcelularActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        cmb_tipousuario.requestFocus();
    }//GEN-LAST:event_txtcelularActionPerformed

    private void cmb_tipousuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_tipousuarioActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        cmb_estado.requestFocus();
    }//GEN-LAST:event_cmb_tipousuarioActionPerformed

    private void cmb_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_estadoActionPerformed
         evt.setSource((char) KeyEvent.VK_ENTER);
        btn_guardar.requestFocus();
    }//GEN-LAST:event_cmb_estadoActionPerformed

    private void accesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accesosActionPerformed
        new Permisos(this,true).setVisible(true);
    }//GEN-LAST:event_accesosActionPerformed

public void Activar  (){
     Usuarios.txtusuario.setEnabled(true);
     Usuarios.txtnombre.setEnabled(true);
     Usuarios.txtapellido.setEnabled(true);
     Usuarios.txtcontraseña.setEnabled(true);
     Usuarios.txtrecontraseña.setEnabled(true);
     Usuarios.txtdireccion.setEnabled(true);
     Usuarios.txtcorreo.setEnabled(true);
     Usuarios.txttelefono.setEnabled(true);
     Usuarios.txtcelular.setEnabled(true);
     Usuarios.cmb_estado.setEnabled(true);
     Usuarios.cmb_tipousuario.setEnabled(true);
     Usuarios.btn_guardar.setEnabled(true);
}

public void Desactivar  (){
     Usuarios.txtusuario.setEnabled(false);
     Usuarios.txtnombre.setEnabled(false);
     Usuarios.txtapellido.setEnabled(false);
     Usuarios.txtcontraseña.setEnabled(false);
     Usuarios.txtrecontraseña.setEnabled(false);
     Usuarios.txtdireccion.setEnabled(false);
     Usuarios.txtcorreo.setEnabled(false);
     Usuarios.txttelefono.setEnabled(false);
     Usuarios.txtcelular.setEnabled(false);
     Usuarios.cmb_estado.setEnabled(false);
     Usuarios.cmb_tipousuario.setEnabled(false);
     Usuarios.btn_guardar.setEnabled(false);
}

public void Limpiar  (){
    Usuarios.txtcodigo.setText("");
    Usuarios.txtusuario.setText("");
    Usuarios.txtnombre.setText("");
    Usuarios.txtapellido.setText("");
    Usuarios.txtcontraseña.setText("");
    Usuarios.txtrecontraseña.setText("");
    Usuarios.txtdireccion.setText("");
    Usuarios.txtcorreo.setText("");
    Usuarios.txttelefono.setText("");
    Usuarios.txtcelular.setText("");
}
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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Usuarios().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton accesos;
    public static javax.swing.JButton btn_agregar;
    public static javax.swing.JButton btn_eliminar;
    public static javax.swing.JButton btn_guardar;
    public static javax.swing.JButton btn_listar;
    public static javax.swing.JButton btn_regresar;
    public static javax.swing.JComboBox<String> cmb_estado;
    public static javax.swing.JComboBox<String> cmb_tipousuario;
    public static javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_foto;
    public static javax.swing.JTextField txtapellido;
    public static javax.swing.JTextField txtcelular;
    public static javax.swing.JTextField txtcodigo;
    public static javax.swing.JPasswordField txtcontraseña;
    public static javax.swing.JTextField txtcorreo;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtnombre;
    public static javax.swing.JPasswordField txtrecontraseña;
    public static javax.swing.JTextField txttelefono;
    public static javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
   
}
