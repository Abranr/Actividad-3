/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class Conexion {
      public Connection conexionBD;
    public final String bd = "db_diagramadeclases";
    public final String urlConexion = String.format("jdbc:mysql://127.0.0.1:3306/%s",bd);
    public final String usuario = "root";
    public final String contra = "Piwi2003";
    public final String jbdc = "com.mysql.cj.jdbc.Driver";
    
    public void abrir_conexion(){
        try{
            Class.forName(jbdc);
            conexionBD=DriverManager.getConnection(urlConexion,usuario,contra);
              //JOptionPane.showMessageDialog(null, "Conexión exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
        }catch (ClassNotFoundException | SQLException ex){
            System.out.println("Algo salio mal :( " + ex.getMessage());
        }
    }
    public void cerrar_conexion(){
    try{
        conexionBD.close();
        }
        catch (SQLException ex){
            System.out.println("Algo salio mal :( "+ ex.getMessage());
       
    }
    }
}
