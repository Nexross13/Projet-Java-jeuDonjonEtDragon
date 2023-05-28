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
        String nomArmure = armure.getNomArmure();
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            armure.setDurabilite(armure.getDurabiliteMax());
            if (nomArmure.contains("Botte")) {
            	donjon.getJoueur().getInventaire().setSauvegardeArmure(3, 2, armure.getDurabiliteMax());
            }
            else if(nomArmure.contains("Jambiere")){
            	donjon.getJoueur().getInventaire().setSauvegardeArmure(2, 2, armure.getDurabiliteMax());
            }
            else if(nomArmure.contains("Plastron")){
            	donjon.getJoueur().getInventaire().setSauvegardeArmure(1, 2, armure.getDurabiliteMax());
            }            
            else {
            	donjon.getJoueur().getInventaire().setSauvegardeArmure(0, 2, armure.getDurabiliteMax());
            }
            
            return "Armure reparee";
        } else {
            return "Pas assez de sous";
        }
    }

    public String reparerArme(TypeArme arme){
        
        int cout = arme.getPrix() - (arme.getDurabilite() * arme.getPrix()) / arme.getDurabiliteMax();
        cout =(int)(cout * 0.75);
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            arme.setDurabilite(arme.getDurabiliteMax());
            donjon.getJoueur().getInventaire().setSauvegardeArme(1,arme.getDurabiliteMax());
            return "Arme reparee";
        } else {
            return "Pas assez de sous";
        }
    }

    public String ameliorerArmure(TypeArmure armure){
        int cout = armure.getPrix() * 2;
        String nomArmure = armure.getNomArmure();
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            
            if (nomArmure.contains("Botte")) {
            	TypeArmure newArmure =  TypeArmure.BOTTE_LEGENDAIRE.randomStats(donjon.getEtage());
            	donjon.getJoueur().getInventaire().ajouterArmure(newArmure, 3);
                return "Armure amelioree, Botte Legendaire obtenue";
            }
            else if(nomArmure.contains("Jambiere")){
            	TypeArmure newArmure =  TypeArmure.JAMBIERE_LEGENDAIRE.randomStats(donjon.getEtage());
            	donjon.getJoueur().getInventaire().ajouterArmure(newArmure, 2);
                return "Armure amelioree, Jambiere Legendaire obtenue";
            }
            else if(nomArmure.contains("Plastron")){
            	TypeArmure newArmure =  TypeArmure.PLASTRON_LEGENDAIRE.randomStats(donjon.getEtage());
            	donjon.getJoueur().getInventaire().ajouterArmure(newArmure, 1);
                return "Armure amelioree, Plastron Legendaire obtenu";
            }            
            else {
            	TypeArmure newArmure =  TypeArmure.CASQUE_LEGENDAIRE.randomStats(donjon.getEtage());
            	donjon.getJoueur().getInventaire().ajouterArmure(newArmure, 0);
                return "Armure amelioree, Casque Legendaire obtenu";
            }
            
        } else {
            return "Pas assez de sous";
        }
    }
	
	public String ameliorerArme(TypeArme arme){
        int cout = arme.getPrix() * 2;
        
        if(verifPO(cout)){
            donjon.getJoueur().achatItem(cout);
            TypeArme newArme =  TypeArme.EPEE_LEGENDAIRE.randomDMG(donjon.getEtage());
            donjon.getJoueur().getInventaire().ajouterArme(newArme);
            return "Arme amelioree, Epee Legendaire obtenue";
        } else {
            return "Pas assez de sous";
        }
    }
	
	public boolean verifPO(int cout){
        return donjon.getJoueur().getNbrPO() >= cout ? true : false; //Va vérifier si le joueur à assez de PO
    }
}
