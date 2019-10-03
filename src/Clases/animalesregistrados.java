/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Interfaces.Entradas;
import Interfaces.Facturacion;
import Interfaces.ModificarEntradas;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author Tserng
 */
public class animalesregistrados {
    ResultSet rsmachos,rshembras,todos,rsentradas, buy, cargall, cargalll, ActualizarE,rsexiste, TVendidos, TVendedor, ActualizarEl;
    PreparedStatement machos,hembras,animales,editar,numeroentrada, comprados, compradosl, InsertFact, ActEdetalle, Completados, ActEntrada,existe, TV01, TV02, ActEdetallel;
    Integer totalmachos,totalhembras, ultimaentrada,totalexiste;
    
    //DefaultTableModel tabla;
    Object[] filas = new Object[6];
    Object[] filas1 = new Object[14];
    Object[] filas2 = new Object[10];
       
      
    
    public  animalesregistrados(){
    
    }
    
    public void entradasparamodificar (){
    
     try {
     DefaultTableModel tabla= (DefaultTableModel) ModificarEntradas.jTableModificarEntradas.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(ModificarEntradas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(ModificarEntradas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(ModificarEntradas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
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
            JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
        }
        //-----hasta aki limpiar tabla-----
     
     // creamos la consulta
     consulta="SELECT idAnimal,Tipo,Sexo,Color,Ferrete,ferre2,ferre3,ferre4,ferre5,ferre6,ferre7,CodVendedor,Observacion,idedetalle FROM entrada_detalle  where Fecha ='"+ fecha +"' ORDER BY idAnimal";
     //pasamos la consulta al preparestatement
     animales=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     todos=animales.executeQuery(consulta);
     //recorremos el resulset
    while (todos.next()){
        filas1[0]=todos.getInt("idAnimal");
                    filas1[1]=todos.getString("Tipo");
                    filas1[2]=todos.getString("Sexo");
                    filas1[3]=todos.getString("Color");
                    //filas1[4]=todos.getDouble("Peso");
                    filas1[4]=todos.getString("Ferrete");
                     filas1[5]=todos.getString("ferre2");
                      filas1[6]=todos.getString("ferre3");
                       filas1[7]=todos.getString("ferre4");
                        filas1[8]=todos.getString("ferre5");
                         filas1[9]=todos.getString("ferre6");
                          filas1[10]=todos.getString("ferre7");
                    filas1[11]=todos.getInt("CodVendedor");
                    filas1[12]=todos.getString("Observacion");   
                    filas1[13]=todos.getInt("idedetalle");
                  
       tabla.addRow(filas1);
    }
    todos.close();
    animales.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    }
    
    public void cargaranimalesporcodigo(String codigo ){
 try {
     DefaultTableModel tabla= (DefaultTableModel) ModificarEntradas.jTableModificarEntradas.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(ModificarEntradas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(ModificarEntradas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(ModificarEntradas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
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
     consulta="SELECT idAnimal,Tipo,Sexo,Color,Peso,Ferrete,ferre2,ferre3,ferre4,ferre5,ferre6,ferre7,CodVendedor,Observacion,idedetalle FROM entrada_detalle  where CodVendedor LIKE '"+ codigo +"%' and  Fecha ='"+ fecha +"' ORDER BY idedetalle";
     //pasamos la consulta al preparestatement
     animales=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     todos=animales.executeQuery(consulta);
     //recorremos el resulset
    while (todos.next()){
        filas1[0]=todos.getInt("idAnimal");
                    filas1[1]=todos.getString("Tipo");
                    filas1[2]=todos.getString("Sexo");
                    filas1[3]=todos.getString("Color");
                    //filas1[4]=todos.getDouble("Peso");
                    filas1[4]=todos.getString("Ferrete");
                     filas1[5]=todos.getString("ferre2");
                      filas1[6]=todos.getString("ferre3");
                       filas1[7]=todos.getString("ferre4");
                        filas1[8]=todos.getString("ferre5");
                         filas1[9]=todos.getString("ferre6");
                          filas1[10]=todos.getString("ferre7");
                    filas1[11]=todos.getInt("CodVendedor");
                    filas1[12]=todos.getString("Observacion");   
                    filas1[13]=todos.getInt("idedetalle");
                   
       tabla.addRow(filas1);
    }
    todos.close();
    animales.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }

}
    
    public void cargaranimales(){
    
    try {
     DefaultTableModel tabla= (DefaultTableModel) Entradas.jTableAnimalesRegistrados.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
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
     consulta="SELECT idAnimal,Sexo,Color,CodVendedor,Peso,Observacion FROM entrada_detalle  where Fecha ='"+ fecha +"' ORDER BY idAnimal desc";
     //pasamos la consulta al preparestatement
     animales=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     todos=animales.executeQuery(consulta);
     //recorremos el resulset
    while (todos.next()){
        
                    filas[0]=todos.getInt("idAnimal");
                    filas[1]=todos.getString("Sexo");
                    filas[2]=todos.getString("Color");
                    filas[3]=todos.getInt("CodVendedor");
                    filas[4]=todos.getDouble("Peso");
                    filas[5]=todos.getString("Observacion");                    
       tabla.addRow(filas);
    }
    todos.close();
    animales.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
    }
    
    public Integer buscarultimaentrada(){
     try {
     String consulta; 
     
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
   
     
     // creamos la consulta
     consulta="SELECT MAX(idEntradas) FROM entradas  where Fecha ='"+ fecha +"'";
     //pasamos la consulta al preparestatement
   numeroentrada=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsentradas=numeroentrada.executeQuery(consulta);
     //recorremos el resulset
    rsentradas.next();
        
               ultimaentrada=rsentradas.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   numeroentrada.close();
   rsentradas.close();
   conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }   
    return ultimaentrada;
    }
    
    
   public void cargaracomprados(){
    
    try {
            DefaultTableModel tabla= (DefaultTableModel) Facturacion.jTableAnimalesVendidos.getModel();   
            String consulta;    
            conectar conect = new conectar(); 
            conect.conexion();

            String  dia = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mes = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
            String fecha = (year + "-" + mes+ "-" + dia);
            String Estado = "Subastado";
             
            try {
                if (tabla != null) {
                    while (tabla.getRowCount() > 0) {
                        tabla.removeRow(0);
                    }
                }
           
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error" +ex);
                }
            
            
    
            if(Integer.parseInt(Facturacion.idcomprador.getText())==0)
            {
                if (Facturacion.Orden == "1"){

                    consulta="SELECT idAnimal,Observacion,Sexo,Color,idComprador,Precio,Peso, TotalBruto, CodVendedor FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+Estado+"' Order BY idAnimal";
                }else if (Facturacion.Orden == "2"){
                    consulta="SELECT idAnimal,Observacion,Sexo,Color,idComprador,Precio,Peso, TotalBruto, CodVendedor FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+Estado+"' Order BY CodVendedor";
                }else{
                    consulta="SELECT idAnimal,Observacion,Sexo,Color,idComprador,Precio,Peso, TotalBruto, CodVendedor FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+Estado+"' Order BY idComprador";
                }
                
            }else{
                Integer code = Integer.parseInt(Facturacion.idcomprador.getText());
                 if (Facturacion.Orden == "1"){
                    consulta="SELECT idAnimal,Observacion,Sexo,Color,idComprador,Precio,Peso, TotalBruto, CodVendedor FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+Estado+"' And idComprador = '"+code+"' Order BY idAnimal";
                }else if (Facturacion.Orden == "2"){
                    consulta="SELECT idAnimal,Observacion,Sexo,Color,idComprador,Precio,Peso, TotalBruto, CodVendedor FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+Estado+"' And idComprador = '"+code+"' Order BY CodVendedor";
                }else{
                    consulta="SELECT idAnimal,Observacion,Sexo,Color,idComprador,Precio,Peso, TotalBruto, CodVendedor FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+Estado+"' And idComprador = '"+code+"' Order BY idComprador";
                }
            }

            
            
            
            comprados=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            buy=comprados.executeQuery(consulta);

            while (buy.next()){
                    filas2[0]= true;
                    filas2[1]=buy.getString("idAnimal");
                    filas2[2]=buy.getString("Observacion");
                    filas2[3]=buy.getString("Sexo");
                    filas2[4]=buy.getString("Color");
                    filas2[5]=buy.getString("idComprador");
                    filas2[6]=buy.getDouble("Precio");
                    filas2[7]=buy.getDouble("Peso");
                    filas2[8]=buy.getDouble("TotalBruto");
                    filas2[9]=buy.getString("CodVendedor"); 
                    tabla.addRow(filas2);
            }
            buy.close();
            comprados.close();
            conect.desconectar();
           
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"No Hay Animales Comprados o Subastados el dia de hoy");
        }
     
    }
   
    
    public void cargartodasfacturas(){
    
    try {
            DefaultTableModel tabla= (DefaultTableModel) Facturacion.jTableAnimalesVendidos.getModel();   
            String consulta, CActualizar, Consulta2,Consulta3,Consulta4, EActualizar;    
            conectar conect = new conectar(); 
            conect.conexion();

            String  dia = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mes = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
            String fecha = (year + "-" + mes+ "-" + dia);

            String Estado = "Subastado";
            Integer Codigo= Integer.parseInt(Facturacion.idcomprador.getText());
            Integer CODFact= Integer.parseInt(Facturacion.NumFactura.getText());
            
            Integer TH, TM, TT, TR, TT2, TN1, TN2, TTOR, TY, TV, TVC, TCAB;
            Double PromH, PromM, PesoM, PesoH;
                TH = 0;
                TM = 0;
                TT = 0;
                TR = 0;
                TT2 = 0;
                TN1 = 0;
                TN2 = 0;
                TTOR = 0;
                TY = 0; 
                TV = 0;
                TVC = 0;
                TCAB = 0;
                PromH = 0.00;
                PromM = 0.00;
                PesoM = 0.00;
                PesoH = 0.00;
            for (int i = 0; i < Facturacion.jTableAnimalesVendidos.getRowCount(); i++) {
            Integer Animal =Integer.parseInt(String.valueOf(Facturacion.jTableAnimalesVendidos.getValueAt(i, 1)));
            Integer Comprador =Integer.parseInt(String.valueOf(Facturacion.jTableAnimalesVendidos.getValueAt(i, 5)));            
            
            if (Facturacion.seleccion.isSelected()== true){ 
                  if (Comprador == Codigo)
                  {     
                        if( Facturacion.jTableAnimalesVendidos.getValueAt(i, 0)!=null){                       
                        }else{
                            continue;
                        }
                  } else{
                         continue; 
                          }
                }
            
                consulta="SELECT CodVendedor, idAnimal,Precio, Tipo, Color, Sexo, Ferrete, Precio, totalBruto, IdComprador, ferre2, ferre3, ferre4, ferre5, ferre6, ferre7, Peso FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+Estado+"' And idComprador = '"+Codigo+"' And idAnimal = '"+Animal+"' ";
           
                comprados=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                cargall=comprados.executeQuery(consulta);
                Integer CodVen = 0;
                Double comision, TotalReal, TotalBruto, Valor; 

                while (cargall.next()){

                    InsertFact=conect.con.prepareStatement("INSERT INTO facturas_detalle ( idFactura, idAnimal, Color, Tipo, Sexo, Peso, Precio, TotalBruto, CodVendedor, idComprador, Ferrete, ferre2, ferre3, ferre4, ferre5, ferre6, ferre7) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                    InsertFact.setInt(1,CODFact);
                    InsertFact.setInt(2,cargall.getInt("idAnimal"));        
                    InsertFact.setString(3,cargall.getString("Color")); 
                    InsertFact.setString(4,cargall.getString("Tipo")); 
                    InsertFact.setString(5,cargall.getString("Sexo")); 
                    InsertFact.setDouble(6,cargall.getDouble("Peso")); 
                    InsertFact.setDouble(7,cargall.getDouble("Precio")); 
                    InsertFact.setDouble(8,cargall.getDouble("TotalBruto")); 
                    InsertFact.setInt(9,cargall.getInt("CodVendedor")); 
                    InsertFact.setInt(10,cargall.getInt("idComprador")); 
                    InsertFact.setString(11,cargall.getString("Ferrete")); 
                    InsertFact.setString(12,cargall.getString("ferre2")); 
                    InsertFact.setString(13,cargall.getString("ferre3")); 
                    InsertFact.setString(14,cargall.getString("ferre4")); 
                    InsertFact.setString(15,cargall.getString("ferre5")); 
                    InsertFact.setString(16,cargall.getString("ferre6")); 
                    InsertFact.setString(17,cargall.getString("ferre7")); 
                    InsertFact.execute();
                    
                    int decimales;
                    decimales = 2;
                    redondear redon  = new redondear();
                    
                    TR = TR + 1;
                    if ("HEMBRA".equals(cargall.getString("Sexo"))){
                        TH = TH + 1;
                        PesoH = redon.redondearDecimales((PesoH + cargall.getDouble("Peso")), decimales);
                    }
                     if ("MACHO".equals(cargall.getString("Sexo"))){
                        TM = TM + 1;
                        PesoM = redon.redondearDecimales((PesoM + cargall.getDouble("Peso")), decimales);
                    }
                      if ("TE".equals(cargall.getString("Tipo"))){
                        TT = TT + 1;
                    }
                    if ("TA".equals(cargall.getString("Tipo"))){
                        TT2 = TT2 + 1;
                    }
                     if ("VA".equals(cargall.getString("Tipo"))){
                        TV = TV + 1;
                    }
                      if ("VF".equals(cargall.getString("Tipo"))){
                        TVC = TVC + 1;
                    }
                    if ("NA".equals(cargall.getString("Sexo"))){
                        TN1 = TN1 + 1;
                    }
                     if ("NO".equals(cargall.getString("Sexo"))){
                        TN2 = TN2 + 1;
                    }
                      if ("TO".equals(cargall.getString("Tipo"))){
                        TTOR = TTOR + 1;
                    } 
                      if ("YG".equals(cargall.getString("Sexo"))){
                        TY = TY + 1;
                    }
                     if ("CB".equals(cargall.getString("Sexo"))){
                        TCAB = TCAB + 1;
                    }                   
                      
                    
                    
                    TotalBruto = redon.redondearDecimales((cargall.getDouble("Precio")*cargall.getDouble("Peso")), decimales);                   
                    comision = redon.redondearDecimales((TotalBruto* 0.03), decimales);
                    TotalReal = redon.redondearDecimales((TotalBruto - comision), decimales);

                    CActualizar="UPDATE entrada_detalle SET Estado =?,TotalBruto=?,Comision=?,TotalReal=? WHERE idComprador= '"+Codigo+"' And Fecha = '"+fecha+"' And Estado = '"+Estado+"' And idAnimal = ?";
                    //pasamos la consulta al preparestatement
                    ActEdetalle=conect.con.prepareStatement(CActualizar);
                    ActEdetalle.setString(1, "Completado");
                    ActEdetalle.setDouble(2, TotalBruto);
                    ActEdetalle.setDouble(3, comision);
                    ActEdetalle.setDouble(4, TotalReal);
                    ActEdetalle.setInt(5, cargall.getInt("idAnimal"));
                    ActEdetalle.executeUpdate();  
                   CodVen = cargall.getInt("CodVendedor");
                }
                                 

                   
                   
                   
                    Integer Vendidos = 0;
                    Integer Totales = 0;
                    
                    Consulta2 = " SELECT count(idEntrada) AS 'Vendidos' FROM entrada_detalle Where Estado = '"+"Completado"+"' AND Fecha = '"+fecha+"' AND CodVendedor = '"+CodVen+"'";
                    TV01=conect.con.prepareStatement(Consulta2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    TVendidos=TV01.executeQuery(Consulta2);
                    while (TVendidos.next()){
                        Vendidos = TVendidos.getInt("Vendidos");
                    }
                    Consulta3 = " SELECT count(idEntrada) AS 'Total' FROM entrada_detalle Where Fecha = '"+fecha+"' AND CodVendedor = '"+CodVen+"'";
                    TV02=conect.con.prepareStatement(Consulta3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    TVendedor=TV02.executeQuery(Consulta3);
                    while (TVendedor.next()){
                        Totales = TVendedor.getInt("Total");
                    }
   
                    if (Objects.equals(Vendidos, Totales)){
                        
                        Consulta4="SELECT SUM(TotalReal) AS Total FROM entrada_detalle Where Fecha = '"+fecha+"' And Estado = '"+"Completado"+"' And CodVendedor = '"+CodVen+"'";
                        
                        Completados=conect.con.prepareStatement(Consulta4,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ActualizarE=Completados.executeQuery(Consulta4);

                            while (ActualizarE.next()){
                                EActualizar="UPDATE entradas SET Estado =?,Total=? WHERE CodCliente= '"+CodVen+"' And Fecha = '"+fecha+"'";
                                ActEntrada=conect.con.prepareStatement(EActualizar);
                                ActEntrada.setString(1, "Completado");
                                ActEntrada.setDouble(2, ActualizarE.getDouble("Total"));
                                ActEntrada.executeUpdate();  

                            }
                            
                        } 
                    
            }
            Integer Fact;
                    consulta="SELECT MAX(idFacturas) AS 'Ultima' From Facturas";    
                    compradosl=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    cargalll=compradosl.executeQuery(consulta);
                    String CActualizarl;
                    cargalll.next();
                    Fact = cargalll.getInt(1);
                    int decimales1;
                    decimales1 = 2;
                    redondear redon  = new redondear();
                    
                    if (PesoH!=0){
                        PromH = redon.redondearDecimales((PesoH/TH), decimales1);
                    }
                    if (PesoM!=0){
                        PromM = redon.redondearDecimales((PesoM/TM), decimales1);
                    }
 
                    // ACTUALIZAR CANTIDAD DE RESES
                    
                    
                    CActualizarl="UPDATE facturas SET TotalAnimales =?,TotalHembras=?,TotalMachos=?,TotalTerneros=?, TotalTerneras =?,TotalNovillos=?,TotalNovillas=?,TotalVacas=?, TotalYeguas =?,TotalCaballos=?,TotalVacaCeba=?,TotalToros=?, PPromHembras=?, PPromMachos=? WHERE idFacturas = '"+Fact+"'";
                    //pasamos la consulta al preparestatement
                    ActEdetallel=conect.con.prepareStatement(CActualizarl);
                    ActEdetallel.setInt(1, TR);
                    ActEdetallel.setInt(2, TH);
                    ActEdetallel.setInt(3, TM);
                    ActEdetallel.setInt(4, TT);                
                    ActEdetallel.setInt(5, TT2);
                    ActEdetallel.setInt(6, TN1);
                    ActEdetallel.setInt(7, TN2);
                    ActEdetallel.setInt(8, TV);
                    ActEdetallel.setInt(9, TY);
                    ActEdetallel.setInt(10, TCAB);
                    ActEdetallel.setInt(11, TVC);
                    ActEdetallel.setInt(12, TTOR);
                    ActEdetallel.setDouble(13, PromH);
                    ActEdetallel.setDouble(14, PromM);
                    ActEdetallel.executeUpdate(); 
          
            
            cargall.close();
            comprados.close();
            conect.desconectar();
            InsertFact.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Al Registrar Detalle de Factura");
        }
     
    }
    
    public Integer existeanimalentradas(Integer idanimal, String fecha){
       try {
     String consulta; 
     
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
  
     // creamos la consulta
     consulta="SELECT count(*) FROM entrada_detalle  where Fecha ='"+ fecha +"' and idAnimal='"+idanimal+"' ORDER BY idAnimal";
     //pasamos la consulta al preparestatement
    existe=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsexiste=existe.executeQuery(consulta);
     //recorremos el resulset
    rsexiste.next();
        
               totalexiste=rsexiste.getInt(1);
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   existe.close();
   rsexiste.close();
   conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }    
    return totalexiste;
    }
    
    public void machos(){
    try {
     String consulta; 
     
     conectar conect = new conectar(); 
                 conect.conexion();
                 
//Calendar c = Calendar.getInstance();
    
//-----obtener la fecha----------------------
      String  dia = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
   
     
     // creamos la consulta
     consulta="SELECT count(*) FROM entrada_detalle  where Fecha ='"+ fecha +"' and sexo='MACHO' ORDER BY idAnimal";
     //pasamos la consulta al preparestatement
    machos=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsmachos=machos.executeQuery(consulta);
     //recorremos el resulset
    rsmachos.next();
        
               totalmachos=rsmachos.getInt(1);
          Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
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
      String  dia = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Entradas.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
   
     
     // creamos la consulta
     consulta="SELECT count(*) FROM entrada_detalle  where Fecha ='"+ fecha +"' and sexo='HEMBRA' ORDER BY idAnimal";
     //pasamos la consulta al preparestatement
    hembras=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rshembras=hembras.executeQuery(consulta);
     //recorremos el resulset
    rshembras.next();
        
          totalhembras=rshembras.getInt(1);
          Entradas.jTextFieldTotalHembras.setText(totalhembras.toString());
  
   hembras.close();
   rshembras.close();
   conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    
    }
public void totalmachoshembras(){
   try{
   Integer m,h,t;
    h= Integer.parseInt(Entradas.jTextFieldTotalHembras.getText());
    
    m= Integer.parseInt(Entradas.jTextFieldTotalMachos.getText());
    t=h+m;
    
    Entradas.jTextFieldTotalAnimales.setText(t.toString());
   }catch(Exception ex){
     JOptionPane.showMessageDialog(null,"Error" +ex);
   }    
    }
       
public void guardareditados ( Integer numero,Integer idedetalle, String tipo,String sexo,String color,String ferrete,String ferre2,String ferre3,String ferre4,String ferre5,String ferre6,String ferre7,String observacion ){
try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();  
    
     // creamos la consulta
     consulta="UPDATE entrada_detalle SET idAnimal =?,Tipo=?,Sexo=?,Color=?,Ferrete=?,ferre2=?,ferre3=?,ferre4=?,ferre5=?,ferre6=?,ferre7=?,Observacion=? WHERE idedetalle= ? ";
    //pasamos la consulta al preparestatement
    editar=conect.con.prepareStatement(consulta);
    editar.setInt(1, numero);
   // editar.setInt(2, cod_cliente);
    editar.setString(2, tipo);
    editar.setString(3, sexo);
    editar.setString(4, color);
    editar.setString(5, ferrete);
    editar.setString(6, ferre2);
    editar.setString(7, ferre3);
    editar.setString(8, ferre4);
    editar.setString(9, ferre5);
    editar.setString(10, ferre6);
    editar.setString(11, ferre7);
    editar.setString(12, observacion);
    //editar.setDouble(7, peso);
    editar.setInt(13, idedetalle);
    
    editar.executeUpdate(); 
    conect.desconectar(); 
    editar.close();
    JOptionPane.showMessageDialog(null, "Registro Editado Satisfactoriamente");
        }catch(SQLException ex){
            
       JOptionPane.showMessageDialog(null,"Error" +ex);  
        
        }

}


}
