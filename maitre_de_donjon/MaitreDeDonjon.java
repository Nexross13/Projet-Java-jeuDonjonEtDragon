package maitre_de_donjon;

import java.util.Iterator;

import protagonistes.Personnage;
import terrain.Cardinalite;
import terrain.Piece;
import terrain.TypePiece;

public class MaitreDeDonjon {
	
	private Personnage joueur;

	private Cardinalite[] cardinaliteImpossiblesLab = { // Permet de stocker toutes les cardinalités impossibles (a savoir les bords du labyrinthe)
	        Cardinalite.NORD_OUEST, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD, Cardinalite.NORD_EST, 
	        Cardinalite.OUEST, null, null, null, null, null, null, null, null, null, null, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST,  null, null /*30*/, null, null, null, null, null, null, null, null, null, null /*40*/, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null, null, null, null, null, null, null/*50*/, null, null, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null, null, null/*60*/, null, null, null, null, null, null, null, null,Cardinalite.EST, 
	        Cardinalite.OUEST /*70*/, null, null, null, null, null, null, null, null, null, null/*80*/, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null, null, null, null, null/*90*/, null, null, null, null, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null /*100 */, null, null, null, null, null, null, null, null, null, null/*110*/, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null, null, null, null, null, null, null/*120*/, null, null, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null, null, null/*130*/, null, null, null, null, null, null, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST/*140*/, null, null, null, null, null, null, null, null, null, null/*150*/, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null, null, null, null, null/*160 */, null, null, null, null, null, null, Cardinalite.EST, 
	        Cardinalite.OUEST, null, null/*170 */, null, null, null, null, null, null, null, null, null, null/*180 */, Cardinalite.EST,
	        Cardinalite.SUD_OUEST, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD/*190*/, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD, Cardinalite.SUD_EST};
	
	public MaitreDeDonjon(Personnage persoCharger){
         joueur = persoCharger;
    }
	
	public String changerPieceouPosition(String posiHori, int posiVerti, int typePieceChoisi) { // Change une pièce ou la position du joueur dans le labyrinthe
		
		if (posiVerti > 0 && posiVerti < 15) { // Si la position vertical est compris entre 0 et 15 non inclus
			
			String [] tabPosiHori = {"A","0","B","1","C","2","D","3","E","4","F","5","G","6","H","7","I","8","J","9","K","10","L","11","M","12","N","13"};
			for (int i=0;i<tabPosiHori.length;i++){  
				if (tabPosiHori[i].contains(posiHori)){ // Si la position vertical correspond à une lettre du tableau
					int posiHoriTemp = Integer.parseInt(tabPosiHori[i+1]); // Transforme la lettre correspondante au nombre suivant
					int positionPiece = ((posiHoriTemp * 14) + posiVerti)-1; // Position correspondant au labyrinthe
					
					// Changement de position du joueur
					if (typePieceChoisi == -1) {
						joueur.getDonjon().setPositionJoueur(positionPiece);
						return "Position joueur effectue";
					}
					
					int nbSortie = 0;
					int nbBoss = 0;
					
					for (int j=0;j<joueur.getDonjon().getLabyrintheActuel().length;j++) {
		        		if (joueur.getDonjon().getLabyrintheActuel()[i].getType() == TypePiece.SORTIE) {
		        			nbSortie++;
		        		}
		        		if (joueur.getDonjon().getLabyrintheActuel()[i].getType() == TypePiece.HOSTILE_BOSS) {
		        			nbBoss++;
		        		}
		        	}
					
		        	if (joueur.getDonjon().getLabyrintheActuel()[positionPiece].getType() == TypePiece.SORTIE && nbSortie==1) {// Si il n'y a qu'une sortie dans le labyrinthe, on ne peut pas modifier la sortie actuelle puisque c'est le chemin pour passer à l'étage suppérieur
		        		return "Impossible de supprimer la sortie, il n'en reste qu'une";		        		
		        	}
		        	
		        	if (joueur.getDonjon().getLabyrintheActuel()[positionPiece].getType() == TypePiece.HOSTILE_BOSS && nbBoss==1) {// Si il n'y a qu'un BOSS dans le labyrinthe, on ne peut pas modifier le BOSS actuelle puisque c'est le chemin pour passer à l'étage suppérieur
		        		return "Impossible de supprimer le BOSS, il n'en reste qu'un";		        		
		        	}
		        	
					
					switch (typePieceChoisi) {
			        
			        case 9:			        
			            Piece sortie = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.SORTIE);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = sortie;
			            break;
			        case 4:
			            Piece marchant = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.MARCHANT);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = marchant;
			            break;
			        case 5:
			            Piece forge = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.FORGE);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = forge;
			            break;
			        case 8:
			            Piece hostileBoss = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.HOSTILE_BOSS);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = hostileBoss;
			            break;
			        case 7:
			            Piece tresor = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.TRESOR);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = tresor;
			            break;
			        case 6:
			            Piece neutre = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.NEUTRE);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = neutre;
			            break;
			        case 2:
			            Piece hostileIntermediaire = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.HOSTILE_INTERMEDIAIRE);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = hostileIntermediaire;
			            break;
			        case 3:
			            Piece hostileDifficile = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.HOSTILE_DIFFICILE);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = hostileDifficile;
			            break;
			        case 1:
			            Piece hostileFacile = new Piece(positionPiece, cardinaliteImpossiblesLab[positionPiece], TypePiece.HOSTILE_FACILE);
			            joueur.getDonjon().getLabyrintheActuel()[positionPiece] = hostileFacile;
			            break;
			            
			        default:
			        	break;
					}
					return "Changement de piece effectue";
				}
			}		
		}
		return "Aucun changement de piece effectue";
	}
	
	public String changerStatsDMGJoueur(int dmgPerso) {
		if (dmgPerso >=0) {
			joueur.setDegatInit(dmgPerso);
			return "Changement d'attaque du personnage effectue";
		}
		return "Aucun changement effectue";
		
	}
	
	public String changerStatsPVJoueur(int pvActuelPerso, int pvMaxPerso) {
		if (pvActuelPerso >=0 && pvMaxPerso >=0 && pvActuelPerso <= pvMaxPerso) {
			joueur.setPvActuel(pvActuelPerso);
			joueur.setPvInit(pvMaxPerso);
			return "Changement des pv du personnage effectue";
		}
		
		return "Aucun changement effectue";
	}
	
	public String changerStatsPAJoueur(int pointArmure) {
		if (pointArmure >=0) {
			joueur.setPAInit(pointArmure);
			return "Changement des points d'armure du personnage effectue";
		}
		
		return "Aucun changement effectue";
	}
	
	public String changerPOJoueur(int piecedOr) {
		
		joueur.setNbrPO(piecedOr);
		return "Changement piece d'Or effectue";
	}
		
	
	public String ressusciterJoueur() {
		if (joueur.getJoueurMort()) {
			joueur.setJoueurMort(false);
			joueur.setPvActuel(joueur.getPvMax());
			return "Le joueur a ete ressuscite !";
		}
		else {
			return "Le joueur est deja vivant.";
		}
		
	}
}
