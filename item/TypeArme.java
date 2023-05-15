package item;

public enum TypeArme {
	
	EPEE_COMMUN(5, 25, 0, 0,"Epee Commun", 100, 100),
	EPEE_RARE(35, 75, 0, 0,"Epee Rare", 250, 200),
	EPEE_LEGENDAIRE(125, 275, 0, 0,"Epee Legendaire", 600, 300);
	
	private int dmgMin;
	private int dmgMax;
	private int dmg;
	private int tempDmg;
	private String nomArme;
	private int prix;
	private int durabiliteMax;
	private int durabilite = durabiliteMax;
	
	TypeArme(int dmgMin, int dmgMax , int dmg, int tempDmg, String nomArme, int prix, int durabiliteMax){
		this.dmgMax = dmgMax;
		this.dmgMin = dmgMin;
		this.nomArme = nomArme;
		this.prix = prix;
		this.durabiliteMax = durabiliteMax;
		this.durabilite = durabiliteMax;
	}
	
	public TypeArme randomDMG(int etage) {
		etage++;
		this.tempDmg = (int)  (dmgMin * etage + Math.random() * (dmgMax * etage - dmgMin * etage));
		return this;
	}
	
	public int getDMG() {
		return dmg;
	}
	
	public void setDMG() {
		this.dmg = tempDmg;
	}
	
	public void setDMG(int dmg) {
		this.dmg = dmg;
	}
	
	public String getNomArme() {
		return nomArme;
	}
	
	public int getDMGTemp() {
		return tempDmg;
	}

	public int getPrix() {
		return prix;
	}

	public int getDurabiliteMax() {
		return durabiliteMax;
	}

	public int getDurabilite() {
		return durabilite;
	}

	// Setteur 

	public void setDurabilite(int durabilite) {
		this.durabilite = durabilite;
	}
}

