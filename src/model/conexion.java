
package model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author asael
 */
public class conexion {
    //Variables de conexion 
    private final String Driver = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String passwd = "pass";
    private final String url = "jdbc:mysql://localhost:3306/tienda"; 
    
    private Connection con;
    
    
    
    //Metodo para conectarse 
    public Connection getconexion(){
        try{
            Class.forName(Driver);
            con = (Connection) DriverManager.getConnection(url,user,passwd);  
        }catch(ClassNotFoundException | SQLException ex){
           // System.err.println("Error al conectar" +ex);
           JOptionPane.showMessageDialog(null, "Error al conectar"+ex);
        }
        
        return con; 
    }
    
    public void close(){
        try{
            con.close(); 
        }catch(SQLException ex){
           JOptionPane.showMessageDialog(null, "Error al cerrar conexion"+ex);
        }
    }
    
}
