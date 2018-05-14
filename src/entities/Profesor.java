package entities;

import utilities.RaportElev;
import utilities.RaportProfesor;
import utilities.ReadProfesor;

public class Profesor implements ReadProfesor,RaportElev,RaportProfesor{
	private String id;
    public Profesor(String id){
    	this.id=id;
    }
    public String getId()
    {
       return id;
    }
    
}
