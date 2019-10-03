/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Entradas;
import static Interfaces.Entradas.jTextFieldNumeroanimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Tserng
 */
public class Entradasubasta {
    ResultSet rs2;
    PreparedStatement cargar2,guardarentradas,guardardetallesentradas;
    DefaultTableModel tabla;
     
    public Entradasubasta(){
   
    }
    
    public void buscarcliente(Integer Codigo){
    conectar conect = new conectar(); 
    conect.conexion();
    try {
    String consulta; 
     // creamos la consulta
     consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM clientes where idClientes ='"+ Codigo +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){
            Entradas.jTextFieldCodigoG.setText(String.valueOf(rs2.getInt("idClientes")) );                
            Entradas.jTextFieldCedula.setText(rs2.getString("Cedula"));
            Entradas.jTextFieldNombre.setText(rs2.getString("Nombre"));
            Entradas.jTextFieldApellido.setText(rs2.getString("Apellido"));
            Entradas.jTextFieldDireccion.setText(rs2.getString("Direccion"));
            Entradas.jTextFieldCodigoG.setEnabled(false);
            Entradas.jTextFieldNumeroanimal.requestFocus();
          
            //imagen pendiente 
       
    rs2.close();
    cargar2.close();
    conect.desconectar();
           }else{
           JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar"  ); 
            Entradas.jTextFieldCodigoG.selectAll();
            Entradas.jTextFieldCodigoG.requestFocus();
            rs2.close();
            cargar2.close();
           conect.desconectar();
            }
    }catch (SQLException ex){
    conect.desconectar();   
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }
    
    }
    
       
    
    public void guardarentradas(){
   
    conectar conect = new conectar(); 
    conect.conexion();
    try {
    int idEntrada=0;
     //se deshabilita el modo de confirmación automática
    conect.con.setAutoCommit(false);
    DefaultTableModel tabla= (DefaultTableModel) Entradas.jTableEntradaDeAnimales.getModel();
    animalesregistrados identra = new animalesregistrados();
    
    //en esta clase debo guardar los datos del cliente y las entradas al sistema
    int codigocliente= Integer.parseInt(Entradas.jTextFieldCodigoG.getText());
    //int codigo=Integer.parseInt(Entradas.jTextFieldNumeroanimal.getText());
    
    //-----obtener la fecha----------------------
      String  dia = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
          
      String estado="Por Subastar";
      String condicion="Contado";
    idEntrada=identra.buscarultimaentrada();
      //codigo para guardar en tabla entradas.
  guardarentradas=conect.con.prepareStatement("INSERT INTO entradas ( idEntradas,Fecha, CodCliente, Condicion,Estado) VALUES (?,?,?,?,?)");
  //este es duplicando el numero consultar a juan el uso del codigo
  guardarentradas.setInt(1, idEntrada);
  guardarentradas.setString(2, fecha);
   guardarentradas.setInt(3, codigocliente);
   guardarentradas.setString(4, condicion);
   guardarentradas.setString(5, estado);
  guardarentradas.execute();
 // guardarentradas.close();
      
      //hasta aki  tabla de entradas
      
   
    //-------aki para guardar el contenido del jtable en la tabla detallesentradas
    for (int i = 0; i < Entradas.jTableEntradaDeAnimales.getRowCount(); i++) {
    
    String numero =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 0));
    String tipo =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 1));
    String sexo =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 2));
    String color =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 3));    
    String ferrete =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 4));
    String ferre2 =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 5));
    String ferre3 =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 6));
    String ferre4 =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 7));
    String ferre5 =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 8));
    String ferre6 =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 9));
    String ferre7 =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 10));
    //double peso = Double.parseDouble(String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 5)));
    String observacion =String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 11));
    //int cantidad=Integer.parseInt(String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 3)));
    //double costounitario=Double.parseDouble(String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 4))); 
    //double total=Double.parseDouble(String.valueOf(Entradas.jTableEntradaDeAnimales.getValueAt(i, 5))); 
  guardardetallesentradas=conect.con.prepareStatement("INSERT INTO entrada_detalle ( idEntrada,idAnimal, Fecha, CodVendedor, Tipo, Color,Sexo,Ferrete,ferre2,ferre3,ferre4,ferre5,ferre6,ferre7,Observacion,Estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
  //este es duplicando el numero consultar a juan el uso del codigo
  guardardetallesentradas.setInt(1, idEntrada);
  guardardetallesentradas.setString(2,numero);
  guardardetallesentradas.setString(3, fecha);
  guardardetallesentradas.setInt(4, codigocliente);
  guardardetallesentradas.setString(5, tipo);
  guardardetallesentradas.setString(6, color);
  guardardetallesentradas.setString(7, sexo);
  guardardetallesentradas.setString(8, ferrete);
  guardardetallesentradas.setString(9, ferre2);
  guardardetallesentradas.setString(10, ferre3);
  guardardetallesentradas.setString(11, ferre4);
  guardardetallesentradas.setString(12, ferre5);
  guardardetallesentradas.setString(13, ferre6);
  guardardetallesentradas.setString(14, ferre7);
  //guardardetallesentradas.setDouble(9, peso);
  guardardetallesentradas.setString(15, observacion);
  guardardetallesentradas.setString(16, estado);
 
  guardardetallesentradas.execute();
  
  
       }
    
    //-------------------hasta aki guardo en detallesventa-------------//
    
    //aplico la confirmacion  
    conect.con.commit();  
     //--------limpiar tabla------
      try {
            if (tabla != null) {
                while (tabla.getRowCount() > 0) {
                    tabla.removeRow(0);
                }
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
        }
        //-----hasta aki limpiar tabla-----
    
     
    Entradas.jTextFieldCodigoG.setText("");
    Entradas.jTextFieldNombre.setText("");
    Entradas.jTextFieldApellido.setText("");
    Entradas.jTextFieldCedula.setText("");
    Entradas.jTextFieldDireccion.setText("");
    Entradas.jTextFieldCodigoG.requestFocus();
    //ACTUALIZA LA TABLA DE INGRESADOS
     animalesregistrados animal = new animalesregistrados();
     animal.cargaranimales();
  
       animal.machos();
       animal.hembras();
       animal.totalmachoshembras();
     //
    JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
          
     }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());   
        if (conect.con!=null){
        try{
        conect.con.rollback();
        JOptionPane.showMessageDialog(null, "La Operacion No Pudo Realizarce, Se Restableceran Los Datos");
         }catch(SQLException ex1){
         JOptionPane.showMessageDialog(null, "Error" + ex1.getMessage());
         }   
         }
                
        }finally{
         try{
         guardarentradas.close();
         guardardetallesentradas.close(); 
         conect.con.setAutoCommit(true);
         conect.desconectar();
         }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());   
         }         
         }
          
        
         }
    
    
    public void nuevaentrada(){
    
        try {
        DefaultTableModel tabla= (DefaultTableModel) Entradas.jTableEntradaDeAnimales.getModel();
        //--------limpiar tabla------
      try {
            if (tabla != null) {
                while (tabla.getRowCount() > 0) {
                    tabla.removeRow(0);
                }
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
        }
        //-----hasta aki limpiar tabla-----
    Entradas.jTextFieldCodigoG.setText("");
    Entradas.jTextFieldNombre.setText("");
    Entradas.jTextFieldApellido.setText("");
    Entradas.jTextFieldCedula.setText("");
    Entradas.jTextFieldDireccion.setText("");
    Entradas.jTextFieldFerrete.setText("");
    Entradas.jTextFieldTipo.setText("");
    Entradas.jTextFieldSexo.setText("");
    Entradas.jTextFieldColor.setText("");
    Entradas.jTextFieldNumeroanimal.setText("");
    Entradas.jTextFieldDescripcion.setText("");
    Entradas.jTextFieldCodigoG.requestFocus();
    Entradas.jTextFieldCodigoG.setEnabled(true);
    
        }catch (Exception ex){
        JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());      
        }
    
    }
    
    }


