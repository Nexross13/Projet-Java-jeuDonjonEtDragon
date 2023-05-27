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
    private boolean cleSortie = false;
    private boolean joueurMort = false;
   
    
    public Personnage(String nom){
        super(100, 25);
        this.nom = nom;
        this.statsInitiale[0] = 100;
        this.statsInitiale[1] = 25;
        inventaire = new Inventaire();
    }

    //guetteur

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

    //setteur
    
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

    

    public String attaquer(Monstre monstre){
        String texte = monstre.subirAttaque(force);
        TypeArme arme = getInventaire().getArme();
        if (arme != null) {
            arme.setDurabilite(arme.getDurabilite() - 2);

            if (arme.getDurabilite() == 0) {
            inventaire.DetruireArme();
            }
        }
        
        return texte;
    }

    
    public String subirAttaque(int degat){
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
        
        degat = degat - degatReduit; // On réduit les dégats reçu en fonction de notre résistance
        
        if (pvActuel <= degat) {
        	mourir(); 
        } 
        else {
            pvActuel = pvActuel - degat;  // Changement des pv actuel en fonction des dégats reçu après réduction
            text = text + nom+" prend " + degat + " de degats. Il lui reste " + pvActuel + "PV!";
        }
        return text;
    }

    public void mourir(){
    	pvActuel = 0;
        joueurMort = true;
    }
    

    public String gagnerPO(Monstre monstre){
        this.nbrPO = this.nbrPO + monstre.getGainPO();
        return "";
    }

    public String achatItem(int cout){
        this.nbrPO = this.nbrPO - cout;
        return "";
    }
    
}
