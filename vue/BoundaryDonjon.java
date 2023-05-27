package vue;


import controleur.ControleurDonjon;


public class BoundaryDonjon {
	
	//Attribut
	public ControleurDonjon controleur;
		
	//Constructeur
	public BoundaryDonjon(ControleurDonjon controleur){
		this.controleur = controleur;
	}
	
	public void affichageLaby() {	// affichage du labyrinthe vue par l'utilisateur en fonction des pièces découvertes
		
        String murHorizontal = "   +---------------------------------------------------------------------+"; // Définit la ligne horizontale du mur
        String murVertical = "|"; // Définit le mur vertical de chaque côté

        System.out.print("    ");
        for (int i = 1; i <= controleur.d().getLabyrintheActuel().length / 14; i++) {
            int nombreDeChiffres = String.valueOf(i).length();
            if(nombreDeChiffres == 2){
                System.out.print(" " + i + "  ");
            } else {
                System.out.print("  " + i + "  ");
            }
        }
        System.out.println();
        System.out.println(murHorizontal);
        
        char lettre = 'A'; // première lettre
        for (int i = 0; i < controleur.d().getLabyrintheActuel().length; i+=14) {
            System.out.print(" " + lettre + " "); // afficher la lettre de la ligne
            for (int j = 0; j < 14; j++) {
                int index = i + j;
                if (controleur.d().getPositionJoueur() == index) {
                    System.out.print("|####"); // Le joueur est dans cette pièce
                } else if (controleur.d().getLabyrintheActuel()[index].getEstDecouverte()) {
                    switch (controleur.d().getLabyrintheActuel()[index].getType()) {
                        case HOSTILE_FACILE:
                            System.out.print("| hf ");
                            break;
                        case HOSTILE_INTERMEDIAIRE:
                            System.out.print("| hi ");
                            break;
                        case HOSTILE_DIFFICILE:
                            System.out.print("| hd ");
                            break;
                        case HOSTILE_BOSS:
                            System.out.print("| hb ");
                            break;
                        case ENTREE:
                            System.out.print("| EN ");
                            break;
                        case SORTIE:
                            System.out.print("| SO ");
                            break;
                        case MARCHANT:
                            System.out.print("| MA ");
                            break;
                        case NEUTRE:
                            System.out.print("| NE ");
                            break;
                        case TRESOR:
                            System.out.print("| TR ");
                            break;
                        case FORGE:
                            System.out.print("| FO ");
                            break;
                    }
                } else {
                    System.out.print("|    "); // Pièce non découverte
                }
            }
            System.out.println(murVertical); // mur vertical de fin de ligne
            if (i != controleur.d().getLabyrintheActuel().length - 14) { // ne pas afficher la ligne horizontale en bas du tableau
                System.out.println(murHorizontal); // ligne horizontale
            }
            lettre++; // lettre suivante
        }
        System.out.println(murHorizontal);        
	}
	
	public void affichageLabyMD() { // Affichage du labyrinthe vue par le maitre du donjon, toutes les pièces sont révélés
		
		String murHorizontal = "   +---------------------------------------------------------------------+"; // Définit la ligne horizontale du mur
        String murVertical = "|"; // Définit le mur vertical de chaque côté

        System.out.print("    ");
        for (int i = 1; i <= controleur.d().getLabyrintheActuel().length / 14; i++) {
            int nombreDeChiffres = String.valueOf(i).length();
            if(nombreDeChiffres == 2){
                System.out.print(" " + i + "  ");
            } else {
                System.out.print("  " + i + "  ");
            }
        }
        System.out.println();
        System.out.println(murHorizontal);
        
        char lettre = 'A'; // première lettre
        for (int i = 0; i < controleur.d().getLabyrintheActuel().length; i+=14) {
            System.out.print(" " + lettre + " "); // afficher la lettre de la ligne
            for (int j = 0; j < 14; j++) {
                int index = i + j;
                if (controleur.d().getPositionJoueur() == index) {
                    System.out.print("|####"); // Le joueur est dans cette pièce
                } else {
                    switch (controleur.d().getLabyrintheActuel()[index].getType()) {
                        case HOSTILE_FACILE:
                            System.out.print("| hf ");
                            break;
                        case HOSTILE_INTERMEDIAIRE:
                            System.out.print("| hi ");
                            break;
                        case HOSTILE_DIFFICILE:
                            System.out.print("| hd ");
                            break;
                        case HOSTILE_BOSS:
                            System.out.print("| hb ");
                            break;
                        case ENTREE:
                            System.out.print("| EN ");
                            break;
                        case SORTIE:
                            System.out.print("| SO ");
                            break;
                        case MARCHANT:
                            System.out.print("| MA ");
                            break;
                        case NEUTRE:
                            System.out.print("| NE ");
                            break;
                        case TRESOR:
                            System.out.print("| TR ");
                            break;
                        case FORGE:
                            System.out.print("| FO ");
                            break;
                    }
                } 
            }
            System.out.println(murVertical); // mur vertical de fin de ligne
            if (i != controleur.d().getLabyrintheActuel().length - 14) { // ne pas afficher la ligne horizontale en bas du tableau
                System.out.println(murHorizontal); // ligne horizontale
            }
            lettre++; // lettre suivante
        }
        System.out.println(murHorizontal);        
	}
}
