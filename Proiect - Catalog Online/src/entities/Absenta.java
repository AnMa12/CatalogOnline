package entities;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
public class Absenta {
      private String motivare;
      private Date data;
      private String id_elev;
      private String id_materie;
      public Absenta(String data,String motivare,String id_elev,String id_materie)
      {
    	  this.motivare=motivare;
    	  this.id_elev=id_elev;
    	  this.id_materie=id_materie;
    	  try {
			this.data = new SimpleDateFormat("yyyy-mm-dd").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
      }
      @Override
      public String toString()
      {  String motivare;
    	  if(this.motivare.equals("0"))
    	    motivare=" nemotivata";
    	  else
    		motivare=" motivata";
    	  return new SimpleDateFormat("dd-mm").format(data)+motivare+"\n";
      }
	public String getMotivare() {
		return motivare;
	}
	static public void AdaugaAbsenta(Connection connection, String Id, Date date, String denumireMaterie){
	  PreparedStatement  stmt=null;
	        try {
	         stmt = (PreparedStatement) connection.prepareStatement("insert into Absente (id_elev,data,id_materie,motivare) select ?,?,id_materie,false from Materie where denumire=?;");
	         stmt.setString(1, Id);
	         stmt.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(date));
	         stmt.setString(3,denumireMaterie);
	         stmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Absenta inserata deja exista");
	        }
	  
	 }
	 
	 public void motiveazaAbsenta(Connection connection)
	 {
	  PreparedStatement  stmt=null;
	        try {
	         stmt = (PreparedStatement) connection.prepareStatement("UPDATE Absente SET motivare = true WHERE id_elev = ? and Data = ? and id_materie=?;");
	         stmt.setString(1, id_elev);
	         stmt.setString(2, new SimpleDateFormat("yyyy-mm-dd").format(data));
	         stmt.setString(3,id_materie);
	            stmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
}
