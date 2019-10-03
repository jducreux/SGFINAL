/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.FacturarV;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Juan
 */
public class FactVentas {
   ResultSet ventas, rs;
   PreparedStatement buscarv, facturas, cargar;   
   Object[] filas = new Object[6];
    

    
public FactVentas(){
}
 

public void buscarfventas(){
    
       try {
     DefaultTableModel tabla= (DefaultTableModel) FacturarV.Tventas.getModel();   
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
      
      consulta="SELECT idEntradas, Fecha, CodCliente, Condicion, Total, Estado FROM entradas  where Fecha ='"+ fecha +"' ORDER BY idEntradas ASC";
      
     if ("1".equals(FacturarV.Orden)) {
         consulta="SELECT idEntradas, Fecha, CodCliente, Condicion, Total, Estado FROM entradas  where Fecha ='"+ fecha +"' ORDER BY idEntradas ASC";
     }
     
         if ("2".equals(FacturarV.Orden)) {
         consulta="SELECT idEntradas, Fecha, CodCliente, Condicion, Total, Estado FROM entradas  where Fecha ='"+ fecha +"' ORDER BY CodCliente ASC";
     }
          
         if ("3".equals(FacturarV.Orden)) {
         consulta="SELECT idEntradas, Fecha, CodCliente, Condicion, Total, Estado FROM entradas  where Fecha ='"+ fecha +"' ORDER BY Estado ASC";
     }
               
     
     //pasamos la consulta al preparestatement
     buscarv=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     //pasamos al resulset la consulta preparada y ejecutamos
     ventas=buscarv.executeQuery(consulta);
     //recorremos el resulset
    while (ventas.next()){
        
                    filas[0]=ventas.getInt("idEntradas");
                    filas[1]=ventas.getString("Fecha");
                    filas[2]=ventas.getString("CodCliente");
                    filas[3]=ventas.getString("Condicion");
                    filas[4]=ventas.getDouble("Total");
                    filas[5]=ventas.getString("Estado");
                    
       tabla.addRow(filas);
    }
    ventas.close();
    buscarv.close();
    conect.desconectar();
     FacturarV.Orden = "1";      
   }catch (Exception ex){
   JOptionPane.showMessageDialog(null,"Error" +ex);
   }
    
    
}

public void InsertarValores(){
      try {
    String consulta;      
    DefaultTableModel tabla = (DefaultTableModel) FacturarV.Tventas.getModel();
    conectar conect = new conectar(); 
    conect.conexion();
            facturas=conect.con.prepareStatement("TRUNCATE TABLE rptselectventas");
            facturas.execute();
            facturas.close();   
Calendar c1 = Calendar.getInstance();
      String  dia = Integer.toString(c1.get(Calendar.DAY_OF_MONTH));
      String  mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
      String year = Integer.toString(c1.get(Calendar.YEAR));
      String fecha = (year + "-" + mes+ "-" + dia);    
    
    for (int i = 0; i < FacturarV.Tventas.getRowCount(); i++) { 
        if( FacturarV.Tventas.getValueAt(i, 6)!=null){ 
            
            int Factura= Integer.parseInt(FacturarV.Tventas.getValueAt(i, 0).toString());
            consulta = "select  cc.idClientes,cc.Nombre,cc.Apellido,cc.Direccion, ed.Fecha,ed.idAnimal,ed.nombrecomprador,ed.Tipo,ed.Color,ed.Sexo,ed.Ferrete,ed.Peso,ed.Precio,ed.TotalBruto,ed.Comision,ed.TotalReal from clientes cc, entrada_detalle ed where cc.idClientes=ed.CodVendedor and ed.idEntrada='"+Factura+"' and ed.Fecha='"+fecha+"'";
            
            cargar=conect.con.prepareStatement(consulta,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=cargar.executeQuery(consulta);
            
           while (rs.next()){               
                 
               facturas=conect.con.prepareStatement("INSERT INTO rptselectventas (idClientes, Nombre, Apellido, Direccion, Fecha, idAnimal, nombrecomprador, Tipo, Color, Sexo, Ferrete, Peso, Precio, TotalBruto, Comision, TotalReal) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
               facturas.setInt(1,rs.getInt("idClientes"));
               facturas.setString(2,rs.getString("Nombre"));
               facturas.setString(3,rs.getString("Apellido"));
               facturas.setString(4,rs.getString("Direccion"));
               facturas.setDate(5,rs.getDate("Fecha"));
               facturas.setInt(6,rs.getInt("idAnimal"));
               facturas.setString(7,rs.getString("nombrecomprador"));
               facturas.setString(8,rs.getString("Tipo"));
               facturas.setString(9,rs.getString("Color"));
               facturas.setString(10,rs.getString("Sexo"));
               facturas.setString(11,rs.getString("Ferrete"));
               facturas.setDouble(12,rs.getDouble("Peso"));
               facturas.setDouble(13,rs.getDouble("Precio"));
               facturas.setDouble(14,rs.getDouble("TotalBruto"));
               facturas.setDouble(15,rs.getDouble("Comision"));
               facturas.setDouble(16,rs.getDouble("TotalReal"));                      
               
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
                    String  ruta="C:\\SG-SOFT\\subastaganadera\\src\\ReportesSG\\" +  "VentasFact.jrxml";  
                    jasperReport =JasperCompileManager.compileReport(ruta);
                    jasperPrint = JasperFillManager.fillReport(jasperReport, params, conect.con);
                    JasperViewer.viewReport(jasperPrint, false);

                 } catch (Exception ex) {
                    System.err.println("Error JRException: " + ex.getMessage());
                 }
 }
}
