package vue;

import java.io.File;

import controleur.ControleurDonjon;
import controleur.ControleurPerso;
import controleur.ControleurTresor;


public class BoundaryJeu {

	ControleurPerso controleurPerso;
	ControleurDonjon controleurDonjon;
	BoundaryDonjon boundaryDonjon;
	BoundaryPerso boundaryPerso;
	ControleurTresor controleurTresor;
	
	//Constructeur
	public BoundaryJeu(){
		this.controleurPerso = new ControleurPerso();
		this.controleurDonjon = new ControleurDonjon();	
		this.boundaryDonjon = new BoundaryDonjon(controleurDonjon);
		this.boundaryPerso = new BoundaryPerso(controleurPerso);
		this.controleurTresor = new ControleurTresor();		
	}
	
	public void nouvellePartie(File repertoire,String[] liste, String chemin) { // Création d'une partie, création du personnage
		System.out.print("Entrer le nom du Heros: ");
		String nomPersonnage = Clavier.entrerClavierString();
		System.out.println("Bienvenue dans le donjon " + nomPersonnage);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			
		}
		controleurPerso.creerPerso(nomPersonnage);		
		controleurDonjon.creerDonjon(controleurPerso.p(),controleurPerso);
		System.out.println();
		menuPartie(chemin);		// Lancement de la Partie du personnage
	}
	
	public void chargerPartie(File repertoire,String[] liste, String chemin) { // Chargement d'une partie sauvegardé
		int nbPartie = 0;
        if (liste != null) {         // Savoir le nombre de partie
            for (int i = 0; i < liste.length; i++) {
            	
             	if (liste[i].contains("Partie de")) {
               		nbPartie++;
            	}
            }
        } 
     
        if (nbPartie == 0){
            System.out.println("Pas de Partie a charger");
            return;
        }
        
        System.out.println("Choisir une partie a charger:");
        
		int positionAnnuler = 0;
        for (int i = 0; i < liste.length; i++) {
        	if (liste[i].contains("Partie de")) {
        		System.out.println((i+1)+" --> "+liste[i]);
				positionAnnuler = i+2;
        	}
        }
		System.out.println(positionAnnuler+" --> annuler");
        System.out.print("Votre choix: ");
        int choixPartie = Clavier.entrerClavierInt();
        choixPartie--;
        
        if (choixPartie < liste.length && choixPartie >=0) {
        	if (liste[choixPartie].contains("Partie de")) {
            	System.out.println(liste[choixPartie]);
            	controleurPerso.charger(chemin, liste[choixPartie], controleurDonjon);
            	menuPartie(chemin);
            	return;
        	}
        }                      
        System.out.println("Annulation");
	}
	
	public void supprPartie(File repertoire,String[] liste, String chemin) { // Suppréssion d'une partie sauvegardé
		int nbPartie2 = 0;
        if (liste != null) {         // Savoir le nombre de partie
            for (int i = 0; i < liste.length; i++) {
            	
             	if (liste[i].contains("Partie de")) {
               		nbPartie2++;
            	}
            }
        } 
     
        if (nbPartie2 == 0){
            System.out.println("Pas de partie a supprimer");
            return;
        }

		System.out.println("Choisir une partie a supprimer:");
        
		int positionAnnuler2 = 0;
		int [] tabChoixPossible =  new int[nbPartie2];
		int j=0;
        for (int i = 0; i < liste.length; i++) {
        	if (liste[i].contains("Partie de")) {
        		System.out.println((i+1)+" --> "+liste[i]);
				tabChoixPossible[j] = i; // Stocke les valeurs possible
				j++;
				positionAnnuler2 = i+2;
        	}
        }
		System.out.println(positionAnnuler2+" --> annuler");
        System.out.print("Votre choix: ");
        int choixPartie2 = Clavier.entrerClavierInt();
        choixPartie2--;

        
		for (int i = 0; i < tabChoixPossible.length ; i++){ 
			
			if (tabChoixPossible[i] == choixPartie2){ // Si le nombre entré correspond à une valeur d'une partie, elle sera supprimé, cela sert de sécurité à pas supprimer d'autre fichier dans le répertoire
				System.out.println(liste[choixPartie2]+".txt");
				File fileToDelete = new File(liste[choixPartie2]+".txt");
				if (fileToDelete.delete()) {
					System.out.println("Fichier supprime avec succes");	
					break;
        		}
			}
		}				
		
        System.out.println("Annulation");
	}
	
	public void menuPartie(String chemin) {  // Action Principale du Personnage 
		boundaryDonjon.affichageLaby();		// Affiche du plan du labyrinthe
        
        while (99999 == 99999 && controleurPerso.vivant()) {
            boundaryPerso.afficherStatsPersonnage();
            System.out.println("+----------Choisir Action------------+");
            System.out.println("|1 --> Se Deplacer                   |");
            System.out.println("|2 --> Consulter Inventaire          |");
            System.out.println("|3 --> Boire Potion                  |");
            System.out.println("|4 --> Sauvegarder                   |");
            System.out.println("|5 --> Quitter la partie             |");
            System.out.println("+------------------------------------+");
            System.out.print("Votre choix: ");
            int choix_action = Clavier.entrerClavierInt();
            
            switch(choix_action) {
            case 1:
            	boundaryPerso.afficherDeplacement(boundaryDonjon, controleurDonjon, controleurTresor);
            	break;
            
            case 2:
            	boundaryPerso.afficherInventairePersonnage();
            	break;
            	
            case 3:
            	boundaryPerso.boirePotion();
            	break;
            	
            case 4:
            	controleurPerso.sauvegarder(chemin);
            	break;
            	
            case 5:
            	return;
            
            default:
            	break;
            }
            System.out.println();
        }
        if (!controleurPerso.vivant()) {
        	System.out.println("-----Game Over-----");
        	System.out.println(controleurPerso.p().getNom()+" est mort");
            System.out.println("Recap Partie:");
            boundaryPerso.afficherInventairePersonnage();
            controleurPerso.sauvegarder(chemin);
        }
	}
}
