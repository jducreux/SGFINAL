/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Cheques;
import Interfaces.Permisos;
import Interfaces.Usuarios;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sg.soft.Principal;

/**
 *
 * @author Juan
 */
public class Accesos {
    PreparedStatement cargar2,guardar,numerocheque, ActEdetalle, ActEdetalle2, guardaracceso;
    ResultSet rs2,rscheque;
    Integer ultimocheque;
    
        public Accesos() { 
}
        
    public void buscaracceso(String id){
    conectar conect = new conectar(); 
    conect.conexion();
    try {
    String consulta; 
     // creamos la consulta
     consulta="SELECT  * From Accesos where Usuario ='"+ id +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
     if (rs2.next()){
            
        if (rs2.getInt("MA01")==0){
                    Principal.MA01.setEnabled(false);
                } 
        if (rs2.getInt("MA02")==0){
                    Principal.MA02.setEnabled(false);
                } 
        if (rs2.getInt("MA03")==0){
                    Principal.MA03.setEnabled(false);
                } 
        if (rs2.getInt("MB01")==0){
                    Principal.MB01.setEnabled(false);
                } 
        if (rs2.getInt("MB02")==0){
                    Principal.MB02.setEnabled(false);
                } 
        if (rs2.getInt("MB03")==0){
                    Principal.MB03.setEnabled(false);
                } 
        if (rs2.getInt("MB04")==0){
                    Principal.MB04.setEnabled(false);
                } 
        if (rs2.getInt("MB05")==0){
                    Principal.MB05.setEnabled(false);
                } 
        if (rs2.getInt("MB06")==0){
                    Principal.MB06.setEnabled(false);
                } 
        if (rs2.getInt("MR01")==0){
                    Principal.MR01.setEnabled(false);
                } 
        if (rs2.getInt("MR02")==0){
                    Principal.MR02.setEnabled(false);
                } 
        if (rs2.getInt("MR03")==0){
                    Principal.MR03.setEnabled(false);
                } 
        if (rs2.getInt("MR04")==0){
                    Principal.MR04.setEnabled(false);
                } 
        if (rs2.getInt("MR05")==0){
                    Principal.MR05.setEnabled(false);
                } 
        if (rs2.getInt("MC01")==0){
                    Principal.MC01.setEnabled(false);
                } 
        if (rs2.getInt("BP01")==0){
                    Principal.BP01.setEnabled(false);
                } 
        if (rs2.getInt("BP02")==0){
                    Principal.BP02.setEnabled(false);
                } 
        if (rs2.getInt("BP03")==0){
                    Principal.BP03.setEnabled(false);
                } 
        if (rs2.getInt("BP04")==0){
                    Principal.BP04.setEnabled(false);
                } 
        if (rs2.getInt("BP05")==0){
                    Principal.BP05.setEnabled(false);
                } 
        if (rs2.getInt("BP06")==0){
                    Principal.BP06.setEnabled(false);
                } 
       
                rs2.close();
                cargar2.close();
                conect.desconectar();
           }else{
                JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar"  ); 
                rs2.close();
                cargar2.close();
                conect.desconectar();
            }
    }catch (SQLException ex){
    conect.desconectar();   
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }
    
    }
        
    public void cargaraccesos(String id){
    conectar conect = new conectar(); 
    conect.conexion();
    try {
    String consulta; 
     // creamos la consulta
     consulta="SELECT  * From Accesos where Usuario ='"+ id +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
     if (rs2.next()){
         
        if (rs2.getInt("MA01")==0){
                    Permisos.MA01.setSelected(false);
                } 
        if (rs2.getInt("MA02")==0){
                    Permisos.MA02.setSelected(false);
                } 
        if (rs2.getInt("MA03")==0){
                    Permisos.MA03.setSelected(false);
                } 
        if (rs2.getInt("MB01")==0){
                    Permisos.MB01.setSelected(false);
                } 
        if (rs2.getInt("MB02")==0){
                    Permisos.MB02.setSelected(false);
                } 
        if (rs2.getInt("MB03")==0){
                    Permisos.MB03.setSelected(false);
                } 
        if (rs2.getInt("MB04")==0){
                    Permisos.MB04.setSelected(false);
                } 
        if (rs2.getInt("MB05")==0){
                    Permisos.MB05.setSelected(false);
                } 
        if (rs2.getInt("MB06")==0){
                    Permisos.MB06.setSelected(false);
                } 
        if (rs2.getInt("MR01")==0){
                    Permisos.MR01.setSelected(false);
                } 
        if (rs2.getInt("MR02")==0){
                    Permisos.MR02.setSelected(false);
                } 
        if (rs2.getInt("MR03")==0){
                    Permisos.MR03.setSelected(false);
                } 
        if (rs2.getInt("MR04")==0){
                    Permisos.MR04.setSelected(false);
                } 
        if (rs2.getInt("MR05")==0){
                    Permisos.MR05.setSelected(false);
                } 
        if (rs2.getInt("MC01")==0){
                    Permisos.MC01.setSelected(false);
                } 
        if (rs2.getInt("BP01")==0){
                    Permisos.BP01.setSelected(false);
                } 
        if (rs2.getInt("BP02")==0){
                    Permisos.BP02.setSelected(false);
                } 
        if (rs2.getInt("BP03")==0){
                    Permisos.BP03.setSelected(false);
                } 
        if (rs2.getInt("BP04")==0){
                    Permisos.BP04.setSelected(false);
                } 
        if (rs2.getInt("BP05")==0){
                    Permisos.BP05.setSelected(false);
                } 
        if (rs2.getInt("BP06")==0){
                    Permisos.BP06.setSelected(false);
                } 
       
                rs2.close();
                cargar2.close();
                conect.desconectar();
           }else{
                JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar"  ); 
                rs2.close();
                cargar2.close();
                conect.desconectar();
            }
    }catch (SQLException ex){
    conect.desconectar();   
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }
    
    }
    
