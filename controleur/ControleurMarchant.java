package controleur;

import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
import pnj.Marchant;
import protagonistes.Personnage;
import vue.BoundaryMarchant;
import vue.BoundaryPerso;

public class ControleurMarchant {

	Marchant marchant;
	Personnage proprietaire;
	ControleurTresor controleurTresor;
	BoundaryMarchant boundaryMarchant;
	BoundaryPerso boundaryPerso;
	
	public ControleurMarchant(Marchant marchant, Personnage proprietaire, BoundaryPerso boundaryPerso) {
		this.marchant = marchant;
		this.proprietaire = proprietaire;
		this.controleurTresor = new ControleurTresor();
		this.boundaryMarchant = new BoundaryMarchant(this);
		this.boundaryPerso = boundaryPerso;
	}
	
	public void ajouterArmure(TypeArmure typeArmure, int position) {
			
		marchant.setStockArmure(typeArmure,position);
		marchant.getStockArmure()[position].setStats();
		marchant.setInfoTypeDansChoix("Armure", position);
		
		marchant.setStockStatsArmure(typeArmure.getPV(), position, 0);  // Sauvegarde les PV
		marchant.setStockStatsArmure(typeArmure.getPA(), position, 1);  // Sauvegarde les PA	
	}
	
	public void ajouterArme(TypeArme typeArme, int position) {
		
		marchant.setStockArme(typeArme, position);
		marchant.getStockArme()[position].setDMG();
		marchant.setInfoTypeDansChoix("Arme", position);
		marchant.setStockStatArme(marchant.getStockArme()[position].getDMG(),position); 	// Sauvegarde les DMG
	}
	
	public void ajouterPotion(TypePotion typePotion, int position) {
		marchant.setStockPotion(typePotion, position);
		marchant.setInfoTypeDansChoix("Potion",position);
		
	}

	public void Produit() {		
		
		if (!marchant.getProductionProduit()) { // Création des produits que le marchant va pouvoir vendre
						
			for (int i = 0; i < marchant.getNbProduitAVendre(); i++) { 
				int chanceTypeItem = (int) (Math.random() * 100); 
				
				if (chanceTypeItem < 25) {    // 25% chance echange potion
					TypePotion potion = controleurTresor.potionAleatoire(50);	
					ajouterPotion(potion, i);
					
				}
				else if (chanceTypeItem < 70) {   // 45 % chance echange armure
					TypeArmure armure = controleurTresor.armureRareteAleatoire(20,47).randomStats(proprietaire.getDonjon().getEtage());
					ajouterArmure(armure, i);
							
				}
				else { 		// 30 % chance echange arme				   
					TypeArme arme = controleurTresor.armeAleatoire(20,47).randomDMG(proprietaire.getDonjon().getEtage());
					ajouterArme(arme, i);
					
				}
			}
			marchant.setProductionProduit(true);
		}
		
		for (int i = 0 ; i < marchant.getNbProduitAVendre(); i++) {
			switch (marchant.getInfoTypeDansChoix()[i]) {
			case "Armure":		
				boundaryMarchant.afficherArmure(marchant.getStockArmure()[i], marchant.getStockStatsArmure()[i][0], marchant.getStockStatsArmure()[i][1], i);
				break;
				
			case "Arme":
				boundaryMarchant.afficherArme(marchant.getStockArme()[i],marchant.getStockStatArme()[i], i);
				break;
				
			case "Potion":
				boundaryMarchant.afficherPotion(marchant.getStockPotion()[i], i);
				break;
			}
		}
	}
	

	public String Achat(Personnage proprietaire, int choix){
		String texte = "";
		switch (marchant.getInfoTypeDansChoix()[choix]) {
			case "Armure":
				if (verifPO(marchant.getStockArmure()[choix].getPrix())) {
					proprietaire.achatItem(marchant.getStockArmure()[choix].getPrix());
					
					String nomArmure = marchant.getStockArmure()[choix].getNomArmure();
					int positionArmure = -1;
					
					if (nomArmure.contains("Casque")){
						positionArmure = 0;
					}
					
					else if (nomArmure.contains("Plastron")) {
						positionArmure = 1;						
					}

					else if (nomArmure.contains("Jambiere")) {
						positionArmure = 2;						
					}

					else if (nomArmure.contains("Botte")) {
						positionArmure = 3;						
					}
					
					proprietaire.getInventaire().ajouterArmure(marchant.getStockArmure()[choix], positionArmure); // On ajoute - modifie l'armure actuel du joueur
					proprietaire.getInventaire().getArmures(positionArmure).setPV(marchant.getStockStatsArmure()[choix][0]); // On modifie les PV en fonction de ce que le joueur a choisi
					proprietaire.getInventaire().getArmures(positionArmure).setPA(marchant.getStockStatsArmure()[choix][1]); // On modifie les PA
					
					texte = "Vous venez d'acheter " + marchant.getStockArmure()[choix].getNomArmure()+" PV:"+marchant.getStockStatsArmure()[choix][0]+" PA:"+marchant.getStockStatsArmure()[choix][1] ;
					marchant.setInfoTypeDansChoix("",choix); // Le produit disparait une fois vendu
					marchant.setNbProduitRestant(marchant.getNbProduitRestant() - 1);
					
				} else {
					texte = "Pas assez de sous";
				}
				break;

			case "Arme":
				if (verifPO(marchant.getStockArme()[choix].getPrix())) {
					proprietaire.achatItem(marchant.getStockArme()[choix].getPrix());
					
					proprietaire.getInventaire().ajouterArme(marchant.getStockArme()[choix]); // On ajoute - modifie l'arme actuel du joueur
					proprietaire.getInventaire().getArme().setDMG(marchant.getStockStatArme()[choix]); // On modifie les dégats en fonction de ce que le joueur a choisi

				
					texte = "Vous venez d'acheter " + marchant.getStockArme()[choix].getNomArme() +" DMG:"+marchant.getStockStatArme()[choix];
					marchant.setInfoTypeDansChoix("",choix);
					marchant.setNbProduitRestant(marchant.getNbProduitRestant() - 1);
					
				} else {
					texte = "Pas assez de sous";
				}
				break;

			case "Potion":
				if (verifPO(marchant.getStockPotion()[choix].getPrix())) {
					proprietaire.achatItem(marchant.getStockPotion()[choix].getPrix());
					boundaryPerso.sEquiperPotion(marchant.getStockPotion()[choix]);
					texte = "Vous venez d'acheter " + marchant.getStockPotion()[choix].getNomPotion();
					marchant.setInfoTypeDansChoix("",choix);
					marchant.setNbProduitRestant(marchant.getNbProduitRestant() - 1);
					
				} else {
					texte = "Pas assez de sous";
				}
				break;

		}
		return texte;
	}

    public boolean verifPO(int cout){
		return proprietaire.getNbrPO() >= cout ? true : false; //Va vérifier si le joueur à assez de PO
    }
}
