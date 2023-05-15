package protagonistes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import affichage.AffichageForge;
import affichage.AffichageMarchant;
import affichage.Clavier;
import item.Tresor;
import affrontement.Bataille;
import inventaire.Inventaire;
import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
import pnj.Forgeron;
import pnj.Marchant;
import terrain.Cardinalite;
import terrain.Donjon;
import terrain.Piece;

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
    private static String cheminStockage = "sauvegarde//";
    
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

    public void setDegaReduit(int protection){
        degatReduit = protection;
    }

    public String rejointDonjon(Donjon donjon){
        this.donjon = donjon;
        return "";
    }

    public String rejointBataille(Bataille bataille){
        this.bataille = bataille;
        return "";
    }

    public boolean deplacer(String cardinalite){
        boolean possible = true;
        switch (cardinalite) {
            case "N":
                possible = donjon.changerPositionJoueur(Cardinalite.NORD);
                break;
            case "O":
                possible = donjon.changerPositionJoueur(Cardinalite.OUEST);
                break;
            case "E":
                possible = donjon.changerPositionJoueur(Cardinalite.EST);
                break;
            case "S":
                possible = donjon.changerPositionJoueur(Cardinalite.SUD);
                break;
        }
        entreePiece(donjon.getLabyrintheActuel()[donjon.getPositionJoueur()]);
        return possible;
    }

    public String entreePiece(Piece piece){
        piece.setEstDecouverte(true);
        switch (piece.getType()) {
            case HOSTILE_FACILE:
                Monstre monstreFacile;

                if (donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreFacile = new MonstreFacile(donjon.getEtage());  // On créer le mosntre
                    
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreFacile = donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre();
                    
                }
                
                Bataille batailleFacile = new Bataille(this, monstreFacile);
                rejointBataille(batailleFacile);
                this.bataille.combat();
                break;

            case HOSTILE_INTERMEDIAIRE:

                Monstre monstreIntermediaire;

                if (donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreIntermediaire = new MonstreIntermediaire(donjon.getEtage());
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreIntermediaire = donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre();
                }
                
                Bataille batailleIntermediaire = new Bataille(this, monstreIntermediaire);
                rejointBataille(batailleIntermediaire);
                this.bataille.combat();
                break;

            case HOSTILE_DIFFICILE:

                Monstre monstreDifficile;

                if (donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreDifficile = new MonstreDifficile(donjon.getEtage());
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreDifficile = donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre();
                }
                
                Bataille batailleDifficile = new Bataille(this, monstreDifficile);
                rejointBataille(batailleDifficile);
                this.bataille.combat();
                break;

            case HOSTILE_BOSS:

                Monstre monstreBoss;

                if (donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreBoss = new MonstreBoss(donjon.getEtage());
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreBoss = donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMonstre();
                }
                
                Bataille batailleBoss = new Bataille(this, monstreBoss);
                rejointBataille(batailleBoss);
                this.bataille.combat();
                break;

            case TRESOR:
                Tresor.Tresor(this); // Récupère le trésor
                getDonjon().getLabyrintheActuel()[getDonjon().getPositionJoueur()].setEstFinie(true); // Rend la pièce NEUTRE 
                break;
                
            case FORGE:
                Forgeron forgeron;

                if(donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getForgeron() == null){
                    forgeron = new Forgeron(donjon.getPositionJoueur(), donjon);
                } else {
                    forgeron = donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getForgeron();
                }
                AffichageForge.actionEntrer(donjon, forgeron);
            	break;
            	
            case MARCHANT:
                Marchant marchant;

                if(donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMarchant() == null){
                    marchant = new Marchant(donjon.getPositionJoueur(), donjon);
                    
                } else {
                    marchant = donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getMarchant();
                }
                AffichageMarchant.actionEntrer(donjon, marchant);
                if (marchant.getNbProduitRestant() ==0) { // On change la salle, le marchant quitte les lieux, il n'a plus rien à cendre
                	this.getDonjon().getLabyrintheActuel()[this.getDonjon().getPositionJoueur()].setEstFinie(true);
                }
                break;
            
            case SORTIE:
                if (cleSortie) {
                    System.out.println("On passe a l'etage sup");
                    
                    donjon.creerLabyrinthe();
                } else {
                    System.out.println("Trouve le boss d'abord");
                }
                break;

            default:
                break;
        }
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

    public String actionCombat(int numAction){
        switch (numAction) {
            case 1:
                System.out.println(this.attaquer(bataille.getMonstre()));
                break;

            case 2:
                boirePotion();
                break;

            case 3:
            	if (this.getDonjon().getAnciennePosition() != (-1)) { // Si le joueur a une anciennce position
            		getDonjon().enregistrerPiece(getBataille().getMonstre()); // Enregistre le monstre dans la pièce car la pièce est découverte
            		fuir(); // Il peut fuir
            	}                
                break;
        }
        return "";
    }

    public String fuir(){
        getBataille().fuir();
        this.bataille = null;
        String texte = "Je fuis le combat!";
        donjon.personnageFuit(); // Retourne dans la salle précédente
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
    

    
    public String sEquiperArmure(TypeArmure armure) {
    		String nomArmure = armure.getNomArmure();
			
						
			if(nomArmure.contains("Botte")) {
				int position = 3;
				if(inventaire.getArmures(position) == null) { // Si l'utilisateur de possède pas de botte
					System.out.println("Voulez-vous vous equiper de cette paire de "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Ajout effectue");
						inventaire.ajouterArmure(armure,position); // On ajoute la paire de botte dans l'équipement du joueur
					}							
					
				}
				else { // Si l'utilisateur possède déjà une paire de botte, on lui demande s'il veut la remplacer
					System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+inventaire.getArmures(position).getNomArmure()+" (PV:"+inventaire.getArmures(position).getPV()+" PA:"+inventaire.getArmures(position).getPA()+")? Oui(O) ou Non(N)"); // On demande à l'utilisateur s'il veut remplacer la nouvelle paire de botte par une autre
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Remplacement effectue");
						inventaire.ajouterArmure(armure,position); // On remplace les anciennes bottes par les nouvelles
					}
				}
				return"";	
			}
				
			if(nomArmure.contains("Jambiere")) {
				int position = 2;
				if(inventaire.getArmures(position) == null) {
					System.out.println("Voulez-vous vous equiper de cette "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Ajout effectue");
						inventaire.ajouterArmure(armure,position); 
					}		
				}
				else {
					System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+inventaire.getArmures(position).getNomArmure()+" (PV:"+inventaire.getArmures(position).getPV()+" PA:"+inventaire.getArmures(position).getPA()+")? Oui(O) ou Non(N)");
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Remplacement effectue");
						inventaire.ajouterArmure(armure,position); 		
					}
				}
				return"";		
			}
			
			if(nomArmure.contains("Plastron")) {			
				int position = 1;
				if(inventaire.getArmures(position) == null) {
					System.out.println("Voulez-vous vous equiper de ce "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Ajout effectue");
						inventaire.ajouterArmure(armure,position); 
					}	
				}
				else {
					System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+inventaire.getArmures(position).getNomArmure()+" (PV:"+inventaire.getArmures(position).getPV()+" PA:"+inventaire.getArmures(position).getPA()+")? Oui(O) ou Non(N)");
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Remplacement effectue");
						inventaire.ajouterArmure(armure,position); 		
					}
				}
				return"";		
			}
						
			if(nomArmure.contains("Casque")) {
				int position = 0;
				if(inventaire.getArmures(position) == null) {
					System.out.println("Voulez-vous vous equiper de ce "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Ajout effectue");
						inventaire.ajouterArmure(armure,position); 
					}	
				}
				else {
					System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+inventaire.getArmures(position).getNomArmure()+" (PV:"+inventaire.getArmures(position).getPV()+" PA:"+inventaire.getArmures(position).getPA()+")? Oui(O) ou Non(N)");
					String choix = Clavier.entrerClavierString();
					
					if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
						
						System.out.println("Remplacement effectue");
						inventaire.ajouterArmure(armure,position); 		
					}
				}
				return"";		
			}
				
			else {
				System.out.println("Erreur, le joueur ne peut pas s'equiper");
				return"";	
			}
		}
	

	
	public String sEquiperArme(TypeArme arme) {
		
		if (inventaire.getArme() == null) { 	// Si l'utilisateur ne possède pas d'épee
			System.out.println("Voulez-vous vous equiper de cette "+arme.getNomArme()+" ? Oui(O) ou Non(N)"); 
			String choix = Clavier.entrerClavierString();
			
			if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
				
				System.out.println("Ajout effectue");
				inventaire.ajouterArme(arme);
			}	
			
		}
		else {
			System.out.println("Remplacer "+arme.getNomArme()+" (DMG:"+arme.getDMGTemp()+") ==> "+inventaire.getArme().getNomArme()+" (DMG:"+inventaire.getArme().getDMG()+")? Oui(O) ou Non(N)"); // On demande à l'utilisateur s'il veut remplacer la nouvelle Epée par une autre
		
			String choix = Clavier.entrerClavierString();		
			if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
				
				System.out.println("Remplacement effectue");
				inventaire.ajouterArme(arme); // On remplace l'anciennce potion par la nouvelle		
				
			}
		}
        return"";
	}
	
	public String sEquiperPotion(TypePotion potion) {
		if (inventaire.getPotions().size() < 10) {  // Si le nombre de potion stocket est inferieur à 10, on stock directement la potion dans l'inventaire
			inventaire.ajouterPotion(potion);
			System.out.println("Recu: "+potion.getNomPotion());
		}
		else {					 	// Si l'inventaire de potion est plein
			System.out.println("Votre inventaire de potion est plein, voulez-vous remplacer une potion ? Oui(O) ou Non(N)"); // On demande à l'utilisateur s'il veut remplacer la nouvelle potion par une autre
			String choix = Clavier.entrerClavierString();
			
			if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
				System.out.println("Quelle potion souhaitez vous remplacer ?");
				for (int j=0; j < inventaire.getPotions().size() ; j++ ) {
					System.out.println(j+1+"--> "+inventaire.getPotions().get(j).getNomPotion());
				}
				int choix_potion = Clavier.entrerClavierInt();
				while (choix_potion <1 || choix_potion > inventaire.getPotions().size()) {  // Tant que le nombre entré n'est pas compris entre 1 et la longueur de la taille du stock de potion
					choix_potion = Clavier.entrerClavierInt();			
				}	
				System.out.println("Vous remplacer "+inventaire.getPotions().get(choix_potion-1).getNomPotion()+" par une "+potion.getNomPotion());
				inventaire.getPotions().set(choix_potion-1, potion); // On remplace l'anciennce potion par la nouvelle		
			}
		}	
        return"";
	}
	
	public String boirePotion() {
		
		if (inventaire.getPotions().size() !=0 || pvActuel != pvMax) {
			System.out.println("Quelle potion souhaitez vous boire?");
            System.out.println("0--> Annuler Boire Potion");
	        for (int j=0; j < inventaire.getPotions().size() ; j++ ) {
	            System.out.println(j+1+"--> "+inventaire.getPotions().get(j).getNomPotion());
	        }
	        int choix_potion = Clavier.entrerClavierInt();
	        if(choix_potion >=1 && choix_potion <= inventaire.getPotions().size()) {  // Tant que le nombre entré est compris entre 1 et la longueur de la taille du stock de potion
	            double soin = ((double)inventaire.getPotions().get(choix_potion-1).getPourcent_Soin()/(double)100) * (double)pvMax; // Soin en pourcentage convertit en soin fixe en fonction des PV max du Personnage
                if ((int) soin + pvActuel < pvMax) { 
                    pvActuel = pvActuel + (int)soin;
                    System.out.println("Soin: +"+(int)soin+" PV");
                }
                else { // Si les soins dépasse les PV Max, le surplus de soin ne sera pas comptabilisé
                    int tempPvActuel = pvActuel;
                    pvActuel = pvMax;
                    System.out.println("Soin: +"+(pvMax-tempPvActuel)+" PV");
                }
                
                inventaire.getPotions().remove(choix_potion-1);
                System.out.println("PV:"+pvActuel+"/"+pvMax);
	        }
            else{
                System.out.println("Annulation Boire Potion");
            }
	
			
		}
		else {
			System.out.println("Impossible d'utiliser de Potion");
		}
        return"";
        
	}

    public void majStatJoueur(){
    	
    	double ratioPV_PVmax =  ((double) pvActuel / (double)pvMax);    // Mise à jour des stats PV, PVMAX et PAactuel
        
        int pvBonus = 0;
        int paBonus = 0;
    	
    	if (pvActuel == 0) {
            mourir();
    	}
    	
        if (inventaire.getArme() != null) {
            int forceArme = inventaire.getArme().getDMG();
	        force = forceArme + statsInitiale[1]; // Mise à jour des dégats d'attaque en fonction de l'épée
	    }
        else {
        	force = statsInitiale[1];
        }
        
        for (int i=0; i<4; i++){
            if (inventaire.getArmures(i) != null){
                pvBonus = pvBonus + inventaire.getArmures(i).getPV();
                paBonus = paBonus + inventaire.getArmures(i).getPA();
            }
        }

        pvMax =  pvBonus + statsInitiale[0];
        pvActuel = (int) (pvMax  * ratioPV_PVmax);
        degatReduit = paBonus + statsInitiale[2];
    }
    
    public void sauvegarder(String s) { // Sauvegarde le personnage dans un fichier
    	String chemin = cheminStockage+"Partie de "+s;
        try {
            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(chemin));
            save.writeObject(this); //Où "this" est l'objet donjon
            save.close();
            System.out.println("La partie du personnage "+nom+" a bien ete enregistree");
        } catch (IOException e) {
            System.out.println("Impossible de sauvegarder votre Personnage");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Personnage charger(String s) {
    	String chemin = cheminStockage+s;
        Object obj = null;
 
        try {
            ObjectInputStream load = new ObjectInputStream(new FileInputStream(chemin));
            obj = load.readObject();
            load.close();
        } catch (IOException e) {
            System.out.println("Impossible de charger votre Personnage");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       Personnage personnage = (Personnage) obj;  // Récupération de l'object stocker
       personnage.chargerStats();				  //On charge ses stats enregistrées
        return personnage;
    }
    
    public void chargerStats() {
        for (int i = 0; i<inventaire.getSauvegardeArme().length; i++){
            if (inventaire.getSauvegardeArme()[i] != 0) {
                inventaire.getArme().setDMG(inventaire.getSauvegardeArme()[0]);
                inventaire.getArme().setDurabilite(inventaire.getSauvegardeArme()[1]);
            }
        }
    	
    	for (int i =0; i<inventaire.getSauvegardeArmure().length; i++ ) {
    		
			if (inventaire.getSauvegardeArmure()[i][0] != 0) {
				
				inventaire.getArmures(i).setPV(inventaire.getSauvegardeArmure()[i][0]);
				inventaire.getArmures(i).setPA(inventaire.getSauvegardeArmure()[i][1]);		
			}
		}
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
