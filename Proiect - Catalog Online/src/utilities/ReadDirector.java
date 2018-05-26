package utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import entities.Elev;
import entities.Nota;

public interface ReadDirector {

	default ArrayList <String> getClase(Connection connection)
	{
		ArrayList <String> clase=new ArrayList<>();
		
        try {
        	Statement stmt;
    	    stmt = (Statement) connection.createStatement();
    	    String sql = "Select distinct clasa from Clasa;";
    	    ResultSet rs;
    	    rs = stmt.executeQuery(sql);
            while(rs.next())
            {  
            	clase.add(new String(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clase;
	}
	
	default ArrayList <Elev> getElevi(String clasa,Connection connection)
	{
		ArrayList <Elev> elevi=new ArrayList<>();
		PreparedStatement  stmt=null;
        try {
        	stmt = connection.prepareStatement("select id_elev,nume,prenume\r\n" + 
        			"from Elev\r\n" + 
        			"where clasa= ?;");
        	stmt.setString(1, clasa);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {  
            	elevi.add(new Elev(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return elevi;
	}
	
	default Boolean updateElev(String nume,String prenume,String clasa,String id,Connection connection) {
		PreparedStatement  stmt=null;
        try {
         stmt = (PreparedStatement) connection.prepareStatement("update Elev\r\n" + 
         		"set nume = ?, prenume = ?, clasa = ?\r\n" + 
         		"where id_elev = ?;");
         stmt.setString(1, nume);
         stmt.setString(2, prenume);
         stmt.setString(3, clasa);
         stmt.setString(4, id);
         stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}
	
	default Boolean removeElev(String id, Connection connection)
	{
		PreparedStatement  stmt=null;
        try {
         stmt = (PreparedStatement) connection.prepareStatement("delete from Elev where id_elev = ?;"
         		+ "delete from Note where id_elev = ?;"
         		+ "delete from Absente where id_elev = ?;"
         		+ "delete from LoginData where id = ?;");
         stmt.setString(1, id);
         stmt.setString(2, id);
         stmt.setString(3, id);
         stmt.setString(4, id);
         stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
	}
}

