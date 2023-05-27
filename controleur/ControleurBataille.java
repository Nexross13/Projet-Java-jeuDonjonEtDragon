package controleur;


import affrontement.Bataille;
import protagonistes.TypeMonstre;
import vue.BoundaryBataille;
import vue.BoundaryPerso;
import vue.BoundaryTresor;
import vue.Clavier;

public class ControleurBataille {
	
	Bataille bataille;
	ControleurPerso controleurPerso;
	BoundaryPerso boundaryPerso;
	BoundaryTresor boundaryTresor;
	BoundaryBataille boundaryBataille;
	
	public ControleurBataille(Bataille bataille, ControleurPerso controleurPerso, ControleurTresor controleurTresor) {
		this.bataille = bataille;
		this.controleurPerso = controleurPerso;		
		this.boundaryPerso = new BoundaryPerso(controleurPerso);
		this.boundaryTresor = new BoundaryTresor(controleurTresor);
		this.boundaryBataille = new BoundaryBataille(this);
	}
	
	
    public String combat() { // Lancement Combat
    	return boundaryBataille.combat(bataille, controleurPerso, boundaryPerso, boundaryTresor);
    }
}
