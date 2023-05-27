package terrain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pnj.Marchant;
import protagonistes.Monstre;
import protagonistes.Personnage;

public class Donjon implements Serializable{

    // Attributs
    private Piece[] labyrintheActuel = new Piece[196];
    //private String[] labyrintheModele ={"E","M","M","M","M","M","M","F","HB","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M","M"};
    private String[] labyrintheModele ={"E","S","M","M","M","M","M","F","HB","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","N","HD","HD","HD","HD","HD","HD","HD","HD","HD","HD","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HI","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF","HF"};
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
    private int positionJoueur;
    private int anciennePosition = -1; 
    private int etage = 0;
    private Personnage joueur;

    // Constructeur
    public Donjon(Personnage joueur){
        creerLabyrinthe();
        this.joueur = joueur;
    }

    // Getteur
    public int getEtage() {
        return etage;
    }
    
    public void setEtage(int etage) {
    	this.etage = etage;
    }

    public Piece[] getLabyrintheActuel() {
        return labyrintheActuel;
    }

    public int getPositionJoueur() {
        return positionJoueur;
    }
    
    public void setPositionJoueur(int posi) {
        this.positionJoueur = posi;
    }

    public int getAnciennePosition() {
        return anciennePosition;
    }
    
    public void setAnciennePosition(int posi) {
        this.anciennePosition = posi;
    }

    public Personnage getJoueur() {
        return joueur;
    }
    
    public Cardinalite[] getCardinaliteImpossiblesLab() {
    	return this.cardinaliteImpossiblesLab;
    }

    // Autres méthodes
    public String creerLabyrinthe(){
        String[] preLabyrintheAleatoire = labyrintheModele;
        List<String> list = Arrays.asList(preLabyrintheAleatoire);
        Collections.shuffle(list);
        list.toArray(preLabyrintheAleatoire);

        for (int i = 0; i < preLabyrintheAleatoire.length; i++) {

            switch (preLabyrintheAleatoire[i]) {
                case "E":
                    Piece entree = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.ENTREE);
                    labyrintheActuel[i] = entree;
                    positionJoueur = i;
                    entree.setEstDecouverte(true);
                    break;
                case "S":
                    Piece sortie = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.SORTIE);
                    labyrintheActuel[i] = sortie;
                    break;
                case "M":
                    Piece marchant = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.MARCHANT);
                    labyrintheActuel[i] = marchant;
                    break;
                case "F":
                    Piece forge = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.FORGE);
                    labyrintheActuel[i] = forge;
                    break;
                case "HB":
                    Piece hostileBoss = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.HOSTILE_BOSS);
                    labyrintheActuel[i] = hostileBoss;
                    break;
                case "T":
                    Piece tresor = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.TRESOR);
                    labyrintheActuel[i] = tresor;
                    break;
                case "N":
                    Piece neutre = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.NEUTRE);
                    labyrintheActuel[i] = neutre;
                    break;
                case "HI":
                    Piece hostileIntermediaire = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.HOSTILE_INTERMEDIAIRE);
                    labyrintheActuel[i] = hostileIntermediaire;
                    break;
                case "HD":
                    Piece hostileDifficile = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.HOSTILE_DIFFICILE);
                    labyrintheActuel[i] = hostileDifficile;
                    break;
                default:
                    Piece hostileFacile = new Piece(i, cardinaliteImpossiblesLab[i], TypePiece.HOSTILE_FACILE);
                    labyrintheActuel[i] = hostileFacile;
                    break;
            }
        }
        String text = "Creation etage fait!";
        return text;
    }
    
 // Lors de la fuite, on rétablit l'ancienne position du joueur en position actuelle
    public String personnageFuit(){
        positionJoueur = anciennePosition;
        return "";
    }

    public String enregistrerPiece(Monstre monstre){
        labyrintheActuel[positionJoueur].setMonstre(monstre); //Enregistre le monstre dans la pièce
        return "";
    }
    
    public String enregistrerPiece(Marchant marchant){
    
    	labyrintheActuel[positionJoueur].setMarchant(marchant); //Enregistre le marchant dans la pièce
        return "";
    }

    
}