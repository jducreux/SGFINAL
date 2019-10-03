/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.BuscarProveedor;
import Interfaces.Proveedor;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Juan
 */
public class CrearProvedor {
   PreparedStatement guardarbanco, numerop, cargar, cargar2, cargar3;
    String idproveedor,nombre,Dv,Direccion,Ruc, Telefono01, Telefono02, Email, estado;
    Double montoi, montoa;
    ResultSet aux, rs, rs2, rsp;
    Integer ultimop;
    DefaultTableModel tabla = new DefaultTableModel(); 
    Object[] filas = new Object[9];
    public CrearProvedor() {
    
}
  
    
    public void guardarproveedor(){
    
        try {
            
        Calendar c1 = Calendar.getInstance();
        String  dia = Integer.toString(c1.get(Calendar.DAY_OF_MONTH));
        String  mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
        String year = Integer.toString(c1.get(Calendar.YEAR));
        String fecha = (year + "-" + mes+ "-" + dia); 
        
        idproveedor=Proveedor.codigo.getText();
        nombre=Proveedor.nombre.getText();
        Dv=Proveedor.dv.getText();
        Direccion= Proveedor.direccion.getText();
        estado=Proveedor.estado.getSelectedItem().toString();
        Email=Proveedor.email.getText();
        Ruc=Proveedor.ruc.getText();
        Telefono01=Proveedor.tel01.getText();
        Telefono02=Proveedor.tel02.getText();
       
        conectar conexcio = new conectar(); 
        conexcio.conexion();

        guardarbanco=conexcio.con.prepareStatement("INSERT INTO proveedor (idProveedor, Fecha, Nombre, Ruc, Dv, Telefono1, Telefono2, Direccion, Email, Estado) VALUES (?,?,?,?,?,?,?,?,?,?)");
        guardarbanco.setString(1, idproveedor);
        guardarbanco.setString(2, fecha);
        guardarbanco.setString(3, nombre);
        guardarbanco.setString(4, Ruc);
        guardarbanco.setString(5, Dv);
        guardarbanco.setString(6, Telefono01);
        guardarbanco.setString(7, Telefono02);
        guardarbanco.setString(8, Direccion);
        guardarbanco.setString(9, Email);
        guardarbanco.setString(10, estado);
        guardarbanco.execute();
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
        
    
    }
  public Integer buscarultimo(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
     // creamos la consulta
     consulta="SELECT MAX(idProveedor) FROM Proveedor ";
     //pasamos la consulta al preparestatement
   numerop=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsp=numerop.executeQuery(consulta);
     //recorremos el resulset
    rsp.next();
        
               ultimop=rsp.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
   }catch (SQLException ex1){
   JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
   }finally{
         try {
             numerop.close();
             rsp.close();
             conect.desconectar();
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
         }
   
     }   
    return ultimop;
    }
    
public void eliminarproveedor(Integer Codigo){
  try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    // creamos la consulta
     consulta="DELETE FROM proveedor where idProveedor = ?";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta);
     //pasamos al resulset la consulta preparada y ejecutamos
     cargar.setInt(1, Codigo);  
     cargar.execute();
    conect.desconectar();          
    JOptionPane.showMessageDialog(null,"Registro Eliminado Satisfactoriamente");
    Proveedor.editar.setEnabled(false);
    Proveedor.eliminar.setEnabled(false);
    Date date = new Date();
            Proveedor.fecha.setDate(date);
            Proveedor.direccion.setText("");
            Proveedor.nombre.setText("");
            Proveedor.ruc.setText("");
            Proveedor.dv.setText("");
            Proveedor.tel01.setText("");
            Proveedor.tel02.setText("");
            Proveedor.email.setText("");
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
  
  
  } 

