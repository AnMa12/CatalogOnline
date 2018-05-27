package entities;

import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import utilities.ReadParinte;

public class Parinte implements ReadParinte{
	private ArrayList<Elev> elevi;
	private String id;
	private String nume;
	private String prenume;
	public Parinte(String id,String nume,String prenume,Connection connection) {
		setId(id);
		this.nume = nume;
		this.prenume = prenume;
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
	public String getNume() {
		return nume;
	}
	public String getPrenume() {
		return prenume;
	}
}
