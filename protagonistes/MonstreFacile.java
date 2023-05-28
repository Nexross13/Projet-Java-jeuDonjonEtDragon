package protagonistes;

import java.io.Serializable;

public class MonstreFacile extends Monstre implements Serializable{

    // Attribut
    private int[] poBorne = {15,25};
    private int[] pvBorne = {40,130};
    private int[] forceBorne = {10,30};
    private TypeMonstre type = TypeMonstre.FACILE;

    //Constructeur
    public MonstreFacile(int etage){
        super(0,0,0);
        etage++; // Diffilculté dynamique en fonction de l'étage
        this.pvMax = (int) (pvBorne[0] + ( Math.random() * ((pvBorne[1]+1)*etage - pvBorne[0]*etage))); // Borne[0] Minimum , Borne[1] Maximum
        this.pvActuel = pvMax;
        this.force = (int) (forceBorne[0] + ( Math.random() * ((forceBorne[1]+1)*etage - forceBorne[0]*etage)));
        this.gainPO = (int) (poBorne[0] + ( Math.random() * ((poBorne[1]+1)*etage - poBorne[0]*etage))); 
    }

    // Guetteur
    public TypeMonstre getType(){
        return type;
    }

    // Autres méthodes        
    public String attaquer(Personnage personnage){
        String texte = "Le monstre attaque "+personnage.getNom()+"\n";
        texte = texte + personnage.subirAttaque(this.force);
        return texte;
    }
}

