package vue;

import java.io.File;


import controleur.ControleurDonjon;
import controleur.ControleurMaitreDonjon;
import controleur.ControleurPerso;


public class BoundaryMaitreDonjon {
	
	
	ControleurPerso controleurPerso;
	ControleurDonjon controleurDonjon;
	ControleurMaitreDonjon controleurMaitreDonjon;
	BoundaryDonjon boundaryDonjon;
	BoundaryPerso boundaryPerso;	
		
	
	public BoundaryMaitreDonjon(){
		this.controleurPerso = new ControleurPerso();
		this.controleurDonjon = new ControleurDonjon();	
		this.controleurMaitreDonjon = new ControleurMaitreDonjon();
		this.boundaryDonjon = new BoundaryDonjon(controleurDonjon);
		this.boundaryPerso = new BoundaryPerso(controleurPerso);
	}
	
	public void menuMaitreDuDonjon(String chemin) { // Action maitre du donjon
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
	             	controleurPerso.charger(chemin, liste[choixPartie], controleurDonjon);
	             	controleurMaitreDonjon.chargerJoueur(controleurPerso);
	             	
	             	
	             	while(true) {
	        				        		        
	        			 System.out.println("+-Gestion de la partie de "+controleurPerso.p().getNom()+"-+");
	        	         System.out.println("|1 --> Modifier une piece            |");
	        	         System.out.println("|2 --> Modifier Stat Joueur          |");	
	        	         System.out.println("|3 --> Changer position joueur	  	  |");
	        	         System.out.println("|4 --> Ouvrir Inventaire joueur      |");	
	        	         System.out.println("|5 --> Ressusciter le joueur	  	  |");	        	         
	        	         System.out.println("|6 --> Sauvegarder les modifications |");
	        	         System.out.println("|7 --> Quitter la gestion            |");
	        			 System.out.println("+------------------------------------+");
	        			 System.out.print("Votre choix: ");
	        	         int choix_action_MdJ = Clavier.entrerClavierInt();
	        	         	         
	        	         switch(choix_action_MdJ) {
	        	         
	        	         case 1:
	        	        	 boundaryDonjon.affichageLabyMD();
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
		        			 System.out.println(controleurMaitreDonjon.changerPieceouPosition(pieceChoisiHori, pieceChoisiVerti, typePieceChoisi, controleurDonjon));
	        	        	 break;
	        	        	 
	        	         case 2:
	        	        	 boundaryPerso.afficherStatsPersonnage();
	        	        	 System.out.println("+-Choix des Statistiques Joueur a modifier-+");
	        	        	 System.out.println("|1 --> Attaque								| ");
	        	        	 System.out.println("|2 --> PV (actuel/max)						| ");   
	        	        	 System.out.println("|3 --> Point d'Armure						| ");  
	        	        	 System.out.println("|4 --> Piece d'Or							| "); 
		        	         System.out.println("|5 --> Annuler								| ");		        	     
		        			 System.out.println("+------------------------------------------+");
		        			 System.out.print("Votre choix: ");
		        			 int choixPerso = Clavier.entrerClavierInt();
		        			 
		        			 switch(choixPerso) {
		        			 case 1:
		        				 System.out.print("Nouvelle attaque:");
		        				 int dmgPerso = Clavier.entrerClavierInt();
		        				 System.out.println(controleurMaitreDonjon.changerStatsDMGJoueur(dmgPerso));
		        				 controleurPerso.majStatJoueur();
		        				 break;
		        			 case 2:
		        				 System.out.print("Nouveau PV Actuel:");
		        				 int pvActuelPerso = Clavier.entrerClavierInt();
		        				 System.out.print("Nouveau PV MAX:");
		        				 int pvMaxPerso = Clavier.entrerClavierInt();
		        				 System.out.println(controleurMaitreDonjon.changerStatsPVJoueur(pvActuelPerso,pvMaxPerso));
		        				 controleurPerso.majStatJoueur();
		        				 break;
		        				
		        			 case 3:
		        				 System.out.print("Nouveau PA :");
		        				 int paPerso = Clavier.entrerClavierInt();
		        				 System.out.println(controleurMaitreDonjon.changerStatsPAJoueur(paPerso));
		        				 controleurPerso.majStatJoueur();
		        				 break;
		        				 
		        			 case 4:
		        				 System.out.print("Nouveau PO :");
		        				 int poPerso = Clavier.entrerClavierInt();
		        				 System.out.println(controleurMaitreDonjon.changerPOJoueur(poPerso));
		        				 controleurPerso.majStatJoueur();
		        				 break;
		        				 
		        			default:
		        				break;
		        			 }
	        	        	 break;
	        	        	 
	        	         case 3:
	        	        	 
	        	        	 boundaryDonjon.affichageLabyMD();
	        	        	 System.out.print("Choix position horizontale (ex: G) :");
	        	        	 String pieceChoisiHori2 = Clavier.entrerClavierString();
	        	        	 System.out.print("Choix position verticale (ex: 13) :");
	        	        	 int pieceChoisiVerti2 = Clavier.entrerClavierInt();
	        	        	 System.out.println(controleurMaitreDonjon.changerPieceouPosition(pieceChoisiHori2, pieceChoisiVerti2, -1, controleurDonjon));
	        	        	 break;
	        	        	 
	        	         
	        	         case 4:
	        	        	 boundaryPerso.afficherInventairePersonnage();
	        	        	 break;
	        	        	 
	        	         case 5:
	        	        	 System.out.println(controleurMaitreDonjon.ressusciterJoueur());
	        	        	 break;
	        	        	 
	        	         case 6:
	        	        	 controleurPerso.sauvegarder(chemin);
	        	        	break;
	        	         
	        	        	 
	        	         case 7:
	        	        	 return; // Quitte le menu gestion de partie du maitre de donjon
	        	        	 
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
