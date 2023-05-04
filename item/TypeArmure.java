package item;

public enum TypeArmure {
	
	// Enum
	CASQUE_COMMUN(25,10,1,1,"Casque Commun", 100),
	CASQUE_RARE(75,25,1,1,"Casque Rare", 200),
	CASQUE_LEGENDAIRE(150,40,1,1,"Casque Legendaire", 300),
	
	PLASTRON_COMMUN(50,20,1,1,"Plastron Commun", 100),
	PLASTRON_RARE(200,45,1,1,"Plastron Rare", 200),
	PLASTRON_LEGENDAIRE(300,70,1,1,"Plastron Legandaire", 300),
	
	JAMBIERE_COMMUN(30,15,1,1,"Jambiere Commun", 100),
	JAMBIERE_RARE(125,30,1,1,"Jambiere Rare", 200),
	JAMBIERE_LEGENDAIRE(200,50,1,1,"Jambiere Legendaire", 300),
	
	BOTTE_COMMUN(25,10,1,1,"Botte Commun", 100),
	BOTTE_RARE(75,25,1,1,"Botte Rare", 200),
	BOTTE_LEGENDAIRE(150,40,1,1,"Botte Legendaire", 300);
	
	// Attributs
	private int rangePV;
	private int rangePA;
	private int tempPV;
	private int tempPA;
	private String nomArmure;
	private int PV;
	private int PA;
	private int prix;
	
	// Constructeur
	TypeArmure(int rangePV, int rangePA, int tempPV, int tempPA, String nomArmure, int prix){
		this.rangePV = rangePV;
		this.rangePA = rangePA;
		this.nomArmure = nomArmure;
		this.prix = prix;
	}
	
	// Guetteur
	public int getPV() {
		return PV;
	}
	
	public int getPA() {
		return PA;
	}
	
	public int getTempPV(){
		return tempPV;
	}
	
	public int getTempPA(){
		return tempPA;
	}

	public String getNomArmure() {
		return nomArmure;
	}

	public int getPrix() {
		return prix;
	}
	
	// Setteur
	public void setStats(){
		PV = tempPV;
		PA = tempPA;
	}
	
	public void setPV(int PV) {
		this.PV = PV;
	}
	public void setPA(int PA) {
		this.PA = PA;
	}

	// Autres méthodes
	public TypeArmure randomStats() {
		this.tempPV = (int) (rangePV*0.8 + Math.random() *  (rangePV*1.2 - rangePV*0.8));
		this.tempPA = (int) (rangePA*0.8 + Math.random() *  (rangePA*1.2 - rangePA*0.8));
		return this;
	}
}