package protagonistes;

import java.io.Serializable;

public class Monstre extends EtreVivant implements Serializable{

    // Attribut
    protected TypeMonstre type;
    protected int gainPO;
  
    //Constructeur
    Monstre(int pvMax, int force, int gainPO){
        super(pvMax,force);
        this.gainPO = gainPO;
    }

    // Guetteur
    public TypeMonstre getType(){
        return type;
    }

    public int getGainPO(){
        return gainPO;
    }
    
    // Autres méthodes
    public String attaquer(Personnage personnage){
        String texte = "";
        texte = personnage.subirAttaque(this.force);
        return texte;
    }

    public String subirAttaque(int degat){
        String text = "";
        if (pvActuel <= degat) {
            text = mourir();
            pvActuel = 0;
        } 
        else {
            pvActuel = pvActuel - degat;
            text = "Le monstre a subit une attaque de " + degat + " degats!";
        }
        return text;
    }

    public String mourir(){
        String texte = "Le monstre est mort!";
        return texte;
    }
}

