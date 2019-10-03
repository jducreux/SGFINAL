/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Bancos;
import java.awt.HeadlessException;
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
public class CrearBancos {
    PreparedStatement guardarbanco, UltimoRg, cargar, numerobanco;
    String idbanco,nombre,cuenta,detalle,estado;
    Integer ultimobanco;
    Double montoi, montoa;
    ResultSet aux, rs, rsbanco;
    DefaultTableModel tabla = new DefaultTableModel(); 
    Object[] filas = new Object[8];

   public CrearBancos(){
  
    }
   
   
public void guardarbanco(){
    
        try {
            
        Calendar c1 = Calendar.getInstance();
        String  dia = Integer.toString(c1.get(Calendar.DAY_OF_MONTH));
        String  mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
        String year = Integer.toString(c1.get(Calendar.YEAR));
        String fecha = (year + "-" + mes+ "-" + dia); 
        
        idbanco=Bancos.codigo.getText();
        nombre=Bancos.nombre.getText();
        cuenta=Bancos.cuenta.getText();
        detalle= Bancos.detalle.getSelectedItem().toString();
        estado=Bancos.estado.getSelectedItem().toString();
        montoi=Double.parseDouble(Bancos.montoi.getText());
        montoa=Double.parseDouble(Bancos.montoa.getText());
       
        conectar conexcio = new conectar(); 
        conexcio.conexion();
        if (Bancos.Edicion == 0)
        {
            guardarbanco=conexcio.con.prepareStatement("INSERT INTO cuentas (idCuentas, Fecha, Nombre, Numero, Tipo, SaldoInicial, SaldoActual, Estado) VALUES (?,?,?,?,?,?,?,?)");
            guardarbanco.setString(1, idbanco);
            guardarbanco.setString(2, fecha);
            guardarbanco.setString(3, nombre);
            guardarbanco.setString(4, cuenta);
            guardarbanco.setString(5, detalle);
            guardarbanco.setDouble(6, montoi);
            guardarbanco.setDouble(7, montoa);
            guardarbanco.setString(8, estado);
            guardarbanco.execute();
                JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }else{
            guardarbanco=conexcio.con.prepareStatement("UPDATE cuentas SET Fecha=?, Nombre=?, Numero=?, Tipo=?, SaldoInicial=?, SaldoActual=?, Estado=? WHERE idCuentas = '"+idbanco+"'");   
            guardarbanco.setString(1, fecha);
            guardarbanco.setString(2, nombre);
            guardarbanco.setString(3, cuenta);
            guardarbanco.setString(4, detalle);
            guardarbanco.setDouble(5, montoi);
            guardarbanco.setDouble(6, montoa);
            guardarbanco.setString(7, estado);
            guardarbanco.execute();
                JOptionPane.showMessageDialog(null, "Registro Actualizado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        }
        


        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
        
    
    }

public void buscarregistros(){
     try {
         tabla = (DefaultTableModel) Bancos.registros.getModel();
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
     consulta="SELECT Fecha, idCuentas, Nombre, Numero, Tipo, SaldoInicial, SaldoActual, Estado FROM cuentas where idCuentas!='"+ '0' +"' ORDER BY idCuentas ASC";
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     rs=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (rs.next()){
        
                    filas[0]=rs.getString("Fecha");
                    filas[1]=rs.getInt("IdCuentas");
                    filas[2]=rs.getString("Nombre");
                    filas[3]=rs.getString("Numero");
                    filas[4]=rs.getString("Tipo");
                    filas[5]=rs.getDouble("SaldoInicial");
                    filas[6]=rs.getDouble("SaldoActual");
                    filas[7]=rs.getString("Estado");
                                        
       tabla.addRow(filas);
    }
    rs.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
     }

public Integer buscarultimo(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
     // creamos la consulta
     consulta="SELECT MAX(idCuentas) FROM cuentas ";
     //pasamos la consulta al preparestatement
   numerobanco=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsbanco=numerobanco.executeQuery(consulta);
     //recorremos el resulset
    rsbanco.next();
        
               ultimobanco=rsbanco.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
   }catch (SQLException ex1){
   JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
   }finally{
         try {
             numerobanco.close();
             rsbanco.close();
             conect.desconectar();
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
         }
   
     }   
    return ultimobanco;
    }
}
