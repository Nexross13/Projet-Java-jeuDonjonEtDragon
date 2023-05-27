package protagonistes;

import java.io.Serializable;

public class MonstreIntermediaire extends Monstre implements Serializable{

    // Attribut
    private int[] poBorne = {12,17};
    private int[] pvBorne = {130,600};
    private int[] forceBorne = {50,300};
    private TypeMonstre type = TypeMonstre.INTERMEDIAIRE;

    //Constructeur
    public MonstreIntermediaire(int etage){
        super(0,0,0);
        etage++; // Diffilculté dynamique en fonction de l'étage
        this.pvMax = (int) (pvBorne[0] + ( Math.random() * ((pvBorne[1]+1)*etage - pvBorne[0]*etage))); 
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
        String texte = "Le monstre tranche "+personnage.getNom()+"\n";
        texte = texte + personnage.subirAttaque(this.force);
        return texte;
    }
}