package utilities;

import entities.Elev;
//elevul implementeaza interfata raport
public interface RaportElev {
       default float getMedie(Elev elev, String denumire_materie) {
    	   
       }
       default int getNrAbsente (Elev elev, String denumire_materie) {
    	   
       }
       default float getMedieGenerala(Elev elev) {
    	   
       }
}
