package utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import entities.Elev;

public interface ReadProfesor {
	default ArrayList<String> getClase(String id_profesor,Connection connection,String materie)
	{
		ArrayList <String> clase=new ArrayList<>();
		PreparedStatement  stmt=null;
        try {
        	stmt = connection.prepareStatement("select c.clasa\r\n" + 
        			"from Clasa c join Materie m on(m.id_materie=c.id_materie)\r\n" + 
        			"where c.id_profesor=? AND m.denumire=?;");
        	stmt.setString(1, id_profesor);
        	stmt.setString(2, materie);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {  
            	clase.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clase;
	}
	default ArrayList<Elev> getElevi(String id_profesor,Connection connection,String clasa){
		ArrayList <Elev> elevi=new ArrayList<>();
		PreparedStatement  stmt=null;
        try {
        	stmt = connection.prepareStatement("select e.id_elev,e.nume, e.prenume\r\n" + 
        			"from Elev e join Clasa c on(e.clasa=c.clasa)\r\n" + 
        			"             join Materie m on(c.id_materie=m.id_materie)\r\n" + 
        			"where c.id_profesor=? and e.clasa=? ;");
        	stmt.setString(1, id_profesor);
        	stmt.setString(2, clasa);
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
	default ArrayList<String> getMaterii(String id_profesor,Connection connection){
		ArrayList <String> materii=new ArrayList<>();
		PreparedStatement  stmt=null;
        try {
        	stmt = connection.prepareStatement("select distinct denumire\r\n" + 
        			"from Clasa c join Materie m on(c.id_materie=m.id_materie)\r\n" + 
        			"where c.id_profesor=?;");
        	stmt.setString(1,  id_profesor);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {  
            	materii.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materii;
	}
}
