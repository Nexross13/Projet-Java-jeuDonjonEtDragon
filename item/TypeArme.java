package item;

public enum TypeArme {
	
	EPEE_COMMUN(5, 25, 0, 0,"Epee Commun", 50, 10), // Epée commum peut aller de 5 à 25 de dégat, prix en vente de 50 et 10 de durabilité
	EPEE_RARE(35, 75, 0, 0,"Epee Rare", 200, 20),
	EPEE_LEGENDAIRE(125, 275, 0, 0,"Epee Legendaire", 400, 50);
	
	private int dmgMin; // Dégat minimum de l'arme
	private int dmgMax; // Dégat maximum de l'arme
	private int dmg;   // Dégat de l'arme
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
	
	public TypeArme randomDMG(int etage) { // Dégat de l'arme aléatoire entre dégat minimum et dégat maximum
		etage++; // Adaptation de l'arme à la difficulté de l'étage
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

