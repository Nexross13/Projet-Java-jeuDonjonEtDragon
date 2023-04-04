package pnj;

import java.io.Serializable;

import item.TypeArme;
import item.TypeArmure;
import terrain.Donjon;

public class Forgeron extends PersonnageNonJoueur implements Serializable{
    private TypePersonnageNonJoueur type = TypePersonnageNonJoueur.FORGERON;

    public Forgeron(int position, Donjon donjon){
        super(position, donjon);
    }

    public String reparerArmure(TypeArmure armure){
        System.out.println("Réparation d'une armure");
        return "";
    }

    public String reparerArme(TypeArme arme){
        System.out.println("Réparation d'une arme");
        return "";
    }

    public String ameliorerArmure(TypeArmure armure){
        System.out.println("Amélioration d'une armure");
        return "";
    }

    public String ameliorerArme(TypeArme arme){
        System.out.println("Amélioration d'une armure");
        return "";
    }

}
