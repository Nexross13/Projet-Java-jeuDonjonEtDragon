package item;

public enum TypePotion {
	PETITE_POTION(33,"Petite Potion", 50), // Petite potion donne 33% de vie au personnage, vaut 50 pièce d'or en vente
	MAX_POTION(100,"Potion Max", 100);
		
	private int pourcentSoin;
	private String nomPotion;
	private int prix;
	
	TypePotion(int pourcent_Soin, String nomPotion, int prix) {
        this.pourcentSoin = pourcent_Soin;	
		this.nomPotion = nomPotion;	
		this.prix = prix;
	}
	
	public int getPourcent_Soin() {
		return pourcentSoin;
	}	

	public String getNomPotion() {
		return nomPotion;
	}

	public int getPrix() {
		return prix;
	}
}
