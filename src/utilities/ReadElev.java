package utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import entities.Absenta;
import entities.Nota;

public interface ReadElev {

	default ArrayList <Nota> getNote(String id_elev,String denumire_materie,Connection connection)
	{
		ArrayList <Nota> note=new ArrayList<>();
		PreparedStatement  stmt=null;
		try {
			stmt = connection.prepareStatement("Select nota,data \r\n" + 
					"FROM Note \r\n" + 
					"WHERE id_elev=? and id_materie=(Select id_materie from Materie where ?=denumire)");
			stmt.setString(1, id_elev);
			stmt.setString(2, denumire_materie);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{  
				note.add(new Nota(rs.getString(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return note;
	}
	default ArrayList <Absenta> getAbsente(String id_elev,String denumire_materie,Connection connection)
	{
		ArrayList <Absenta> absente=new ArrayList<>();
		PreparedStatement  stmt=null;
		try {
			stmt = connection.prepareStatement("select Data,motivare,id_elev,a.id_materie\r\n" + 
					"from Absente a join Materie m on(a.id_materie=m.id_materie)\r\n" + 
					"where id_elev=? and denumire=?;");
			stmt.setString(1, id_elev);
			stmt.setString(2, denumire_materie);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{  
				absente.add(new Absenta(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return absente;
	}
	default ArrayList<String> getMaterii(String id_elev,Connection connection){
		ArrayList <String> materii=new ArrayList<>();
		PreparedStatement  stmt=null;
		try {
			stmt = connection.prepareStatement("select denumire\r\n" + 
					"from Elev e join Clasa c on(e.clasa=c.clasa)\r\n" + 
					"            join Materie m on(m.id_materie=c.id_materie)\r\n" + 
					"where e.id_elev=?;");
			stmt.setString(1, id_elev);
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
	default ArrayList<String> getProfesori(String id_elev,Connection connection){
		ArrayList <String> profi=new ArrayList<>();
		PreparedStatement stmt=null;
		try {
		stmt = connection.prepareStatement("select pr.nume , pr.prenume\r\n" + 
		"from Elev el join Clasa cls on el.clasa = cls.clasa\r\n" + 
		" join Profesor pr on cls.id_profesor = pr.id_profesor\r\n" + 
		"where id_elev = ?;");
		stmt.setString(1, id_elev);
		ResultSet rs=stmt.executeQuery();
		while(rs.next())
		{ 
			profi.add(rs.getString(1) +"-"+ rs.getString(2));
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return profi;
		}
	default String getIdProfesor(String numele,Connection connection)
	{
	String parts[] = numele.split("-");
	String nume = parts[0];
	String prenume = parts[1];

	PreparedStatement stmt=null;
	String id_prof = "0";
	try {
	stmt = connection.prepareStatement("select id_profesor\n" +
	"from Profesor\n" +
	"where nume = ? and prenume = ?;");
	stmt.setString(1, nume);
	stmt.setString(2, prenume);
	ResultSet rs=stmt.executeQuery();
	while(rs.next())
	{
	id_prof = rs.getString(1);
	}
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return id_prof;

	}
}
