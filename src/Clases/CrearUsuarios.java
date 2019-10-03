/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Interfaces.BuscarU;
import Interfaces.Usuarios;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Juan
 */


public class CrearUsuarios {

    Integer ultimoreg;
    Connection conectar;
    PreparedStatement guardarusuario, uregistro, guardaracceso;
    String idusuario,nombre,apellido,clave,tipodeusuario,estado,telefono,celular,correo,direccion, ruta, codigo;
 PreparedStatement cargar,cargar2,cargar3,cargar4;
 ResultSet rs,rs2,rs4,rsregistro;
 DefaultTableModel tabla = new DefaultTableModel(); 
 Object[] filas = new Object[5]; 
    
    public CrearUsuarios(){
  
    }
    
    public void guardarusuario(){
    
        try {
                   
        codigo = Usuarios.txtcodigo.getText();
        idusuario=Usuarios.txtusuario.getText();
        nombre=Usuarios.txtnombre.getText();
        apellido=Usuarios.txtapellido.getText();
        clave=Usuarios.txtcontraseña.getText();
        tipodeusuario= Usuarios.cmb_tipousuario.getSelectedItem().toString();
        estado=Usuarios.cmb_estado.getSelectedItem().toString();
        telefono=Usuarios.txttelefono.getText();
        celular=Usuarios.txtcelular.getText();
        correo=Usuarios.txtcorreo.getText();
        direccion=Usuarios.txtdireccion.getText();
        
        conectar conexcio = new conectar(); 
        conexcio.conexion();
        guardarusuario=conexcio.con.prepareStatement("INSERT INTO usuarios (Usuario, Contraseña, Nombre, Apellido, Direccion, Correo, TipoUsuario, Telefono1, Telefono2, Estado, idUsuarios) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        guardarusuario.setString(1, idusuario);
        guardarusuario.setString(2, clave);
        guardarusuario.setString(3, nombre);
        guardarusuario.setString(4, apellido);
        guardarusuario.setString(5, direccion);
        guardarusuario.setString(6, correo);
        guardarusuario.setString(7, tipodeusuario);
        guardarusuario.setString(8, telefono);
        guardarusuario.setString(9, celular);
        guardarusuario.setString(10, estado);
        guardarusuario.setString(11, codigo);

        guardarusuario.execute();
        
        guardaracceso=conexcio.con.prepareStatement("INSERT INTO Accesos (Usuario, MA01,MA02,MA03,MB01,MB02,MB03,MB04,MB05,MB06,MR01,MR02,MR03,MR04,"
                + "MR05,MC01,BP01,BP02,BP03,BP04,BP05,BP06) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        guardaracceso.setString(1, idusuario);
        guardaracceso.setInt(2, 1);
        guardaracceso.setInt(3, 1);
        guardaracceso.setInt(4, 1);
        guardaracceso.setInt(5, 1);
        guardaracceso.setInt(6, 1);
        guardaracceso.setInt(7, 1);
        guardaracceso.setInt(8, 1);
        guardaracceso.setInt(9, 1);
        guardaracceso.setInt(10, 1);
        guardaracceso.setInt(11, 1);
        guardaracceso.setInt(12, 1);
        guardaracceso.setInt(13, 1);
        guardaracceso.setInt(14, 1);
        guardaracceso.setInt(15, 1);
        guardaracceso.setInt(16, 1);
        guardaracceso.setInt(17, 1);
        guardaracceso.setInt(18, 1);
        guardaracceso.setInt(19, 1); 
        guardaracceso.setInt(20, 1);
        guardaracceso.setInt(21, 1);
        guardaracceso.setInt(22, 1);

        guardaracceso.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error: El usuario Ya Existe");
            
        }
        
    
    }
    
    
    public void actualizarusuario(){
    
        try {
                   
        codigo = Usuarios.txtcodigo.getText();
        idusuario=Usuarios.txtusuario.getText();
        nombre=Usuarios.txtnombre.getText();
        apellido=Usuarios.txtapellido.getText();
        clave=Usuarios.txtcontraseña.getText();
        tipodeusuario= Usuarios.cmb_tipousuario.getSelectedItem().toString();
        estado=Usuarios.cmb_estado.getSelectedItem().toString();
        telefono=Usuarios.txttelefono.getText();
        celular=Usuarios.txtcelular.getText();
        correo=Usuarios.txtcorreo.getText();
        direccion=Usuarios.txtdireccion.getText();
        
        conectar conexcio = new conectar(); 
        conexcio.conexion();
        guardarusuario=conexcio.con.prepareStatement("UPDATE Usuarios SET Usuario=?, Contraseña=?, Nombre=?, Apellido=?, Direccion=?, Correo=?, TipoUsuario=?, Telefono1=?, Telefono2=?, Estado=? WHERE idUsuarios=? ");
        guardarusuario.setString(1, idusuario);
        guardarusuario.setString(2, clave);
        guardarusuario.setString(3, nombre);
        guardarusuario.setString(4, apellido);
        guardarusuario.setString(5, direccion);
        guardarusuario.setString(6, correo);
        guardarusuario.setString(7, tipodeusuario);
        guardarusuario.setString(8, telefono);
        guardarusuario.setString(9, celular);
        guardarusuario.setString(10, estado);
        guardarusuario.setString(11, codigo);

        guardarusuario.executeUpdate();  
        JOptionPane.showMessageDialog(null, "Registro Actualizado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
  
        
    
    }
    public void buscarpornombre(String nombre){
     try {
         tabla = (DefaultTableModel) BuscarU.Tbl_usuarios.getModel();
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
     //--------limpiar tabla------
      try {
            if (tabla != null) {
                while (tabla.getRowCount() > 0) {
                    tabla.removeRow(0);
                }
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }
        //-----hasta aki limpiar tabla-----
     
     // creamos la consulta
     consulta="SELECT idUsuarios,Usuario, Nombre, Apellido, Direccion FROM Usuarios where Nombre LIKE'"+ nombre +"%' ORDER BY idUsuarios";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idUsuarios");
                    filas[1]=rs.getString("Usuario");
                    filas[2]=rs.getString("Nombre");
                    filas[3]=rs.getString("Apellido");
                    filas[4]=rs.getString("Direccion");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     }
    
    
    public void buscarporcodigo(String codigo){
     try {
         tabla = (DefaultTableModel) BuscarU.Tbl_usuarios.getModel();
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
     //--------limpiar tabla------
      try {
            if (tabla != null) {
                while (tabla.getRowCount() > 0) {
                    tabla.removeRow(0);
                }
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }
        //-----hasta aki limpiar tabla-----
     
     // creamos la consulta
     consulta="SELECT idUsuarios,Usuario, Nombre, Apellido, Direccion FROM Usuarios where idUsuarios LIKE'"+ codigo +"%' ORDER BY idUsuarios";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idUsuarios");
                    filas[1]=rs.getString("Usuario");
                    filas[2]=rs.getString("Nombre");
                    filas[3]=rs.getString("Apellido");
                    filas[4]=rs.getString("Direccion");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     }
    
     public void buscartodos(){
     try {
         tabla = (DefaultTableModel) BuscarU.Tbl_usuarios.getModel();
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
     //--------limpiar tabla------
      try {
            if (tabla != null) {
                while (tabla.getRowCount() > 0) {
                    tabla.removeRow(0);
                }
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }
        //-----hasta aki limpiar tabla-----
     
     // creamos la consulta
     consulta="SELECT idUsuarios,Usuario, Nombre, Apellido, Direccion FROM Usuarios ORDER BY idUsuarios";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idUsuarios");
                    filas[1]=rs.getString("Usuario");
                    filas[2]=rs.getString("Nombre");
                    filas[3]=rs.getString("Apellido");
                    filas[4]=rs.getString("Direccion");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     } 
    
      public void buscarparaeditar(Integer Codigo){
  try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     
     // creamos la consulta
     consulta="SELECT * FROM Usuarios where idUsuarios ='"+ Codigo +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){

                            Usuarios.txtcodigo.setText(String.valueOf(rs2.getInt("idUsuarios")) );                
                            Usuarios.txtusuario.setText(rs2.getString("Usuario"));
                            Usuarios.txtcontraseña.setText(rs2.getString("Contraseña"));
                            Usuarios.txtnombre.setText(rs2.getString("Nombre"));
                            Usuarios.txtapellido.setText(rs2.getString("Apellido"));
                            Usuarios.txtdireccion.setText(rs2.getString("Direccion"));
                            Usuarios.txtcorreo.setText(rs2.getString("Correo"));
                            Usuarios.txttelefono.setText(rs2.getString("Telefono1"));
                            Usuarios.txtcelular.setText(rs2.getString("Telefono2"));
                            Usuarios.cmb_tipousuario.setSelectedItem(rs2.getString("TipoUsuario"));
                            Usuarios.cmb_estado.setSelectedItem(rs2.getString("Estado"));
                            //imagen pendiente 

                            Usuarios.txtcontraseña.setEnabled(true);
                            Usuarios.txtnombre.setEnabled(true);
                            Usuarios.txtapellido.setEnabled(true);
                            Usuarios.txtdireccion.setEnabled(true);
                            Usuarios.txtcorreo.setEnabled(true);
                            Usuarios.txttelefono.setEnabled(true);
                            Usuarios.txtcelular.setEnabled(true);
                            Usuarios.cmb_tipousuario.setEnabled(true);
                            Usuarios.cmb_estado.setEnabled(true);
                            Usuarios.jButton2.setEnabled(true);
                            Usuarios.btn_guardar.setEnabled(false);
                            Usuarios.btn_eliminar.setEnabled(true);
                            if ("ADMINISTRADOR".equals(rs2.getString("TipoUsuario"))){
                                     // No lo habilita                      
                            }else{
                                           Usuarios.accesos.setEnabled(true);
                            }

                            rs2.close();
                            conect.desconectar();
                          }
           else{
           JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar"  ); 
           conect.desconectar();
               }
    
    
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
  
  }
  
      
      public Integer buscarultimoregistro(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
     // creamos la consulta
     consulta="SELECT MAX(idUsuarios) FROM usuarios ";
     //pasamos la consulta al preparestatement
    uregistro=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsregistro=uregistro.executeQuery(consulta);
     //recorremos el resulset
    rsregistro.next();
        
               ultimoreg=rsregistro.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
   }catch (SQLException ex1){
   JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
   }finally{
         try {
             uregistro.close();
             rsregistro.close();
             conect.desconectar();
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
         }
   
     }   
    return ultimoreg;
    }
    }
