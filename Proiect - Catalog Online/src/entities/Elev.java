package entities;
import java.util.ArrayList;

import utilities.RaportElev;
import utilities.ReadElev;
public class Elev implements ReadElev,RaportElev{
	private String id;
	private String nume;
	private String prenume;
    public static ArrayList <Absenta> absente;
    public static ArrayList <Nota> note;
    public Elev(String id){
    	this.id=id;
    }
    public Elev(String id,String nume,String prenume){
    	this.id=id;
    	this.nume=nume;
    	this.prenume=prenume;
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
