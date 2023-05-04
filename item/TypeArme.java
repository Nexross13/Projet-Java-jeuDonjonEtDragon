package item;

public enum TypeArme {
	
	EPEE_COMMUN(30, 50, 0, 0,"Epee Commun", 100),
	EPEE_RARE(60, 100, 0, 0,"Epee Rare", 200),
	EPEE_LEGENDAIRE(150, 300, 0, 0,"Epee Legendaire", 300);
	
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

