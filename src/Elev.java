import java.util.ArrayList;

public class Elev implements ReadElev{
	private String id;
	private String nume;
	private String prenume;
    static ArrayList <Absenta> absente;
    static ArrayList <Nota> note;
    Elev(String id){
    	this.id=id;
    }
    Elev(String id,String nume,String prenume){
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
