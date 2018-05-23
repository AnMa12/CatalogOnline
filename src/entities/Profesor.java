package entities;

import utilities.RaportElev;
import utilities.RaportProfesor;
import utilities.ReadProfesor;

public class Profesor implements ReadProfesor,RaportElev,RaportProfesor{
	private String id;
	private String nume;
	private String prenume;
    public Profesor(String id,String nume,String prenume){
    	this.nume = nume;
    	this.prenume = prenume;
    	this.id=id;
    }
    public String getId()
    {
       return id;
    }
    public String getNume() {
    	return nume;
    }
    public String getPrenume() {
    	return prenume;
    }
}
