package affichage;

import item.TypeArmure;
import pnj.Forgeron;
import pnj.PersonnageNonJoueur;
import terrain.Donjon;

public class AffichageForge {
    public static void actionEntrer(Donjon donjon, Forgeron forgeron){
        System.out.println("-----------Choix-----------");
        System.out.println("0 - Quitter la salle");
        System.out.println("1 - Améliorer un equipement");
        System.out.println("2 - Réparer un equipement");
        String choix = Clavier.entrerClavierString();
        String type;
        switch (choix) {
            case "0":
                System.out.println("Vous quittez la forge, où voulez vous allez ?");
                AffichagePerso.afficherDeplacement(donjon.getJoueur(), donjon);
                break;
        
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

    public static void choixEquipement(String type, Donjon donjon, Forgeron forgeron){
        System.out.println("0 - Retour en arrière");
        System.out.println("1 - "+type+" une arme");
        System.out.println("2 - "+type+" une armure");
        String choix = Clavier.entrerClavierString();
        switch (choix) {
            case "0":
                actionEntrer(donjon, forgeron);
                break;
            
            case "1":
                if (type == "améliorer") {
                    forgeron.ameliorerArme(donjon.getJoueur().getInventaire().getArme());
                } else {
                    forgeron.reparerArme(donjon.getJoueur().getInventaire().getArme());
                }
                break;
            
            case "2":
                System.out.println("Quelle armure vous voulez " + type + " ?");
                TypeArmure[] tabArmure = donjon.getJoueur().getInventaire().getArmuresStock();
                for (int i = 0; i < donjon.getJoueur().getInventaire().getArmuresStock().length; i++) {
                    System.out.println(i + " - " + tabArmure[i]);
                }
                int choixInt = Clavier.entrerClavierInt();
                if (type == "améliorer") {
                    forgeron.ameliorerArmure(donjon.getJoueur().getInventaire().getArmuresStock()[choixInt]);
                } else {
                    forgeron.reparerArmure(donjon.getJoueur().getInventaire().getArmuresStock()[choixInt]);
                }
                break;
        }
    }
}
