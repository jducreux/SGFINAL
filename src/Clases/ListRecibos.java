/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.ListarRecibos;
import Interfaces.Recibos;
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
public class ListRecibos {
 Connection conectar;
 PreparedStatement guardarusuario;
 PreparedStatement cargar,cargar2,cargar3,cargar4;
 ResultSet rs,rs2,rs4;
 DefaultTableModel tabla = new DefaultTableModel(); 
 Object[] filas = new Object[11]; 
 
     public ListRecibos(){
  // tabla = (DefaultTableModel) buscarclientes.Tbl_Clientes.getModel();
        }
     
     public void buscartodos(){
     try {
         tabla = (DefaultTableModel) ListarRecibos.Tbl_Recibos.getModel();
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

         consulta="SELECT Codigo, CodCliente, NombreCliente, MontoTotal, Detalle, Fecha, Tipo, Estado, MontoLetras, AFactura, SaldoActual FROM recibos ORDER BY Codigo";
  
     
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("Codigo");
                    filas[1]=rs.getString("CodCliente");
                    filas[2]=rs.getString("NombreCliente");
                    filas[3]=rs.getDouble("MontoTotal");
                    filas[4]=rs.getString("Detalle");
                    filas[5]=rs.getString("Fecha");
                    filas[6]=rs.getString("Tipo");
                    filas[7]=rs.getString("Estado");
                    filas[8]=rs.getString("MontoLetras");
                    filas[9]=rs.getString("AFactura");
                    filas[10]=rs.getDouble("SaldoActual");

                                        
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
     consulta="SELECT Codigo, CodCliente, NombreCliente, MontoTotal, Detalle, Fecha, Tipo, Estado, MontoLetras, AFactura, SaldoActual FROM recibos where Codigo ='"+ Codigo +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){

                            Recibos.recibo.setText(String.valueOf(rs2.getInt("Codigo")) );                
                            Recibos.cliente.setText(String.valueOf(rs2.getInt("CodCliente")) );
                            Recibos.txtBeneficiario.setText(rs2.getString("NombreCliente"));
                            Recibos.txtCantidad.setText(String.valueOf(rs2.getDouble("MontoTotal")) );
                            Recibos.detalle.setText(rs2.getString("Detalle"));
                            Recibos.jDateChooserFecha.setDate(rs2.getDate("Fecha"));
                            Recibos.tipo.setSelectedItem(rs2.getString("Tipo"));
                            Recibos.Suma.setText(rs2.getString("MontoLetras"));
                            Recibos.saldo.setText(String.valueOf(rs2.getDouble("SaldoActual")) );
                            Recibos.Fact.setText(rs2.getString("AFactura"));
                            Recibos.estado.setText(rs2.getString("Estado"));
                            Recibos.jButton3.setEnabled(true);
                            Recibos.Guardar.setEnabled(false);
                            if ("".equals(rs2.getString("AFactura")))
                            {
                                Recibos.AFactura.setSelected(false);
                                Recibos.asaldo.setSelected(true);
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
      
             public void buscarpornombre(String nombre){
     try {
         tabla = (DefaultTableModel) ListarRecibos.Tbl_Recibos.getModel();
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
     consulta="SELECT Codigo, CodCliente, NombreCliente, MontoTotal, Detalle, Fecha, Tipo, Estado, MontoLetras, AFactura, SaldoActual FROM recibos where NombreCliente LIKE'"+ nombre +"%' ORDER BY NombreCliente";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("Codigo");
                    filas[1]=rs.getInt("CodCliente");
                    filas[2]=rs.getString("NombreCliente");
                    filas[3]=rs.getDouble("MontoTotal");
                    filas[4]=rs.getString("Detalle");
                    filas[5]=rs.getString("Fecha");
                    filas[6]=rs.getString("Tipo");
                    filas[7]=rs.getString("Estado");
                    filas[8]=rs.getString("MontoLetras");
                    filas[9]=rs.getInt("AFactura");
                    filas[10]=rs.getDouble("SaldoActual");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     }
             
             public void buscarporcedula( String Recibo){
     try {
         tabla = (DefaultTableModel) ListarRecibos.Tbl_Recibos.getModel();
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
     consulta="SELECT Codigo, CodCliente, NombreCliente, MontoTotal, Detalle, Fecha, Tipo, Estado, MontoLetras, AFactura, SaldoActual FROM recibos where Codigo LIKE'"+ Recibo +"%' ORDER BY Fecha ASC";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("Codigo");
                    filas[1]=rs.getInt("CodCliente");
                    filas[2]=rs.getString("NombreCliente");
                    filas[3]=rs.getDouble("MontoTotal");
                    filas[4]=rs.getString("Detalle");
                    filas[5]=rs.getString("Fecha");
                    filas[6]=rs.getString("Tipo");
                    filas[7]=rs.getString("Estado");
                    filas[8]=rs.getString("MontoLetras");
                    filas[9]=rs.getInt("AFactura");
                    filas[10]=rs.getDouble("SaldoActual");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
  
   public void buscarporcodigo( String Fecha){
     try {
         tabla = (DefaultTableModel) ListarRecibos.Tbl_Recibos.getModel();
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

     consulta="SELECT Codigo, CodCliente, NombreCliente, MontoTotal, Detalle, Fecha, Tipo, Estado, MontoLetras, AFactura, SaldoActual FROM recibos where Fecha = '"+ Fecha +"' ORDER BY Codigo";
  
//pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("Codigo");
                    filas[1]=rs.getInt("CodCliente");
                    filas[2]=rs.getString("NombreCliente");
                    filas[3]=rs.getDouble("MontoTotal");
                    filas[4]=rs.getString("Detalle");
                    filas[5]=rs.getString("Fecha");
                    filas[6]=rs.getString("Tipo");
                    filas[7]=rs.getString("Estado");
                    filas[8]=rs.getString("MontoLetras");
                    filas[9]=rs.getInt("AFactura");
                    filas[10]=rs.getDouble("SaldoActual");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (HeadlessException | SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
}
