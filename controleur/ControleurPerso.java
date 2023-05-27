package controleur;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import affrontement.Bataille;
import pnj.Forgeron;
import pnj.Marchant;
import protagonistes.Monstre;
import protagonistes.MonstreBoss;
import protagonistes.MonstreDifficile;
import protagonistes.MonstreFacile;
import protagonistes.MonstreIntermediaire;
import protagonistes.Personnage;
import terrain.Cardinalite;
import terrain.Piece;
import vue.BoundaryForge;
import vue.BoundaryMarchant;
import vue.BoundaryPerso;
import vue.BoundaryTresor;

public class ControleurPerso {
	
	Personnage personnage;
	ControleurTresor controleurTresor;
	BoundaryTresor boundaryTresor;
	BoundaryPerso boundaryPerso;	

	public ControleurPerso(){
		this.controleurTresor = new ControleurTresor();
		this.boundaryTresor = new BoundaryTresor(controleurTresor);
		this.boundaryPerso = new BoundaryPerso(this);
	}
	
	public void creerPerso(String nomPersonnage) {
		Personnage personnage = new Personnage(nomPersonnage); // Création du personnage
		this.personnage = personnage;
	}
	
	public Personnage p() {
		return personnage;
	}
	
	public Boolean vivant() {
		return !personnage.getJoueurMort();
	}
	
