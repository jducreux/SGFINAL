/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
/**
 *
 * @author Juan
 */
public class Numero_a_Letra {    
    //private final String[] UNIDADES = {"", "uno ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] UNIDADES = {"", "uno ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trescientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};
    private final String [] ESPECIAL={"veintiuno","veintidós","veintitrés","veinticuatro","veinticinco","veintiséis","veintisiete","veintiocho","veintinueve"};
   public Numero_a_Letra() {
   }

    public String Convertir(String numero, boolean mayusculas) {
        String literal = "";
        String parte_decimal;    
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if(numero.indexOf(",")==-1){
            numero = numero + ",00";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");            
            //de da formato al numero decimal
            //parte_decimal = "Con " + Num[1] + "/100 Balboas.";
            parte_decimal = "Con " + Num[1] + "/100";
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {
                return (literal + parte_decimal).toUpperCase();
            } else {
                return (literal + parte_decimal);
            }
        } else {//error, no se puede convertir
            return literal = null;
        }
    }

    /* funciones para convertir los numeros a literales */

    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }
    
    
    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        //JOptionPane.showMessageDialog(null, "el valor es:::::"+n);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
            
            
        } else if (n > 19) {//para 20...99
            //JOptionPane.showMessageDialog(null, "el valor es:::::"+n );
         
            String u = getUnidades(num);
                //JOptionPane.showMessageDialog(null, "el valor es:::::"+u);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                  //JOptionPane.showMessageDialog(null, "el valor es:::::"+u);
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                
                if (n==21){
                return ESPECIAL [0]+" ";                
                }else if (n==22){
                  return ESPECIAL [1]+" ";  
                }else if (n==23){
                    return ESPECIAL [2]+" ";  
                }else if(n==24){
                    return ESPECIAL [3]+" ";  
                }else if(n==25){
                    return ESPECIAL [4]+" ";  
                }else if(n==26){
                    return ESPECIAL [5]+" ";  
                }else if(n==27){
                    return ESPECIAL [6]+" ";  
                }else if(n==28){
                    return ESPECIAL [7]+" ";  
                }else if(n==29){
                    return ESPECIAL [8]+" ";  
                }else {
                
               //JOptionPane.showMessageDialog(null, "el valor es u:::::"+u);
               //JOptionPane.showMessageDialog(null, "el valor es primera:::::"+ (num.substring(0, 1)));
                //u="ún";
               return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
                
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }
/*
    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        JOptionPane.showMessageDialog(null, "el valor es:::::"+n);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            //JOptionPane.showMessageDialog(null, "el valor es:::::"+n );
         
            String u = getUnidades(num);
                //JOptionPane.showMessageDialog(null, "el valor es:::::"+u);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                  JOptionPane.showMessageDialog(null, "el valor es:::::"+u);
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
               JOptionPane.showMessageDialog(null, "el valor es:::::"+num);
                //u="ún";
               return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }
*/
    private String getCentenas(String num) {// 999 o 099
        if( Integer.parseInt(num)>99 ){//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " cien ";
            } else {
                 return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            } 
        }else{//por Ej. 099 
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num)+"");            
        }        
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n="";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);  
            //JOptionPane.showMessageDialog(null, "el valor es:::::"+m );
         //aki valido si es uno(1) para corregir el monto en letras   
          if (Integer.parseInt(m)==1){
             
          return   "mil " + getCentenas(c);
          
          }else{        
           if(Integer.parseInt(m)==21){
               //JOptionPane.showMessageDialog(null, "el valor es:::::"+m );
           return   "veintiún " +"mil " + getCentenas(c);
           }else if(Integer.parseInt(m)==31){
           return   "treinta y un " +"mil " + getCentenas(c);
           }else if(Integer.parseInt(m)==41){
           return   "cuarenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==51){
           return   "cincuenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==61){
           return   "sesenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==71){
           return   "setenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==81){
           return   "ochenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==91){
           return   "noventa y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==121){
           return   "ciento veintiún " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==131){
           return   "ciento treinta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==141){
           return   "ciento cuarenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==151){
           return   "ciento cincuenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==161){
           return   "ciento sesenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==171){
           return   "ciento setenta y un " +"mil " + getCentenas(c);
           }   else if(Integer.parseInt(m)==181){
           return   "ciento ochenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==191){
           return   "ciento noventa y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==221){
           return   "doscientos veintiún " +"mil " + getCentenas(c);
           }   else if(Integer.parseInt(m)==231){
           return   "doscientos treinta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==241){
           return   "doscientos cuarenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==251){
           return   "doscientos cincuenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==261){
           return   "doscientos sesenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==271){
           return   "doscientos setenta y un " +"mil " + getCentenas(c);
           }   else if(Integer.parseInt(m)==281){
           return   "doscientos ochenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==291){
           return   "doscientos noventa y un " +"mil " + getCentenas(c);
           }else if(Integer.parseInt(m)==321){
           return   "trescientos veintiún " +"mil " + getCentenas(c);
           }   else if(Integer.parseInt(m)==331){
           return   "trescientos treinta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==341){
           return   "trescientos cuarenta y un  " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==351){
           return   "trescientos cincuenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==361){
           return   "trescientos sesenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==371){
           return   "trescientos setenta y un " +"mil " + getCentenas(c);
           }   else if(Integer.parseInt(m)==381){
           return   "trescientos ochenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==391){
           return   "trescientos noventa y un " +"mil " + getCentenas(c);
           }else if(Integer.parseInt(m)==421){
           return   "cuatrocientos veintiún " +"mil " + getCentenas(c);
           }   else if(Integer.parseInt(m)==431){
           return   "cuatrocientos treinta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==441){
           return   "cuatrocientos cuarenta y un  " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==451){
           return   "cuatrocientos cincuenta y un " +"mil " + getCentenas(c);
           } else if(Integer.parseInt(m)==461){
           return   "cuatrocientos sesenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==471){
           return   "cuatrocientos setenta y un " +"mil " + getCentenas(c);
           }   else if(Integer.parseInt(m)==481){
           return   "cuatrocientos ochenta y un " +"mil " + getCentenas(c);
           }  else if(Integer.parseInt(m)==491){
           return   "cuatrocientos noventa y un " +"mil " + getCentenas(c);
           }
            //JOptionPane.showMessageDialog(null, "el valor es:::::"+m );
          return   n +"mil " + getCentenas(c);
          }
                       
            
        } else {
            //JOptionPane.showMessageDialog(null, "el valor es:::::"+m );
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000        
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if(millon.length()>1){
            n = getCentenas(millon) + "millones ";
        }else{
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);        
    }
}




