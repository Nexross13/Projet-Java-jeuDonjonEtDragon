package vue;


import controleur.ControleurMarchant;
import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
import pnj.Marchant;
import terrain.Donjon;

public class BoundaryMarchant {

	//Attribut
	public ControleurMarchant controleur;
		
	//Constructeur
	public BoundaryMarchant(ControleurMarchant controleur){
		this.controleur = controleur;
	}
	
	public void actionEntrer(Donjon donjon, Marchant marchant){
        while (marchant.getNbProduitRestant() !=0) { // Tant que le marchant à qqch à vendre
            System.out.println("-----------Choix-----------");
            System.out.println("0 - Quitter la salle");
            controleur.Produit();
            System.out.print("Votre choix: ");
            int choix = Clavier.entrerClavierInt();
            if (choix == 0) {
            	donjon.enregistrerPiece(marchant); // Enregistre le marchant dans la piece
                return;
            } else {
                System.out.println(controleur.Achat(donjon.getJoueur(), choix-1));
            }
        }
        System.out.println("Le marchant n'a plus rien a vendre, il part reprendre son voyage ailleur");
    }
    
    public void afficherPotion(TypePotion potion, int position){
        System.out.println((position+1)+" - "+potion.getNomPotion() + " Prix: " + potion.getPrix() + " PO"); 
    }
    
    public void afficherArme(TypeArme arme, int DMG, int position){
        System.out.println((position+1)+" - "+arme.getNomArme()+" (DMG: " +DMG+") Prix: " + arme.getPrix() + " PO");				
    }
    
    public void afficherArmure(TypeArmure armure, int PV, int PA, int position){
        System.out.println((position+1)+" - "+armure.getNomArmure()+" (PV: "+PV+" PA:"+PA+") Prix: " + armure.getPrix() + " PO");
    }
}
