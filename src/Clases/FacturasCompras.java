/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.EstadoCuenta;
import Interfaces.Facturacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
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
public class FacturasCompras {
    Object[] filas = new Object[6];
    Object[] filas1 = new Object[9];
    ResultSet rs1, rs2, rs3, rs4, rs5,  todos, rsfact;
    PreparedStatement cargar2, cargar3, cargar4, cargar5, factmax, guardarrecibo, facturas, numerofact;
    DefaultTableModel tabla; 
    Integer ultimo;
    
    
    public FacturasCompras(){
    }
    
    
    public void buscarcliente(Integer Codigo){
    try {
     String consulta, consulta1, consulta2, consulta3;  
     conectar conect = new conectar(); 
     conect.conexion();
    
     //-----obtener la fecha----------------------
      String  dia = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
     
     String Estado="Subastado";
     
     // creamos la consulta
     consulta="SELECT idClientes,Cedula, Nombre, Apellido, Direccion FROM clientes where idClientes ='"+ Codigo +"'";
     consulta1="SELECT COUNT(Sexo) As 'MACHOS' FROM entrada_detalle Where Sexo = '"+"MACHO"+"' And Estado = '"+Estado+"' And idComprador = '"+Codigo+"' And Fecha = '"+fecha+"'";
     consulta2="SELECT COUNT(Sexo) As 'HEMBRAS' FROM entrada_detalle Where Sexo = '"+"HEMBRA"+"' And Estado = '"+Estado+"' And idComprador = '"+Codigo+"' And Fecha = '"+fecha+"'";
     
     //pasamos la consulta al preparestatement
    
     cargar2=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     cargar3=conect.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     cargar4=conect.con.prepareStatement(consulta2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    
     rs1=cargar2.executeQuery(consulta);
     rs2=cargar3.executeQuery(consulta1);
     rs3=cargar4.executeQuery(consulta2);
           if (rs1.next()){               
                Facturacion.idcomprador.setText(rs1.getString("idClientes"));   
                Facturacion.txtnombre.setText(rs1.getString("Nombre"));         
                Facturacion.txtapellido.setText(rs1.getString("Apellido"));  
                Facturacion.txtcedula.setText(rs1.getString("Cedula"));  
                Facturacion.txtdireccion.setText(rs1.getString("Direccion"));  
                
                
                if (rs2.next()){               
                    Facturacion.txtmachos.setText(rs2.getString("MACHOS"));    
                }else{
                    JOptionPane.showMessageDialog(null,"Error De Conteo de Machos"  ); 
                    conect.desconectar();
                }
    
                if (rs3.next()){               
                    Facturacion.txthembras.setText(rs3.getString("HEMBRAS"));    
                }else{
                    JOptionPane.showMessageDialog(null,"Error De Conteo de Hembras"  ); 
                    conect.desconectar();
                }
               
                    int ID=Integer.parseInt(rs1.getString("idClientes"));
                    consulta3="SELECT Precio, Peso FROM entrada_detalle Where Estado = '"+Estado+"' And idComprador = '"+ID+"' And Fecha ='"+fecha+"'";
                    cargar5=conect.con.prepareStatement(consulta3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs4=cargar5.executeQuery(consulta3);
                Double MTotal, NuevoValor;
                MTotal = (0.00);
                 redondear redon  = new redondear();
                while (rs4.next()){
                // if (rs4.next()){
                     
                    NuevoValor = redon.redondearDecimales((rs4.getDouble("Precio")*rs4.getDouble("Peso")), 2);
                    
                    MTotal = redon.redondearDecimales(MTotal + NuevoValor, 2);
               // }else{
                  //  JOptionPane.showMessageDialog(null,"No Hay Facturas Por Completar Para Este Cliente"  );
                //    conect.desconectar();
                //}
                }
                    Facturacion.txtmonto.setText(""+MTotal); 

                    int H= Integer.parseInt(rs3.getString("HEMBRAS"));
                    int M= Integer.parseInt(rs2.getString("MACHOS"));
                    int Final= (H+M);
                    Facturacion.txttotal.setText(""+Integer.valueOf(Final));
                     Facturacion.guardar.setEnabled(true);
                    rs1.close();
                    rs2.close();
                    rs3.close();
                    rs4.close(); 
                
                conect.desconectar();
                
           }else{
                JOptionPane.showMessageDialog(null,"Este Numero De Cliente No Existe"  ); 
                conect.desconectar();
           }
    
           
           
   }catch (SQLException ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
}

    
public Integer BuscarUltFact(){
    
     conectar conect = new conectar(); 
                 conect.conexion();
         try {
     String consulta; 
                      
     // creamos la consulta
     consulta="SELECT MAX(idFacturas) FROM facturas ";
     //pasamos la consulta al preparestatement
   numerofact=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
    rsfact=numerofact.executeQuery(consulta);
     //recorremos el resulset
    rsfact.next();
        
               ultimo=rsfact.getInt(1)+1;
          //Entradas.jTextFieldTotalMachos.setText(totalmachos.toString());
  
   
           
   }catch (SQLException ex1){
   JOptionPane.showMessageDialog(null,"Error" +ex1.getMessage());
   }finally{
         try {
             numerofact.close();
             rsfact.close();
             conect.desconectar();
         } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Error" +ex.getMessage());
         }
   
     }   
    return ultimo;

}    
    
public void guardarfactura(){    
      try {
          
    DefaultTableModel tabla = (DefaultTableModel) Facturacion.jTableAnimalesVendidos.getModel();
    conectar conect = new conectar(); 
    conect.conexion();

    //-----obtener la fecha----------------------
      String  dia = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.MONTH) + 1);
      String year = Integer.toString(Facturacion.jDateChooserFecha.getCalendar().get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         
     //---------fin de obtener la fecha
         
               
    int codigo= Integer.parseInt(Facturacion.NumFactura.getText());
    int codigocliente= Integer.parseInt(Facturacion.idcomprador.getText());
    redondear redon  = new redondear();
    //Double Monto = redon.redondearDecimales(Double.parseDouble(Facturacion.txtmonto.getText()), 2);
    String Tipo = String.valueOf(Facturacion.tipo.getSelectedItem());
    String Estado = "POR PAGAR";  
    Double Acumulado=0.00;
    for (int i = 0; i < Facturacion.jTableAnimalesVendidos.getRowCount(); i++) {

    if( Facturacion.jTableAnimalesVendidos.getValueAt(i, 0)!=null){ 
        Double Monto = Double.parseDouble(String.valueOf(Facturacion.jTableAnimalesVendidos.getValueAt(i, 8)));
        Acumulado = Acumulado + Monto; 
                        }else{
                            continue;
                        }   
    }
    
    Acumulado = redon.redondearDecimales(Acumulado, 2);

  facturas=conect.con.prepareStatement("INSERT INTO facturas ( idFacturas, Monto, CodCliente, Saldo, Fecha, Tipo, Estado) VALUES (?,?,?,?,?,?,?)");
  //este es duplicando el numero consultar a juan el uso del codigo

  facturas.setInt(1,codigo);
  facturas.setDouble(2, Acumulado);
  facturas.setInt(3, codigocliente);
  facturas.setDouble(4, Acumulado);
  facturas.setString(5, fecha);
  facturas.setString(6, Tipo);
  facturas.setString(7, Estado);
  facturas.execute();
  
  
    
    facturas.close();
    conect.desconectar();
    
        }catch(SQLException ex){
           
        JOptionPane.showMessageDialog(null, "Error" + ex);
        
        }
     }

  public void LimpiarCampos()
    {
        Facturacion.txthembras.setText("");
        Facturacion.txtlote.setText("");
        Facturacion.txtmonto.setText("");
        Facturacion.txttotal.setText("");
        Facturacion.txtnombre.setText("");
        Facturacion.txtapellido.setText("");
        Facturacion.txtcedula.setText("");
        Facturacion.txtdireccion.setText("");
        Facturacion.idcomprador.setText("");
        Facturacion.txtmachos.setText("");  
        Facturacion.guardar.setEnabled(false);
        Facturacion.manual.setEnabled(false);
        Facturacion.tipo.setEnabled(false);
        Facturacion.seleccion.setEnabled(false);
        Facturacion.todos.setEnabled(false);
        Facturacion.guardar.setEnabled(false);
        Facturacion.idcomprador.setText("0");
    }
  
  public void centrar_datos(){  
    
    DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer(); 
    modelocentrar.setHorizontalAlignment(SwingConstants.CENTER); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(1).setCellRenderer(modelocentrar); 
    DefaultTableCellRenderer modelocentrar1 = new DefaultTableCellRenderer(); 
    modelocentrar1.setHorizontalAlignment(SwingConstants.CENTER); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(3).setCellRenderer(modelocentrar1); 
    DefaultTableCellRenderer modelocentrar2 = new DefaultTableCellRenderer(); 
    modelocentrar2.setHorizontalAlignment(SwingConstants.CENTER); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(4).setCellRenderer(modelocentrar2); 
    DefaultTableCellRenderer modelocentrar3 = new DefaultTableCellRenderer(); 
    modelocentrar3.setHorizontalAlignment(SwingConstants.CENTER); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(5).setCellRenderer(modelocentrar3); 
    DefaultTableCellRenderer modelocentrar4 = new DefaultTableCellRenderer(); 
    modelocentrar4.setHorizontalAlignment(SwingConstants.CENTER); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(9).setCellRenderer(modelocentrar4); 
    DefaultTableCellRenderer modelocentrar5 = new DefaultTableCellRenderer(); 
    modelocentrar5.setHorizontalAlignment(SwingConstants.LEFT); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(2).setCellRenderer(modelocentrar5); 
    DefaultTableCellRenderer modelocentrar6 = new DefaultTableCellRenderer(); 
    modelocentrar6.setHorizontalAlignment(SwingConstants.RIGHT); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(6).setCellRenderer(modelocentrar6); 
    DefaultTableCellRenderer modelocentrar7 = new DefaultTableCellRenderer(); 
    modelocentrar7.setHorizontalAlignment(SwingConstants.RIGHT); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(7).setCellRenderer(modelocentrar7); 
    DefaultTableCellRenderer modelocentrar8 = new DefaultTableCellRenderer(); 
    modelocentrar8.setHorizontalAlignment(SwingConstants.RIGHT); Facturacion.jTableAnimalesVendidos.getColumnModel().getColumn(8).setCellRenderer(modelocentrar8); 

 }
  
  
         public void imprimirfactura(Integer nfactura){
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
                    String  ruta="C:\\SG-SOFT\\subastaganadera\\src\\ReportesSG\\" +  "Facturadecompra.jrxml";  
                    jasperReport =JasperCompileManager.compileReport(ruta);
                    params.put("nfactura", nfactura);
                    jasperPrint = JasperFillManager.fillReport(jasperReport, params, conect.con);
                  JasperViewer.viewReport(jasperPrint, false);
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
         
         
    
}
