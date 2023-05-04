package affichage;

import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;
import pnj.Marchant;
import terrain.Donjon;

public class AffichageMarchant {

    public static void actionEntrer(Donjon donjon, Marchant marchant){
        System.out.println("-----------Choix-----------");
        System.out.println("0 - Quitter la salle");
        marchant.Produit();
        System.out.print("Votre choix: ");
        int choix = Clavier.entrerClavierInt();
        marchant.Achat(donjon.getJoueur(), choix);
    }
    
    public static void AfficherPotion(TypePotion potion, int position){
        System.out.println((position+1)+" - "+potion.getNomPotion()); 
    }

    public static void AfficherArme(TypeArme arme, int position){
        System.out.println((position+1)+" - "+arme.getNomArme()+" (DMG: " + arme.getDMGTemp()+")");				
    }

    public static void AfficherArmure(TypeArmure armure, int position){
        System.out.println((position+1)+" - "+armure.getNomArmure()+" (PV: "+armure.getTempPV()+" PA:"+armure.getTempPA()+")");
    }


}
