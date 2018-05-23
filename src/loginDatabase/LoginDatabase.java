package loginDatabase;

import java.sql.*;
public class LoginDatabase
{   private Connection  connection = null;
    private String id;
    private String statut;
    private String nume;
    private String prenume;
    static private LoginDatabase ad;
    private LoginDatabase(String user,char[] password){
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://myownpi.ddns.net:3306/register?allowMultiQueries=true", "admin", "admin"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement  stmt=null;
        try {
        	stmt = connection.prepareStatement("SELECT * FROM LoginData WHERE username=? and password=SHA1(?)");
        	stmt.setString(1, user);
        	((PreparedStatement) stmt).setString(2, new String(password));
            ResultSet rs=stmt.executeQuery();
            if(!rs.next()){
                    connection.close();
                    connection=null;
            }
            else
            {
                id=rs.getString(3);
                statut=rs.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
        	String query= "";
        	Statement stmt1 = null;
            stmt1 = connection.createStatement();
        	switch (statut) {
        	case "3" : query = "SELECT nume,prenume FROM Parinte WHERE id_parinte =  " + id + ";"; break;
        	case "2" : query = "SELECT nume,prenume FROM Elev WHERE id_elev =  " + id + ";"; break;
        	default :  query = "SELECT nume,prenume FROM Profesor WHERE id_profesor =  " + id + ";"; break;
        	}
        	System.out.println(query);
            ResultSet rs=stmt1.executeQuery(query);
            if(rs.next())
            {   
                nume = rs.getString("nume");
                prenume =rs.getString("prenume");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    static public LoginDatabase getAccess(String user,char[] password){
         if(ad!=null)
             if(ad.connection!=null)
             return ad;

         ad=new LoginDatabase(user,password);
         return ad;
     }
     public String getId()
     {
        return id;
     }
     public Connection getConnection()
     {
        return connection;
     }
     public String getStatut()
     {
         return statut;
     }
     public String getNume() {
    	 return nume;
     }
     public String getPrenume() {
    	 return prenume;
     }

}