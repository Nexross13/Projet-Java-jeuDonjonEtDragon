package protagonistes;

import java.io.Serializable;

public class Monstre extends EtreVivant implements Serializable{

    // Attribut
    protected TypeMonstre type;
  
    //Constructeur
    Monstre(int pvMax, int force){
        super(pvMax,force);
    }

    // Guetteur
    public TypeMonstre getType(){
        return type;
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

