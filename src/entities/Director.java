package entities;

import utilities.ReadDirector;

public class Director implements ReadDirector {
	private String id;
	private String nume;
	private String prenume;
	public Director(String id)
	{
		this.id=id;
	}
	public Director(String id,String nume,String prenume)
	{
		this.setId(id);
		this.setNume(nume);
		this.setPrenume(prenume);
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

	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

}
