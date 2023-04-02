package test;

import affichage.Clavier;
import protagonistes.Personnage;
import terrain.Donjon;

public class testJeu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
            		
            		Personnage persoCharger = Personnage.charger("LilianLaFripouille");
            		testLab.main(persoCharger,persoCharger.getDonjon());
            		break;
            		
            	case 3:
            		System.exit(0);
            		
            	default:
            		break;
            }
		}
	}

}
