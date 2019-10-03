/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.Entradas;
import Interfaces.Subastas;
import Interfaces.Nosubastados;
import static Interfaces.Subastas.Lista;
import static Interfaces.Subastas.jRadioButtonIndividual;
import static Interfaces.Subastas.jTextFieldCeduladelcomprador;
import static Interfaces.Subastas.jTextFieldCodigoComprador;
import static Interfaces.Subastas.jTextFieldColor;
import static Interfaces.Subastas.jTextFieldDetalle;
import static Interfaces.Subastas.jTextFieldFerrete;
import static Interfaces.Subastas.jTextFieldMontoTotal;
import static Interfaces.Subastas.jTextFieldNanimal;
import static Interfaces.Subastas.jTextFieldNombredelcomprador;
import static Interfaces.Subastas.jTextFieldNumerodelote;
import static Interfaces.Subastas.jTextFieldPeso;
import static Interfaces.Subastas.jTextFieldPesoNeto;
import static Interfaces.Subastas.jTextFieldPrecio;
import static Interfaces.Subastas.jTextFieldPrecioPactado;
import static Interfaces.Subastas.jTextFieldSexo;
import static Interfaces.Subastas.jTextFieldTipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Tserng
 */
public class subastas {
    ResultSet rs2,animal,rsmachos,rssubastados,rshembras,todos,ntodos,rsverificar;
    PreparedStatement cargar2,cargaranimal,guardarsubastas,machos,subastados,hembra,estado,animales,nanimales,verificaranimal;
    DefaultTableModel tabla;
    Integer totalmachos,totalhembra,totalporsubastar;
    Integer verificacion;
    Object[] filas = new Object[7];
    Object[] filas1 = new Object[7];
    String sexo,idanimal,tipo,color;
    public subastas(){
    }
    
     public void buscarcliente(Integer Codigo){
    try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     
     // creamos la consulta
     consulta="SELECT idClientes,Cedula, Nombre, Apellido FROM clientes where idClientes ='"+ Codigo +"'";
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){
            Subastas.jTextFieldCodigoComprador.setText(String.valueOf(rs2.getInt("idClientes")) );                
            Subastas.jTextFieldCeduladelcomprador.setText(rs2.getString("Cedula"));
            Subastas.jTextFieldNombredelcomprador.setText(rs2.getString("Nombre") + " " + rs2.getString("Apellido"));
            //Subastas.jTextFieldApellido.setText(rs2.getString("Apellido"));
           // Entradas.jTextFieldDireccion.setText(rs2.getString("Direccion"));
            jTextFieldCodigoComprador.setEnabled(false);
            jTextFieldDetalle.requestFocus();
            //imagen pendiente 
       
      rs2.close();
      cargar2.close();
    conect.desconectar();
           }else{
           JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar: El Cliente No Esta Registrado"  ); 
           Subastas.jTextFieldCodigoComprador.selectAll();
           Subastas.jTextFieldCodigoComprador.requestFocus();
           conect.desconectar();
            }
    
    
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
    
