//---cod Ana begin---//
package entities;

import java.sql.*;

public class User {
    // JDBC driver name and database URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://myownpi.ddns.net:3306/register";

    //  Database credentials
    public static final String USER = "admin";
    public static final String PASS = "admin";

    public static Connection conn = null;
    public static Statement stmt = null;

    public static void createConnection() throws ClassNotFoundException, SQLException {
        //STEP 2: Register JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        //STEP 3: Open a connection
        System.out.println("Connecting to a selected database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Connected database successfully...");
    }


    public static void updateParolaBD(String username, char[] parolaNoua) throws SQLException, ClassNotFoundException {
        createConnection();
        System.out.println("Updating parola");
        stmt = conn.createStatement();
        String sql = "UPDATE LoginData\n" +
                "SET password = '" + parolaNoua +
                "' WHERE username = '" + username + "';";
        stmt.executeUpdate(sql);
        System.out.println("Updated parola");
    }

    public static char[] getPasswordByUsername(String username) throws SQLException, ClassNotFoundException {
        createConnection();
        System.out.println("Cautare parola");
        stmt = conn.createStatement();
        String sql = "SELECT password\n" +
                "FROM LoginData\n" +
                "WHERE username = '" + username + "' ;";
        ResultSet rs = stmt.executeQuery(sql);

        String str = "";
        char[] parola= str.toCharArray();
        while(rs.next()){
            parola = rs.getString("password").charAt([0]); //!!problema mare
        }

        rs.close();
        stmt.close();
        System.out.println("Parola gasita");

        return parola;
    }
}
//---cod Ana end---//