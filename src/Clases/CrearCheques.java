/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Cheques;
import Interfaces.ReporteCheques;
import Interfaces.Listadodedepositos;
import Interfaces.Listadenotas;
import Interfaces.MantChk;
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
public class CrearCheques {
    PreparedStatement guardarbanco, UltimoRg, cargar,reporte,reporte2, cargar2;
    String idbanco,nombre,cuenta,detalle,estado;
    Double montoi, montoa;
    ResultSet aux, rs, rs2, aux1,rsreporte,rsreporte2;
    DefaultTableModel tabla = new DefaultTableModel(); 
    Object[] filas = new Object[8];
    Object[] filas1 = new Object[7];
    public CrearCheques() {
  
}
    public void llenarcomboreporte(){
        
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
        try {
    
     
     // creamos la consulta
     consulta="SELECT Nombre FROM cuentas Where idCuentas !='"+'0'+"'";
   
     //pasamos la consulta al preparestatement
    
    reporte=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
    
     rsreporte=reporte.executeQuery(consulta);

          while(rsreporte.next()){               
                
                    ReporteCheques.cuenta.addItem(rsreporte.getString("Nombre"));                                 
               }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }finally{
        conect.desconectar();
        
        }
    
    } 
    
      public void llenarcomboreportedeposito(){
        
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
        try {
    
     
     // creamos la consulta
     consulta="SELECT Nombre FROM cuentas Where idCuentas !='"+'0'+"'";
   
     //pasamos la consulta al preparestatement
    
    reporte2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
    
     rsreporte2=reporte2.executeQuery(consulta);

          while(rsreporte2.next()){               
                
                    Listadodedepositos.cuenta.addItem(rsreporte2.getString("Nombre"));                                 
               }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }finally{
        conect.desconectar();
        
        }
    
    } 
    
     public void llenarcomboreportenotas(){
        
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
        try {
    
     
     // creamos la consulta
     consulta="SELECT Nombre FROM cuentas Where idCuentas !='"+'0'+"'";
   
     //pasamos la consulta al preparestatement
    
    reporte2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    
    
     rsreporte2=reporte2.executeQuery(consulta);

          while(rsreporte2.next()){               
                
                    Listadenotas.Cuenta.addItem(rsreporte2.getString("Nombre"));                                 
               }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }finally{
        conect.desconectar();
        
        }
    
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
 
               
                if (MantChk.Orden == "1"){
                
                MantChk.cuenta.addItem(aux.getString("Nombre"));
                }
                if (Cheques.Orden == "1"){
                    Cheques.cuenta.addItem(aux.getString("Nombre"));
                }
                    
               
           }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
    