    public void actualizaraccesos(String iduser){
    

           Integer MA01,MA02,MA03,MB01,MB02,MB03,MB04,MB05,MB06,MR01,MR02,MR03,MR04,MR05,MC01,BP01,BP02,BP03,BP04,BP05,BP06;     

               if (Permisos.MA01.isSelected()){
                   MA01 = 1;
               }else{
                   MA01 = 0;       
               } 
        
               if (Permisos.MA02.isSelected()){
                   MA02 = 1;
               }else{
                   MA02 = 0;       
               } 
        
               if (Permisos.MA03.isSelected()){
                   MA03 = 1;
               }else{
                   MA03 = 0;       
               } 
        
               if (Permisos.MB01.isSelected()){
                   MB01 = 1;
               }else{
                   MB01 = 0;       
               } 
        
               if (Permisos.MB02.isSelected()){
                   MB02 = 1;
               }else{
                   MB02 = 0;       
               } 
        
               if (Permisos.MB03.isSelected()){
                   MB03 = 1;
               }else{
                   MB03 = 0;       
               } 
        
               if (Permisos.MB04.isSelected()){
                   MB04 = 1;
               }else{
                   MB04 = 0;       
               } 
        
               if (Permisos.MB05.isSelected()){
                   MB05 = 1;
               }else{
                   MB05 = 0;       
               } 
        
               if (Permisos.MB06.isSelected()){
                   MB06 = 1;
               }else{
                   MB06 = 0;       
               } 
        
               if (Permisos.MR01.isSelected()){
                   MR01 = 1;
               }else{
                   MR01 = 0;       
               } 
        
               if (Permisos.MR02.isSelected()){
                   MR02 = 1;
               }else{
                   MR02 = 0;       
               } 
        
               if (Permisos.MR03.isSelected()){
                   MR03 = 1;
               }else{
                   MR03 = 0;       
               } 
        
               if (Permisos.MR04.isSelected()){
                   MR04 = 1;
               }else{
                   MR04 = 0;       
               } 
        
               if (Permisos.MR05.isSelected()){
                   MR05 = 1;
               }else{
                   MR05 = 0;       
               } 
        
               if (Permisos.MC01.isSelected()){
                   MC01 = 1;
               }else{
                   MC01 = 0;       
               } 
        
               if (Permisos.BP01.isSelected()){
                   BP01 = 1;
               }else{
                   BP01 = 0;       
               } 
        
               if (Permisos.BP02.isSelected()){
                   BP02 = 1;
               }else{
                   BP02 = 0;       
               } 
        
               if (Permisos.BP03.isSelected()){
                   BP03 = 1;
               }else{
                   BP03 = 0;       
               } 
        
               if (Permisos.BP04.isSelected()){
                   BP04 = 1;
               }else{
                   BP04 = 0;       
               } 
        
               if (Permisos.BP05.isSelected()){
                   BP05 = 1;
               }else{
                   BP05 = 0;       
               } 
        
               if (Permisos.BP06.isSelected()){
                   BP06 = 1;
               }else{
                   BP06 = 0;       
               } 
            try {    
               
        conectar conexcio = new conectar(); 
        conexcio.conexion();
        guardaracceso=conexcio.con.prepareStatement("UPDATE Accesos SET MA01=?,MA02=?,MA03=?,MB01=?,MB02=?,MB03=?,MB04=?,MB05=?,MB06=?,MR01=?,MR02=?,MR03=?,MR04=?,MR05=?,MC01=?,BP01=?,BP02=?,BP03=?,BP04=?,BP05=?,BP06=? WHERE Usuario=?");
        guardaracceso.setInt(1, MA01);
        guardaracceso.setInt(2, MA02);
        guardaracceso.setInt(3, MA03);
        guardaracceso.setInt(4, MB01);
        guardaracceso.setInt(5, MB02);
        guardaracceso.setInt(6, MB03);
        guardaracceso.setInt(7, MB04);
        guardaracceso.setInt(8, MB05);
        guardaracceso.setInt(9, MB06);
        guardaracceso.setInt(10, MR01);
        guardaracceso.setInt(11, MR02);
        guardaracceso.setInt(12, MR03);
        guardaracceso.setInt(13, MR04);
        guardaracceso.setInt(14, MR05);
        guardaracceso.setInt(15, MC01);
        guardaracceso.setInt(16, BP01);
        guardaracceso.setInt(17, BP02);
        guardaracceso.setInt(18, BP03);
        guardaracceso.setInt(19, BP04); 
        guardaracceso.setInt(20, BP05);
        guardaracceso.setInt(21, BP06);
        guardaracceso.setString(22, iduser);

        guardaracceso.execute();
        
        JOptionPane.showMessageDialog(null, "Accesos Actualizados Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null,"No se han actualizado los campos.");
            
        } catch (SQLException ex) {
            Logger.getLogger(Accesos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
}
