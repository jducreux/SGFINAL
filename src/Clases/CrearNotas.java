/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.MantNotas;
import Interfaces.NotasDC;
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
public class CrearNotas {
    
     PreparedStatement guardarbanco, UltimoRg, cargar, cargar2, ActEdetalle;
    String idbanco,nombre,cuenta,detalle,estado, tipo;
    Double montoi, montoa;
    ResultSet aux, rs, aux1, rsnotas, rs2;
    Integer ultimo;
    Object[] filas1 = new Object[7];  
    
   public CrearNotas() {
    
} 
 public void guardarnotas(){
    
        try {
            
        String  dia = Integer.toString(NotasDC.fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        String  mes = Integer.toString(NotasDC.fecha.getCalendar().get(Calendar.MONTH) + 1);
        String year = Integer.toString(NotasDC.fecha.getCalendar().get(Calendar.YEAR));

        String fecha = (year + "-" + mes+ "-" + dia); 
        String Estado = "Registrada";
        int idbanco=Integer.parseInt(NotasDC.ID.getText());
        cuenta=String.valueOf(NotasDC.cuenta.getSelectedItem());
        tipo= String.valueOf(NotasDC.tipo.getSelectedItem());
        detalle= NotasDC.detalle.getText();
        Double monto = Double.parseDouble(NotasDC.monto.getText());
        conectar conexcio = new conectar(); 
        conexcio.conexion();

        guardarbanco=conexcio.con.prepareStatement("INSERT INTO notas (idNotas, Cuenta, Fecha, Detalle, Monto, Tipo, Estado) VALUES (?,?,?,?,?,?,?)");
        guardarbanco.setInt(1, idbanco);
        guardarbanco.setString(2, cuenta);
        guardarbanco.setString(3, fecha);
        guardarbanco.setString(4, detalle);
        guardarbanco.setDouble(5, monto);
        guardarbanco.setString(6, tipo);
        guardarbanco.setString(7, Estado);
        guardarbanco.execute();
        ActualizarSaldo(monto, tipo, cuenta);
        
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
        
    
    }



public Integer buscarultimanota(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
     // creamos la consulta
     consulta="SELECT MAX(idNotas) FROM notas ";
     //pasamos la consulta al preparestatement
   UltimoRg=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsnotas=UltimoRg.executeQuery(consulta);
     //recorremos el resulset
    rsnotas.next();
        
               ultimo=rsnotas.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
   }catch (SQLException ex1){
   JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
   }finally{
         try {
             UltimoRg.close();
             rsnotas.close();
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
     consulta="SELECT Nombre FROM cuentas Where idCuentas !='"+'0'+"'";
   
     //pasamos la consulta al preparestatement
    
     UltimoRg=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
    
     aux=UltimoRg.executeQuery(consulta);

          while(aux.next()){               
                if (MantNotas.Orden == "1"){
                
                MantNotas.cuenta.addItem(aux.getString("Nombre"));
                }else{
                    NotasDC.cuenta.addItem(aux.getString("Nombre"));
                }
               
           }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
 
 public void cargarnotas(){
       try {
     DefaultTableModel tabla= (DefaultTableModel) MantNotas.jTablenotas.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 

      String  dia1 = Integer.toString(MantNotas.jDateChooserFecha1.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes1 = Integer.toString(MantNotas.jDateChooserFecha1.getCalendar().get(Calendar.MONTH) + 1);
      String year1 = Integer.toString(MantNotas.jDateChooserFecha1.getCalendar().get(Calendar.YEAR));
      String fecha1 = (year1 + "-" + mes1+ "-" + dia1);   

      String  dia2 = Integer.toString(MantNotas.jDateChooserFecha2.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes2 = Integer.toString(MantNotas.jDateChooserFecha2.getCalendar().get(Calendar.MONTH) + 1);
      String year2 = Integer.toString(MantNotas.jDateChooserFecha2.getCalendar().get(Calendar.YEAR));
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

    String Cuenta = MantNotas.cuenta.getSelectedItem().toString();
    if (MantNotas.todas.isSelected()) 
    {   
        consulta="SELECT idNotas, Fecha, Cuenta, Detalle, Monto, Tipo, Estado FROM notas where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' ORDER BY Fecha";
    }else if (MantNotas.credito.isSelected()) {
        consulta="SELECT idNotas, Fecha, Cuenta, Detalle, Monto, Tipo, Estado FROM notas where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Tipo = '"+ "Credito" +"' ORDER BY Fecha";
    }else if (MantNotas.debito.isSelected()){
        consulta="SELECT idNotas, Fecha, Cuenta, Detalle, Monto, Tipo, Estado FROM notas where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Tipo = '"+ "Debito" +"' ORDER BY Fecha";    
    }
        

     // creamos la consulta
     
     //pasamos la consulta al preparestatement
     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux1=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (aux1.next()){
                    filas1[0]=aux1.getInt("idNotas");
                    filas1[1]=aux1.getString("Fecha");
                    filas1[2]=aux1.getString("Cuenta");
                    filas1[3]=aux1.getString("Detalle");
                    filas1[4]=aux1.getDouble("Monto");
                    filas1[5]=aux1.getString("Tipo");
                    filas1[6]=aux1.getString("Estado");                    
       tabla.addRow(filas1);
    }
    cargar.close();
    aux1.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
 }
 
 
 public void eliminarnotas(){
  try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    // creamos la consulta
    
     DefaultTableModel tabla = (DefaultTableModel) MantNotas.jTablenotas.getModel();
     
     
    for (int i = 0; i < MantNotas.jTablenotas.getRowCount(); i++) {
         int Codigo;
         Codigo = Integer.parseInt(MantNotas.jTablenotas.getValueAt(i, 0).toString());
    if( MantNotas.jTablenotas.getValueAt(i, 7)!=null){ 
         //pasamos la consulta al preparestatement

         consulta="DELETE FROM notas where idNotas = ?";
     cargar=conect.con.prepareStatement(consulta);
     //pasamos al resulset la consulta preparada y ejecutamos
     cargar.setInt(1, Codigo);  
     cargar.execute(); 
                        }else{
                            continue;
                        }

    }
    
    
    
        cargar.close();
    conect.desconectar();          
    JOptionPane.showMessageDialog(null,"Registro Eliminado Satisfactoriamente");
  
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
  
  
  }
 
 public void ActualizarNotas(){
       
        try {
     String consulta, Estado;  
     conectar conect = new conectar(); 
     conect.conexion();  
    
     String EActual;
     Double monto;
    //pasamos la consulta al preparestatement
    
    for (int i = 0; i < MantNotas.jTablenotas.getRowCount(); i++) {

    if( MantNotas.jTablenotas.getValueAt(i, 7)!=null){ 
        EActual = MantNotas.jTablenotas.getValueAt(i, 6).toString();
             // creamos la consulta
     consulta="UPDATE notas SET Estado =?, Tipo =?  WHERE idNotas= ? ";
         int Codigo;
         String Tipo, cuenta1;
         cuenta1 = MantNotas.cuenta.getSelectedItem().toString();
         Estado = MantNotas.Estado.getSelectedItem().toString();
         Codigo = Integer.parseInt(MantNotas.jTablenotas.getValueAt(i, 0).toString());
         Tipo = MantNotas.jTablenotas.getValueAt(i, 5).toString();
         monto = Double.valueOf(MantNotas.jTablenotas.getValueAt(i, 4).toString());
     cargar=conect.con.prepareStatement(consulta);
     //pasamos al resulset la consulta preparada y ejecutamos
     
     cargar.setString(1, Estado); 
     cargar.setString(2, Tipo); 
     cargar.setInt(3, Codigo);
     
      cargar.executeUpdate();
      if ("Registrada".equals(EActual)){
        if("Anulada".equals(Estado)){
         monto = (monto*-1);
         ActualizarSaldo(monto, Tipo, cuenta1);
        }  
      }
      
      if ("Anulada".equals(EActual)){
        if("Registrada".equals(Estado)){
         monto = (monto);   
         ActualizarSaldo(monto, Tipo, cuenta1);
        }  
      }
      
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

   public void ActualizarSaldo(Double monto, String tipo, String cuenta){
               //ACTUALIZAR SALDO EN CUENTA BANCARIA
                  try {
              conectar conect = new conectar(); 
     conect.conexion();    
        String consulta1, CActualizar; 
        Double SaldoNuevo = 0.00, SaldoActual = 0.00;
        consulta1="SELECT  SaldoActual FROM cuentas where Nombre ='"+cuenta+"'";    
        cargar2=conect.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
        rs2=cargar2.executeQuery(consulta1);
            if (rs2.next()){
                SaldoActual = (Double.valueOf(rs2.getString("SaldoActual")));
            }   
            
            if ("CREDITO".equals(tipo)){
                SaldoNuevo = (SaldoActual + monto);
            }
            if ("DEBITO".equals(tipo)){
                SaldoNuevo = (SaldoActual - monto);
            }

            CActualizar="UPDATE cuentas SET SaldoActual =? WHERE Nombre= '"+cuenta+"'";
            //pasamos la consulta al preparestatement
            ActEdetalle=conect.con.prepareStatement(CActualizar);
            ActEdetalle.setDouble(1, SaldoNuevo);
            ActEdetalle.executeUpdate();  
            ActEdetalle.close();
            rs2.close();
            cargar2.close();
                conect.desconectar(); 
        }catch(SQLException ex){
            
       JOptionPane.showMessageDialog(null,"Error" +ex);  
        
        }
   }
           
}
