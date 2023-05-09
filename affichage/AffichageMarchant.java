package affichage;

import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
import pnj.Marchant;
import terrain.Donjon;

public class AffichageMarchant {

    public static void actionEntrer(Donjon donjon, Marchant marchant){
        //while (true) {
            System.out.println("-----------Choix-----------");
            System.out.println("0 - Quitter la salle");
            marchant.Produit();
            System.out.print("Votre choix: ");
            int choix = Clavier.entrerClavierInt();
            if (choix == 0) {
                AffichagePerso.afficherDeplacement(donjon.getJoueur(), donjon);
            } else {
                System.out.println(marchant.Achat(donjon.getJoueur(), choix-1));
            }
        //}
    }
    
    public static void AfficherPotion(TypePotion potion, int position){
        System.out.println((position+1)+" - "+potion.getNomPotion() + " Prix: " + potion.getPrix() + " PO"); 
    }

    public static void AfficherArme(TypeArme arme, int position){
        System.out.println((position+1)+" - "+arme.getNomArme()+" (DMG: " + arme.getDMGTemp()+") Prix: " + arme.getPrix() + " PO");				
    }

    public static void AfficherArmure(TypeArmure armure, int position){
        System.out.println((position+1)+" - "+armure.getNomArmure()+" (PV: "+armure.getTempPV()+" PA:"+armure.getTempPA()+") Prix: " + armure.getPrix() + " PO");
    }


}
