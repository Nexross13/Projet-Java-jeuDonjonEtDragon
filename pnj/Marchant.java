package pnj;

import java.io.Serializable;

import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
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
    
    public void setNbProduitRestant(int nbProduitRestant) {
    	this.nbProduitRestant = nbProduitRestant;
    }
    
    public TypeArmure[] getStockArmure() {
    	return stockArmure;
    }
    
    public void setStockArmure(TypeArmure typeArmure, int position) {
    	this.stockArmure[position] = typeArmure;
    }
    
    public TypeArme[] getStockArme() {
    	return stockArme;
    }
    
    public void setStockArme(TypeArme typeArme, int position) {
    	this.stockArme[position] = typeArme;
    }
    
    public int[] getStockStatArme() {
    	return stockStatArme;
    }
    
    public void setStockStatArme(int dmg, int position) {
    	this.stockStatArme[position] = dmg;
    }
    
    public void getStockStatArme(int dmg, int position) {
    	this.stockStatArme[position] = dmg;
    }
    
    public TypePotion[] getStockPotion() {
    	return stockPotion;
    }
    
    public void setStockPotion(TypePotion typePotion, int position) {
    	this.stockPotion[position] = typePotion;
    }
    
    public String[] getInfoTypeDansChoix() {
    	return infoTypeDansChoix;
    }
    
    public void setInfoTypeDansChoix(String nom, int position) {
    	this.infoTypeDansChoix[position] = nom;
    }
    
    
    public int[][] getStockStatsArmure(){
    	return stockStatsArmure;
    }
    
    public void setStockStatsArmure(int pv, int posi1, int posi2){
    	this.stockStatsArmure[posi1][posi2] = pv;
    }
    
    public boolean getProductionProduit() {
    	return productionProduit;
    }
    
    public int getNbProduitAVendre() {
    	return nbProduitAVendre;
    }
    
    public void setProductionProduit(boolean production) {
    	this.productionProduit = production;
    }
    
	

		
	

}