public void buscartodos(){
     try {
         tabla = (DefaultTableModel) BuscarProveedor.Tbl_Proveedor.getModel();
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
     consulta="SELECT idProveedor, Nombre, Ruc, Dv, Telefono1, Telefono2, Direccion, Email, Estado FROM proveedor ORDER BY idProveedor";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idProveedor");
                    filas[1]=rs.getString("Nombre");
                    filas[2]=rs.getString("Ruc");
                    filas[3]=rs.getString("Dv");
                    filas[4]=rs.getString("Telefono1");
                    filas[5]=rs.getString("Telefono2");
                    filas[6]=rs.getString("Direccion");
                    filas[7]=rs.getString("Email");
                    filas[8]=rs.getString("Estado");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     } 


public void buscarpornombre(String nombre){
     try {
         tabla = (DefaultTableModel) BuscarProveedor.Tbl_Proveedor.getModel();
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
     consulta="SELECT idProveedor, Nombre, Ruc, Dv, Telefono1, Telefono2, Direccion, Email, Estado FROM proveedor  where Nombre LIKE'"+ nombre +"%' ORDER BY idProveedor";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idProveedor");
                    filas[1]=rs.getString("Nombre");
                    filas[2]=rs.getString("Ruc");
                    filas[3]=rs.getString("Dv");
                    filas[4]=rs.getString("Telefono1");
                    filas[5]=rs.getString("Telefono2");
                    filas[6]=rs.getString("Direccion");
                    filas[7]=rs.getString("Email");
                    filas[8]=rs.getString("Estado");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     }

public void buscarporcedula( String Cedula){
     try {
         tabla = (DefaultTableModel) BuscarProveedor.Tbl_Proveedor.getModel();
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
     consulta="SELECT idProveedor, Nombre, Ruc, Dv, Telefono1, Telefono2, Direccion, Email, Estado FROM proveedor  where idProveedor LIKE'"+ Cedula +"%' ORDER BY idProveedor";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idProveedor");
                    filas[1]=rs.getString("Nombre");
                    filas[2]=rs.getString("Ruc");
                    filas[3]=rs.getString("Dv");
                    filas[4]=rs.getString("Telefono1");
                    filas[5]=rs.getString("Telefono2");
                    filas[6]=rs.getString("Direccion");
                    filas[7]=rs.getString("Email");
                    filas[8]=rs.getString("Estado");
                                        
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
     consulta="SELECT idProveedor, Nombre, Ruc, Dv, Telefono1, Telefono2, Direccion, Email, Estado FROM proveedor where idProveedor ='"+ Codigo +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){

                            Proveedor.codigo.setText(String.valueOf(rs2.getInt("idProveedor")) );                
                            Proveedor.email.setText(rs2.getString("Email"));
                            Proveedor.nombre.setText(rs2.getString("Nombre"));
                            Proveedor.dv.setText(rs2.getString("Dv"));
                            Proveedor.ruc.setText(rs2.getString("Ruc"));
                            Proveedor.tel01.setText(rs2.getString("Telefono1"));
                            Proveedor.tel02.setText(rs2.getString("Telefono2"));
                            Proveedor.estado.setSelectedItem(rs2.getString("Estado"));
                            Proveedor.direccion.setText(rs2.getString("Direccion"));
                            //imagen pendiente 
                            Proveedor.editar.setEnabled(true);
                            Proveedor.guardar.setEnabled(false);
                            Proveedor.eliminar.setEnabled(true);
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


public void editarproveedor(Integer codigox,String nombre, String Dv, String Ruc, String Direccion, String Telefono01, String Telefono02, String Email,String estado, String Fecha){
       
        try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();  
    
     // creamos la consulta
     consulta="UPDATE proveedor SET Nombre =?,DV=?,Ruc=?,Direccion=?,Telefono1=?,Telefono2=?,Email=?,Estado=?,Fecha=? WHERE idProveedor= ? ";
    //pasamos la consulta al preparestatement
    cargar3=conect.con.prepareStatement(consulta);
    cargar3.setString(1, nombre);
    cargar3.setString(2, Dv);
    cargar3.setString(3, Ruc);
    cargar3.setString(4, Direccion);
    cargar3.setString(5, Telefono01);
    cargar3.setString(6, Telefono02);
    cargar3.setString(7, Email);
    cargar3.setString(8, estado);
    cargar3.setString(9, Fecha);
    cargar3.setInt(10, codigox);
    cargar3.executeUpdate();    
    conect.desconectar(); 
    JOptionPane.showMessageDialog(null, "Registro Editado Satisfactoriamente");
        }catch(SQLException ex){
            
       JOptionPane.showMessageDialog(null,"Error" +ex);  
        
        }
       
   } 
    
}
