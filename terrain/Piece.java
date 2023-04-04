package terrain;
import java.io.Serializable;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import pnj.Forgeron;
import pnj.PersonnageNonJoueur;
import protagonistes.Monstre;

public class Piece implements Serializable{

    // Attributs
    private int idPiece;
    private boolean estDecouverte = false;
    private boolean estFinie = false;
    private Cardinalite cardinalitesImpossibles;
    private TypePiece type;
    private Monstre monstre;
    private Forgeron forgeron;

    // Constructeur
    public Piece(int idPiece, Cardinalite cardinalitesImpossibles, TypePiece type){
        this.idPiece = idPiece;
        this.cardinalitesImpossibles = cardinalitesImpossibles;
        this.type = type;
        this.monstre = null;
        this.forgeron = null;
    }

    // Guetteur
    public int getIdPiece() {
        return idPiece;
    }

    public boolean getEstDecouverte(){
        return estDecouverte;
    }

    public boolean getEstFinie(){
        return estFinie;
    }

    public Cardinalite getCardinalitesImpossibles() {
        return cardinalitesImpossibles;
    }

    public TypePiece getType() {
        return type;
    }

    public Monstre getMonstre(){
        return monstre;
    }

    public Forgeron getForgeron() {
        return forgeron;
    }

    // Setteur
    public void setType(TypePiece type) {
        this.type = type;
    }

    public void setEstDecouverte(boolean estDecouverte) {
        this.estDecouverte = estDecouverte;
    }

    public void setEstFinie(boolean estFinie) {
        this.estFinie = estFinie;
        setType(TypePiece.NEUTRE);
    }

    public void setMonstre(Monstre monstre){
        this.monstre = monstre;
    }

    public void setForgeron(Forgeron forgeron) {
        this.forgeron = forgeron;
    }
}