         public void machos(){
    try {
     String consulta; 
     
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
   
     
     // creamos la consulta
     consulta="SELECT count(*) FROM entrada_detalle  where Fecha ='"+ fecha +"' and sexo='MACHO'";
     //pasamos la consulta al preparestatement
    machos=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsmachos=machos.executeQuery(consulta);
     //recorremos el resulset
    rsmachos.next();
        
          totalmachos=rsmachos.getInt(1);
          Subastas.jTextFieldMachos.setText(totalmachos.toString());
  
   machos.close();
   rsmachos.close();
   conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
       // return totalmachos;
   
    }
     
      public void hembras(){
    try {
     String consulta; 
     
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
   
     
     // creamos la consulta
     consulta="SELECT count(*) FROM entrada_detalle  where Fecha ='"+ fecha +"' and sexo='HEMBRA'";
     //pasamos la consulta al preparestatement
    hembra=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rshembras=hembra.executeQuery(consulta);
     //recorremos el resulset
    rshembras.next();
        
               totalhembra=rshembras.getInt(1);
          Subastas.jTextFieldHembras.setText(totalhembra.toString());
  
   hembra.close();
   rshembras.close();
   conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
       // return totalmachos;
   
    }   
    
      public void totalmachoshembras(){
     
    try{
    Integer m,h,t;
    h= Integer.parseInt(Subastas.jTextFieldHembras.getText());
    
    m= Integer.parseInt(Subastas.jTextFieldMachos.getText());
    t=h+m;
    
    Subastas.jTextFieldTotaldeanimales.setText(t.toString());
    }catch(Exception ex){
     JOptionPane.showMessageDialog(null,"Error" + ex);
    }      
   
    }
         
      public void buscarporsubastar(){
    try {
     String consulta; 
     
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
   
     
     // creamos la consulta
     consulta="SELECT count(*) FROM entrada_detalle  where Fecha ='"+ fecha +"' and Estado='Por Subastar'";
     //pasamos la consulta al preparestatement
    subastados=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rssubastados=subastados.executeQuery(consulta);
     //recorremos el resulset
    rssubastados.next();
    totalporsubastar= rssubastados.getInt(1);
   Subastas.jTextFieldPorsubastar.setText(totalporsubastar.toString());
    
   subastados.close();
   rssubastados.close();
   conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
       // return totalmachos;
   
    }
     
       public void tablenosubastado(){
     try{
     DefaultTableModel tabla= (DefaultTableModel) Nosubastados.jTableNoSubastados.getModel();
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 
      //-----obtener la fecha----------------------
      String  dia = Integer.toString(Nosubastados.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Nosubastados.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Nosubastados.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
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
      // creamos la consulta
     consulta="SELECT idAnimal,Observacion,Sexo,Color,Precio,Peso,CodVendedor FROM entrada_detalle  where Fecha ='"+ fecha +"' and Estado='Por Subastar'";
     //pasamos la consulta al preparestatement
     nanimales=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     ntodos=nanimales.executeQuery(consulta);
     //recorremos el resulset
    while (ntodos.next()){
        
                    filas1[0]=ntodos.getInt("idAnimal");
                    filas1[1]=ntodos.getString("Observacion");
                    filas1[2]=ntodos.getString("Sexo");
                    filas1[3]=ntodos.getString("Color");
                    filas1[4]=ntodos.getDouble("Precio");
                    filas1[5]=ntodos.getDouble("Peso");
                    filas1[6]=ntodos.getInt("CodVendedor");
                                      
       tabla.addRow(filas1);
    }
      nanimales.close();
      ntodos.close();
      conect.desconectar();
     }catch(Exception ex){
     JOptionPane.showMessageDialog(null,"Error" +ex);
     }
     
     } 
      
      
      
      
     public void tablesubastado(){
     try{
     DefaultTableModel tabla= (DefaultTableModel) Subastas.jTableSubastados.getModel();
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 
      //-----obtener la fecha----------------------
      String  dia = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
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
      // creamos la consulta
     consulta="SELECT idAnimal,Observacion,Sexo,Color,Precio,Peso,idComprador FROM entrada_detalle  where  (Estado='Subastado' xor Estado='Completado') and Fecha ='"+ fecha +"' ";
     //pasamos la consulta al preparestatement
     animales=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     todos=animales.executeQuery(consulta);
     //recorremos el resulset
    while (todos.next()){
        
                    filas[0]=todos.getInt("idAnimal");
                    filas[1]=todos.getString("Observacion");
                    filas[2]=todos.getString("Sexo");
                    filas[3]=todos.getString("Color");
                    filas[4]=todos.getDouble("Precio");
                    filas[5]=todos.getDouble("Peso");
                    filas[6]=todos.getInt("idComprador");
                                      
       tabla.addRow(filas);
    }
      animales.close();
      todos.close();
      conect.desconectar();
     }catch(Exception ex){
     JOptionPane.showMessageDialog(null,"Error" +ex);
     }
     
     } 
      
     public void guardarsubastaporlote() throws SQLException{
         
    conectar conect = new conectar(); 
    conect.conexion();
      try {
          
    String consulta,tipo,detalle,nombrecomprador;
    Double peso,precio,valortotal;
    Integer numeroa,codcomprador,nlote;
    conect.con.setAutoCommit(false);
    subastas s = new subastas();
    codcomprador= Integer.parseInt(Subastas.jTextFieldCodigoComprador.getText());
    peso= Double.parseDouble(Subastas.jTextFieldPesoNeto.getText());
    precio=Double.parseDouble(Subastas.jTextFieldPrecioPactado.getText());
    detalle=Subastas.jTextFieldDetalle.getText();
    valortotal=Double.parseDouble(Subastas.jTextFieldMontoTotal.getText());
     nombrecomprador=Subastas.jTextFieldNombredelcomprador.getText();
   // nlote= Integer.parseInt(Subastas.jTextFieldNumerodelote.getText());
    lotes lo = new lotes();
    nlote= lo.buscarultimolote();
    //-----obtener la fecha----------------------
      String  dia = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
          
   String estados="Subastado";
     //String condicion="Contado";
   Iterator i = Lista.iterator();
        while(i.hasNext())
        {
            numeroa= Integer.parseInt(i.next().toString());
            tipo= s.buscaranimalporlote(numeroa, Subastas.fecha);
            //numeroa= Subastas.idaimal;
            
           //codigo para guardar en tabla subastas.
  guardarsubastas=conect.con.prepareStatement("INSERT INTO subastas ( Fecha, Tipo, NumeroA,CodComprador,Peso,Precio,Detalle,ValorTotal,nlote) VALUES (?,?,?,?,?,?,?,?,?)");
  //este es duplicando el numero consultar a juan el uso del codigo
  guardarsubastas.setString(1, fecha);
  guardarsubastas.setString(2, tipo);
  guardarsubastas.setInt(3, numeroa);
  guardarsubastas.setInt(4, codcomprador);
  guardarsubastas.setDouble(5, peso);
  guardarsubastas.setDouble(6, precio);
  guardarsubastas.setString(7, detalle);
  guardarsubastas.setDouble(8, valortotal);
  guardarsubastas.setInt(9, nlote);
  guardarsubastas.execute();
 //hasta aki
    
 //codigo para actualizar el estado en entrada detalle a subastado   
 consulta="UPDATE entrada_detalle SET Estado =?, idComprador=?,TotalBruto=?,Precio=?,Peso=?,nlote=?,nombrecomprador=?   WHERE idAnimal= ? and Fecha=?";
    //pasamos la consulta al preparestatement
    estado =conect.con.prepareStatement(consulta);
    estado.setString(1, estados);
    estado.setInt(2, codcomprador);
    estado.setDouble(3, valortotal);
    estado.setDouble(4, precio);
    estado.setDouble(5, peso);
    estado.setInt(6, nlote);
    estado.setString(7, nombrecomprador);
    estado.setInt(8, numeroa);    
    estado.setString(9, fecha);
    estado.executeUpdate(); 
 //hasta aki 
       }           
    conect.con.commit();
    JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
  
    //limipiando
    Lista.clear();  
        jRadioButtonIndividual.setSelected(true);
        jTextFieldNumerodelote.setEnabled(false);
        jTextFieldNumerodelote.setText("");
        jTextFieldDetalle.setText("");
        jTextFieldNanimal.setText("");
        jTextFieldCodigoComprador.setText("");
        jTextFieldTipo.setText("");
        jTextFieldColor.setText("");
        jTextFieldSexo.setText("");
        jTextFieldFerrete.setText("");
        jTextFieldNombredelcomprador.setText("");
        jTextFieldCeduladelcomprador.setText("");
        jTextFieldPesoNeto.setText("");
        jTextFieldPrecioPactado.setText("");
        jTextFieldMontoTotal.setText("");
        jTextFieldPrecio.setText("");
        jTextFieldPeso.setText("");
        jTextFieldNanimal.setEnabled(true);
        jTextFieldCodigoComprador.setEnabled(true);
        jTextFieldNanimal.requestFocus();
    //hasta aki
           
        }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());   
        if (conect.con!=null){
        try{
        conect.con.rollback();
        JOptionPane.showMessageDialog(null, "La Operacion No Pudo Realizarce, Se Restableceran Los Datos");
         }catch(SQLException ex1){
         JOptionPane.showMessageDialog(null, "Error" + ex1.getMessage());
         }catch(Exception exx){  
          JOptionPane.showMessageDialog(null, "Error" + exx.getMessage());   
         }
        }       
        }finally{
         try{
         guardarsubastas.close();
         estado.close();  
         conect.con.setAutoCommit(true);
         conect.desconectar();
         }catch(SQLException ex){
          JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());   
         }         
         }
     }
     
     
     public void guardarsubasta() throws SQLException{
    String consulta,tipo,detalle,nombrecomprador;
    Double peso,precio,valortotal;
    Integer numeroa,codcomprador;  
    conectar conect = new conectar();  
     conect.conexion();
    try {
   
    //se deshabilita el modo de confirmación automática
    conect.con.setAutoCommit(false);
    codcomprador= Integer.parseInt(Subastas.jTextFieldCodigoComprador.getText());
    
    tipo= Subastas.jTextFieldTipo.getText();
    numeroa= Integer.parseInt(Subastas.jTextFieldNanimal.getText());
    peso= Double.parseDouble(Subastas.jTextFieldPesoNeto.getText());
    precio=Double.parseDouble(Subastas.jTextFieldPrecioPactado.getText());
    detalle=Subastas.jTextFieldDetalle.getText();
    valortotal=Double.parseDouble(Subastas.jTextFieldMontoTotal.getText());
    nombrecomprador=Subastas.jTextFieldNombredelcomprador.getText();
    //numero=Subastas.jTextFieldNumerodelote.getText();
   // nlote= Integer.parseInt(numero);
    //-----obtener la fecha----------------------
      String  dia = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Subastas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
          
      String estados="Subastado";
     //String condicion="Contado";
   
      //codigo para guardar en tabla subastas.
  guardarsubastas=conect.con.prepareStatement("INSERT INTO subastas ( Fecha, Tipo, NumeroA,CodComprador,Peso,Precio,Detalle,ValorTotal) VALUES (?,?,?,?,?,?,?,?)");
  //este es duplicando el numero consultar a juan el uso del codigo
  guardarsubastas.setString(1, fecha);
  guardarsubastas.setString(2, tipo);
  guardarsubastas.setInt(3, numeroa);
  guardarsubastas.setInt(4, codcomprador);
  guardarsubastas.setDouble(5, peso);
  guardarsubastas.setDouble(6, precio);
  guardarsubastas.setString(7, detalle);
  guardarsubastas.setDouble(8, valortotal);
  //guardarsubastas.setInt(9, nlote);
  guardarsubastas.execute();
 //hasta aki
  
 //codigo para actualizar el estado en entrada detalle a subastado   
 consulta="UPDATE entrada_detalle SET Estado =?, idComprador=?,TotalBruto=?,Precio=?,Peso=? , nombrecomprador=? WHERE idAnimal= ? and Fecha=?";
    //pasamos la consulta al preparestatement
    estado =conect.con.prepareStatement(consulta);
    estado.setString(1, estados);
    estado.setInt(2, codcomprador);
    estado.setDouble(3, valortotal);
    estado.setDouble(4, precio);
    estado.setDouble(5, peso);
    estado.setString(6,nombrecomprador);
   // estado.setInt(6, nlote);
    estado.setInt(7, numeroa);
    estado.setString(8, fecha);
   
    estado.executeUpdate(); 
 //hasta aki     
    
    //se indica que se deben aplicar los cambios en la base de datos
    conect.con.commit();  
    
        Subastas.jTextFieldDetalle.setText("");
        jTextFieldNanimal.setText("");
        jTextFieldCodigoComprador.setText("");
        jTextFieldTipo.setText("");
        jTextFieldColor.setText("");
        jTextFieldSexo.setText("");
        jTextFieldFerrete.setText("");
        jTextFieldNombredelcomprador.setText("");
        jTextFieldCeduladelcomprador.setText("");
        jTextFieldPesoNeto.setText("");
        jTextFieldPrecioPactado.setText("");
        jTextFieldMontoTotal.setText("");
        Subastas.jTextFieldPrecio.setText("");
        jTextFieldPeso.setText("");
        Subastas.jTextFieldNanimal.requestFocus();
        jTextFieldNanimal.setEnabled(true);
        jTextFieldCodigoComprador.setEnabled(true);
    
    JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
    
    
    guardarsubastas.close();
    estado.close();
    conect.desconectar();
    
        }catch(SQLException ex){
        conect.con.rollback();
        guardarsubastas.close();
        estado.close();
        conect.desconectar();
        JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        
        }
        
     
    
    }    
     
     public Integer verificaranimal(String Codigo, String fecha){
     try {
    
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     
     // creamos la consulta
     consulta="SELECT idAnimal FROM entrada_detalle where idAnimal ='"+ Codigo +"' and Fecha ='"+ fecha +"' and Estado='Por Subastar'  ";
     //pasamos la consulta al preparestatement
    
     verificaranimal=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rsverificar=verificaranimal.executeQuery(consulta);
     if ( rsverificar.next()==true){
           
    verificacion=1;   
    rsverificar.close();
    verificaranimal.close();
    conect.desconectar();
           }else{
           JOptionPane.showMessageDialog(null,"El   Animal No Esta Registrado o Ya Ha Sido Subastado"  );
           verificacion=0;   
           Subastas.jTextFieldNumerodelote.selectAll();
           Subastas.jTextFieldNumerodelote.requestFocus();
           conect.desconectar();
            }
      
      }catch(Exception ex){
      
      JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
      }   
         
     return verificacion;
     }
     
      public void buscaranimal(Integer Codigo, String fecha){
     try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     
     // creamos la consulta
     consulta="SELECT idAnimal,Tipo, Color, Sexo,Ferrete FROM entrada_detalle where idAnimal ='"+ Codigo +"' and Fecha ='"+ fecha +"' and Estado='Por Subastar'  ";
     //pasamos la consulta al preparestatement
    
     cargaranimal=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     animal=cargaranimal.executeQuery(consulta);
           if (animal.next()==true){
            Subastas.jTextFieldNanimal.setText(String.valueOf(animal.getInt("idAnimal")) );                
            Subastas.jTextFieldTipo.setText(animal.getString("Tipo"));
            Subastas.jTextFieldColor.setText(animal.getString("Color"));
            Subastas.jTextFieldSexo.setText(animal.getString("Sexo"));
            Subastas.jTextFieldFerrete.setText(animal.getString("Ferrete"));
            //Subastas.jTextFieldPeso.setText(String.valueOf(animal.getDouble("Peso")));
            //Subastas.jTextFieldPesoNeto.setText(String.valueOf(animal.getDouble("Peso")));
            //Subastas.jTextFieldApellido.setText(rs2.getString("Apellido"));
           // Entradas.jTextFieldDireccion.setText(rs2.getString("Direccion"));
            jTextFieldNanimal.setEnabled(false);
          jTextFieldCodigoComprador.requestFocus();
            //imagen pendiente 
       
    animal.close();
    cargaranimal.close();
    conect.desconectar();
           }else{
           JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar: El Animal Ha Sido Subastado O no se a Realizado Su Registro De Entada"  ); 
           Subastas.jTextFieldNanimal.selectAll();
           Subastas.jTextFieldNanimal.requestFocus();
           conect.desconectar();
            }
    
    
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }
     
     }
     
     
     
     public String buscaranimalporlote(Integer Codigo, String fecha){
     try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     
     // creamos la consulta
     consulta="SELECT idAnimal,Tipo, Color, Sexo,Ferrete FROM entrada_detalle where idAnimal ='"+ Codigo +"' and Fecha ='"+ fecha +"' and Estado='Por Subastar'  ";
     //pasamos la consulta al preparestatement
    
     cargaranimal=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     animal=cargaranimal.executeQuery(consulta);
           if (animal.next()==true){
            //idanimal=(String.valueOf(animal.getInt("idAnimal")) );                
           tipo=animal.getString("Tipo");
               
    animal.close();
    cargaranimal.close();
    conect.desconectar();
           }else{
           JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar: El Animal Ha Sido Subastado O no se a Realizado Su Registro De Entada"  ); 
           //Subastas.jTextFieldNanimal.selectAll();
           //Subastas.jTextFieldNanimal.requestFocus();
           conect.desconectar();
            }
    
   
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
   }
     return tipo;
     }
     
}
