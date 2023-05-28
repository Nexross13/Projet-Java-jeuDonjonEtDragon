package inventaire;
import item.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventaire implements Serializable{
	
	// Attributs
	private TypeArmure[] armuresStock = new TypeArmure[4];
	private int[][] sauvegardeStatsArmure = new int[4][3];
	private TypeArme arme = null;
	private int[] sauvegardeStatsArme = new int[2];
	private ArrayList<TypePotion> potionsStock = new ArrayList<TypePotion>();
	
	// Constructeur
	public Inventaire(){
	}

	// Guetteur
	public TypeArmure getArmures(int i) {
		return armuresStock[i];
	}

	public TypeArmure[] getArmuresStock() {
		return armuresStock;
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
	
	public void setSauvegardeArmure(int posi1, int posi2, int valeur){
		sauvegardeStatsArmure[posi1][posi2] = valeur;
	}
	
	public int[] getSauvegardeArme(){
		return sauvegardeStatsArme;
	}
	
	public void setSauvegardeArme(int posi, int valeur){
		sauvegardeStatsArme[posi] = valeur;
	}
	
	// Autres méthodes
	public void ajouterArmure(TypeArmure typeArmure, int position) {
		typeArmure.setStats();
		armuresStock[position] = typeArmure;
		
		sauvegardeStatsArmure[position][0] = typeArmure.getPV();  // Sauvegarde les PV
		sauvegardeStatsArmure[position][1] = typeArmure.getPA();
		sauvegardeStatsArmure[position][2] = typeArmure.getDurabilite();  
	}
	
	public void ajouterArme(TypeArme typeArme) {
		typeArme.setDMG();
		typeArme.setDurabilite(typeArme.getDurabiliteMax());
		arme = typeArme;
		sauvegardeStatsArme[0] = arme.getDMG();
		sauvegardeStatsArme[1] = arme.getDurabilite(); 	
	}
	
	public void ajouterPotion(TypePotion typePotion) {
		if (potionsStock.size() < 10) {
			potionsStock.add(typePotion);
		}
		else {
			System.out.println("Operation impossible : ajouterPotion dans inventaire");
		}
	}

	public String DetruireArme(){
		arme = null;
		sauvegardeStatsArme[0] = 0;
		return "Votre arme est détruite";
	}
	
	public String DetruireArmure(int position){
		armuresStock[position] = null;
		sauvegardeStatsArmure[position][0] = 0; 
		sauvegardeStatsArmure[position][1] = 0;
		return "Votre armure est détruite";
	}
}
	
	
	

