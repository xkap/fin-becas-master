package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {    
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/financiamiento?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    //private static final String JDBC_URL = "jdbc:mysql://localhost:3306/financiamiento?useUnicode=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Duoc2021";
    
    public static DataSource getDataSource(){        
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        ds.setInitialSize(50);        
        return ds;
    }
    
    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs){
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> Exception Close(ResultSet) \n" + ex.getMessage());
        }
    }
    
    public static void close(PreparedStatement stmt){
        try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> Exception Close(PreparedStatement) \n" + ex.getMessage());
        }
    }
    
    public static void close(Connection conn){
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException ex) {
            //ex.printStackTrace(System.out);
            System.out.println(">>> Exception Close(Connection) \n" + ex.getMessage());
        }
    }

    public void disconnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
