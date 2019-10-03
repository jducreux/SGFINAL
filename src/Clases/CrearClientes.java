/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Cheques;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Interfaces.Clientes;
import Interfaces.Facturacion;
import javax.swing.table.DefaultTableModel;
import Interfaces.buscarclientes;
import java.awt.HeadlessException;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author Juan
 */
public class CrearClientes {
 Connection conectar;
 PreparedStatement guardarusuario;
 PreparedStatement cargar,cargar2,cargar3,cargar4;
 ResultSet rs,rs2,rs4;
 DefaultTableModel tabla = new DefaultTableModel(); 
 Object[] filas = new Object[5];
 
    public CrearClientes(){
  // tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
        }
      
  public void guardar(Integer idClientes, String Nombre, String Apellido, String Cedula, String Direccion, String Telefono1, String Telefono2, String Credito,String Imagen, String Estado, String Audito1,String Audito2,String Fecha1,String Fecha2){
     

             try {
                conectar conect = new conectar(); 
                 conect.conexion();
            guardarusuario=conect.con.prepareStatement("INSERT INTO clientes (idClientes, Nombre, Apellido, Cedula, Direccion,Telefono1, Telefono2,Credito,Imagen,Estado,Audito1,Audito2,Fecha1,Fecha2 ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            guardarusuario.setInt(1, idClientes);
            guardarusuario.setString(2, Nombre);
            guardarusuario.setString(3, Apellido);
            guardarusuario.setString(4, Cedula);
            guardarusuario.setString(5, Direccion);
            guardarusuario.setString(6, Telefono1);
            guardarusuario.setString(7, Telefono2);
            guardarusuario.setString(8, Credito);
            guardarusuario.setString(9, Imagen);
            guardarusuario.setString(10, Estado);
            guardarusuario.setString(11, Audito1);
            guardarusuario.setString(12, Audito2);
            guardarusuario.setString(13, Fecha1);
            guardarusuario.setString(14, Fecha2);
            guardarusuario.execute();
            conect.desconectar();
            JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente");
            Date date = new Date();
            Clientes.jDateChooserFecha1.setDate(date);
            Clientes.jTextFieldIDcliente.setText("");
            Clientes.jTextFieldNombre.setText("");
            Clientes.jTextFieldApellido.setText("");
            Clientes.jTextFieldCedula.setText("");
            Clientes.jTextFieldDireccion.setText("");
            Clientes.jTextFieldTelefono1.setText("");
            Clientes.jTextFieldTelefono2.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"El Registro no se ha guardado. Es probable que el codigo este Duplicado", "Registro Existente", JOptionPane.WARNING_MESSAGE);
                ex= null;
            }
             
        
    }
    
