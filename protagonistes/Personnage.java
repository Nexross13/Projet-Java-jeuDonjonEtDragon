package protagonistes;


import affrontement.Bataille;
import inventaire.Inventaire;
import item.TypeArme;
import item.TypeArmure;
import terrain.Donjon;


public class Personnage extends EtreVivant{
    // Attributs
    private String nom;
    private int nbrPO = 50;
    private Bataille bataille;
    private Donjon donjon;
    private Inventaire inventaire;
    private int  statsInitiale [] = new int[3]; // Sert de référence aux stats initiales du joueur (pouvant être modifier par le maitre  du donjon) PV / DEGAT / PA
    private int degatReduit;
    private boolean cleSortie = false;	// clé de sortie de l'étage permettant de passer à l'étage suppérieur via la sortie, false signifiant qu'il ne la possède pas
    private boolean joueurMort = false;	// etat du joueur, false = vivant, true = mort
   
    
    public Personnage(String nom){
        super(100, 25);
        this.nom = nom;
        this.statsInitiale[0] = 100; // PV d'origine pouvant être modifier par le maitre du donjon
        this.statsInitiale[1] = 25;  // Attaque d'origine, idem
        inventaire = new Inventaire(); // Création de l'invention
    }

    

    public Inventaire getInventaire() {
		return inventaire;
	}

    public String getNom() {
        return nom;
    }

    public int getNbrPO(){
        return nbrPO;
    }
    
    public void setNbrPO(int po){
        this.nbrPO = po;
    }

    public int getProtection(){
        return degatReduit;
    }

    public Bataille getBataille() {
        return bataille;
    }
    
    public void setBataille(Bataille bataille) {
    	this.bataille = bataille;
    }

    public Donjon getDonjon() {
        return donjon;
    }
    
    public boolean getJoueurMort() {
    	return joueurMort;
    }
  
    public void setJoueurMort(boolean bool) {
    	this.joueurMort = bool;
    }
    
    public void setPvInit(int pvMax) {
    	this.statsInitiale[0] = pvMax;
    	this.pvMax = statsInitiale[0];
    }
    
    public void setDegatInit(int degat) {
    	this.statsInitiale[1] = degat;
    }
    
    public void setPAInit(int pa) {
    	this.statsInitiale[2] = pa;
    }
    
    public void setCleSortie(boolean cleSortie) {
        this.cleSortie = cleSortie;
    }
    
    public boolean getCleSortie() {
    	return cleSortie;
    }

    public void setDegaReduit(int protection){
        degatReduit = protection;
    }

    public int[] getStatsInitiale() {
    	return statsInitiale;
    }
       
    public String rejointDonjon(Donjon donjon){
        this.donjon = donjon;
        return "";
    }

    public String rejointBataille(Bataille bataille){
        this.bataille = bataille;
        return "";
    }
   
    
    public String attaquer(Monstre monstre){ // Le personnage attaque le monstre en combat
        String texte = monstre.subirAttaque(force);
        TypeArme arme = getInventaire().getArme();
        if (arme != null) { // En cas de possession d'arme, perd de la durabilité en attaquant
            arme.setDurabilite(arme.getDurabilite() - 2);

            if (arme.getDurabilite() == 0) {
            inventaire.DetruireArme();
            }
        }
        
        return texte;
    }

    
    public String subirAttaque(int degat){ // Le personnage subit des dégats en fonction de l'attaque du monstre
        String text = "";
        for (int i = 0; i < getInventaire().getArmuresStock().length; i++) {
            TypeArmure armure = getInventaire().getArmuresStock()[i];
            if (armure != null) {
                armure.setDurabilite(armure.getDurabilite() - 1);
                if (armure.getDurabilite() <= 0) {
                	text = text + inventaire.DetruireArmure(i)+'\n';
                }
            }
        }
        
        if (degat - degatReduit <=0) {
        	text = text +nom+" resiste au coup du monstre";
        	return text;
        }
        
        degat = degat - degatReduit; // Réduction les dégats reçu en fonction de notre résistance
        
        if (pvActuel <= degat) {
        	mourir(); 
        } 
        else {
            pvActuel = pvActuel - degat;  // Changement des pv actuel en fonction des dégats reçu après réduction
            text = text + nom+" prend " + degat + " de degats. Il lui reste " + pvActuel + "PV!";
        }
        return text;
    }

    public void mourir(){ // Le personnage meurt
    	pvActuel = 0;
        joueurMort = true;
    }
    

    public String gagnerPO(Monstre monstre){ // Le personnage gagne des Pièces d'Or en fonction du monstre battu
        this.nbrPO = this.nbrPO + monstre.getGainPO();
        return "";
    }

    public String achatItem(int cout){ // Soustrait l'Or du personnage lors d'achat d'équipement ou de consommable
        this.nbrPO = this.nbrPO - cout;
        return "";
    }
    
}
