package ra.model.until;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/student";
    private static final String USER="root";
    private static final String PASS="phamchithanh";
    public static Connection openConnection(){
        Connection conn= null;
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public static void closeConnection(Connection conn, CallableStatement cast) throws SQLException {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if  (cast!=null){
            cast.close();
        }
    }
}
