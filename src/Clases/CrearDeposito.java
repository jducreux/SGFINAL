/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Depositos;
import Interfaces.MantDepositos;
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
public class CrearDeposito {
    
   PreparedStatement guardarbanco, UltimoRg, cargar, ActEdetalle, cargar2;
    String idbanco,nombre,cuenta,detalle,estado;
    Double montoi, montoa;
    ResultSet aux, rs, aux1, rsdeposito, rs2;
    Integer ultimodeposito;
    DefaultTableModel tabla = new DefaultTableModel(); 
    Object[] filas = new Object[8];  
    Object[] filas1 = new Object[6];  
    
    
    
    public CrearDeposito() {
    
}
    public void guardardeposito(){
    
        try {
            
        String  dia = Integer.toString(Depositos.fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        String  mes = Integer.toString(Depositos.fecha.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(Depositos.fecha.getCalendar().get(Calendar.YEAR));
        String fecha = (year + "-" + mes+ "-" + dia); 
        String Estado = "Depositado";
        int idbanco=Integer.parseInt(Depositos.ID.getText());
        cuenta=String.valueOf(Depositos.cuenta.getSelectedItem());
        detalle= Depositos.detalle.getText();
        Double monto = Double.parseDouble(Depositos.monto.getText());
        conectar conexcio = new conectar(); 
        conexcio.conexion();

        guardarbanco=conexcio.con.prepareStatement("INSERT INTO depositos (idDepositos, Cuenta, Fecha, Detalle, Monto, Estado) VALUES (?,?,?,?,?,?)");
        guardarbanco.setInt(1, idbanco);
        guardarbanco.setString(2, cuenta);
        guardarbanco.setString(3, fecha);
        guardarbanco.setString(4, detalle);
        guardarbanco.setDouble(5, monto);
        guardarbanco.setString(6, Estado);
        guardarbanco.execute();
        
        //ACTUALIZAR SALDO EN CUENTA BANCARIA
            
        String consulta1, CActualizar; 
        Double SaldoNuevo = 0.00, SaldoActual = 0.00;
             
        consulta1="SELECT  SaldoActual FROM cuentas where Nombre ='"+ cuenta +"'";    
        cargar2=conexcio.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
        rs2=cargar2.executeQuery(consulta1);
            if (rs2.next()){
                SaldoActual = (Double.valueOf(rs2.getString("SaldoActual")));
            }   
            
            SaldoNuevo = (SaldoActual + monto);
            CActualizar="UPDATE cuentas SET SaldoActual =? WHERE Nombre= '"+cuenta+"'";
            //pasamos la consulta al preparestatement
            ActEdetalle=conexcio.con.prepareStatement(CActualizar);
            ActEdetalle.setDouble(1, SaldoNuevo);
            ActEdetalle.executeUpdate();  
            ActEdetalle.close();
            rs2.close();
            cargar2.close();
        
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
        
    
    }



public Integer buscarultimodeposito(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
     // creamos la consulta
     consulta="SELECT MAX(idDepositos) FROM depositos ";
     //pasamos la consulta al preparestatement
   UltimoRg=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsdeposito=UltimoRg.executeQuery(consulta);
     //recorremos el resulset
    rsdeposito.next();
        
               ultimodeposito=rsdeposito.getInt(1)+1;
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
    return ultimodeposito;
    }


 public void llenarcombo(){
        try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
     
     // creamos la consulta
     consulta="SELECT Nombre FROM cuentas Where idCuentas !='"+'0'+"'";
   
     //pasamos la consulta al preparestatement
    
     UltimoRg=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
    
     aux=UltimoRg.executeQuery(consulta);

          while(aux.next()){               
                if (MantDepositos.Orden == "1"){
                
                MantDepositos.cuenta.addItem(aux.getString("Nombre"));
                }else{
                    Depositos.cuenta.addItem(aux.getString("Nombre"));
                }
               
           }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
 
 public void cargardepositos(){
       try {
     DefaultTableModel tabla= (DefaultTableModel) MantDepositos.jTabledepositos.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 

      String  dia1 = Integer.toString(MantDepositos.jDateChooserFecha1.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes1 = Integer.toString(MantDepositos.jDateChooserFecha1.getCalendar().get(Calendar.MONTH) + 1);
      String year1 = Integer.toString(MantDepositos.jDateChooserFecha1.getCalendar().get(Calendar.YEAR));
      String fecha1 = (year1 + "-" + mes1+ "-" + dia1);   

      String  dia2 = Integer.toString(MantDepositos.jDateChooserFecha2.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes2 = Integer.toString(MantDepositos.jDateChooserFecha2.getCalendar().get(Calendar.MONTH) + 1);
      String year2 = Integer.toString(MantDepositos.jDateChooserFecha2.getCalendar().get(Calendar.YEAR));
      String fecha2 = (year2 + "-" + mes2+ "-" + dia2);      
     //---------fin de obtener la fecha
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
     
    consulta = ""; 
    String Estado;
    String Cuenta = MantDepositos.cuenta.getSelectedItem().toString();
    if (MantDepositos.todos.isSelected()) 
    {   
        consulta="SELECT idDepositos, Fecha, Cuenta, Detalle, Monto, Estado FROM depositos where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' ORDER BY Fecha";
    }else if (MantDepositos.depositado.isSelected()) {
        Estado = "Depositado";
        consulta="SELECT idDepositos, Fecha, Cuenta, Detalle, Monto, Estado FROM depositos where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Estado = '"+ Estado +"' ORDER BY Fecha";
    }else if (MantDepositos.transito.isSelected()){
        Estado = "Transito";
        consulta="SELECT idDepositos, Fecha, Cuenta, Detalle, Monto, Estado FROM depositos where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Estado = '"+ Estado +"' ORDER BY Fecha";    
    } else if (MantDepositos.conciliado.isSelected()) {
        Estado = "Conciliado";
        consulta="SELECT idDepositos, Fecha, Cuenta, Detalle, Monto, Estado FROM depositos where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Estado = '"+ Estado +"' ORDER BY Fecha";    
    }

     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux1=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (aux1.next()){
                    filas1[0]=aux1.getString("idDepositos");
                    filas1[1]=aux1.getString("Fecha");
                    filas1[2]=aux1.getString("Cuenta");
                    filas1[3]=aux1.getString("Detalle");
                    filas1[4]=aux1.getDouble("Monto");
                    filas1[5]=aux1.getString("Estado");                    
       tabla.addRow(filas1);
    }
    cargar.close();
    aux1.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
 }
 
 public void eliminardeposito(){
  try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    // creamos la consulta
    
     DefaultTableModel tabla = (DefaultTableModel) MantDepositos.jTabledepositos.getModel();
     consulta="DELETE FROM DEPOSITOS where idDepositos = ?";
     
    for (int i = 0; i < MantDepositos.jTabledepositos.getRowCount(); i++) {
    if( MantDepositos.jTabledepositos.getValueAt(i, 6)!=null){ 
         //pasamos la consulta al preparestatement
         int Codigo;
         Codigo = Integer.parseInt(MantDepositos.jTabledepositos.getValueAt(i, 0).toString());
     cargar=conect.con.prepareStatement(consulta);
     //pasamos al resulset la consulta preparada y ejecutamos
     cargar.setInt(1, Codigo);  
     cargar.execute(); 
                        }else{
                            continue;
                        }
    cargar.close();
    }
    
    
    
    
    conect.desconectar();          
    JOptionPane.showMessageDialog(null,"Registro Eliminado Satisfactoriamente");
  
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
  
  
  }
 
 public void ActualizarDepositos(){
       
        try {
     String consulta, Estado;  
     conectar conect = new conectar(); 
     conect.conexion();  
    
     // creamos la consulta
     consulta="UPDATE depositos SET Estado =?  WHERE idDepositos= ? ";
    //pasamos la consulta al preparestatement
    
    for (int i = 0; i < MantDepositos.jTabledepositos.getRowCount(); i++) {

    if( MantDepositos.jTabledepositos.getValueAt(i, 6)!=null){ 
         //pasamos la consulta al preparestatement
         int Codigo;
         Estado = MantDepositos.Estado.getSelectedItem().toString();
         Codigo = Integer.parseInt(MantDepositos.jTabledepositos.getValueAt(i, 0).toString());
     cargar=conect.con.prepareStatement(consulta);
     //pasamos al resulset la consulta preparada y ejecutamos
     cargar.setString(1, Estado); 
     cargar.setInt(2, Codigo);
      cargar.executeUpdate();  
                        }else{
                            continue;
                        }
    cargar.close();
    }
      
    conect.desconectar(); 
    JOptionPane.showMessageDialog(null, "Registro Actualizado Satisfactoriamente");
        }catch(SQLException ex){
            
       JOptionPane.showMessageDialog(null,"Error" +ex);  
        
        }
       
   } 
}


