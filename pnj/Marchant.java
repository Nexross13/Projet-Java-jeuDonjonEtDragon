package pnj;

import java.io.Serializable;

import affichage.AffichageMarchant;
import item.Tresor;
import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
import protagonistes.Personnage;
import terrain.Donjon;

public class Marchant extends PersonnageNonJoueur implements Serializable{
	private int nbProduitAVendre;
	private TypePersonnageNonJoueur type = TypePersonnageNonJoueur.MARCHANT;
    private TypeArme[] stockArme;
	private int[] stockStatArme;
	private TypeArmure[] stockArmure;
	private int[][] stockStatsArmure;
	private TypePotion[] stockPotion;
	private String[] infoTypeDansChoix;
	private boolean productionProduit = false; // Lorsque le marchant choisi ses produit à vendre, il ne peut plus choisir d'autre produit
	private int nbProduitRestant;
	

    public Marchant(int position, Donjon donjon){
        super(position, donjon);
        int maxProduit = 5;
		int minProduit = 3;
		nbProduitAVendre = minProduit + (int)(Math.random() * ((maxProduit - minProduit))); // Nombre de produit vendu par le marchant est aléatoire
		
		stockArme = new TypeArme[nbProduitAVendre];
		stockStatArme = new int[nbProduitAVendre];
		stockArmure = new TypeArmure[nbProduitAVendre];
		stockStatsArmure = new int[nbProduitAVendre][2];
		stockPotion = new TypePotion[nbProduitAVendre];
		infoTypeDansChoix = new String[nbProduitAVendre];
		nbProduitRestant = nbProduitAVendre;
    }


    public int getNbProduitRestant() {
    	return nbProduitRestant;
    }
    
	public void ajouterArmure(TypeArmure typeArmure, int position) {
		
		stockArmure[position] = typeArmure;
		stockArmure[position].setStats();
		infoTypeDansChoix[position] = "Armure";
		
		stockStatsArmure[position][0] = typeArmure.getPV();  // Sauvegarde les PV
		stockStatsArmure[position][1] = typeArmure.getPA();  // Sauvegarde les PA	
	}
	
	public void ajouterArme(TypeArme typeArme, int position) {
		
		stockArme[position] = typeArme;
		stockArme[position].setDMG();
		infoTypeDansChoix[position] = "Arme";
		stockStatArme[position] = stockArme[position].getDMG(); 	// Sauvegarde les DMG
	}
	
	public void ajouterPotion(TypePotion typePotion, int position) {
		stockPotion[position] = typePotion;
		infoTypeDansChoix[position] = "Potion";
		
	}

		
	
	public void Produit() {		
		
		if (!productionProduit) { // Création des produits que le marchant va pouvoir vendre
						
			for (int i = 0; i < this.nbProduitAVendre; i++) { 
				int chanceTypeItem = (int) (Math.random() * 100); 
				
				if (chanceTypeItem < 25) {    // 25% chance echange potion
					TypePotion potion = Tresor.potionAleatoire(50);	
					ajouterPotion(potion, i);
					
				}
				else if (chanceTypeItem < 70) {   // 45 % chance echange armure
					TypeArmure armure = Tresor.armureRareteAleatoire(20,47).randomStats();
					ajouterArmure(armure, i);
							
				}
				else { 		// 30 % chance echange arme				   
					TypeArme arme = Tresor.armeAleatoire(20,47).randomDMG();
					ajouterArme(arme, i);
					
				}
			}
			productionProduit = true;
		}
		
		for (int i = 0 ; i < this.nbProduitAVendre; i++) {
			switch (infoTypeDansChoix[i]) {
			case "Armure":		
				AffichageMarchant.AfficherArmure(stockArmure[i], stockStatsArmure[i][0], stockStatsArmure[i][1], i);
				break;
				
			case "Arme":
				AffichageMarchant.AfficherArme(stockArme[i],stockStatArme[i], i);
				break;
				
			case "Potion":
				AffichageMarchant.AfficherPotion(stockPotion[i], i);
				break;
			}
		}
	}
	

	public String Achat(Personnage proprietaire, int choix){
		String texte = "";
		switch (infoTypeDansChoix[choix]) {
			case "Armure":
				if (verifPO(stockArmure[choix].getPrix())) {
					proprietaire.achatItem(stockArmure[choix].getPrix());
					
					String nomArmure = stockArmure[choix].getNomArmure();
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
					
					proprietaire.getInventaire().ajouterArmure(stockArmure[choix], positionArmure); // On ajoute - modifie l'armure actuel du joueur
					proprietaire.getInventaire().getArmures(positionArmure).setPV(stockStatsArmure[choix][0]); // On modifie les PV en fonction de ce que le joueur a choisi
					proprietaire.getInventaire().getArmures(positionArmure).setPA(stockStatsArmure[choix][1]); // On modifie les PA
					
					texte = "Vous venez d'acheter " + stockArmure[choix].getNomArmure()+" PV:"+stockStatsArmure[choix][0]+" PA:"+stockStatsArmure[choix][1] ;
					infoTypeDansChoix[choix] = ""; // Le produit disparait une fois vendu
					nbProduitRestant--;
					
				} else {
					texte = "Pas assez de sous";
				}
				break;

			case "Arme":
				if (verifPO(stockArme[choix].getPrix())) {
					proprietaire.achatItem(stockArme[choix].getPrix());
					
					proprietaire.getInventaire().ajouterArme(stockArme[choix]); // On ajoute - modifie l'arme actuel du joueur
					proprietaire.getInventaire().getArme().setDMG(stockStatArme[choix]); // On modifie les dégats en fonction de ce que le joueur a choisi

				
					texte = "Vous venez d'acheter " + stockArme[choix].getNomArme() +" DMG:"+stockStatArme[choix];
					infoTypeDansChoix[choix] = "";
					nbProduitRestant--;
					
				} else {
					texte = "Pas assez de sous";
				}
				break;

			case "Potion":
				if (verifPO(stockPotion[choix].getPrix())) {
					proprietaire.achatItem(stockPotion[choix].getPrix());
					proprietaire.sEquiperPotion(stockPotion[choix]);
					texte = "Vous venez d'acheter " + stockPotion[choix].getNomPotion();
					infoTypeDansChoix[choix] = "";
					nbProduitRestant--;
					
				} else {
					texte = "Pas assez de sous";
				}
				break;

		}
		return texte;
	}

    public boolean verifPO(int cout){
		return donjon.getJoueur().getNbrPO() >= cout ? true : false; //Va vérifier si le joueur à assez de PO
    }

}
