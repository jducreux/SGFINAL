/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.ReciboCaja;
import Interfaces.RegCaja;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Juan
 */
public class Caja {
    PreparedStatement guardarbanco, UltimoRg, cargar, cargar3, facturas;
    String idbanco,nombre,cuenta,detalle,estado;
    Double montoi, montoa;
    ResultSet aux, rs, aux1, rsdeposito, todos; 
    DefaultTableModel tabla = new DefaultTableModel(); 
    Object[] filas = new Object[7];
    int ultimo;
    public Caja() { 
}
    
    public void guardarrecibo(){
    
        try {
            
        String  dia = Integer.toString(ReciboCaja.fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        String  mes = Integer.toString(ReciboCaja.fecha.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(ReciboCaja.fecha.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes+ "-" + dia); 
        String Estado = ReciboCaja.ESTADO.getSelectedItem().toString();
        int idcaja=Integer.parseInt(ReciboCaja.ID.getText());
        detalle= ReciboCaja.detalle.getText();
        String Nombre = ReciboCaja.Nombre.getText();
        Double montoi = Double.parseDouble(ReciboCaja.montoi.getText());
        Double montoa = Double.parseDouble(ReciboCaja.montoa.getText());
        conectar conexcio = new conectar(); 
        conexcio.conexion();

        guardarbanco=conexcio.con.prepareStatement("INSERT INTO caja (idCaja, Fecha, MontoActual, MontoInicial, Detalle, Nombre, Estado) VALUES (?,?,?,?,?,?,?)");
        guardarbanco.setInt(1, idcaja);
        guardarbanco.setString(2, fecha);
        guardarbanco.setDouble(3, montoa);
        guardarbanco.setDouble(4, montoi);
        guardarbanco.setString(5, detalle);
        guardarbanco.setString(6, Nombre);
        guardarbanco.setString(7, Estado);
        guardarbanco.execute();
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
        
    
    }
   
    public void guardarreciboCaja(){
    
        try {
        String consulta, consulta1;    
        String  dia = Integer.toString(RegCaja.fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        String  mes = Integer.toString(RegCaja.fecha.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(RegCaja.fecha.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes+ "-" + dia); 
        String Estado = "Registrado";
        int idcaja=Integer.parseInt(RegCaja.ID.getText());
        detalle= RegCaja.detalle.getText();
        String Caja = RegCaja.cuenta.getSelectedItem().toString();
        Double monto = Double.parseDouble(RegCaja.monto.getText());
        String Tipo = RegCaja.tipo.getSelectedItem().toString();
        conectar conexcio = new conectar(); 
        conexcio.conexion();

        guardarbanco=conexcio.con.prepareStatement("INSERT INTO registroscaja (idRegistrosCaja, Fecha, Monto, Descripcion, Tipo, Caja, Estado) VALUES (?,?,?,?,?,?,?)");
        guardarbanco.setInt(1, idcaja);
        guardarbanco.setString(2, fecha);
        guardarbanco.setDouble(3, monto);
        guardarbanco.setString(4, detalle);
        guardarbanco.setString(5, Tipo);
        guardarbanco.setString(6, Caja);
        guardarbanco.setString(7, Estado);
        guardarbanco.execute();
        String Nombre = RegCaja.cuenta.getSelectedItem().toString();
        
        Double MontoActual=0.00; 
        consulta1="SELECT MontoActual FROM caja WHERE Nombre= '"+Nombre+"'";

        facturas=conexcio.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        todos=facturas.executeQuery(consulta1);
      
                            while (todos.next()){   

                                    MontoActual = (todos.getDouble("MontoActual"));                              
                            }
        
        consulta="UPDATE caja SET MontoActual =? WHERE Nombre= '"+Nombre+"' ";
        if("GASTO".equals(RegCaja.tipo.getSelectedItem().toString())){
                     cargar3=conexcio.con.prepareStatement(consulta);
                     cargar3.setDouble(1, MontoActual - monto);
                     cargar3.executeUpdate(); 
        }else{
                     cargar3=conexcio.con.prepareStatement(consulta);
                     cargar3.setDouble(1, MontoActual + monto);
                     cargar3.executeUpdate(); 
            
        }
        

                               
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
        
    
    }
    
    
    public Integer buscarultimo(){
    
        conectar conect = new conectar(); 
                 conect.conexion();
         try {
        String consulta; 
                      
            // creamos la consulta
            consulta="SELECT MAX(idCaja) FROM caja ";
            //pasamos la consulta al preparestatement
            UltimoRg=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //pasamos al resulset la consulta preparada y ejecutamos
            rsdeposito=UltimoRg.executeQuery(consulta);
            //recorremos el resulset
            rsdeposito.next();
        
            ultimo=rsdeposito.getInt(1)+1;
            //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
        }catch (SQLException ex1){
            JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
        }finally{
            try {
                UltimoRg.close();
                rsdeposito.close();
                conect.desconectar();
            } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
            }
   
        }   
    return ultimo;
    }
    
    public Integer buscarultimoR(){
    
        conectar conect = new conectar(); 
                 conect.conexion();
         try {
        String consulta; 
                      
            // creamos la consulta
            consulta="SELECT MAX(idRegistrosCaja) FROM registroscaja ";
            //pasamos la consulta al preparestatement
            UltimoRg=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //pasamos al resulset la consulta preparada y ejecutamos
            rsdeposito=UltimoRg.executeQuery(consulta);
            //recorremos el resulset
            rsdeposito.next();
        
            ultimo=rsdeposito.getInt(1)+1;
            //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
        }catch (SQLException ex1){
            JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
        }finally{
            try {
                UltimoRg.close();
                rsdeposito.close();
                conect.desconectar();
            } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
            }
   
        }   
    return ultimo;
    }
    
    public void llenarcombo(){
        try {
            String consulta;  
            conectar conect = new conectar(); 
            conect.conexion();
     
            // creamos la consulta
            consulta="SELECT Nombre FROM Caja Where idCaja !='"+'0'+"'";
   
            //pasamos la consulta al preparestatement
    
            UltimoRg=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
    
            aux=UltimoRg.executeQuery(consulta);

            while(aux.next()){               

                
                        RegCaja.cuenta.addItem(aux.getString("Nombre"));
               
                }
           
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }
    
    }
 
public void buscarregistros(){
     try {
         tabla = (DefaultTableModel) ReciboCaja.registros.getModel();
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
     consulta="SELECT * FROM Caja";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getInt("idCaja");
                    filas[1]=rs.getString("Nombre");
                    filas[2]=rs.getString("Fecha");
                    filas[3]=rs.getString("Detalle");
                    filas[4]=rs.getDouble("MontoActual");
                    filas[5]=rs.getDouble("MontoInicial");
                    filas[6]=rs.getString("Estado");
                   
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     }
}
