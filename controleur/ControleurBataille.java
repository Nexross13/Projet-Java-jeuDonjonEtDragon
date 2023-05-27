package controleur;


import affrontement.Bataille;
import protagonistes.TypeMonstre;
import vue.BoundaryPerso;
import vue.BoundaryTresor;
import vue.Clavier;

public class ControleurBataille {
	
	Bataille bataille;
	ControleurPerso controleurPerso;
	BoundaryPerso boundaryPerso;
	BoundaryTresor boundaryTresor;
	
	public ControleurBataille(Bataille bataille, ControleurPerso controleurPerso, ControleurTresor controleurTresor) {
		this.bataille = bataille;
		this.controleurPerso = controleurPerso;		
		this.boundaryPerso = new BoundaryPerso(controleurPerso);
		this.boundaryTresor = new BoundaryTresor(controleurTresor);
	}
	
	// Autres méthodes
    public String combat(){
        // texte faire apparaitre les stats du monstre et du personnage
        System.out.println("---------------------Combat-------------------------");
        if (bataille.getMonstre().getType() == TypeMonstre.BOSS){
            System.out.println("Attention !!!, vous combattez un "+bataille.getMonstre().getType());
        }
        else{
            System.out.println("Vous êtes face à un monstre "+bataille.getMonstre().getType());
        }
        
        while(bataille.getPersonnage().getPvActuel() != 0 && bataille.getMonstre().getPvActuel() != 0 && bataille.getPersonnageContinuer()){
            if (bataille.getTour() % 2 != 0){

                System.out.println("------------------------------------------");
                System.out.println("Stats Monstre:    Dégat: "+bataille.getMonstre().getForce()+" | PV: "+bataille.getMonstre().getPvActuel()+"/"+bataille.getMonstre().getPvMax());
                System.out.println("Stats Personnage: Dégat: "+bataille.getPersonnage().getForce()+" | PV: "+bataille.getPersonnage().getPvActuel()+"/"+bataille.getPersonnage().getPvMax()+" |  PA:"+bataille.getPersonnage().getProtection());
                System.out.println("------------------------------------------");
                // Action Joueur
                System.out.println("1 --> Attaquer");
                System.out.println("2 --> Soigner");
                if (bataille.getPersonnage().getDonjon().getAnciennePosition() != (-1)) { // Si le joueur a une ancienne position 
                	System.out.println("3 --> Fuir"); // Il peut fuir
                }
                
                int choix = Clavier.entrerClavierInt();
                controleurPerso.actionCombat(choix, boundaryPerso); // Boundary qui va faire les paramètres d'entrés
                               
            }

            if (bataille.getTour() % 2 == 0){
                // Attaque Monstre
                System.out.println(bataille.getMonstre().attaquer(bataille.getPersonnage()));
            }
            bataille.setTour(bataille.getTour()+1);               
        }
        if (bataille.getMonstre().getPvActuel() == 0){
            if(bataille.getMonstre().getType() == TypeMonstre.BOSS){ // Si le mosntre etait un BOSS
            	bataille.getPersonnage().setCleSortie(true);
            	boundaryTresor.tresor(controleurPerso);
                bataille.getPersonnage().getDonjon().getLabyrintheActuel()[bataille.getPersonnage().getDonjon().getPositionJoueur()].setEstFinie(true);
                try {
                    Thread.sleep(3000); // Pause le programme pendant 3 secondes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Le Monstre est mort
            	boundaryTresor.tresor(controleurPerso); // Le personange obtient un trésor
                bataille.getPersonnage().gagnerPO(bataille.getMonstre());
                bataille.getPersonnage().getDonjon().getLabyrintheActuel()[bataille.getPersonnage().getDonjon().getPositionJoueur()].setEstFinie(true); // Permet de rendre la piece en type NEUTRE quand le bataille est gagnée
                try {
                    Thread.sleep(3000); // Pause le programme pendant 3 secondes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (bataille.getPersonnage().getPvActuel() == 0){
        	return ""; 
        }
        else{
            // Sauvegarder le monstre dans la pièce 
            System.out.println("Vous fuyer le combat!");
            
        }
        System.out.println("--------------------Fin-Combat-------------------------");
        return ""; 
    }
    
    public void bataille(Bataille bataille) {
    	this.bataille = bataille;
    }
}
