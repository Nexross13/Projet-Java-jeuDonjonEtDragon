package pnj;

import terrain.Donjon;

public class PersonnageNonJoueur {

    protected TypePersonnageNonJoueur type;
    protected int position;
    protected Donjon donjon;
    
    public PersonnageNonJoueur(int position, Donjon donjon){
        this.position = position;
        this.donjon = donjon;
    }

    public int getPosition() {
        return position;
    }

    public TypePersonnageNonJoueur getType() {
        return type;
    }

    public Donjon getDonjon() {
        return donjon;
    }
}
