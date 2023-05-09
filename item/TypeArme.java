package item;

public enum TypeArme {
	
	EPEE_COMMUN(5, 25, 0, 0,"Epee Commun", 100),
	EPEE_RARE(35, 75, 0, 0,"Epee Rare", 250),
	EPEE_LEGENDAIRE(125, 275, 0, 0,"Epee Legendaire", 600);
	
	private int dmgMin;
	private int dmgMax;
	private int dmg;
	private int tempDmg;
	private String nomArme;
	private int prix;
	
	TypeArme(int dmgMin, int dmgMax , int dmg, int tempDmg, String nomArme, int prix){
		this.dmgMax = dmgMax;
		this.dmgMin = dmgMin;
		this.nomArme = nomArme;
		this.prix = prix;
	}
	
	public TypeArme randomDMG() {
		this.tempDmg = (int)  (dmgMin + Math.random() * (dmgMax - dmgMin));
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
}

