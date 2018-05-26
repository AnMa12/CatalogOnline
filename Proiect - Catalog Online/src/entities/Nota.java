package entities;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
public class Nota {
      private int nota;
      private Date data;
      public Nota(String nota,String data)
      {
    	  this.nota=Integer.parseInt(nota);
    	  try {
			this.data = new SimpleDateFormat("yyyy-mm-dd").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
      }
      @Override
      public String toString()
      {
    	  return Integer.toString(nota)+"/"+new SimpleDateFormat("dd-mm").format(data)+"\n";
      }
      public static void adaugaNota(Connection connection,String id_elev,String nota,Date date,String denumire_materie) {
    	  PreparedStatement  stmt=null;
	        try {
	         stmt = (PreparedStatement) connection.prepareStatement("insert into Note (id_elev,nota,data,id_materie) select ?,?,?,id_materie from Materie where denumire=?;");
	         stmt.setString(1, id_elev);
	         stmt.setString(2, nota);
	         stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(date));
	         stmt.setString(4,denumire_materie);
	         stmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Nota inserata deja exista");
	        }
    	  
      }
}
