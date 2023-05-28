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
		while(true) {
				System.out.println("-----------Choix-----------");
	        System.out.println("0 - Quitter la salle");
	        System.out.println("1 - Ameliorer un equipement");
	        System.out.println("2 - Reparer un equipement");
	        String choix = Clavier.entrerClavierString();
	        String type;
	        switch (choix) {
	            case "0":
	                System.out.println("Vous quittez la forge");
	                return;
	        
	            case "1":
	                type = "ameliorer";
	                System.out.println("Quel type d'equipement vous voulez ameliorer au rang superieur ?");
	                choixEquipement(type, donjon, forgeron);
	                break;
	                
	            case "2":
	                type = "reparer";
	                System.out.println("Quel type d'equipement vous voulez reparer ?");
	                choixEquipement(type, donjon, forgeron);
	                break;
	                
	            default:
	            	break;
	        }
		}
        
    }

    public void choixEquipement(String type, Donjon donjon, Forgeron forgeron){ // Le personnage à choisi l'équipement à réparer ou à améliorer
        System.out.println("-----------Choix-----------");
        System.out.println("0 - Retour en arriere");
        System.out.println("1 - "+type+" une arme");
        System.out.println("2 - "+type+" une armure");
        String choix = Clavier.entrerClavierString();
        switch (choix) {
            case "0":
                actionEntrer(donjon, forgeron);
                break;
            
            case "1":
                
                if (donjon.getJoueur().getInventaire().getArme() != null) {
                    if (type == "ameliorer") {
                    	System.out.println(controleur.ameliorerArme(donjon.getJoueur().getInventaire().getArme()));
                    } else {
                    	System.out.println(controleur.reparerArme(donjon.getJoueur().getInventaire().getArme()));
                    }
                }
                else {
                	   System.out.println("Vous n'avez pas d'arme a "+type);
                }             
                break;
                 
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
                if (choixInt >=0 && choixInt <=3) {
                	if (tabArmure[choixInt] != null) {
                		if (type == "ameliorer") {
                			System.out.println(controleur.ameliorerArmure(tabArmure[choixInt])); 
		                } else {
		                	System.out.println( controleur.reparerArmure(tabArmure[choixInt]));
		                }
                	}
                	else {
                		System.out.println("Vous n'avez pas d'armure a "+type);
                	}                	
                }                
                break;
                
            default:
            	break;
        }
    }
}
