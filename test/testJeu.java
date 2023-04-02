package test;

import java.io.File;

import affichage.Clavier;
import protagonistes.Personnage;
import terrain.Donjon;

public class testJeu {

	public static void main(String[] args) {
		String chemin = "sauvegarde//";
		File repertoire = new File(chemin);
        String liste[] = repertoire.list();  
        
		while(true) {
			System.out.println("\n---------MENU PRINCIPAL---------");
            System.out.println("\n1 --> Creer une nouvelle Partie");
            System.out.println("2 --> Charger une Partie");
            System.out.println("3 --> Quitter le Jeu");
            
            int choix_action = Clavier.entrerClavierInt();
            
            switch(choix_action) {
            	case 1:
            		Donjon donjon = new Donjon(); 					// Création du donjon
            		System.out.print("Entrer le nom du Heros:");
            		String nomPersonnage = Clavier.entrerClavierString();
            		Personnage personnage = new Personnage(nomPersonnage); // Création du personnage
            		System.out.println();
            		testLab.main(personnage,donjon);			// Lancement de la Partie du personnage
            		break;
            		
            	case 2:
            		
            		int nbPartie = 0;
                    if (liste != null) {         
                        for (int i = 0; i < liste.length; i++) {
                        	
	                     	if (liste[i].contains("Partie De")) {
	                       		nbPartie++;
                        	}
                        }
                    } 
                 
                    if (liste == null || nbPartie == 0){
                        System.out.println("Pas de Partie a charger");
                        break;
                    }
                    
                    System.out.println("Choisir une partie a charger:");
                    
                    for (int i = 0; i < liste.length; i++) {
                    	if (liste[i].contains("Partie De")) {
                    		System.out.println((i+1)+" --> "+liste[i]);
                    	}
                    }
                    
                    int choixPartie = Clavier.entrerClavierInt();
                    choixPartie--;
                    
                    if (liste[choixPartie].contains("Partie De")) {
                    	System.out.println(liste[choixPartie]);
                    	Personnage persoCharger = Personnage.charger(liste[choixPartie]);
                    	testLab.main(persoCharger,persoCharger.getDonjon());
                    }
                    
            		break;
            		
            	case 3:
            		System.exit(0);
            		
            	default:
            		break;
            }
		}
	}
}
