package entities;

import utilities.ReadProfesor;

public class Profesor implements ReadProfesor{
	private String id;
    public Profesor(String id){
    	this.id=id;
    }
    public String getId()
    {
       return id;
    }
    
}
