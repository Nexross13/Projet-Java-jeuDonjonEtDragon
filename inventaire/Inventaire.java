package inventaire;
import item.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventaire implements Serializable{
	
	// Attributs
	private TypeArmure[] armuresStock = new TypeArmure[4];
	private int[][] sauvegardeStatsArmure = new int[4][2];
	private TypeArme arme = null;
	private int sauvegardeStatsArme;
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
	
	public int getSauvegardeArme(){
		return sauvegardeStatsArme;
	}
	
	// Autres méthodes
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
}
	
	
	

