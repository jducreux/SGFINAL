/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Interfaces.Entradas;
import Interfaces.Recibos;
import Interfaces.buscarclientes;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Juan
 */
public class ReciboAbonos {
     Object[] filas = new Object[6];
    Object[] filas1 = new Object[9];
      ResultSet rs2, rs3, todos, todos2, rs5, aux, rsr, rs, rs0, rs1, rs4;
      Integer ultimor;
    PreparedStatement cargar, cargar0, cargar1, cargar2, cargar3, cargar4, guardarrecibo, guardarrecibo1, guardarrecibo3, facturas, facturas2, factmax, numeror, UltimoRg;
    DefaultTableModel tabla; 


public ReciboAbonos(){

}

public void buscarcliente(Integer Codigo){
    try {
     String consulta;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     
     // creamos la consulta
     consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM clientes where idClientes ='"+ Codigo +"'";
     //pasamos la consulta al preparestatement
    ;
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs2=cargar2.executeQuery(consulta);
           if (rs2.next()){
            Recibos.txtBeneficiario.setText(rs2.getString("Nombre")+ " " + rs2.getString("Apellido"));         
            Recibos.cliente.setText(String.valueOf(rs2.getInt("idClientes")) );
      rs2.close();
    conect.desconectar();
           }else{
           JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar"  ); 
           conect.desconectar();
            }
    
    
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
}

public void cargarfacturas(){
    
    try {
     DefaultTableModel tabla= (DefaultTableModel) Recibos.jTableFacturas.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
                 conect.conexion();

      Integer codigo = Integer.parseInt(Recibos.cliente.getText());         
      String Est = "POR PAGAR";
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
     consulta="SELECT idFacturas, Fecha, Monto, Saldo, Tipo, Estado FROM facturas  where (CodCliente ='"+ codigo +"') and (Saldo <> 0) ORDER BY Fecha ASC";
     //pasamos la consulta al preparestatement
     facturas=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     todos=facturas.executeQuery(consulta);
     //recorremos el resulset
    while (todos.next()){
        
                    filas[0]=todos.getInt("idFacturas");
                    filas[1]=todos.getString("Fecha");
                    filas[2]=todos.getDouble("Monto");
                    filas[3]=todos.getDouble("Saldo");
                    filas[4]=todos.getString("Tipo");
                    filas[5]=todos.getString("Estado");                    
       tabla.addRow(filas);
    }
    
    Double Suma = 0.00;
    for (int i = 0; i < Recibos.jTableFacturas.getRowCount(); i++) {
        Suma = Suma + (Double.valueOf(Recibos.jTableFacturas.getValueAt(i, 3).toString()));
    }
    redondear redon  = new redondear();
    Suma = redon.redondearDecimales(Suma, 2);  
    Recibos.SaldoT.setText(String.valueOf(Suma));
    
    todos.close();
    facturas.close();
    conect.desconectar();
           
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
     
    }

public void guardarrecibo(){
    try {
            DefaultTableModel tabla = (DefaultTableModel) Recibos.jTableFacturas.getModel();
            conectar conect = new conectar(); 
            conect.conexion();
            String consulta1, consulta2;
                    Double STotal, GValor, SaldoI, aux;
    
            //-----obtener la fecha----------------------
            String  dia = Integer.toString(Recibos.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mes = Integer.toString(Recibos.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(Recibos.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
            String fecha = (year + "-" + mes+ "-" + dia);         
            //---------fin de obtener la fecha
         
               
            int codigo= Integer.parseInt(Recibos.recibo.getText());
            int codigocliente= Integer.parseInt(Recibos.cliente.getText());
            Double Monto =Double.parseDouble(Recibos.txtCantidad.getText());
            Double Saldo =Double.parseDouble(Recibos.saldo.getText());
            String Tipo = String.valueOf(Recibos.tipo.getSelectedItem());
            String Concepto = Recibos.detalle.getText();    
            String Banco =String.valueOf(Recibos.banco.getSelectedItem());
            String MontoLetras =String.valueOf(Recibos.Suma.getText());
            String NumeroCHT =String.valueOf(Recibos.NumeroCHT.getText());
            String NombreCliente =String.valueOf(Recibos.txtBeneficiario.getText());
            String AFactura =String.valueOf(Recibos.Fact.getText());
            String Estado = "REGISTRADO";
            if (Recibos.AFactura.isSelected()){
                
                if(!"0.0".equals(Recibos.saldo.getText()) && "CONTADO".equals(Recibos.Fact2.getText())){
                    JOptionPane.showMessageDialog(null, "LA FACTURA ES AL CONTADO, DEBE CANCELAR SU TOTALIDAD");
                     int filaseleccionada;
                    Recibos.txtCantidad.setText("");
                    filaseleccionada= Recibos.jTableFacturas.getSelectedRow();
                    if (filaseleccionada==-1){
                        JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun registro");
                    }else{
                        DefaultTableModel modelotabla=(DefaultTableModel) Recibos.jTableFacturas.getModel();
                        String RIS =String.valueOf(modelotabla.getValueAt(filaseleccionada, 3));
                        Recibos.saldo.setText(RIS);
                    }
                    
                    
                    
                }else
                {
                    guardarrecibo=conect.con.prepareStatement("INSERT INTO recibos ( Codigo, MontoLetras, MontoTotal, MontoEfectivo, MontoCheque, MontoTarjeta, NumeroCH, BancoCh, CodCliente, Detalle, Fecha, AFactura, Tipo, NombreCliente, SaldoActual, Estado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    //este es duplicando el numero consultar a juan el uso del codigo
                    guardarrecibo.setInt(1,codigo);
                    guardarrecibo.setString(2, MontoLetras);
                    guardarrecibo.setDouble(3, Monto);
                    if("Efectivo".equals(Tipo)){
                        guardarrecibo.setDouble(4, Monto);
                    }
                    else{
                        guardarrecibo.setDouble(4, 0.00);
                    }
                    if("Cheque".equals(Tipo)){
                        guardarrecibo.setDouble(5, Monto);
                    }
                    else{
                        guardarrecibo.setDouble(5, 0.00);
                    }
                    if("Tarjeta".equals(Tipo)){
                        guardarrecibo.setDouble(6, Monto);
                    }
                    else{
                        guardarrecibo.setDouble(6, 0.00);
                    }
                    guardarrecibo.setString(7, NumeroCHT);
                    guardarrecibo.setString(8, Banco);
                    guardarrecibo.setInt(9, codigocliente);
                    guardarrecibo.setString(10, Concepto);
                    guardarrecibo.setString(11, fecha);
                    guardarrecibo.setString(12, AFactura);
                    guardarrecibo.setString(13, Tipo);
                    guardarrecibo.setString(14, NombreCliente);
                    guardarrecibo.setDouble(15, Saldo);
                    guardarrecibo.setString(16, Estado);
                    guardarrecibo.execute(); 
                    
                    ActualizarSaldo();  

                    try {
                            if (tabla != null) {
                                while (tabla.getRowCount() > 0) {
                                    tabla.removeRow(0);
                                }
                            }
           
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,"Error" +ex);
                        }
                    
                Recibos.recibo.setText("");
                Recibos.cliente.setText("");
                Recibos.txtCantidad.setText("");
                Recibos.detalle.setText("");
                Recibos.saldo.setText("");
                Recibos.txtBeneficiario.setText("");
                Recibos.Suma.setText("");
                Recibos.SaldoT.setText("0.00");
                JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
                
                ReciboAbonos ra = new ReciboAbonos();
                
                ra.imprimirrecibo(codigo);
                
                guardarrecibo.close();
                conect.desconectar();    
                    
                }
            }else{
    
                    guardarrecibo=conect.con.prepareStatement("INSERT INTO recibos ( Codigo, MontoLetras, MontoTotal, MontoEfectivo, MontoCheque, MontoTarjeta, NumeroCH, BancoCh, CodCliente, Detalle, Fecha, Tipo, NombreCliente, SaldoActual) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    //este es duplicando el numero consultar a juan el uso del codigo
                    guardarrecibo.setInt(1,codigo);
                    guardarrecibo.setString(2, MontoLetras);
                    guardarrecibo.setDouble(3, Monto);
                    if("Efectivo".equals(Tipo)){
                        guardarrecibo.setDouble(4, Monto);
                    }
                    else{
                        guardarrecibo.setDouble(4, 0.00);
                    }
                    if("Cheque".equals(Tipo)){
                        guardarrecibo.setDouble(5, Monto);
                    }
                    else{
                        guardarrecibo.setDouble(5, 0.00);
                    }
                    if("Tarjeta".equals(Tipo)){
                        guardarrecibo.setDouble(6, Monto);
                    }
                    else{
                        guardarrecibo.setDouble(6, 0.00);
                    }
                    guardarrecibo.setString(7, NumeroCHT);
                    guardarrecibo.setString(8, Banco);
                    guardarrecibo.setInt(9, codigocliente);
                    guardarrecibo.setString(10, Concepto);
                    guardarrecibo.setString(11, fecha);
                    guardarrecibo.setString(12, Tipo);
                    guardarrecibo.setString(13, NombreCliente);
                    guardarrecibo.setDouble(14, Saldo);
                    guardarrecibo.execute();                  
                    String Est1 = "POR PAGAR";
                    
                    SaldoI = Double.parseDouble(Recibos.txtCantidad.getText());
                    STotal = 0.00;
                    //
                    consulta1="SELECT idFacturas, Fecha, Monto, Saldo, Tipo, Estado FROM facturas  where (CodCliente ='"+ codigocliente +"') and (Estado = '"+ Est1 +"') ORDER BY Fecha ASC";


                    facturas2=conect.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                    todos2=facturas2.executeQuery(consulta1);
      
                            while (todos2.next()){   
   
                                    redondear redon  = new redondear();
                                    GValor = redon.redondearDecimales(todos2.getDouble("Saldo")- SaldoI, 2);
                                    if(GValor<0){
                                        
                                        Est1 = "CANCELADO";
                                        aux = SaldoI;
                                        SaldoI = GValor*(-1);
                                        STotal = 0.00;
                                       
                                         guardarrecibo1=conect.con.prepareStatement("INSERT INTO recibos_detalles ( idRecibo, idFactura, Abono, Saldo) VALUES (?,?,?,?)");                 
                                         guardarrecibo1.setInt(1,codigo);
                                         guardarrecibo1.setInt(2, todos2.getInt("idFacturas"));
                                         guardarrecibo1.setDouble(3, (aux - SaldoI)); 
                                         guardarrecibo1.setDouble(4, (STotal));
                                         guardarrecibo1.execute(); 
                                         guardarrecibo1.close();
                                         
                                    }else if(GValor==0){
                                        Est1 = "CANCELADO";
                                        aux = SaldoI;
                                        STotal = 0.00;
                                        SaldoI = 0.00;
                                      
                                         guardarrecibo1=conect.con.prepareStatement("INSERT INTO recibos_detalles ( idRecibo, idFactura, Abono, Saldo) VALUES (?,?,?,?)");                 
                                         guardarrecibo1.setInt(1,codigo);
                                         guardarrecibo1.setInt(2, todos2.getInt("idFacturas"));
                                         guardarrecibo1.setDouble(3, (aux - SaldoI)); 
                                         guardarrecibo1.setDouble(4, (STotal));
                                         guardarrecibo1.execute(); 
                                         guardarrecibo1.close();
                                    }else{
                                        Est1 = "POR PAGAR";
                                        aux = SaldoI;
                                        STotal = GValor;
                                        SaldoI = 0.00;
                                        
                                         guardarrecibo1=conect.con.prepareStatement("INSERT INTO recibos_detalles ( idRecibo, idFactura, Abono, Saldo) VALUES (?,?,?,?)");                 
                                         guardarrecibo1.setInt(1,codigo);
                                         guardarrecibo1.setInt(2, todos2.getInt("idFacturas"));
                                         guardarrecibo1.setDouble(3, (aux));
                                         guardarrecibo1.setDouble(4, (STotal));
                                         guardarrecibo1.execute(); 
                                         guardarrecibo1.close();
                                    }
                                    
                                    consulta2="UPDATE facturas SET Saldo =?,Estado=? WHERE idFacturas= ? ";

                                        cargar3=conect.con.prepareStatement(consulta2);
                                        cargar3.setDouble(1, STotal);
                                        cargar3.setString(2, Est1);
                                        cargar3.setInt(3, todos2.getInt("idFacturas"));
                                        cargar3.executeUpdate();
                        
                            }
            
                    todos2.close();
                    facturas2.close();
                    conect.desconectar();
            

                    try {
                            if (tabla != null) {
                                while (tabla.getRowCount() > 0) {
                                    tabla.removeRow(0);
                                }
                            }
           
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,"Error" +ex);
                        }
                    
                Recibos.recibo.setText("");
                Recibos.cliente.setText("");
                Recibos.txtCantidad.setText("");
                Recibos.detalle.setText("");
                Recibos.saldo.setText("");
                Recibos.txtBeneficiario.setText("");
                Recibos.Suma.setText("");

                JOptionPane.showMessageDialog(null, "Registro Guardado Exitosamente");
                ReciboAbonos ra = new ReciboAbonos();
                ra.imprimirrecibo(codigo);
                guardarrecibo.close();
                conect.desconectar();   
            

            
            }
    
        }catch(SQLException ex){
           
        JOptionPane.showMessageDialog(null, "Error" + ex);
        
        }
     }

    public void ActualizarSaldo()
    {
    try {
        String consulta;  
        conectar conect = new conectar(); 
        conect.conexion();  
        String Estado;
        Double Saldo;
        Integer idFactura;
        
        if (Double.parseDouble(Recibos.saldo.getText()) == 0.00)
        {
            Estado = "CANCELADO";
        }else{
            Estado = "POR PAGAR";
        }
    redondear redon  = new redondear();
    Saldo = redon.redondearDecimales(Double.parseDouble(Recibos.saldo.getText()), 2);
    idFactura  = Integer.parseInt(Recibos.Fact.getText());

    consulta="UPDATE facturas SET Saldo =?,Estado=? WHERE idFacturas= ? ";

    cargar3=conect.con.prepareStatement(consulta);
    cargar3.setDouble(1, Saldo);
    cargar3.setString(2, Estado);
    cargar3.setInt(3, idFactura);
    cargar3.executeUpdate();  
    
    conect.desconectar(); 

        }catch(SQLException ex){
            
       JOptionPane.showMessageDialog(null,"Error" +ex);  
        
        }
       
    }
 
    public void Anular()
    {
    try {
        String consulta;  
        conectar conect = new conectar(); 
        conect.conexion();  
        String Estado1, Estado2, ValidarE, ValidarF;
        Double Saldo=0.00, Monto=0.00;
        Integer idFactura=0, idRecibo=0;
        redondear redon  = new redondear();
        Estado1 = "POR PAGAR";
        Estado2 = "ANULADO";
        ValidarE = Recibos.estado.getText();
        idRecibo = Integer.parseInt(Recibos.recibo.getText());

        
        if (!"ANULADO".equals(ValidarE))
        {
            ValidarF = Recibos.Fact.getText();

            if (ValidarF!=null)
            {
                idFactura = Integer.parseInt(Recibos.Fact.getText());
                Monto = Double.parseDouble(Recibos.txtCantidad.getText());
                consulta="SELECT idFacturas, Saldo, Estado FROM facturas where idFacturas ='"+ idFactura +"'";
                cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs2=cargar2.executeQuery(consulta);

                while (rs2.next())
                { 
                    consulta="UPDATE facturas SET Saldo =?,Estado=? WHERE idFacturas= ? ";
                    cargar3=conect.con.prepareStatement(consulta);
                    cargar3.setDouble(1, (rs2.getDouble("Saldo"))+ Monto);
                    cargar3.setString(2, Estado1);
                    cargar3.setInt(3, idFactura);
                    cargar3.executeUpdate();
                
                    consulta="UPDATE recibos SET Estado =? WHERE idRecibos= ? ";
                    cargar4=conect.con.prepareStatement(consulta);
                    cargar4.setString(1, Estado2);
                    cargar4.setInt(2, idRecibo);
                    cargar4.executeUpdate();                    
                }
                JOptionPane.showMessageDialog(null,"Recibo Anulado Con Exito");
            
            }else{
                JOptionPane.showMessageDialog(null,"entro al else recibo: "+idRecibo);
                
                consulta="SELECT idFactura, Saldo, Abono FROM recibos_detalles where idRecibo ='"+ idRecibo +"'";
                cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs2=cargar2.executeQuery(consulta);
                JOptionPane.showMessageDialog(null,"paso consulta");
                while (rs2.next())
                { 
                    JOptionPane.showMessageDialog(null,"entro al while");
                    consulta="SELECT idFacturas, Saldo, Estado FROM facturas where idfacturas ='"+ (rs2.getInt("idFactura")) +"'";
                    cargar3=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs3=cargar3.executeQuery(consulta);

                    while (rs3.next())
                    { 
                        JOptionPane.showMessageDialog(null,"while 2");
                        consulta="UPDATE facturas SET Saldo =?,Estado=? WHERE idFacturas= ? ";
                        cargar3=conect.con.prepareStatement(consulta);
                        Double Valor = redon.redondearDecimales((rs3.getDouble("Saldo"))+ (rs2.getDouble("Abono")), 2);
                        cargar3.setDouble(1, Valor);
                        cargar3.setString(2, Estado1);
                        cargar3.setInt(3, (rs2.getInt("idFactura")));
                        cargar3.executeUpdate();
                JOptionPane.showMessageDialog(null,"actualiza 1");
                        consulta="UPDATE recibos SET Estado =? WHERE idRecibos= ? ";
                        cargar4=conect.con.prepareStatement(consulta);
                        cargar4.setString(1, Estado2);
                        cargar4.setInt(2, idRecibo);
                        cargar4.executeUpdate();   
                        JOptionPane.showMessageDialog(null,"Actualiza 2");
                    }
                    JOptionPane.showMessageDialog(null,"Factura N°: '"+rs2.getInt("idFactura")+"' Actualizada");            
                }
                
                JOptionPane.showMessageDialog(null,"Recibo Anulado Con Exito");
   
             }  

        }else{

          JOptionPane.showMessageDialog(null,"Recibo Ya Esta Anulado");  
        }
    
    conect.desconectar(); 

    }catch(SQLException ex){
            
       JOptionPane.showMessageDialog(null,"Error" +ex);  
        
        }
       
    }
    
public Integer buscarultimo(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
     // creamos la consulta
     consulta="SELECT MAX(idRecibos) FROM Recibos ";
     //pasamos la consulta al preparestatement
   numeror=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsr=numeror.executeQuery(consulta);
     //recorremos el resulset
    rsr.next();
        
               ultimor=rsr.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
   }catch (SQLException ex1){
   JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
   }finally{
         try {
             numeror.close();
             rsr.close();
             conect.desconectar();
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
         }
   
     }   
    return ultimor;
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
 
                Recibos.banco.addItem(aux.getString("Nombre"));
                     
               
           }
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    }
       public void imprimirrecibo(Integer nrecibo){
    // JOptionPane.showMessageDialog(null,"Se Genero");
    conectar conect = new conectar(); 
    conect.conexion();
    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, null);
    PrintService impresora = (PrintService) JOptionPane.showInputDialog(null, "Eliga impresora:",
                "Imprimir Reporte", JOptionPane.QUESTION_MESSAGE, null, printService, printService[1]);       
       //JOptionPane.showMessageDialog(null,"Se Genero en la66 " + printService);
       // PrinterJob job = PrinterJob.getPrinterJob();
    if( impresora!= null)//si existen impresoras
        {           
                      try {
                   
                    JasperReport jasperReport;
                    JasperPrint jasperPrint;
                
                     Map<String, Object> params = new HashMap<String, Object>();
                    String  ruta="C:\\SG-SOFT\\subastaganadera\\src\\ReportesSG\\" +  "ImprimirRecibo.jrxml";  
                    jasperReport =JasperCompileManager.compileReport(ruta);
                    params.put("nrecibo", nrecibo);
                    jasperPrint = JasperFillManager.fillReport(jasperReport, params, conect.con);
                  //JasperViewer.viewReport(jasperPrint, false);
                   //se manda a la impresora
                   JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
                   jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint );
                   jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, impresora );
                   //jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                   jrprintServiceExporter.exportReport();
                  
                JOptionPane.showMessageDialog(null,"Se Genero en la " + impresora);
                 } catch (Exception ex) {
                    System.err.println("Error JRException: " + ex.getMessage());
                 }
                              
            }else {
               JOptionPane.showMessageDialog(null,"El Proceso Ha Sido Cancelado O no Hay Impresoras Instaladas");
           }
        }
   
