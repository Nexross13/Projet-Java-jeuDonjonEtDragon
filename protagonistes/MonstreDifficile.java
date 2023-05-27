package protagonistes;

import java.io.Serializable;

public class MonstreDifficile extends Monstre implements Serializable{

    // Attribut
    private int[] poBorne = {20,50};
    private int[] pvBorne = {600,1000};
    private int[] forceBorne = {250,500};
    private TypeMonstre type = TypeMonstre.DIFFICILE;

    //Constructeur
    public MonstreDifficile(int etage){
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
    
    // Autres Méthodes
    public String attaquer(Personnage personnage){
        String texte = "Le monstre decharge sa haine contre "+personnage.getNom()+"\n";
        texte = texte + personnage.subirAttaque(this.force);
        return texte;
    }
}

