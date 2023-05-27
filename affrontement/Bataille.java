package affrontement;

import java.io.Serializable;

import protagonistes.Monstre;
import protagonistes.Personnage;
import protagonistes.TypeMonstre;

public class Bataille implements Serializable{
    // Attribut
    private Personnage personnage;
    private Monstre monstre;
    private int tour = 1;
    private boolean personnageContinuer = true;

    // Constructeur
    public Bataille(Personnage personnage, Monstre monstre){
        this.personnage = personnage; 
        this.monstre = monstre;
    }

    // Guetteur
    public Personnage getPersonnage(){
        return personnage;
    }

    public Monstre getMonstre(){
        return monstre;
    }
    
    public int getTour() {
    	return tour;
    }
    
    public void setTour(int tour) {
    	this.tour = tour;
    }
    
   public boolean getPersonnageContinuer() {
	   return personnageContinuer;
   }
    
    
    public void fuir(){ // Le personnage fuit le combat, il retourne à son ancienne position
        this.personnageContinuer = false;
    }
}