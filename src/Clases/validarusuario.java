/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Interfaces.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import sg.soft.Principal;
/**
 *
 * @author Juan
 */
public class validarusuario {
    String usertipe;
     PreparedStatement busqueda;
     ResultSet usuario, usuario1;
     String consulta;
    public  validarusuario( ){
    
    }
    
    public String BusquedaUsuario (String id, String clave){
     try {
     conectar coneccion = new conectar();
     coneccion.conexion();
     consulta="SELECT * FROM usuarios where Usuario = '"+ id + "' and Contraseña = '"+ clave +"' ";
     busqueda=coneccion.con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     usuario=busqueda.executeQuery();
     if (usuario.next()){
     usertipe=usuario.getString("Usuario");
     //JOptionPane.showMessageDialog(null,"el usuario es: " + usertipe);
     }else{
     JOptionPane.showMessageDialog(null,"Usuario y/o Contraseña Incorrecta, Intente de nuevo");
     
     }
     }catch(Exception ex) {
     JOptionPane.showMessageDialog(null,"ERROR" +ex);
     }   
    return usertipe;
    }
    
     public void datos (){
     try {
         String usuario;
         usuario = Login.txtusuario.getText();
     conectar coneccion = new conectar();
     coneccion.conexion();
     consulta="SELECT Nombre, Apellido, TipoUsuario FROM usuarios where Usuario = '"+ usuario + "'";
     busqueda=coneccion.con.prepareStatement(consulta, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
     usuario1=busqueda.executeQuery();
     if (usuario1.next()){
     Principal.usuario.setText(usuario1.getString("Nombre")+" "+usuario1.getString("Apellido"));
     Principal.tipo.setText(usuario1.getString("TipoUsuario"));
     //JOptionPane.showMessageDialog(null,"el usuario es: " + usertipe);
     }else{
     JOptionPane.showMessageDialog(null,"Usuario y/o Contraseña Incorrecta, Intente de nuevo");
     
     }
     }catch(Exception ex) {
     JOptionPane.showMessageDialog(null,"ERROR" +ex);
     }   
    }
}
