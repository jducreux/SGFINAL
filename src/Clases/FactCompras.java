/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.FacturarC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
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
public class FactCompras {
    
 ResultSet ventas, rs;
   PreparedStatement buscarv, facturas, cargar;   
   Object[] filas = new Object[8];
    

    
public FactCompras(){
}
 

public void buscarfcompras(){
    
       try {
     DefaultTableModel tabla= (DefaultTableModel) FacturarC.Tcompras.getModel();   
     String consulta;    
     conectar conect = new conectar(); 
     conect.conexion();
       
     Calendar c1 = Calendar.getInstance();
      String  dia = Integer.toString(c1.get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
      String year = Integer.toString(c1.get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);         

      try {
            if (tabla != null) {
                while (tabla.getRowCount() > 0) {
                    tabla.removeRow(0);
                }
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }

     consulta="SELECT facturas.idFacturas, facturas.Fecha, facturas.CodCliente, clientes.Nombre, clientes.Apellido, facturas.Tipo, facturas.Monto, facturas.Estado FROM facturas, clientes  where facturas.Fecha ='"+ fecha +"' AND clientes.idClientes = facturas.CodCliente ORDER BY idFacturas ASC";
      
     if ("1".equals(FacturarC.Orden)) {
         consulta="SELECT facturas.idFacturas, facturas.Fecha, facturas.CodCliente, clientes.Nombre, clientes.Apellido, facturas.Tipo, facturas.Monto, facturas.Estado FROM facturas, clientes  where facturas.Fecha ='"+ fecha +"' AND clientes.idClientes = facturas.CodCliente ORDER BY idFacturas ASC";
     }
     
         if ("2".equals(FacturarC.Orden)) {
         consulta="SELECT facturas.idFacturas, facturas.Fecha, facturas.CodCliente, clientes.Nombre, clientes.Apellido, facturas.Tipo, facturas.Monto, facturas.Estado FROM facturas, clientes  where facturas.Fecha ='"+ fecha +"' AND clientes.idClientes = facturas.CodCliente ORDER BY CodCliente ASC";
     }
          
         if ("3".equals(FacturarC.Orden)) {
         consulta="SELECT facturas.idFacturas, facturas.Fecha, facturas.CodCliente, clientes.Nombre, clientes.Apellido, facturas.Tipo, facturas.Monto, facturas.Estado FROM facturas, clientes  where facturas.Fecha ='"+ fecha +"' AND clientes.idClientes = facturas.CodCliente ORDER BY Estado ASC";
     }
     //pasamos la consulta al preparestatement
     buscarv=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     ventas=buscarv.executeQuery(consulta);
     //recorremos el resulset
    while (ventas.next()){
        
                    filas[0]=ventas.getInt("idFacturas");
                    filas[1]=ventas.getString("Fecha");
                    filas[2]=ventas.getString("CodCliente");
                    filas[3]=ventas.getString("Nombre");
                    filas[4]=ventas.getString("Apellido");
                    filas[5]=ventas.getString("Tipo");
                    filas[6]=ventas.getDouble("Monto");
                    filas[7]=ventas.getString("Estado");
                    
       tabla.addRow(filas);
    }
    ventas.close();
    buscarv.close();
    conect.desconectar();
    FacturarC.Orden = "1";       
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    
}

public void InsertarValores(){
      try {
    String consulta;      
    DefaultTableModel tabla = (DefaultTableModel) FacturarC.Tcompras.getModel();
    conectar conect = new conectar(); 
    conect.conexion();
            facturas=conect.con.prepareStatement("TRUNCATE TABLE rptselectcompras");
            facturas.execute();
            facturas.close();       
    
    for (int i = 0; i < FacturarC.Tcompras.getRowCount(); i++) { 
        if( FacturarC.Tcompras.getValueAt(i, 8)!=null){ 
            
            int Factura= Integer.parseInt(FacturarC.Tcompras.getValueAt(i, 0).toString());
            consulta = "select   f.idFacturas, f.Fecha,f.CodCliente,f.Monto,f.TotalAnimales,f.TotalHembras,f.TotalMachos,f.TotalTerneros,f.PPromHembras,f.PPromMachos, c.Nombre,c.Apellido,c.Cedula,fd.idAnimal,fd.Color,fd.Tipo,fd.CodVendedor,fd.Ferrete,fd.ferre2,fd.ferre3,fd.Precio,fd.Peso,fd.TotalBruto from facturas f, clientes c , facturas_detalle fd WHERE f.CodCliente=c.idClientes and f.idFacturas=fd.idFactura and fd.idFactura='"+Factura+"'";
            
            cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=cargar.executeQuery(consulta);
            
           while (rs.next()){               
                 
               facturas=conect.con.prepareStatement("INSERT INTO rptselectcompras (Factura, Fecha, CodCliente, Monto, TAnimales, THembras, TMachos, TTerneros, PPHembras, PPMachos, CNombre, CApellido, CCedula, idAnimal, Color, Tipo, CodVendedor, Ferrete, Ferrete2, Ferrete3, Precio, Peso, TotalBruto ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
               facturas.setInt(1,Factura);
               facturas.setString(2,rs.getString("Fecha"));
               facturas.setInt(3,rs.getInt("CodCliente"));
               facturas.setDouble(4,rs.getDouble("Monto"));
               facturas.setInt(5,rs.getInt("TotalAnimales"));
               facturas.setInt(6,rs.getInt("TotalHembras"));
               facturas.setInt(7,rs.getInt("TotalMachos"));
               facturas.setInt(8,rs.getInt("TotalTerneros"));
               facturas.setDouble(9,rs.getDouble("PPromHembras"));
               facturas.setDouble(10,rs.getDouble("PPromMachos"));
               facturas.setString(11,rs.getString("Nombre"));
               facturas.setString(12,rs.getString("Apellido"));
               facturas.setString(13,rs.getString("Cedula"));
               facturas.setInt(14,rs.getInt("idAnimal"));
               facturas.setString(15,rs.getString("Color"));
               facturas.setString(16,rs.getString("Tipo"));
               facturas.setInt(17,rs.getInt("CodVendedor"));
               facturas.setString(18,rs.getString("Ferrete"));
               facturas.setString(19,rs.getString("ferre2"));
               facturas.setString(20,rs.getString("ferre3"));
               facturas.setDouble(21,rs.getDouble("Precio"));
               facturas.setDouble(22,rs.getDouble("Peso"));
               facturas.setDouble(23,rs.getDouble("TotalBruto"));
                        
               
               facturas.execute();
               facturas.close();
           }
            }else{
                  continue;
            }   
    }
    
    conect.desconectar();
    
        }catch(SQLException ex){
           
        JOptionPane.showMessageDialog(null, "Error" + ex);
        
        }
    
}

 public void imprimirfactura(){
      conectar conect = new conectar(); 
    conect.conexion();
 
                      try {
                   
                    JasperReport jasperReport;
                    JasperPrint jasperPrint;
                
                     Map<String, Object> params = new HashMap<String, Object>();
                    String  ruta="C:\\SG-SOFT\\subastaganadera\\src\\ReportesSG\\" +  "ComprasFact.jrxml";  
                    jasperReport =JasperCompileManager.compileReport(ruta);
                    jasperPrint = JasperFillManager.fillReport(jasperReport, params, conect.con);
                    JasperViewer.viewReport(jasperPrint, false);

                 } catch (Exception ex) {
                    System.err.println("Error JRException: " + ex.getMessage());
                 }
 }
}