  public void buscarpornombre(String nombre, String apellido){
     try {
         tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
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
     consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM clientes where Nombre LIKE'"+ nombre +"%' OR Apellido LIKE'"+ apellido +"%' ORDER BY Nombre";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idClientes");
                    filas[1]=rs.getString("Cedula");
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
         tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
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

         consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM clientes ORDER BY Nombre";
  
     
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idClientes");
                    filas[1]=rs.getString("Cedula");
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
  
   public void buscarvendedores(){
     try {
         tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
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
      String  dia = Integer.toString(Cheques.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Cheques.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Cheques.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String Fecha = (year + "-" + mes+ "-" + dia); 
     // creamos la consulta

      consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM `sg-soft`.entradas, `sg-soft`.clientes WHERE idClientes = CodCliente AND Fecha = '"+Fecha+"' ORDER BY Nombre";

     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idClientes");
                    filas[1]=rs.getString("Cedula");
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
      
public void buscarcompradores(){
     try {
         tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
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
      String  dia = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String Fecha = (year + "-" + mes+ "-" + dia); 
     // creamos la consulta

      consulta="SELECT DISTINCT idClientes,Cedula, Nombre, Apellido, Direccion FROM `sg-soft`.subastas, `sg-soft`.clientes WHERE idClientes = CodComprador AND Fecha = '"+Fecha+"' ORDER BY Nombre";

     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idClientes");
                    filas[1]=rs.getString("Cedula");
                    filas[2]=rs.getString("Nombre");
                    filas[3]=rs.getString("Apellido");
                    filas[4]=rs.getString("Direccion");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"No hay registro de compradores para el dia de hoy", "Registros",JOptionPane.WARNING_MESSAGE);
   ex=null;
   }
     
     } 

  public void buscarporcedula( String Cedula){
     try {
         tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
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
     consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM clientes where Cedula LIKE'"+ Cedula +"%' ORDER BY Nombre";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idClientes");
                    filas[1]=rs.getString("Cedula");
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
  
   public void buscarporcodigo( String Codigo){
     try {
         tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
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

     consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM clientes where idClientes LIKE'"+ Codigo +"%' ORDER BY idClientes";
  
//pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idClientes");
                    filas[1]=rs.getString("Cedula");
                    filas[2]=rs.getString("Nombre");
                    filas[3]=rs.getString("Apellido");
                    filas[4]=rs.getString("Direccion");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (HeadlessException | SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
    
  public void buscarparaeditar(Integer Codigo){
  try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     
     // creamos la consulta
     consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion,Telefono1,Telefono2,Credito,Estado FROM clientes where idClientes ='"+ Codigo +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){

                            Clientes.jTextFieldIDcliente.setText(String.valueOf(rs2.getInt("idClientes")) );                
                            Clientes.jTextFieldCedula.setText(rs2.getString("Cedula"));
                            Clientes.jTextFieldNombre.setText(rs2.getString("Nombre"));
                            Clientes.jTextFieldApellido.setText(rs2.getString("Apellido"));
                            Clientes.jTextFieldDireccion.setText(rs2.getString("Direccion"));
                            Clientes.jTextFieldTelefono1.setText(rs2.getString("Telefono1"));
                            Clientes.jTextFieldTelefono2.setText(rs2.getString("Telefono2"));
                            Clientes.jComboBoxActivo.setSelectedItem(rs2.getString("Estado"));
                            Clientes.jComboBoxCredito.setSelectedItem(rs2.getString("Credito"));
                            //imagen pendiente 
                            Clientes.jButtonEditar.setEnabled(true);
                            Clientes.jButtonGuardar.setEnabled(false);
                            Clientes.jButtonEliminar.setEnabled(true);
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
     
  public void eliminarcliente(Integer Codigo){
  try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    // creamos la consulta
     consulta="DELETE FROM clientes where idClientes = ?";
     //pasamos la consulta al preparestatement
     cargar4=conect.con.prepareStatement(consulta);
     //pasamos al resulset la consulta preparada y ejecutamos
     cargar4.setInt(1, Codigo);  
     cargar4.execute();
    conect.desconectar();          
    JOptionPane.showMessageDialog(null,"Registro Eliminado Satisfactoriamente");
    Clientes.jButtonEditar.setEnabled(false);
    Clientes.jButtonEliminar.setEnabled(false);
    Clientes.jButtonGuardar.setEnabled(true);
    Date date = new Date();
            Clientes.jDateChooserFecha1.setDate(date);
            Clientes.jTextFieldIDcliente.setText("");
            Clientes.jTextFieldNombre.setText("");
            Clientes.jTextFieldApellido.setText("");
            Clientes.jTextFieldCedula.setText("");
            Clientes.jTextFieldDireccion.setText("");
            Clientes.jTextFieldTelefono1.setText("");
            Clientes.jTextFieldTelefono2.setText("");
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
  
  
  }
   
  public void editarclientes(Integer idClientes,String Nombre, String Apellido, String Cedula, String Direccion, String Telefono1, String Telefono2, String Credito,String Imagen, String Estado,String Audito2,String Fecha2){
       
        try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();  
    
     // creamos la consulta
     consulta="UPDATE clientes SET Nombre =?,Apellido=?,Cedula=?,Direccion=?,Telefono1=?,Telefono2=?,Credito=?,Imagen=?,Estado=?,Audito2=?,Fecha2=?  WHERE idClientes= ? ";
    //pasamos la consulta al preparestatement
    cargar3=conect.con.prepareStatement(consulta);
    cargar3.setString(1, Nombre);
    cargar3.setString(2, Apellido);
    cargar3.setString(3, Cedula);
    cargar3.setString(4, Direccion);
    cargar3.setString(5, Telefono1);
    cargar3.setString(6, Telefono2);
    cargar3.setString(7, Credito);
    cargar3.setString(8, Imagen);
    cargar3.setString(9, Estado);
    cargar3.setString(10, Audito2);
    cargar3.setString(11, Fecha2);
    cargar3.setInt(12, idClientes);
    cargar3.executeUpdate();    
    conect.desconectar(); 
    JOptionPane.showMessageDialog(null, "Registro Editado Satisfactoriamente");
        }catch(SQLException ex){
            
       JOptionPane.showMessageDialog(null,"Error" +ex);  
        
        }
       
   } 
    
}