    public void imprimirecuenta(Integer CodCliente){

    conectar conect = new conectar(); 
    conect.conexion();
 
                      try {
                   
                    JasperReport jasperReport;
                    JasperPrint jasperPrint;
                
                     Map<String, Object> params = new HashMap<String, Object>();
                    String  ruta="C:\\SG-SOFT\\subastaganadera\\src\\ReportesSG\\" +  "EstadoCuenta.jrxml";  
                    jasperReport =JasperCompileManager.compileReport(ruta);
                    params.put("CodCliente", CodCliente);
                    jasperPrint = JasperFillManager.fillReport(jasperReport, params, conect.con);
                    JasperViewer.viewReport(jasperPrint, false);

                 } catch (Exception ex) {
                    System.err.println("Error JRException: " + ex.getMessage());
                 }

        }
      
    
    public void EstadoCuenta(Integer codigo){
    try {
     Integer IdCliente=0, Diferencia=0;
     String consulta0, consulta1, consulta2, consulta3, consulta4, Nombre, Apellido, Direccion, Concepto, Pd="01";  
     conectar conect = new conectar(); 
     conect.conexion();
     
          consulta0="TRUNCATE TABLE rptestadocuenta";
            //pasamos la consulta al preparestatement
            cargar4=conect.con.prepareStatement(consulta0);
            cargar4.executeUpdate(consulta0);    
            
            
            
    Nombre = "";
    Apellido = "";
    Direccion = "";
     Concepto = "Pago por Venta de Animales";
    //-----obtener la fecha----------------------
    
            String  dia = Integer.toString(Recibos.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mes = Integer.toString(Recibos.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(Recibos.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
            String fecha = (year + "-" + mes+ "-" + dia);
            String fechaMes = (year + "-" + mes + "-" +Pd);
            //---------fin de obtener la fecha
     consulta0="SELECT Fecha FROM facturas where CodCliente ='"+ codigo +"' AND Estado = '"+"POR PAGAR"+"' ORDER BY Fecha ASC";  
     cargar0=conect.con.prepareStatement(consulta0,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);   
     rs0=cargar0.executeQuery(consulta0);
     Date Inicio = new Date();
          if (rs0.next()){
            Inicio = rs0.getDate("Fecha");           
           }
          
     consulta1="SELECT idClientes, Nombre, Apellido, Direccion FROM clientes where idClientes ='"+ codigo +"'";
     consulta2="SELECT Fecha, idFacturas, Monto  FROM facturas where CodCliente ='"+ codigo +"' AND Estado = '"+"POR PAGAR"+"' ORDER BY Fecha ASC";
     consulta3="SELECT Fecha,Codigo, MontoTotal, Detalle FROM recibos where CodCliente ='"+ codigo +"' AND Fecha >= '"+ Inicio + "' ORDER BY Fecha ASC";
     
     
     cargar1=conect.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     cargar2=conect.con.prepareStatement(consulta2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     cargar3=conect.con.prepareStatement(consulta3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     
     
     rs1=cargar1.executeQuery(consulta1);
     rs2=cargar2.executeQuery(consulta2);
     rs3=cargar3.executeQuery(consulta3);
     Date Actual = new Date();
     Date Pasada = new Date();

     double A7=0.00, A15=0.00, A30=0.00, A60=0.00, Saldo =0.00;
     
        
        
            if (rs1.next()){
                IdCliente = rs1.getInt("idClientes");
                Nombre = rs1.getString("Nombre");
                Apellido = rs1.getString("Apellido");
                Direccion = rs1.getString("Direccion");
            rs1.close();
           }else{
                JOptionPane.showMessageDialog(null,"No Hay Registros Para Mostrar"  ); 
                conect.desconectar();
            }
            
            
            while (rs2.next()){
                Pasada = rs2.getDate("Fecha");               
                int dias=(int) ((Actual.getTime()-Pasada.getTime())/86400000);
                
                if (dias <= 7){
                    A7 =  rs2.getDouble("Monto");   
                }else if ((dias > 7 )&&(dias <=15)){
                    A15 =  rs2.getDouble("Monto"); 
                }else if ((dias > 15 )&&(dias <=30)){
                    A30 =  rs2.getDouble("Monto"); 
                }else {
                    A60 =  rs2.getDouble("Monto"); 
                }
                
                    guardarrecibo=conect.con.prepareStatement("INSERT INTO rptestadocuenta ( CodCliente, Nombre, Apellido, Direccion, "
                            + "FechaReporte, FechaRegistro, Codigo, Concepto, Cargos, Dias, A7d, A15d, A30d, A60d, Abonos) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    //este es duplicando el numero consultar a juan el uso del codigo
                    guardarrecibo.setInt(1,IdCliente);
                    guardarrecibo.setString(2, Nombre);
                    guardarrecibo.setString(3, Apellido);
                    guardarrecibo.setString(4, Direccion);
                    guardarrecibo.setString(5, fecha);
                    guardarrecibo.setString(6, rs2.getString("Fecha"));
                    guardarrecibo.setInt(7, rs2.getInt("idFacturas"));
                    guardarrecibo.setString(8, Concepto);
                    guardarrecibo.setDouble(9, rs2.getDouble("Monto"));
                    guardarrecibo.setDouble(10, dias);
                    guardarrecibo.setDouble(11, A7);
                    guardarrecibo.setDouble(12, A15);
                    guardarrecibo.setDouble(13, A30);
                    guardarrecibo.setDouble(14, A60);
                    guardarrecibo.setDouble(15, 0.00);
                    guardarrecibo.execute(); 
                    
                    A7=0.00;
                    A15=0.00;
                    A30=0.00;
                    A60=0.00;
                    dias = 0;
           }

            
            while (rs3.next()){

                    guardarrecibo1=conect.con.prepareStatement("INSERT INTO rptestadocuenta ( CodCliente, Nombre, Apellido, Direccion, "
                            + "FechaReporte, FechaRegistro, Codigo, Concepto, Cargos, Abonos) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?)");
                    //este es duplicando el numero consultar a juan el uso del codigo
                    guardarrecibo1.setInt(1,IdCliente);
                    guardarrecibo1.setString(2, Nombre);
                    guardarrecibo1.setString(3, Apellido);
                    guardarrecibo1.setString(4, Direccion);
                    guardarrecibo1.setString(5, fecha);
                    guardarrecibo1.setString(6, rs3.getString("Fecha"));
                    guardarrecibo1.setInt(7, rs3.getInt("Codigo"));
                    guardarrecibo1.setString(8, rs3.getString("Detalle"));
                    guardarrecibo1.setDouble(9, 0.00);
                    guardarrecibo1.setDouble(10, rs3.getDouble("MontoTotal"));
                    guardarrecibo1.execute(); 
                    
           }
            
           consulta4 = "Select idrptestadocuenta, Cargos, Abonos,FechaRegistro Saldo From rptestadocuenta Order By FechaRegistro ASC"; 
           cargar4=conect.con.prepareStatement(consulta4,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); 
           rs4=cargar4.executeQuery(consulta4);
           double Pendiente = 0.00;
           while(rs4.next()){
            Pendiente = (Pendiente +  ((rs4.getDouble("Cargos"))-(rs4.getDouble("Abonos")))) ;
               consulta4="UPDATE rptestadocuenta SET Saldo =? WHERE idrptestadocuenta= ? ";
            //pasamos la consulta al preparestatement
            cargar4=conect.con.prepareStatement(consulta4);
            cargar4.setDouble(1, Pendiente);
            cargar4.setInt(2, rs4.getInt("idrptestadocuenta"));

            cargar4.executeUpdate();    
           }
           
           
            JOptionPane.showMessageDialog(null,"Datos Generados");
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
}
   
 
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 
		Date fechaInicial=dateFormat.parse("2016-02-14");
		Date fechaFinal=dateFormat.parse("2016-03-22");
 
		int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
 
		System.out.println("Hay "+dias+" dias de diferencia");
	}

}