    public void cargarcheques(){
       try {
     DefaultTableModel tabla= (DefaultTableModel) MantChk.jTable1.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 

      String  dia1 = Integer.toString(MantChk.jDateChooserFecha1.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes1 = Integer.toString(MantChk.jDateChooserFecha1.getCalendar().get(Calendar.MONTH) + 1);
      String year1 = Integer.toString(MantChk.jDateChooserFecha1.getCalendar().get(Calendar.YEAR));
      String fecha1 = (year1 + "-" + mes1+ "-" + dia1);   

      String  dia2 = Integer.toString(MantChk.jDateChooserFecha2.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes2 = Integer.toString(MantChk.jDateChooserFecha2.getCalendar().get(Calendar.MONTH) + 1);
      String year2 = Integer.toString(MantChk.jDateChooserFecha2.getCalendar().get(Calendar.YEAR));
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
    String Cuenta = MantChk.cuenta.getSelectedItem().toString();
    if (MantChk.todos.isSelected()) 
    {   
        consulta="SELECT Numero, Fecha, Tipo, Beneficiario, observacion, Monto, Estado FROM cheques where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' ORDER BY Fecha";
    }else if (MantChk.impreso.isSelected()) {
        Estado = "Impreso";
        consulta="SELECT Numero, Fecha, Tipo, Beneficiario, observacion, Monto, Estado FROM cheques where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Estado = '"+ Estado +"' ORDER BY Fecha";
    }else if (MantChk.trancito.isSelected()){
        Estado = "Transito";
        consulta="SELECT Numero, Fecha, Tipo, Beneficiario, observacion, Monto, Estado FROM cheques where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Estado = '"+ Estado +"' ORDER BY Fecha";    
    }else if (MantChk.anulado.isSelected()){
        Estado = "Anulado";
        consulta="SELECT Numero, Fecha, Tipo, Beneficiario, observacion, Monto, Estado FROM cheques where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Estado = '"+ Estado +"' ORDER BY Fecha";    

    } else if (MantChk.conciliado.isSelected()) {
        Estado = "Conciliado";
        consulta="SELECT Numero, Fecha, Tipo, Beneficiario, observacion, Monto, Estado FROM cheques where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Estado = '"+ Estado +"' ORDER BY Fecha";    
    }

     cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux1=cargar.executeQuery(consulta);
     //recorremos el resulset
    while (aux1.next()){
                    filas1[0]=aux1.getString("Numero");
                    filas1[1]=aux1.getString("Fecha");
                    filas1[2]=aux1.getString("Tipo");
                    filas1[3]=aux1.getString("Beneficiario");
                    filas1[4]=aux1.getString("observacion");
                    filas1[5]=aux1.getDouble("Monto");
                    filas1[6]=aux1.getString("Estado");                    
       tabla.addRow(filas1);
    }
    cargar.close();
    aux1.close();
    conect.desconectar();
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
 }
    
    public void eliminarcheque(){
  try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    // creamos la consulta
    
     DefaultTableModel tabla = (DefaultTableModel) MantChk.jTable1.getModel();
     consulta="DELETE FROM cheques where Numero = ?";
     
    for (int i = 0; i < MantChk.jTable1.getRowCount(); i++) {

    if( MantChk.jTable1.getValueAt(i, 6)!=null){ 
         //pasamos la consulta al preparestatement
         int Codigo;
         Codigo = Integer.parseInt(MantChk.jTable1.getValueAt(i, 0).toString());
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
 
 public void ActualizarCheques(){
         try {
     String consulta, Estado;  
     conectar conect = new conectar(); 
     conect.conexion();  
    
 
    //pasamos la consulta al preparestatement
    
    for (int i = 0; i < MantChk.jTable1.getRowCount(); i++) {

    if( MantChk.jTable1.getValueAt(i, 7)!=null){ 
             // creamos la consulta
     consulta="UPDATE cheques SET Estado =?, Tipo =?  WHERE Numero= ? ";
         int Codigo;
         String Tipo;
         Estado = MantChk.Estado.getSelectedItem().toString();
         Codigo = Integer.parseInt(MantChk.jTable1.getValueAt(i, 0).toString());
         Tipo = MantChk.Tipo.getSelectedItem().toString();
     cargar=conect.con.prepareStatement(consulta);
     //pasamos al resulset la consulta preparada y ejecutamos
     cargar.setString(1, Estado); 
     cargar.setString(2, Tipo); 
     cargar.setInt(3, Codigo);
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
    
 
 public void buscarcliente(Integer Codigo){
    conectar conect = new conectar(); 
    conect.conexion();
    try {
    String consulta; 
    
      String  dia = Integer.toString(Cheques.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Cheques.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Cheques.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String Fecha = (year + "-" + mes+ "-" + dia);   
     // creamos la consulta
     consulta="SELECT Nombre, Apellido, Total FROM `sg-soft`.entradas, `sg-soft`.clientes WHERE idClientes = CodCliente AND Fecha = '"+Fecha+"' AND idClientes ='"+ Codigo +"' AND `sg-soft`.entradas.Estado = '"+"Completado"+"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){
           Cheques.Beneficiario.setText((rs2.getString("Nombre"))+" "+ (rs2.getString("Apellido")) );                
           Cheques.txtmonto.setText(rs2.getString("Total"));
           
            Numero_a_Letra NumLetra = new Numero_a_Letra();
            String numero = Cheques.txtmonto.getText();
            Cheques.montoletra.setText(NumLetra.Convertir(numero,true));
            Cheques.Detalle01.setText("PAGO POR VENTA DE ANIMALES");
            Cheques.jButtonImprimir.requestFocus();
       
    rs2.close();
    cargar2.close();
    conect.desconectar();
           }else{
           JOptionPane.showMessageDialog(null,"El Cliente No Existe o No Tiene Facturas Pendientes"  ); 
 //           Entradas.jTextFieldCodigoG.selectAll();
  //          Entradas.jTextFieldCodigoG.requestFocus();
            rs2.close();
            cargar2.close();
           conect.desconectar();
            }
    }catch (SQLException ex){
    conect.desconectar();   
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }
    
    }
}
