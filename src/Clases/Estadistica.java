/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.EstadisticaGeneral;
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
public class Estadistica {
    PreparedStatement guardarbanco, UltimoRg, cargar, facturas;
    ResultSet aux, rs, aux1, rsdeposito; 
    
     public Estadistica() { 
}
     
     public void extraerregistros(){
         try {
            String consulta1, consulta2, consulta3, consulta4, consulta5, consulta6 = null, consulta7 = null;  
            conectar conect = new conectar(); 
            conect.conexion();
            
            facturas=conect.con.prepareStatement("TRUNCATE TABLE rptgeneral");
            facturas.execute();
            facturas.close();       
    
            String  dia = Integer.toString(EstadisticaGeneral.jDateChooserFechaInicio.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mes = Integer.toString(EstadisticaGeneral.jDateChooserFechaInicio.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(EstadisticaGeneral.jDateChooserFechaInicio.getCalendar().get(Calendar.YEAR));
            String  fechainicio = (year + "-" + mes+ "-" + dia);
            //---------fin de obtener la fecha
            //-----obtener la fecha----------------------
            String  diaf = Integer.toString(EstadisticaGeneral.jDateChooserFechaFin.getCalendar().get(Calendar.DAY_OF_MONTH));
            String  mesf = Integer.toString(EstadisticaGeneral.jDateChooserFechaFin.getCalendar().get(Calendar.MONTH) + 1);
            String yearf = Integer.toString(EstadisticaGeneral.jDateChooserFechaFin.getCalendar().get(Calendar.YEAR));
            String  fechafin = (yearf + "-" + mesf+ "-" + diaf);
            
            // creamos la consulta
            
            // MONTO Y CANTIDAD DE CHEQUES IMPRESOS

            consulta1="SELECT COUNT(Numero) AS 'Cantidad', SUM(Monto) AS 'Monto' FROM cheques where Fecha = '"+fechainicio+"'";
            
            Double MontoCH=0.00;
            Integer CantidadCH=0;
            
                        UltimoRg=conect.con.prepareStatement(consulta1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                        aux=UltimoRg.executeQuery(consulta1);

                        while(aux.next()){                  
                                MontoCH = (aux.getDouble("Monto")); 
                                CantidadCH = (aux.getInt("Cantidad")); 
                        }
            UltimoRg.close();
            aux.close();
            // MONTO DE OTROS INGRESOS DE CAJA

            consulta2="SELECT SUM(Monto) AS 'Monto' FROM registroscaja Where Fecha = '"+fechainicio+"' AND Tipo = 'INGRESO';";
            
            Double MontoING=0.00;
            
                        UltimoRg=conect.con.prepareStatement(consulta2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                        aux=UltimoRg.executeQuery(consulta2);

                        while(aux.next()){                  
                                MontoING = (aux.getDouble("Monto"));   
                        }
            UltimoRg.close();
            aux.close();
            // MONTO DE GASTOS DE CAJA

            consulta3="SELECT SUM(Monto) AS 'Monto' FROM registroscaja Where Fecha = '"+fechainicio+"' AND Tipo = 'GASTO';";
            
            Double MontoGAS=0.00;
            
                        UltimoRg=conect.con.prepareStatement(consulta3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                        aux=UltimoRg.executeQuery(consulta3);

                        while(aux.next()){                  
                                MontoGAS = (aux.getDouble("Monto"));   
                        }
            UltimoRg.close();
            aux.close();
            // TODOS LAS FACTURAS DEL DIA DE AQUI SACAMOS (TOTAL  DE TRANSACCIONES, CONTADOS Y CREDITOS, HEMBRAS, MACHOS, TERNEROS
            consulta4="SELECT * FROM facturas Where Fecha = '"+fechainicio+"'";
            
            Integer CantTA=0, CantAM=0, CantAH=0, CantAT=0;
            Double  PromM=0.00, PromH=0.00, MontoFacts=0.00, MontoCONT=0.00, MontoCRED=0.00, Diferencia=0.00, PromMF=0.00, PromHF=0.00;
            
                        UltimoRg=conect.con.prepareStatement(consulta4,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                        aux=UltimoRg.executeQuery(consulta4);

                        while(aux.next()){                  
                              
                            
                            CantAM = CantAM + (aux.getInt("TotalMachos"));
                            CantAH = CantAH + (aux.getInt("TotalHembras"));
                            CantAT = CantAT + (aux.getInt("TotalTerneros"))+(aux.getInt("TotalTerneras"));   
                            
                            PromM = PromM + (aux.getDouble("PPromMachos"));
                            PromH = PromH + (aux.getDouble("PPromHembras"));
                            MontoFacts = MontoFacts + (aux.getDouble("Monto")); 
                            
                            if ("CONTADO".equals(aux.getString("Tipo"))){
                                MontoCONT = MontoCONT + (aux.getDouble("Monto"));
                            }else{
                                MontoCRED = MontoCRED + (aux.getDouble("Monto"));
                            }
                            
                            
                        }
                        PromM = (PromM/CantAM);
                        PromH = (PromH/CantAH);
                        CantTA = CantAM + CantAH;
                        Diferencia = MontoFacts - MontoCH;
            UltimoRg.close();
            aux.close();
            // COMPLEMENTAR DATOS

            Integer Contador=0;
            Double MontoMachos=0.00, MontoHembras=0.00, TotalPrecio=0.00, PromedioPrecio=0.00;
            consulta5="SELECT * FROM entrada_detalle WHERE Fecha = '"+fechainicio+"' AND Estado = '"+"Completado"+"'";

                        UltimoRg=conect.con.prepareStatement(consulta5,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                        aux=UltimoRg.executeQuery(consulta5);

                        while(aux.next()){   
                            Contador = Contador + 1;
                            TotalPrecio = TotalPrecio + (aux.getDouble("Precio"));
                              if ("MACHO".equals(aux.getString("Sexo"))){
                                  MontoMachos = MontoMachos + (aux.getDouble("TotalBruto"));
                              } else{
                                  MontoHembras = MontoHembras + (aux.getDouble("TotalBruto"));
                              }
                              
                        }
            PromedioPrecio = (TotalPrecio/Contador);
            UltimoRg.close();
            aux.close();
            // COMPLEMENTAR DATOS
 
            consulta6="SELECT * FROM recibos WHERE Fecha = '"+fechainicio+"'";
            
            Double ReciboTotal=0.00, ReciboEfectivo=0.00, ReciboCheque=0.00;
            
                        UltimoRg=conect.con.prepareStatement(consulta6,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                        aux=UltimoRg.executeQuery(consulta6);

                        while(aux.next()){   
                       if((aux.getDouble("SaldoActual")!=0)){
                           ReciboTotal = ReciboTotal + (aux.getDouble("MontoTotal")); 
                       }
                                 
                                ReciboEfectivo = ReciboEfectivo + (aux.getDouble("MontoEfectivo"));  
                                ReciboCheque = ReciboCheque + (aux.getDouble("MontoCheque"));  
                        }
            UltimoRg.close();
            aux.close(); 
        // COMPLEMENTAR SALDO DE CAJA

            consulta7="SELECT SUM(MontoActual) AS 'Total' FROM caja";
            Double FCambio=0.00;
                        UltimoRg=conect.con.prepareStatement(consulta7,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                        aux=UltimoRg.executeQuery(consulta7);

                        while(aux.next()){                  
                                FCambio = (aux.getDouble("Total")-ReciboTotal-MontoING+MontoGAS);  
                               
                        }
            UltimoRg.close();
            aux.close();           
           
        guardarbanco=conect.con.prepareStatement("INSERT INTO rptgeneral "
        + "(Fecha1, Fecha2, CantidadA, AMachos, AHembras, CTerneras, MontoMachos, MontoHembras, PromedioMachos, PromedioHembras, PrecPromCompra, "
        + "CantTransacciones, TotalTransacciones, TotalContado, TotalCredito"
        + ", Diferencia, FCambio, Recibos, TGContado, OIngresos, Gastos, MontoCheques, MontoEfectivo, TotalEnCaja, TotalADepositar, CantCheques, TransCheques) "
        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        guardarbanco.setString(1, fechainicio);
        guardarbanco.setString(2, fechafin);
        guardarbanco.setInt(3, CantTA);
        guardarbanco.setInt(4, CantAM);
        guardarbanco.setInt(5, CantAH);
        guardarbanco.setInt(6, CantAT);
        guardarbanco.setDouble(7, MontoMachos);
        guardarbanco.setDouble(8, MontoHembras);
        guardarbanco.setDouble(9, PromM);
        guardarbanco.setDouble(10, PromH);
        guardarbanco.setDouble(11, PromedioPrecio);
        guardarbanco.setInt(12, CantTA);
        guardarbanco.setDouble(13, MontoFacts);
        guardarbanco.setDouble(14, MontoCONT);
        guardarbanco.setDouble(15, MontoCRED);
        guardarbanco.setDouble(16, Diferencia);
        guardarbanco.setDouble(17, FCambio);
        guardarbanco.setDouble(18, ReciboTotal);     
        guardarbanco.setDouble(19, MontoCONT);
        guardarbanco.setDouble(20, MontoING);
        guardarbanco.setDouble(21, MontoGAS);
        guardarbanco.setDouble(22, ReciboCheque);
        guardarbanco.setDouble(23, ReciboEfectivo);
        guardarbanco.setDouble(24, (FCambio+ReciboTotal+MontoCONT+MontoING-MontoGAS));
        guardarbanco.setDouble(25, (ReciboCheque+ReciboEfectivo));
        guardarbanco.setDouble(26, CantidadCH);
        guardarbanco.setDouble(27, MontoCH);
        guardarbanco.execute();
        JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente","Mensaje",JOptionPane.INFORMATION_MESSAGE);
    
            
            
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error" +ex);
        }
    
     }
}
