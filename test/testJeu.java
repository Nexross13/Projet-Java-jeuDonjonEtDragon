package test;

import java.io.File;

import affichage.Clavier;
import protagonistes.Personnage;
import terrain.Donjon;

public class testJeu {

	public static void main(String[] args) {
		String chemin = "sauvegarde//";
        
		while(true) {
			File repertoire = new File(chemin);
	        String liste[] = repertoire.list(); 
	        
			System.out.println("+---------MENU PRINCIPAL---------+");
            System.out.println("|1 --> Creer une nouvelle Partie |");
            System.out.println("|2 --> Charger une Partie        |");
			System.out.println("|3 --> Supprimer une Partie      |");
            System.out.println("|4 --> Devenir Maitre de donjon  |");
            System.out.println("|5 --> Quitter le Jeu            |");
			System.out.println("+--------------------------------+");
			System.out.print("Votre choix: ");
			int choix_action = Clavier.entrerClavierInt();
    
            switch(choix_action) {
            	case 1: 					
            		System.out.print("Entrer le nom du Heros: ");
            		String nomPersonnage = Clavier.entrerClavierString();
					System.out.println("Bienvenue dans le donjon " + nomPersonnage);
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						
					}
            		Personnage personnage = new Personnage(nomPersonnage);// Création du personnage
					Donjon donjon = new Donjon(personnage); 
            		System.out.println();
            		testLab.main(personnage,donjon);			// Lancement de la Partie du personnage
            		break;
            		
            	case 2:
            		
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
                        break;
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
                    
                    if (liste[choixPartie].contains("Partie de")) {
                    	System.out.println(liste[choixPartie]);
                    	Personnage persoCharger = Personnage.charger(liste[choixPartie]);
                    	testLab.main(persoCharger,persoCharger.getDonjon());
						break;
                    }
                    System.out.println("Annulation");
            		break;

				case 3:
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
                        break;
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
            		break;
            		
            	case 4:
            		testMaitreDeDijon.main(args);
            		break;
            	case 5:
            		System.exit(0);
					break;
            }
		}
	}
}
