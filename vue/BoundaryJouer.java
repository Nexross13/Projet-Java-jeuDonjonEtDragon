package vue;

import java.io.File;


public class BoundaryJouer {
	
	BoundaryJeu boundaryJeu;
	BoundaryMaitreDonjon boundaryMaitreDonjon;
	
	public BoundaryJouer(BoundaryJeu boundaryJeu, BoundaryMaitreDonjon boundaryMaitreDonjon) {
		this.boundaryJeu = boundaryJeu;
		this.boundaryMaitreDonjon = boundaryMaitreDonjon;
	}
	
	public void menu() {
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
            		boundaryJeu.nouvellePartie(repertoire,liste,chemin);
            		break;
            		
            	case 2:            		
            		boundaryJeu.chargerPartie(repertoire,liste,chemin);
            		break;

				case 3:
					boundaryJeu.supprPartie(repertoire,liste,chemin);
					break;
            		
            	case 4:
            		boundaryMaitreDonjon.menuMaitreDuDonjon(chemin);            		
            		break;
            		
            	case 5:
            		System.exit(0);
					break;
            }
		}
		
	}
}
