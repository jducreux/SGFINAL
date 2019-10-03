/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Conciliacion;
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
public class Conciliar {
    PreparedStatement UltimoRg, cargar1, cargar2, cargar3, cargar4, cargar5, guardarconcilia;
    String idbanco,nombre,cuenta,detalle,estado;
    Double montoi, montoa;
    ResultSet aux, rs, aux1, aux2, aux3, aux4, aux5;
    Object[] filas = new Object[8];  
    Object[] filas1 = new Object[4]; 
    Object[] filas2 = new Object[3];  
    Object[] filas3 = new Object[3];  
    Object[] filas4 = new Object[3];  
    
    public Conciliar(){}
    
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

                    Conciliacion.cuenta.addItem(aux.getString("Nombre"));
               
           }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   } 
  }   
    
    public void cargardepositos(){
       try {
     DefaultTableModel tabla1 = (DefaultTableModel) Conciliacion.cheques.getModel(); 
     DefaultTableModel tabla2 = (DefaultTableModel) Conciliacion.depositos.getModel();   
     DefaultTableModel tabla3 = (DefaultTableModel) Conciliacion.creditos.getModel();   
     DefaultTableModel tabla4 = (DefaultTableModel) Conciliacion.debitos.getModel();   
     String consulta1, consulta2, consulta3, consulta4, consulta5;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 

      String  dia1 = Integer.toString(Conciliacion.jDateChooserFecha1.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes1 = Integer.toString(Conciliacion.jDateChooserFecha1.getCalendar().get(Calendar.MONTH) + 1);
      String year1 = Integer.toString(Conciliacion.jDateChooserFecha1.getCalendar().get(Calendar.YEAR));
      String fecha1 = (year1 + "-" + mes1+ "-" + dia1);   

      String  dia2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.MONTH) + 1);
      String year2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.YEAR));
      String fecha2 = (year2 + "-" + mes2+ "-" + dia2);      
     //---------fin de obtener la fecha
     //--------limpiar tabla------
      try {
            if (tabla1 != null) {
                while (tabla1.getRowCount() > 0) {
                    tabla1.removeRow(0);
                }
            }
            if (tabla2 != null) {
                while (tabla2.getRowCount() > 0) {
                    tabla2.removeRow(0);
                }
            }
            if (tabla3 != null) {
                while (tabla3.getRowCount() > 0) {
                    tabla3.removeRow(0);
                }
            }
            if (tabla4 != null) {
                while (tabla4.getRowCount() > 0) {
                    tabla4.removeRow(0);
                }
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }
        //-----hasta aki limpiar tabla-----
     
    String Cuenta = Conciliacion.cuenta.getSelectedItem().toString();
    
    consulta1="SELECT Fecha, Numero, Monto, Estado FROM cheques where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Estado = '"+ "Impreso" +"' ORDER BY Fecha";
    consulta2="SELECT Fecha, Monto, Estado FROM depositos where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Estado = '"+ "Depositado" +"' ORDER BY Fecha";
    consulta3="SELECT Fecha, Monto, Detalle FROM notas where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Tipo = '"+ "CREDITO" +"' AND Estado = '"+ "Registrada" +"' ORDER BY Fecha";    
    consulta4="SELECT Fecha, Monto, Detalle FROM notas where Fecha BETWEEN '"+ fecha1 +"' AND '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Tipo = '"+ "DEBITO" +"' AND Estado = '"+ "Registrada" +"' ORDER BY Fecha";    
    consulta5="SELECT SaldoConciliado FROM cuentas where Nombre <= '"+ Conciliacion.cuenta.getSelectedItem().toString() +"'";

     cargar1=conect.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux1=cargar1.executeQuery(consulta1);
     //recorremos el resulset
    while (aux1.next()){
                    filas1[0]=aux1.getString("Fecha");
                    filas1[1]=aux1.getString("Numero");
                    filas1[2]=aux1.getDouble("Monto");
                    filas1[3]=aux1.getString("Estado");                   
       tabla1.addRow(filas1);
    }
    JOptionPane.showMessageDialog(null,"CHEQUES GENERADOS");
     cargar2=conect.con.prepareStatement(consulta2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux2=cargar2.executeQuery(consulta2);
     //recorremos el resulset
    while (aux2.next()){
                    filas2[0]=aux2.getString("Fecha");
                    filas2[1]=aux2.getDouble("Monto");
                    filas2[2]=aux2.getString("Estado");                  
       tabla2.addRow(filas2);
    }
    JOptionPane.showMessageDialog(null,"DEPOSITOS GENERADOS");
     cargar3=conect.con.prepareStatement(consulta3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux3=cargar3.executeQuery(consulta3);
     //recorremos el resulset
    while (aux3.next()){
                    filas3[0]=aux3.getString("Fecha");
                    filas3[1]=aux3.getDouble("Monto");
                    filas3[2]=aux3.getString("Detalle");                    
       tabla3.addRow(filas3);
    }
    JOptionPane.showMessageDialog(null,"NOTAS CREDITO GENERADOS");
     cargar4=conect.con.prepareStatement(consulta4,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux4=cargar4.executeQuery(consulta4);
     //recorremos el resulset
    while (aux4.next()){
                    filas4[0]=aux4.getString("Fecha");
                    filas4[1]=aux4.getDouble("Monto");
                    filas4[2]=aux4.getString("Detalle");                        
       tabla4.addRow(filas4);
    }
    JOptionPane.showMessageDialog(null,"NOTAS DEBITO GENERADOS");
     cargar5=conect.con.prepareStatement(consulta5,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux5=cargar5.executeQuery(consulta5);
     //recorremos el resulset
     double Slibro=0;
    while (aux5.next()){
                    Slibro = Slibro + aux5.getDouble("SaldoConciliado");
    }
    Conciliacion.SaldoLibro.setText(String.valueOf(Slibro));
    
    cargar1.close();
    aux1.close();
    cargar2.close();
    aux2.close();
    cargar3.close();
    aux3.close();
    cargar4.close();
    aux4.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
 }
    
    public void Calculos() {
         try {
        conectar conect = new conectar(); 
        conect.conexion();
        String consulta1, consulta2, consulta3;
        String Cuenta = Conciliacion.cuenta.getSelectedItem().toString();
        double SumaCheques, SumaDepositos, SumaNCreditos, SumaNDebitos, SBancoA, SLibroA, SBancoF, SLibroF, DT, CHKT;
        
        String  dia2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.DAY_OF_MONTH));
        String  mes2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.MONTH) + 1);
        String year2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.YEAR));
        String fecha2 = (year2 + "-" + mes2+ "-" + dia2); 
      
        SumaCheques = 0;
        SumaDepositos = 0;
        SumaNCreditos= 0;
        SumaNDebitos = 0;
        SBancoA = 0;
        SLibroA = 0;
        SBancoF = 0;
        SLibroF = 0;
        DT = 0;
        CHKT = 0;
        
        for (int i = 0; i < Conciliacion.cheques.getRowCount(); i++) {
            SumaCheques = SumaCheques + Double.parseDouble(Conciliacion.cheques.getValueAt(i, 2).toString());
        }
        for (int i = 0; i < Conciliacion.depositos.getRowCount(); i++) {
            SumaDepositos = SumaDepositos + Double.parseDouble(Conciliacion.depositos.getValueAt(i, 1).toString());
        }
        for (int i = 0; i < Conciliacion.creditos.getRowCount(); i++) {
            SumaNCreditos = SumaNCreditos + Double.parseDouble(Conciliacion.creditos.getValueAt(i, 1).toString());
        }
        for (int i = 0; i < Conciliacion.debitos.getRowCount(); i++) {
            SumaNDebitos = SumaNDebitos + Double.parseDouble(Conciliacion.debitos.getValueAt(i, 1).toString());
        }
        
        
    consulta1="SELECT Monto FROM cheques where Fecha <= '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Estado = '"+ "Transito" +"'";
    consulta2="SELECT Monto FROM depositos where Fecha <= '"+ fecha2 +"' AND Cuenta = '"+ Cuenta +"' AND Estado = '"+ "Transito" +"'";
    


     cargar1=conect.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux1=cargar1.executeQuery(consulta1);
     //recorremos el resulset
    while (aux1.next()){
                    CHKT= CHKT + aux1.getDouble("Monto");
    }
    
     cargar2=conect.con.prepareStatement(consulta2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     aux2=cargar2.executeQuery(consulta2);
     //recorremos el resulset
    while (aux2.next()){
                    DT=DT + aux2.getDouble("Monto");
    }

        redondear redon  = new redondear(); 
        Conciliacion.Schequesg.setText(String.valueOf(SumaCheques));
        Conciliacion.Sdepositos.setText(String.valueOf(SumaDepositos));
        Conciliacion.Sncreditos.setText(String.valueOf(SumaNCreditos));
        Conciliacion.Sndebitos.setText(String.valueOf(SumaNDebitos));
        Conciliacion.Schequest.setText(String.valueOf(CHKT));
        Conciliacion.Sdepositost.setText(String.valueOf(DT));
       
       SBancoA = Double.parseDouble(Conciliacion.SaldoBanco.getText());
       SBancoF = redon.redondearDecimales((SBancoA + DT - CHKT), 2);
       Conciliacion.SB.setText(String.valueOf(SBancoF));

       SLibroA = Double.parseDouble(Conciliacion.SaldoLibro.getText());  
       SLibroF = redon.redondearDecimales((SLibroA + SumaDepositos + SumaNCreditos - SumaCheques - SumaNDebitos), 2);
       Conciliacion.SL.setText(String.valueOf(SLibroF));
       }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    }
    
    public void limpiar(){
         try {
             
     DefaultTableModel tabla1 = (DefaultTableModel) Conciliacion.cheques.getModel(); 
     DefaultTableModel tabla2 = (DefaultTableModel) Conciliacion.depositos.getModel();   
     DefaultTableModel tabla3 = (DefaultTableModel) Conciliacion.creditos.getModel();   
     DefaultTableModel tabla4 = (DefaultTableModel) Conciliacion.debitos.getModel();  
            if (tabla1 != null) {
                while (tabla1.getRowCount() > 0) {
                    tabla1.removeRow(0);
                }
            }
            if (tabla2 != null) {
                while (tabla2.getRowCount() > 0) {
                    tabla2.removeRow(0);
                }
            }
            if (tabla3 != null) {
                while (tabla3.getRowCount() > 0) {
                    tabla3.removeRow(0);
                }
            }
            if (tabla4 != null) {
                while (tabla4.getRowCount() > 0) {
                    tabla4.removeRow(0);
                }
            }
          
        Conciliacion.Schequesg.setText("");
        Conciliacion.Sdepositos.setText("");
        Conciliacion.Sncreditos.setText("");
        Conciliacion.Sndebitos.setText("");
        Conciliacion.Schequest.setText("");
        Conciliacion.Sdepositost.setText("");     
        Conciliacion.SB.setText("");
        Conciliacion.SL.setText(""); 
        Conciliacion.SaldoBanco.setText("0.00");
        Conciliacion.SaldoLibro.setText("0.00");
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }
    }
    
     public void guardarconciliacion(){
    
        try {
            String Banco, Año, Mes;
            Double TDepositos, TDTransito, TCheques, TCTrancito, TNotasC, TNotasD, TSL, TSB, SBI, SLI;
            conectar conexcio = new conectar(); 
            conexcio.conexion();
            
            String  dia1 = Integer.toString(Conciliacion.jDateChooserFecha1.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mes1 = Integer.toString(Conciliacion.jDateChooserFecha1.getCalendar().get(Calendar.MONTH) + 1);
            String year1 = Integer.toString(Conciliacion.jDateChooserFecha1.getCalendar().get(Calendar.YEAR));
            String fecha1 = (year1 + "-" + mes1+ "-" + dia1);   

            String  dia2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mes2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.MONTH) + 1);
            String year2 = Integer.toString(Conciliacion.jDateChooserFecha2.getCalendar().get(Calendar.YEAR));
            String fecha2 = (year2 + "-" + mes2+ "-" + dia2);   

            TCheques = Double.valueOf(Conciliacion.Schequesg.getText());
            TDepositos = Double.valueOf(Conciliacion.Sdepositos.getText());
            TNotasC = Double.valueOf(Conciliacion.Sncreditos.getText());
            TNotasD = Double.valueOf(Conciliacion.Sndebitos.getText());
            TCTrancito = Double.valueOf(Conciliacion.Schequest.getText());
            TDTransito = Double.valueOf(Conciliacion.Sdepositost.getText());
            TSB = Double.valueOf(Conciliacion.SB.getText());
            TSL = Double.valueOf(Conciliacion.SL.getText());
            SBI = Double.valueOf(Conciliacion.SaldoBanco.getText());
            SLI = Double.valueOf(Conciliacion.SaldoLibro.getText());
            Año = Conciliacion.ano.getSelectedItem().toString();
            Mes = Conciliacion.mes.getSelectedItem().toString();
            Banco = Conciliacion.cuenta.getSelectedItem().toString();

        guardarconcilia=conexcio.con.prepareStatement("INSERT INTO conciliaciones (cuenta, mes, ano, libroinicial, bancoinicial, depositos, chequesgirados, chequescirculacion, depositotransito, notacredito, notadebito, libroconciliado, bancoconciliado, FechaInicial, FechaFinal) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        guardarconcilia.setString(1, Banco);
        guardarconcilia.setString(2, Mes);
        guardarconcilia.setString(3, Año);
        guardarconcilia.setDouble(4, SLI);
        guardarconcilia.setDouble(5, SBI);
        guardarconcilia.setDouble(6, TDepositos);
        guardarconcilia.setDouble(7, TCheques);
        guardarconcilia.setDouble(8, TCTrancito);
        guardarconcilia.setDouble(9, TDTransito);
        guardarconcilia.setDouble(10, TNotasC);
        guardarconcilia.setDouble(11, TNotasD);
        guardarconcilia.setDouble(12, TSL);
        guardarconcilia.setDouble(13, TSB);
        guardarconcilia.setString(14, fecha1);
        guardarconcilia.setString(15, fecha2);

        guardarconcilia.execute();
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        Conciliacion.guardar.setEnabled(false);
        } catch (SQLException  ex) {
            JOptionPane.showMessageDialog(null,"El Registro No Se Logro Realizar Error:" +ex);
        }
        
    
    }
}
