package utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
public interface RaportElev {
	default float getMedie(String id, Connection connection,String denumire_materie) {
		ArrayList<Float> note = new ArrayList<Float>();
		float suma=0;
		PreparedStatement  stmt=null;
		try {
			stmt = connection.prepareStatement("select n.nota\r\n" + 
				     "From Note n join Materie m on (n.id_materie=m.id_materie)\r\n" + 
				     "Where m.denumire=? and n.id_elev=?;");
			stmt.setString(1, denumire_materie);
			stmt.setString(2, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{  
				note.add(Float.parseFloat(rs.getString(1)));
			}

			for(float x:note)
				suma+=x;


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suma/note.size();

	}
	default int getNrAbsente (String id, String denumire_materie, Connection connection) {
		PreparedStatement  stmt=null;
		int nrAbsente = 0;
		try {
			stmt = connection.prepareStatement("select count(*) from Absente where id_elev = ? and motivare = false;");
			stmt.setString(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{  
				nrAbsente = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nrAbsente;
	}
	default float getMedieGenerala(String id,Connection connection) {
		ArrayList<String> materii = new ArrayList<String>();
		float suma=0;
		PreparedStatement  stmt=null;
		try {
			stmt = connection.prepareStatement("Select m.denumire\r\n" + 
					"from Materie m join Note n on (m.id_materie=n.id_materie)\r\n" + 
					"where n.id_elev=?;");
			stmt.setString(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{  
				materii.add(rs.getString(1));
			}

			for(String x:materii)
				suma+=getMedie(id,connection,x);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suma/materii.size();
	}
	 default Boolean addComment(String id_elev,String id_profesor,String comment,Connection connection) {
		 PreparedStatement stmt=null;
		 try {
		 stmt = (PreparedStatement) connection.prepareStatement("INSERT INTO comentarii Values (?,?,?);");
		 stmt.setString(1, id_elev);
		 stmt.setString(2, id_profesor);
		 stmt.setString(3, comment);
		 stmt.executeUpdate();
		 } catch (SQLException e) {
		 e.printStackTrace();
		 return false;
		 }
		 return true;
		 }
}

