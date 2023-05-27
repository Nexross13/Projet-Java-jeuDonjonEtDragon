package controleur;


import protagonistes.Personnage;
import terrain.Cardinalite;
import terrain.Donjon;
import terrain.Piece;
import vue.BoundaryPerso;

public class ControleurDonjon {
	
	Donjon donjon;
	

	public ControleurDonjon() {
		
	}
	
	public void creerDonjon(Personnage personnage, ControleurPerso controleurPerso) {
		Donjon donjon = new Donjon(personnage); 	
		this.donjon = donjon;
		personnage.rejointDonjon(donjon);     												// Le joueur entre dans le donjon
		controleurPerso.entreePiece(donjon.getLabyrintheActuel()[donjon.getPositionJoueur()],null);    // Le joueur entre dans la pièce
	}
	
	public void chargerDonjon(Personnage personnage, ControleurPerso controleurPerso) {
		this.donjon = personnage.getDonjon();
		controleurPerso.entreePiece(donjon.getLabyrintheActuel()[donjon.getPositionJoueur()],null);    // Le joueur entre dans la pièce
	}
	
	public Donjon d() {
		return donjon;
	}
	
	public boolean changerPositionJoueur(Cardinalite deplacement, BoundaryPerso boundaryPerso){
        Piece pieceActuelle = donjon.getLabyrintheActuel()[donjon.getPositionJoueur()];
        boolean possible = true;
            switch (deplacement) {
                case NORD:
                    if (pieceActuelle.getCardinalitesImpossibles() == Cardinalite.NORD || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.NORD_OUEST || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.NORD_EST) {
                        possible = false;
                    } else {
                        donjon.setAnciennePosition(donjon.getPositionJoueur());
                        donjon.setPositionJoueur(donjon.getPositionJoueur() - 14);
                        boundaryPerso.phrasePiece(this);
                    }
                    break;
                case EST:
                    if (pieceActuelle.getCardinalitesImpossibles() == Cardinalite.EST || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.NORD_EST || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.SUD_EST) {
                        possible = false;
                    } else {
                        donjon.setAnciennePosition(donjon.getPositionJoueur());
                        donjon.setPositionJoueur(donjon.getPositionJoueur() + 1);
                        boundaryPerso.phrasePiece(this);
                    }
                    
                    break;
                case SUD:
                    if (pieceActuelle.getCardinalitesImpossibles() == Cardinalite.SUD || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.SUD_EST || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.SUD_OUEST) {
                        possible = false;
                    } else {
                    	donjon.setAnciennePosition(donjon.getPositionJoueur());
                    	donjon.setPositionJoueur(donjon.getPositionJoueur() + 14);
                        boundaryPerso.phrasePiece(this);
                    }
                    
                    break;
                case OUEST:
                    if (pieceActuelle.getCardinalitesImpossibles() == Cardinalite.OUEST || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.NORD_OUEST || pieceActuelle.getCardinalitesImpossibles() == Cardinalite.SUD_OUEST) {
                        possible = false;
                    } else {
                    	donjon.setAnciennePosition(donjon.getPositionJoueur());
                    	donjon.setPositionJoueur(donjon.getPositionJoueur() - 1);
                        boundaryPerso.phrasePiece(this);
                    }
                    break;
            }
        return possible;
    }

    
    public String EtageSup(){
        donjon.setEtage(donjon.getEtage()+1);
        return donjon.creerLabyrinthe();
    }
}
