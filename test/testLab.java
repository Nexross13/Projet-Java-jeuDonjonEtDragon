package test;

import protagonistes.Personnage;
import terrain.Donjon;
import affichage.AffichageLab;
import affichage.AffichagePerso;
import affichage.Clavier;

public class testLab {

    static Donjon donjon ;
    static Personnage personnage;

    public static void main(Personnage personnageChoix, Donjon donjonChoix) {
    	personnage = personnageChoix;
    	donjon = donjonChoix;
        personnage.rejointDonjon(donjon);
        AffichageLab.AffichageLaby(donjon);
        
        while (99999 == 99999 && !personnage.getJoueurMort()) {
            AffichagePerso.afficherStatsPersonnage(personnage);
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
            	AffichagePerso.afficherDeplacement(personnage, donjon);
            	break;
            
            case 2:
            	AffichagePerso.afficherInventairePersonnage(personnage);
            	break;
            	
            case 3:
            	personnage.boirePotion();
            	break;
            	
            case 4:
            	personnage.sauvegarder(personnage.getNom());
            	break;
            	
            case 5:
            	return;
            
            default:
            	break;
            }
            System.out.println();
        }
    }
}