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
        //System.out.println("Réparation d'une armure");
        if(verifPO(30)){
            donjon.getJoueur().achatItem(30);
            return "Armure réparer";
        } else {
            return "Pas assez de sous";
        }
    }

    public String reparerArme(TypeArme arme){
        //System.out.println("Réparation d'une arme");
        if(verifPO(30)){
            donjon.getJoueur().achatItem(30);
            return "Arme réparer";
        } else {
            return "Pas assez de sous";
        }
    }

    public String ameliorerArmure(TypeArmure armure){
        //System.out.println("Amélioration d'une armure");
        if(verifPO(30)){
            donjon.getJoueur().achatItem(30);
            return "Armure améliorée";
        } else {
            return "Pas assez de sous";
        }
    }

    public String ameliorerArme(TypeArme arme){
        //System.out.println("Amélioration d'une armure");
        if(verifPO(30)){
            donjon.getJoueur().achatItem(30);
            return "Arme améliorée";
        } else {
            return "Pas assez de sous";
        }
    }

    public boolean verifPO(int cout){
        return donjon.getJoueur().getNbrPO() >= cout ? true : false; //Va vérifier si le joueur à assez de PO
    }

}
