package lessom19;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RemoteDB {
    String dbUrl = "jdbc:mysql://ql6.freemysqlhosting.net:3306/sql6456506";
    String username = "sql6456506";
    String password = "XY9s6B6MTf";
    static ResultSet rs;
    static Connection con;
    static Statement stmt;

    public void initSQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl,username,password);
            stmt = con.createStatement();
        }
        catch (Exception e) {
            System.out.println("Error Occurred while Connecting to DB, See Details: " + e);
        }
    }

    public List<String> getCredentials() {
        List<String> credentials = new ArrayList<String>();
        try {
            rs= stmt.executeQuery("SELECT name,password FROM Employees WHERE comments='correct'");
            //rs= stmt.executeQuery("SELECT user_name,password FROM myUsers WHERE id='1'");
            rs.next();
            credentials.add(rs.getString(1));
            credentials.add(rs.getString(2));
        }
        catch(SQLException e) {
            System.out.println("Error Occured While Printing Table Data, See Details: " + e);
        }
        return credentials;
    }

    public void closeDBCon() {
        try {
            con.close();
        }
        catch(SQLException e) {
            System.out.println("Error Occured While Closing JDBC, See Details: " + e);
        }
    }



}







