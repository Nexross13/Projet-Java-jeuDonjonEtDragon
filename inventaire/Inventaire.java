package inventaire;
import item.*;
import protagonistes.Personnage;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventaire implements Serializable{
	
	private TypeArmure[] armuresStock = new TypeArmure[4];
	private int[][] sauvegardeStatsArmure = new int[4][2];
	private TypeArme arme = null;
	private int sauvegardeStatsArme;
	private ArrayList<TypePotion> potionsStock = new ArrayList<TypePotion>();
	private Personnage proprietaire;
	
	
	public Inventaire(Personnage joueur){
		proprietaire = joueur;
	}
		
	public void ajouterArmure(TypeArmure typeArmure, int position) {
		typeArmure.setStats();
		armuresStock[position] = typeArmure;
		
		sauvegardeStatsArmure[position][0] = typeArmure.getPV();  // Sauvegarde les PV
		sauvegardeStatsArmure[position][1] = typeArmure.getPA();  // Sauvegarde les PA	
	}
	
	public void ajouterArme(TypeArme typeArme) {
		typeArme.setDMG();
		arme = typeArme;
		sauvegardeStatsArme = arme.getDMG(); 	// Sauvegarde les DMG
	}
	
	public void ajouterPotion(TypePotion typePotion) {
		if (potionsStock.size() < 10) {
			potionsStock.add(typePotion);
		}
		else {
			System.out.println("Operation impossible : ajouterPotion dans inventaire");
		}
	}
	
	public TypeArmure getArmures(int i) {
		return armuresStock[i];
	}
	
	public TypeArme getArme() {
		return arme;
	}
	
	public ArrayList<TypePotion> getPotions() {
		return potionsStock;
	}
	
	public int[][] getSauvegardeArmure(){
		return sauvegardeStatsArmure;
	}
	
	public int getSauvegardeArme(){
		return sauvegardeStatsArme;
	}
	
	// Permet de définir le drop de la rareté en fonction de l'armure
	public TypeArmure armureRareteAleatoire() {
		
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
	private TypeArmure armureTypeAleatoire(TypeArmure Casque, TypeArmure Plastron, TypeArmure Jambiere, TypeArmure Botte) {
			
			int chance = (int) (Math.random() * 4);
			
			if (chance < 1) {	   // 25% Casque
				return Casque;
			}
			else if (chance < 2) { // 25% Plastron
				return Plastron;
			}
			else if (chance < 3) { // 25% Jambiere
				return Jambiere;
			}
			else {   			   // 25% Botte
				return Botte;
			}
		}
		
	public TypeArme armeAleatoire() {
			
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
	
	public TypePotion potionAleatoire() {
		
		int chance = (int) (Math.random() * 100);
		
		if (chance < 75) {		// 75% Petite potion
			return TypePotion.PETITE_POTION;		
		}
		else {					// 25% Potion Max
			return TypePotion.MAX_POTION;
		}	
	}
	
	
	public void Tresor() {
		int chanceNbTresor =  (int) (Math.random() * 100); //Chance d'obtenir un nombre de Trésor
		int nbTresor = 0;
		
		if (chanceNbTresor < 60) {      // 60% obtenir 1 Trésor
			nbTresor = 1;
		}
		else if (chanceNbTresor < 85) { // 25% obtenir 2 Trésors
			nbTresor = 2;
		}
		else { 						    // 15% obtenir 3 Trésors
			nbTresor = 3;
		}
		
		System.out.println("Vous recevez "+nbTresor+" Tresor(s)");
		
		for (int i = 0; i < nbTresor ; i++) { // Boucle en fonction du nombre de Trésor tiré au hasard
			int chanceTypeTresor = (int) (Math.random() * 100); // Chance pour détermine le type de trésor
			
			if (chanceTypeTresor < 25) {     // 25% obtenir Trésor de type potion
				TypePotion potionTresor = potionAleatoire();	// Création de la potion
				System.out.println("Tresor "+(i+1)+": "+potionTresor.getNomPotion()); // i trésor : potion => nom, 2pée, nom + dmg, et armure => nom, PV et PA
				proprietaire.sEquiperPotion(potionTresor);	// On équipe le joueur avec la potion s'il peut
				
			}
			else if (chanceTypeTresor < 65) { // 45% obtenir Trésor de type armure
				TypeArmure armureTresor = armureRareteAleatoire().randomStats(); // Création de l'armure
				System.out.println("Tresor "+(i+1)+": "+armureTresor.getNomArmure()+" (PV: "+armureTresor.getTempPV()+" PA:"+armureTresor.getTempPA()+")");
				proprietaire.sEquiperArmure(armureTresor);
			}
			else { 						   // 30% obtenir Trésor de type arme
				TypeArme armeTresor = armeAleatoire().randomDMG();	// Création de l'arme			
				System.out.println("Tresor "+(i+1)+": "+armeTresor.getNomArme()+" (DMG: " + armeTresor.getDMGTemp()+")");				
				proprietaire.sEquiperArme(armeTresor); // On équipe le joueur avec l'arme s'il peut
				}
			}
		}

}
	
	
	

