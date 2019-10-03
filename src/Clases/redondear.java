/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import javax.swing.JOptionPane;

/**
 *
 * @author Tserng
 */
public class redondear {
    double parteEntera, resultado;
    public redondear(){}
    
    public  double redondearDecimales(double valor, int numeroDecimales) {
       
        try{
        
        resultado = valor;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
            
        }catch(Exception ex){        
        JOptionPane.showMessageDialog(null,"Error" +ex);
        }
        
        
        return resultado;
    }

}
