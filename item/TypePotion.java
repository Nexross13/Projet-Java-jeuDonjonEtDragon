package item;

public enum TypePotion {
	PETITE_POTION(33,"Petite Potion"),
	MAX_POTION(100,"Potion Max");
		
	private int pourcentSoin;
	private String nomPotion;
	
	TypePotion(int Pourcent_Soin, String nomPotion) {
        this.pourcentSoin = Pourcent_Soin;	
		this.nomPotion = nomPotion;	
	}
	
	public int getPourcent_Soin() {
		return pourcentSoin;
	}	

	public String getNomPotion() {
		return nomPotion;
	}
}
