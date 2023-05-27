package vue;

import affrontement.Bataille;
import controleur.ControleurBataille;
import controleur.ControleurPerso;
import protagonistes.TypeMonstre;

public class BoundaryBataille {
	
	//Attribut
	public ControleurBataille controleur;
		
	//Constructeur
	public BoundaryBataille(ControleurBataille controleur){
		this.controleur = controleur;
	}
	
	public String combat(Bataille bataille, ControleurPerso controleurPerso, BoundaryPerso boundaryPerso, BoundaryTresor boundaryTresor){
        // texte faire apparaitre les stats du monstre et du personnage
        System.out.println("---------------------Combat-------------------------");
        if (bataille.getMonstre().getType() == TypeMonstre.BOSS){
            System.out.println("Attention !!!, vous combattez un "+bataille.getMonstre().getType());
        }
        else{
            System.out.println("Vous êtes face à un monstre "+bataille.getMonstre().getType());
        }
        // Le combat commence au tour 1
        while(bataille.getPersonnage().getPvActuel() != 0 && bataille.getMonstre().getPvActuel() != 0 && bataille.getPersonnageContinuer()){
        	// Tant que les PvActuel du personnage sont pas égales à 0, de même pour le monstre et que le personnage veut continuer le combat
            if (bataille.getTour() % 2 != 0){ // Le personnnage joue tous les tours impairs

                System.out.println("------------------------------------------");
                System.out.println("Stats Monstre:    Dégat: "+bataille.getMonstre().getForce()+" | PV: "+bataille.getMonstre().getPvActuel()+"/"+bataille.getMonstre().getPvMax());
                System.out.println("Stats Personnage: Dégat: "+bataille.getPersonnage().getForce()+" | PV: "+bataille.getPersonnage().getPvActuel()+"/"+bataille.getPersonnage().getPvMax()+" |  PA:"+bataille.getPersonnage().getProtection());
                System.out.println("------------------------------------------");
                
                // Choix Action Joueur
                System.out.println("1 --> Attaquer");
                System.out.println("2 --> Soigner");
                if (bataille.getPersonnage().getDonjon().getAnciennePosition() != (-1)) { // Si le joueur a une ancienne position 
                	System.out.println("3 --> Fuir"); // Il peut fuir
                }
                // Action Joueur
                int choix = Clavier.entrerClavierInt();
                controleurPerso.actionCombat(choix, boundaryPerso); 
                               
            }

            if (bataille.getTour() % 2 == 0){ // Le monstre joue tous les tours pairs 
                // Le monstre attaque le joueur
                System.out.println(bataille.getMonstre().attaquer(bataille.getPersonnage()));
            }
            bataille.setTour(bataille.getTour()+1);   // Tour suivant            
        } // Fin Boucle Combat
        
        if (bataille.getMonstre().getPvActuel() == 0){ // Si le monstre à ses PV actuel à 0
            if(bataille.getMonstre().getType() == TypeMonstre.BOSS){ // Si le monstre etait un BOSS
            	bataille.getPersonnage().setCleSortie(true); // Il récupère la clé pour passer à l'étage suivant par la sortie
            	boundaryTresor.tresor(controleurPerso); // Le joueur récupère le trésor laissé par le monstre
                bataille.getPersonnage().getDonjon().getLabyrintheActuel()[bataille.getPersonnage().getDonjon().getPositionJoueur()].setEstFinie(true); // Le combat est terminé, le monstre est mort, la pèce passe en neutre
                try {
                    Thread.sleep(3000); // Pause le programme pendant 3 secondes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else { // Si le monstre n'est pas un BOSS
                // Le Monstre est mort
            	boundaryTresor.tresor(controleurPerso); // Le personnage obtient un trésor
                bataille.getPersonnage().gagnerPO(bataille.getMonstre()); // Le personnage gagne des pièces d'or en fonction du monstre
                bataille.getPersonnage().getDonjon().getLabyrintheActuel()[bataille.getPersonnage().getDonjon().getPositionJoueur()].setEstFinie(true); // Permet de rendre la piece en type NEUTRE quand le bataille est gagnée
                try {
                    Thread.sleep(3000); // Pause le programme pendant 3 secondes
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (bataille.getPersonnage().getPvActuel() == 0){ // Si le personnage à ses PV actuels égales à 0
        	return ""; 
        }
        else{ // Le personnage fuit
            // Sauvegarde le monstre dans la pièce découverte
            System.out.println("Vous fuyer le combat!");
            
        }
        System.out.println("--------------------Fin-Combat-------------------------");
        return ""; 
    }
}
