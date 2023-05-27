package vue;


import controleur.ControleurForge;
import item.TypeArme;
import item.TypeArmure;
import pnj.Forgeron;
import terrain.Donjon;

public class BoundaryForge {

	//Attribut
		public ControleurForge controleur;
		BoundaryPerso boundaryPerso;
			
	//Constructeur
	public BoundaryForge(ControleurForge controleur, BoundaryPerso boundaryPerso){
		this.controleur = controleur;
		this.boundaryPerso = boundaryPerso;
	}
	
	public void actionEntrer(Donjon donjon, Forgeron forgeron){ // Action du personnage avec le forgeron
        System.out.println("-----------Choix-----------");
        System.out.println("0 - Quitter la salle");
        System.out.println("1 - Améliorer un equipement");
        System.out.println("2 - Réparer un equipement");
        String choix = Clavier.entrerClavierString();
        String type;
        switch (choix) {
            case "0":
                System.out.println("Vous quittez la forge");
                return;
        
            case "1":
                type = "améliorer";
                System.out.println("Quel type d'équipement vous voulez améliorer au rang supérieur ?");
                choixEquipement(type, donjon, forgeron);
                break;
                
            case "2":
                type = "réparer";
                System.out.println("Quel type d'équipement vous voulez réparer ?");
                choixEquipement(type, donjon, forgeron);
                break;
        }
    }

    public void choixEquipement(String type, Donjon donjon, Forgeron forgeron){ // Le personnage à choisi l'équipement à réparer ou  à améliorer
        System.out.println("-----------Choix-----------");
        System.out.println("0 - Retour en arrière");
        System.out.println("1 - "+type+" une arme");
        System.out.println("2 - "+type+" une armure");
        String choix = Clavier.entrerClavierString();
        switch (choix) {
            case "0":
                actionEntrer(donjon, forgeron);
                break;
            
            case "1":
                TypeArme arme = donjon.getJoueur().getInventaire().getArme();
                if (arme != null) {
                    if (type == "améliorer") {
                    	controleur.ameliorerArme(donjon.getJoueur().getInventaire().getArme());
                    } else {
                    	controleur.reparerArme(donjon.getJoueur().getInventaire().getArme());
                    }
                    break;
                }
            
            case "2":
                System.out.println("Quelle armure vous voulez " + type + " ?");
                TypeArmure[] tabArmure = donjon.getJoueur().getInventaire().getArmuresStock();
                for (int i = 0; i < donjon.getJoueur().getInventaire().getArmuresStock().length; i++) {
                    if (tabArmure[i] == null) {
                        System.out.println(i + " - Rien");
                    } else {
                        System.out.println(i + " - " + tabArmure[i].getNomArmure()); 
                    }
                }
                int choixInt = Clavier.entrerClavierInt();
                if (type == "améliorer") {
                	controleur.ameliorerArmure(tabArmure[choixInt]);
                } else {
                	controleur.reparerArmure(tabArmure[choixInt]);
                }
                break;
        }
    }
}
