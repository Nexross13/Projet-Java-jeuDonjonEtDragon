package protagonistes;

import java.io.Serializable;

public class MonstreBoss extends Monstre implements Serializable{

    // Attribut
    private TypeMonstre type = TypeMonstre.BOSS;

    //Constructeur
    MonstreBoss(int etage){
        super(1500,500);
        etage++;
        this.pvMax = pvMax*etage;
        this.force = force*etage;
    }
    
    // Guetteur
    public TypeMonstre getType(){
        return type;
    }

    // Autres méthodes
    public String attaquer(Personnage personnage){
        String texte = "Le Boss souleve "+personnage.getNom()+"\n";
        texte = texte + personnage.subirAttaque(this.force);
        return texte;
    }
    // Lors du combat avec un personnage, le boss peut augmenter ses stats de force lorsqu'il a peu de vie (à voir)
}

