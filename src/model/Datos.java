/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asael
 */
public class Datos {
    //Query for Insert 
    private final String SQLINSERT = "INSERT INTO productos (nombre,precio,existencias) values (?,?,?)";
    //Query for Select
    private final String SQLSELECT = "select * from productos";
    //PreparedStatement
    private PreparedStatement ps ; 
    private final conexion con; 
    //Modelo para llenar tabla
    private DefaultTableModel DT;
    private ResultSet RS;

    //Constructor para inicializar
    public Datos (){
        ps = null; 
        con = new conexion(); 
    }
    
    //Metodo Create (Insertar) 
    public int insertar (String name, int precio, int existencia){
        try{
            //Obtenemos la conexion 
            ps = con.getconexion().prepareStatement(SQLINSERT);
            ps.setString(1, name);
            ps.setInt(2, precio);
            ps.setInt(3,existencia);
            //Exec
            int resultado = ps.executeUpdate();
            
            if (resultado > 0){
                JOptionPane.showMessageDialog(null, "Registro Exitoso");
            }
        }catch (HeadlessException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Fallo al registrar"+ex);
        }finally{
            ps = null; 
            con.close();
        }
        return 0; 
    }
    
    
    
    
    //Definir titulos para la tabla
    public DefaultTableModel titulos (){
        //Inicializamos variable
        DT = new DefaultTableModel(); 
        DT.addColumn("Id");
        DT.addColumn("Nombre Producto");
        DT.addColumn("Precio");
        DT.addColumn("Existencias");
        return DT; 
    }
    
    //Metodo para mostrar datos
    public DefaultTableModel getDatos(){
        try{
            titulos();
            //Obtenemos conexion y pasamos el query 
            ps = con.getconexion().prepareStatement(SQLSELECT);
            //Resulset Execute
            RS = ps.executeQuery();
            
            //arreglo para almacenar las filas 
            Object[] filas = new Object[4];
            //Mientras RS tenga un siguiente 
            while (RS.next()){
                filas[0] = RS.getInt(1);
                filas[1] = RS.getString(2);
                filas[2] = RS.getInt(3);
                filas[3] = RS.getInt(4);
                //Se añade los row 
                DT.addRow(filas);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al insertar filas");
        }finally{
            ps= null; 
            RS = null; 
            con.close();
        }
        return DT; 
    }
    
}
