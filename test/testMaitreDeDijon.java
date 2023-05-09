package test;

import java.io.File;

import affichage.AffichageLab;
import affichage.AffichagePerso;
import affichage.Clavier;
import maitre_de_donjon.MaitreDeDonjon;
import protagonistes.Personnage;


public class testMaitreDeDijon {
	
	 public static void main(String[] args) {
		 String chemin = "sauvegarde//";
		 
		 
		 while(true) {
			File repertoire = new File(chemin);
	        String liste[] = repertoire.list();
		        
			 System.out.println("+------MENU MAITRE DE DONJON------+");
	         System.out.println("|1 --> Gerer une partie           |");
	         System.out.println("|2 --> Quitter le menu            |");
			 System.out.println("+---------------------------------+");
			 System.out.print("Votre choix: ");
	         int choix_action = Clavier.entrerClavierInt();
	         	         
	         switch(choix_action) {
	         
	         case 1:
	        	 int nbPartie = 0;
	             if (liste != null) {         
	                 for (int i = 0; i < liste.length; i++) {
	                 	
	                  	if (liste[i].contains("Partie de")) {
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
	             	if (liste[i].contains("Partie de")) {
	             		System.out.println((i+1)+" --> "+liste[i]);
	             	}
	             }
	             System.out.print("Votre choix: ");
	             int choixPartie = Clavier.entrerClavierInt();
	             choixPartie--;
	             
	             if (liste[choixPartie].contains("Partie de")) {
	             	System.out.println(liste[choixPartie]);
	             	Personnage persoCharger = Personnage.charger(liste[choixPartie]);
	             	MaitreDeDonjon MD = new MaitreDeDonjon(persoCharger);
	             	
	             	while(true) {
	        				        		        
	        			 System.out.println("+-Gestion de la partie de "+persoCharger.getNom()+"-+");
	        	         System.out.println("|1 --> Modifier une piece            |");
	        	         System.out.println("|2 --> Modifier Stat Joueur          |");
	        	         System.out.println("|3 --> Sauvegarder les modifications |");
	        	         System.out.println("|4 --> Quitter la gestion            |");
	        			 System.out.println("+------------------------------------+");
	        			 System.out.print("Votre choix: ");
	        	         int choix_action_MdJ = Clavier.entrerClavierInt();
	        	         	         
	        	         switch(choix_action_MdJ) {
	        	         
	        	         case 1:
	        	        	 AffichageLab.AffichageLabyMD(persoCharger.getDonjon());
	        	        	 System.out.print("Choix position horizontale (ex: G) :");
	        	        	 String pieceChoisiHori = Clavier.entrerClavierString();
	        	        	 System.out.print("Choix position verticale (ex: 13) :");
	        	        	 int pieceChoisiVerti = Clavier.entrerClavierInt();
	        	        	 
	        	        	 System.out.println("+------Choix de pièce a modifier------+");
		        	         System.out.println("|1 --> Monstre Facile                 | ");
		        	         System.out.println("|2 --> Monstre Intermediaire          | ");
		        	         System.out.println("|3 --> Monstre Difficile              | ");
		        	         System.out.println("|4 --> Marchant                       | ");
		        	         System.out.println("|5 --> Forgeron                       | ");
		        	         System.out.println("|6 --> Neutre                         | ");
		        	         System.out.println("|7 --> Tresor                         | ");
		        	         System.out.println("|8 --> Boss                           |");
		        	         System.out.println("|9 --> Sortie                         |");
		        	         System.out.println("|10 --> Annuler                       | ");       	          
		        			 System.out.println("+-------------------------------------+");
		        			 System.out.print("Votre choix: ");
		        			 int typePieceChoisi = Clavier.entrerClavierInt();
		        			 System.out.println(MD.changerPiece(pieceChoisiHori, pieceChoisiVerti, typePieceChoisi));
	        	        	 break;
	        	        	 
	        	         case 2:
	        	        	 AffichagePerso.afficherInventairePersonnage(persoCharger);
	        	        	 System.out.println("+-Choix des Statistiques Joueur a modifier-+");
	        	        	 System.out.println("|1 --> Attaque           				    | ");
	        	        	 System.out.println("|2 --> PV (actuel/max)    				    | ");        	        	         	        		        	        
		        	         System.out.println("|3 --> Annuler                        		| ");		        	     
		        			 System.out.println("+------------------------------------------+");
		        			 System.out.print("Votre choix: ");
		        			 int choixPerso = Clavier.entrerClavierInt();
		        			 
		        			 switch(choixPerso) {
		        			 case 1:
		        				 System.out.print("Nouvelle attaque:");
		        				 int dmgPerso = Clavier.entrerClavierInt();
		        				 System.out.println(MD.changerStatsDMGJoueur(dmgPerso));
		        				 break;
		        			 case 2:
		        				 System.out.print("Nouveau PV Actuel:");
		        				 int pvActuelPerso = Clavier.entrerClavierInt();
		        				 System.out.print("Nouveau PV MAX:");
		        				 int pvMaxPerso = Clavier.entrerClavierInt();
		        				 System.out.println(MD.changerStatsPVJoueur(pvActuelPerso,pvMaxPerso));
		        				 break;
		        			default:
		        				break;
		        			 }
	        	        	 break;
	        	        	 
	        	         case 3:
	        	        	 persoCharger.sauvegarder(persoCharger.getNom());
	        	        	break;
	        	        	 
	        	         case 4:
	        	        	 return;
	        	        	 
	        	         default:
	        	        	 break;
	        	         }
	             	}
	             }
	         	break;
	         
	         case 2:
	         	return;
	         	
	         default:
	         	break;
	         }
		 }
		 
	 }
}
