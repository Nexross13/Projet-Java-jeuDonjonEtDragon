package controleur;

import item.TypeArme;
import item.TypeArmure;
import pnj.Forgeron;
import terrain.Donjon;
import vue.BoundaryPerso;

public class ControleurForge {
	
	Forgeron forgeron;
	Donjon donjon;
	BoundaryPerso boundaryPerso;

	public ControleurForge(Forgeron forgeron, Donjon donjon, BoundaryPerso boundaryPerso) {
		this.forgeron = forgeron;
		this.donjon = donjon;
		this.boundaryPerso = boundaryPerso;
	}
	
	public String reparerArmure(TypeArmure armure){
        int cout = armure.getPrix() - (armure.getDurabilite() * armure.getPrix()) / armure.getDurabiliteMax();
        cout =(int)(cout * 0.75);
        //System.out.println("Réparation d'une armure");
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            armure.setDurabilite(armure.getDurabiliteMax());
            return "Armure réparée";
        } else {
            return "Pas assez de sous";
        }
    }

    public String reparerArme(TypeArme arme){
        //System.out.println("Réparation d'une arme");
        int cout = arme.getPrix() - (arme.getDurabilite() * arme.getPrix()) / arme.getDurabiliteMax();
        cout =(int)(cout * 0.75);
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            arme.setDurabilite(arme.getDurabiliteMax());
            return "Arme réparée";
        } else {
            return "Pas assez de sous";
        }
    }

    public String ameliorerArmure(TypeArmure armure){
        int cout = armure.getPrix() * 2;
        //System.out.println("Amélioration d'une armure");
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            return "Armure améliorée";
        } else {
            return "Pas assez de sous";
        }
    }
	
	public String ameliorerArme(TypeArme arme){
        int cout = arme.getPrix() * 2;
        //System.out.println("Amélioration d'une armure");
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            TypeArme newArme =  TypeArme.EPEE_LEGENDAIRE;
            boundaryPerso.sEquiperArme(null);
            boundaryPerso.sEquiperArme(newArme);
            return "Arme améliorée";
        } else {
            return "Pas assez de sous";
        }
    }
	
	public boolean verifPO(int cout){
        return donjon.getJoueur().getNbrPO() >= cout ? true : false; //Va vérifier si le joueur à assez de PO
    }
}
