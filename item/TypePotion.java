package item;

public enum TypePotion {
	PETITE_POTION(33,"Petite Potion"),
	MAX_POTION(100,"Potion Max");
		
	private int pourcentSoin;
	private String nomPotion;
	
	TypePotion(int pourcent_Soin, String nomPotion) {
        this.pourcentSoin = pourcent_Soin;	
		this.nomPotion = nomPotion;	
	}
	
	public int getPourcent_Soin() {
		return pourcentSoin;
	}	

	public String getNomPotion() {
		return nomPotion;
	}
}