	public void sauvegarder(String chemin) {	 // Sauvegarde le personnage dans un fichier
	    	String fichierPerso = chemin+"Partie de "+personnage.getNom();
	        try {
	            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(fichierPerso));
	            save.writeObject(personnage); //Où "this" est l'objet personnage
	            save.close();
	            System.out.println("La partie du personnage "+personnage.getNom()+" a bien ete enregistree");
	        } catch (IOException e) {
	            System.out.println("Impossible de sauvegarder votre Personnage");
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	

    public void charger(String chemin, String nomPartie, ControleurDonjon controleurDonjon) {
    	String fichierPerso = chemin+nomPartie;
        Object obj = null;
 
        try {
            ObjectInputStream load = new ObjectInputStream(new FileInputStream(fichierPerso));
            obj = load.readObject();
            load.close();
        } catch (IOException e) {
            System.out.println("Impossible de charger votre Personnage");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       Personnage personnage = (Personnage) obj;  // Récupération de l'object stocker       				  				
       this.personnage = personnage;
       chargerStats(); //On charge ses stats enregistrées
       controleurDonjon.chargerDonjon(personnage, this);
       
    }
    
    public void chargerStats() {
        for (int i = 0; i<personnage.getInventaire().getSauvegardeArme().length; i++){
            if (personnage.getInventaire().getSauvegardeArme()[i] != 0) {
            	personnage.getInventaire().getArme().setDMG(personnage.getInventaire().getSauvegardeArme()[0]);
            	personnage.getInventaire().getArme().setDurabilite(personnage.getInventaire().getSauvegardeArme()[1]);
            }
        }
    	
    	for (int i =0; i<personnage.getInventaire().getSauvegardeArmure().length; i++ ) {
    		
			if (personnage.getInventaire().getSauvegardeArmure()[i][0] != 0) {
				
				personnage.getInventaire().getArmures(i).setPV(personnage.getInventaire().getSauvegardeArmure()[i][0]);
				personnage.getInventaire().getArmures(i).setPA(personnage.getInventaire().getSauvegardeArmure()[i][1]);		
			}
		}
    }
    
	public void majStatJoueur(){
	    	
	    	double ratioPV_PVmax =  ((double) personnage.getPvActuel() / (double) personnage.getPvMax());    // Mise à jour des stats PV, PVMAX et PAactuel
	        
	        int pvBonus = 0;
	        int paBonus = 0;
	    	
	    	if (personnage.getPvActuel() == 0) {
	            personnage.mourir();
	    	}
	    	
	        if (personnage.getInventaire().getArme() != null) {
	            int forceArme = personnage.getInventaire().getArme().getDMG();
		        personnage.setForce(forceArme + personnage.getStatsInitiale()[1]); // Mise à jour des dégats d'attaque en fonction de l'épée
		    }
	        else {
	        	personnage.setForce(personnage.getStatsInitiale()[1]);
	        }
	        
	        for (int i=0; i<4; i++){
	            if (personnage.getInventaire().getArmures(i) != null){
	                pvBonus = pvBonus + personnage.getInventaire().getArmures(i).getPV();
	                paBonus = paBonus + personnage.getInventaire().getArmures(i).getPA();
	            }
	        }
	
	        personnage.setPvMax(pvBonus + personnage.getStatsInitiale()[0]);
	        personnage.setPvActuel((int) (personnage.getPvMax()  * ratioPV_PVmax));
	        personnage.setDegaReduit(paBonus + personnage.getStatsInitiale()[2]);
	}
	
	public boolean deplacer(String cardinalite, ControleurDonjon controleurDonjon, BoundaryPerso boundaryPerso, ControleurTresor controleurTresor){
		boolean possible = true;
        switch (cardinalite) {
            case "N":
                possible = controleurDonjon.changerPositionJoueur(Cardinalite.NORD, boundaryPerso);
                break;
            case "O":
                possible = controleurDonjon.changerPositionJoueur(Cardinalite.OUEST, boundaryPerso);
                break;
            case "E":
                possible = controleurDonjon.changerPositionJoueur(Cardinalite.EST, boundaryPerso);
                break;
            case "S":
                possible = controleurDonjon.changerPositionJoueur(Cardinalite.SUD, boundaryPerso);
                break;
        }
        entreePiece(personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()], controleurTresor);
        return possible;
    }

    public String entreePiece(Piece piece, ControleurTresor controleurTresor){
        piece.setEstDecouverte(true);
        switch (piece.getType()) {
            case HOSTILE_FACILE:
                Monstre monstreFacile;

                if (personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreFacile = new MonstreFacile(personnage.getDonjon().getEtage());  // On créer le mosntre
                    
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreFacile = personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre();
                    
                }
                
                Bataille batailleFacile = new Bataille(personnage, monstreFacile);
                ControleurBataille controleurBatailleF = new ControleurBataille(batailleFacile, this, controleurTresor);
                personnage.rejointBataille(batailleFacile);
                controleurBatailleF.combat();
                break;

            case HOSTILE_INTERMEDIAIRE:

                Monstre monstreIntermediaire;

                if (personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreIntermediaire = new MonstreIntermediaire(personnage.getDonjon().getEtage());
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreIntermediaire = personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre();
                }
                
                Bataille batailleIntermediaire = new Bataille(personnage, monstreIntermediaire);
                ControleurBataille controleurBatailleI = new ControleurBataille(batailleIntermediaire, this, controleurTresor);
                personnage.rejointBataille(batailleIntermediaire);
                controleurBatailleI.combat();
                break;

            case HOSTILE_DIFFICILE:

                Monstre monstreDifficile;

                if (personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreDifficile = new MonstreDifficile(personnage.getDonjon().getEtage());
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreDifficile = personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre();
                }
                
                Bataille batailleDifficile = new Bataille(personnage, monstreDifficile);
                ControleurBataille controleurBatailleD = new ControleurBataille(batailleDifficile, this, controleurTresor);
                personnage.rejointBataille(batailleDifficile);
                controleurBatailleD.combat();
                break;

            case HOSTILE_BOSS:

                Monstre monstreBoss;

                if (personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre() == null){ //Si il y a pas de monstre dans la pièce
                    monstreBoss = new MonstreBoss(personnage.getDonjon().getEtage());
                    
                } else{ // Si un monstre a déjà été découvert, on reprend le même monstre
                    monstreBoss = personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMonstre();
                }
                
                Bataille batailleBoss = new Bataille(personnage, monstreBoss);
                ControleurBataille controleurBatailleB = new ControleurBataille(batailleBoss, this, controleurTresor);
                personnage.rejointBataille(batailleBoss);
                controleurBatailleB.combat();
                break;

            case TRESOR:
            	
                boundaryTresor.tresor(this); // Récupère le trésor
                personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].setEstFinie(true); // Rend la pièce NEUTRE 
                break;
                
            case FORGE:
                Forgeron forgeron;

                if(personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getForgeron() == null){
                    forgeron = new Forgeron(personnage.getDonjon().getPositionJoueur(), personnage.getDonjon());
                } else {
                    forgeron = personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getForgeron();
                }
                
                ControleurForge controleurForge = new ControleurForge(forgeron, personnage.getDonjon(), boundaryPerso);
                BoundaryForge boundaryForge = new BoundaryForge(controleurForge, boundaryPerso);
                boundaryForge.actionEntrer(personnage.getDonjon(), forgeron);
            	break;
            	
            case MARCHANT:
                Marchant marchant;

                if(personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMarchant() == null){
                    marchant = new Marchant(personnage.getDonjon().getPositionJoueur(), personnage.getDonjon());
                    
                } else {
                    marchant = personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].getMarchant();
                }
                ControleurMarchant controleurMarchant = new ControleurMarchant(marchant, personnage, boundaryPerso);
                BoundaryMarchant boundaryMarchant = new BoundaryMarchant(controleurMarchant);
                boundaryMarchant.actionEntrer(personnage.getDonjon(), marchant);
                if (marchant.getNbProduitRestant() ==0) { // On change la salle, le marchant quitte les lieux, il n'a plus rien à cendre
                	personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].setEstFinie(true);
                }
                break;
            
            case SORTIE:
                if (personnage.getCleSortie()) {
                    System.out.println("On passe a l'etage sup");
                    
                    personnage.getDonjon().creerLabyrinthe();
                } else {
                    System.out.println("Trouve le boss d'abord");
                }
                break;

            default:
                break;
        }
        return "";
    }
    
    public String actionCombat(int numAction, BoundaryPerso boundaryPerso){
        switch (numAction) {
            case 1:
                System.out.println(personnage.attaquer(personnage.getBataille().getMonstre()));
                break;

            case 2:
            	boundaryPerso.boirePotion();
                break;

            case 3:
            	if (personnage.getDonjon().getAnciennePosition() != (-1)) { // Si le joueur a une anciennce position
            		personnage.getDonjon().enregistrerPiece(personnage.getBataille().getMonstre()); // Enregistre le monstre dans la pièce car la pièce est découverte
            		fuir(); // Il peut fuir
            	}                
                break;
        }
        return "";
    }

    public String fuir(){
        personnage.getBataille().fuir();
        personnage.setBataille(null);
        String texte = "Je fuis le combat!";
        personnage.getDonjon().personnageFuit(); // Retourne dans la salle précédente
        return texte;
    }
    
}
