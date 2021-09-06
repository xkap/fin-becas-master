package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    
    private static Connection c;
    
    
    //VARIABLES DE ENTORNO LOCAL
    
    private static String url = "jdbc:mysql://localhost:3306/financiamiento";
    private static String user = "root";
    private static String pass = "";
    
    //VARIABLES DE ENTORNO EN NUBE
    
        
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
    public static Connection Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                c = DriverManager.getConnection(url,user,pass);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found."); 
        }
        return c;
    }
    
     
    public void cerrarConexion(Connection c){       
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("ERROR CONEXION AQUI -------------------");
            System.out.println(e.getMessage());
        }
    }          
}
