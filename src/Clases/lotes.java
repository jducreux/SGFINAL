/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Veranimalesenlote;
import Interfaces.Subastas;
import static Interfaces.Subastas.Lista;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tserng
 */
public class lotes {
    ResultSet rsentradas,todos;
    PreparedStatement numeroentrada,animales;
    Integer ultimaentrada;
    Object[] filas = new Object[7];
    public lotes(){}
    
     public Integer buscarultimolote(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
   
     
     // creamos la consulta
     consulta="SELECT MAX(nlote) FROM subastas  where Fecha ='"+ fecha +"'";
     //pasamos la consulta al preparestatement
   numeroentrada=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsentradas=numeroentrada.executeQuery(consulta);
     //recorremos el resulset
    rsentradas.next();
        
               ultimaentrada=rsentradas.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
   }catch (SQLException ex1){
   JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
   }finally{
         try {
             numeroentrada.close();
             rsentradas.close();
             conect.desconectar();
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
         }
   
     }   
    return ultimaentrada;
    }
     
    public void mostrarlotes(String fecha){
    
     try {
     DefaultTableModel tabla= (DefaultTableModel) Veranimalesenlote.jTableMostrarListadelote.getModel();   
     String consulta;  
     Integer nanimal;
     conectar conect = new conectar(); 
                 conect.conexion();
                 /*
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
     */
                 
                 
                 Iterator i = Lista.iterator();
        
        while(i.hasNext())
        {
            nanimal= Integer.parseInt(i.next().toString());
           // creamos la consulta
     consulta="SELECT idAnimal,Tipo,Sexo,Color,Ferrete,CodVendedor,Observacion FROM entrada_detalle  where Fecha ='"+ fecha +"' and idAnimal='"+ nanimal +"' ORDER BY idAnimal";
     //pasamos la consulta al preparestatement
     animales=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     todos=animales.executeQuery(consulta);
     //recorremos el resulset
    while (todos.next()){
        filas[0]=todos.getInt("idAnimal");
                    filas[1]=todos.getString("Tipo");
                    filas[2]=todos.getString("Sexo");
                    filas[3]=todos.getString("Color");
                    filas[4]=todos.getString("Ferrete");                     
                    filas[5]=todos.getInt("CodVendedor");
                    filas[6]=todos.getString("Observacion");                 
                  
       tabla.addRow(filas);
    }
        }
                 
     
    todos.close();
    animales.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    }
}
