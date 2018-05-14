package utilities;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import entities.Elev;

public interface RaportProfesor {
	default String getMedieGeneralaPeClasa(ArrayList<Elev> elevi,Connection connection) 
	{
		float suma=0;
		for(Elev x:elevi)
			suma += x.getMedieGenerala(x.getId(),connection);
		float medie = suma/elevi.size();
		DecimalFormat dec = new DecimalFormat("#0.00");
		return dec.format(medie);
	}
}
