package affrontement;

import java.io.Serializable;

import affichage.Clavier;
import protagonistes.Monstre;
import protagonistes.Personnage;
import protagonistes.TypeMonstre;

public class Bataille implements Serializable{
    // Attribut
    private Personnage personnage;
    private Monstre monstre;
    private int tour = 1;
    private boolean personnageContinuer = true;
    private boolean personnageGagner = false;

    // Constructeur
    public Bataille(Personnage personnage, Monstre monstre){
        this.personnage = personnage; 
        this.monstre = monstre;
    }

    // Guetteur
    public Personnage getPersonnage(){
        return personnage;
    }

    public Monstre getMonstre(){
        return monstre;
    }

    // Autres méthodes
    public String combat(){
        String texte ="";
        // texte faire apparaitre les stats du monstre et du personnage
        System.out.println("---------------------Combat-------------------------");
        if (monstre.getType() == TypeMonstre.BOSS){
            System.out.println("Attention !!!, vous combattez un "+monstre.getType());
        }
        else{
            System.out.println("Vous êtes face à un monstre "+monstre.getType());
        }
        
        while(personnage.getPvActuel() != 0 && monstre.getPvActuel() != 0 && personnageContinuer){
            if (tour % 2 != 0){

                System.out.println("------------------------------------------");
                System.out.println("Stats Monstre:    Dégat: "+monstre.getForce()+" | PV: "+monstre.getPvActuel()+"/"+monstre.getPvMax());
                System.out.println("Stats Personnage: Dégat: "+personnage.getForce()+" | PV: "+personnage.getPvActuel()+"/"+personnage.getPvMax()+" |  PA:"+personnage.getProtection());
                System.out.println("------------------------------------------");
                // Action Joueur
                System.out.println("1 --> Attaquer");
                System.out.println("2 --> Soigner");
                System.out.println("3 --> Fuir");
                int choix = Clavier.entrerClavierInt();
                personnage.actionCombat(choix); // Boundary qui va faire les paramètres d'entrés
                               
            }

            if (tour % 2 == 0){
                // Attaque Monstre
                System.out.println(monstre.attaquer(personnage));
            }
            tour++;               
        }
        if (monstre.getPvActuel() == 0){
            if(monstre.getType() == TypeMonstre.BOSS){
                personnage.setCleSortie(true);
                personnageGagner = true;
                personnage.getInventaire().Tresor();
                personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].setEstFinie(true);
            } else {
                // Le Monstre est mort
                personnageGagner = true;
                personnage.getInventaire().Tresor();
                personnage.getDonjon().getLabyrintheActuel()[personnage.getDonjon().getPositionJoueur()].setEstFinie(true); // Permet de rendre la piece en type NEUTRE quand le bataille est gagnée
            }
        }
        else if (personnage.getPvActuel() == 0){
        	return ""; 
        }
        else{
            // Sauvegarder le monstre dans la pièce 
            System.out.println("Vous fuyer le combat!");
            personnage.getDonjon().enregistrerPiece(monstre); // Enregistre le monstre dans la pièce car la pièce est découverte
        }

        System.out.println("--------------------Fin-Combat-------------------------");

        return ""; 
    }
    
    public void fuir(){ // Le personnage fuit le combat, il retourne à son ancienne position
        this.personnageContinuer = false;
    }
}