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
}
