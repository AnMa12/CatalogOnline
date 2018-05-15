package utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import entities.Elev;

public interface ReadParinte {
	default ArrayList<Elev> getElevi(String id_parinte,Connection connection){
		ArrayList <Elev> elevi=new ArrayList<>();
		PreparedStatement  stmt=null;
		try {
			stmt = connection.prepareStatement("select e.id_elev,e.nume,e.prenume \r\n" + 
					"from Elev e join ParinteElev pe on (e.id_elev = pe.nr_matricol)\r\n" + 
					"join Parinte p on (p.id_parinte = pe.id_parinte)\r\n" + 
					"where p.id_parinte = ?;");
			stmt.setString(1, id_parinte);
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
}
