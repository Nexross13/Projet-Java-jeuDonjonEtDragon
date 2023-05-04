package pnj;

import java.io.Serializable;

import affichage.AffichageMarchant;
import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
import protagonistes.Personnage;
import terrain.Donjon;

public class Marchant extends PersonnageNonJoueur implements Serializable{
    private TypePersonnageNonJoueur type = TypePersonnageNonJoueur.MARCHANT;
    private TypeArme[] stockArme = new TypeArme[3];
	private int[] stockStatArme = new int[3];
	private TypeArmure[] stockArmure = new TypeArmure[3];
	private int[][] stockStatsArmure = new int[4][2];
	private TypePotion[] stockPotion = new TypePotion[3];
	private String[] infoTypeDansChoix = new String[3];
	private int[] infoPlaceDansStock = new int[3];

    public Marchant(int position, Donjon donjon){
        super(position, donjon);
    }

	public TypeArmure getArmures(int i) {
		return stockArmure[i];
	}

	public TypeArmure[] getArmuresStock() {
		return stockArmure;
	}

	public TypeArme getArme(int i) {
		return stockArme[i];
	}
	
	public TypeArme[] getArmeStock() {
		return stockArme;
	}

	public TypePotion getPotions(int i) {
		return stockPotion[i];
	}

	public TypePotion[] getPotionsStock() {
		return stockPotion;
	}
	
	public int[][] getSauvegardeArmure(){
		return stockStatsArmure;
	}
	
	public int[] getSauvegardeArme(){
		return stockStatArme;
	}

	public void ajouterArmure(TypeArmure typeArmure, int position) {
		typeArmure.setStats();
		stockArmure[position] = typeArmure;
		infoPlaceDansStock[position] = stockArmure.length - 1;
		infoTypeDansChoix[position] = "Armure";
		
		stockStatsArmure[position][0] = typeArmure.getPV();  // Sauvegarde les PV
		stockStatsArmure[position][1] = typeArmure.getPA();  // Sauvegarde les PA	
	}
	
	public void ajouterArme(TypeArme typeArme, int position) {
		typeArme.setDMG();
		stockArme[position] = typeArme;
		infoPlaceDansStock[position] = stockArme.length - 1;
		infoTypeDansChoix[position] = "Arme";
		stockStatArme[position] = stockArme[position].getDMG(); 	// Sauvegarde les DMG
	}
	
	public void ajouterPotion(TypePotion typePotion, int position) {
		stockPotion[position] = typePotion;
		infoTypeDansChoix[position] = "Potion";
		infoPlaceDansStock[position] = stockPotion.length - 1;
	}

    public static TypeArmure armureRareteAleatoire() {
		
		int chance = (int) (Math.random() * 100);
		
		if (chance < 60) {		// 60% Commun
			return armureTypeAleatoire(TypeArmure.CASQUE_COMMUN,TypeArmure.PLASTRON_COMMUN,TypeArmure.JAMBIERE_COMMUN,TypeArmure.BOTTE_COMMUN);		
		}
		else if (chance < 90) { // 30% Rare
			return armureTypeAleatoire(TypeArmure.CASQUE_RARE,TypeArmure.PLASTRON_RARE,TypeArmure.JAMBIERE_RARE,TypeArmure.BOTTE_RARE);	
		}		
		else {					// 10% Legendaire
			return armureTypeAleatoire(TypeArmure.CASQUE_LEGENDAIRE,TypeArmure.PLASTRON_LEGENDAIRE,TypeArmure.JAMBIERE_LEGENDAIRE,TypeArmure.BOTTE_LEGENDAIRE);
		}	
	}
	
	// Permet de définir le taux de drop de la pièce d'armure
	private static TypeArmure armureTypeAleatoire(TypeArmure casque, TypeArmure plastron, TypeArmure jambiere, TypeArmure botte) {
			
			int chance = (int) (Math.random() * 4);
			
			if (chance < 1) {	   // 25% Casque
				return casque;
			}
			else if (chance < 2) { // 25% Plastron
				return plastron;
			}
			else if (chance < 3) { // 25% Jambiere
				return jambiere;
			}
			else {   			   // 25% Botte
				return botte;
			}
		}
		
	public static TypeArme armeAleatoire() {
			
			int chance = (int) (Math.random() * 100);
	
			if (chance < 75) { 				// 75% Epée Commun
				return TypeArme.EPEE_COMMUN;
				}
			
			else if (chance < 95) {			// 20% Epée Commun
				return TypeArme.EPEE_RARE;	
			}		
			else {							// 5% Epée Commun
				return TypeArme.EPEE_LEGENDAIRE;
			}	
		}
	
	public static TypePotion potionAleatoire() {
		
		int chance = (int) (Math.random() * 100);
		
		if (chance < 75) {		// 75% Petite potion
			return TypePotion.PETITE_POTION;		
		}
		else {					// 25% Potion Max
			return TypePotion.MAX_POTION;
		}	
	}
	
	
	public static void Produit() {
		for (int i = 0; i < 3 ; i++) { 
			int chanceTypeItem = (int) (Math.random() * 100); 
			
			if (chanceTypeItem < 25) {    
				TypePotion potion = potionAleatoire();	
				AffichageMarchant.AfficherPotion(potion, i); 
			}
			else if (chanceTypeItem < 65) { 
				TypeArmure armure = armureRareteAleatoire().randomStats(); 
				AffichageMarchant.AfficherArmure(armure, i);			
			}
			else { 						   
				TypeArme arme = armeAleatoire().randomDMG();
				AffichageMarchant.AfficherArme(arme, i);
			}
		}
	}

	public String Achat(Personnage proprietaire, int choix){
		String texte = "";
		switch (infoTypeDansChoix[choix]) {
			case "Armure":
				if (verifPO(stockArmure[infoPlaceDansStock[choix]].getPrix())) {
					proprietaire.sEquiperArmure(stockArmure[infoPlaceDansStock[choix]]);
					texte = "Vous venez d'acheter l'item";
				} else {
					texte = "Pas assez de sous";
				}

				break;
			case "Arme":
				if (verifPO(stockArme[infoPlaceDansStock[choix]].getPrix())) {
					proprietaire.sEquiperArme(stockArme[infoPlaceDansStock[choix]]);
					texte = "Vous venez d'acheter l'item";
				} else {
					texte = "Pas assez de sous";
				}

				break;
			case "Potion":
				if (verifPO(stockPotion[infoPlaceDansStock[choix]].getPrix())) {
					proprietaire.sEquiperPotion(stockPotion[infoPlaceDansStock[choix]]);
					texte = "Vous venez d'acheter l'item";
				} else {
					texte = "Pas assez de sous";
				}

				break;
		}
		return texte;
	}

    public boolean verifPO(int cout){
        return donjon.getJoueur().getNbrPO() > cout ? true : false; //Va vérifier si le joueur à assez de PO
    }

}
