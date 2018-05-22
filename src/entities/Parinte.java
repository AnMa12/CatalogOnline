package entities;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import utilities.ReadParinte;

public class Parinte implements ReadParinte{
	private ArrayList<Elev> elevi;
	private String id;
	
	public Parinte(String id,Connection connection) {
		setId(id);
		elevi = this.getElevi(id, connection);
	}
	public ArrayList<Elev> getElevi() {
		return elevi;
	}
	public void setElevi(ArrayList<Elev> elevi) {
		this.elevi = elevi;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
