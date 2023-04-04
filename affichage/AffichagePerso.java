package affichage;

import protagonistes.Personnage;
import terrain.Donjon;

public class AffichagePerso {

    // Méthode pour afficher l'interface du personnage
    public static void afficherInventairePersonnage(Personnage joueur) {
		System.out.println("--------- INVENTAIRE "+joueur.getNom()+"--------");
		System.out.println("\nArmure:");
		for (int i = 0;i<4;i++) {
			if (joueur.getInventaire().getArmures(i) != null) {
				System.out.println(joueur.getInventaire().getArmures(i).getNomArmure()+" (PV:"+joueur.getInventaire().getArmures(i).getPV()+" PA:"+joueur.getInventaire().getArmures(i).getPA()+")");
			}
		}
		
		System.out.println("\nArme:");
		if (joueur.getInventaire().getArme() != null) {
			System.out.println(joueur.getInventaire().getArme().getNomArme()+" (DMG:"+joueur.getInventaire().getArme().getDMG()+")");
		}
		else {
			System.out.println("Pas d'arme equipe");
		}
		
		System.out.println("\nPotion:");
		int petitePotion = 0;
		int potionMax = 0;
		for (int i=0; i< joueur.getInventaire().getPotions().size();i++) {
			if (joueur.getInventaire().getPotions().get(i).getNomPotion() =="Petite Potion") { 
				petitePotion++;
			}
			else {
				potionMax++;
			}
		}
		System.out.println(petitePotion+" Petite(s) Potion(s)");
		System.out.println(potionMax+" Potion(s) Max");
        System.out.println("\n");
	}

    public static void afficherStatsPersonnage(Personnage joueur) {
        joueur.majStatJoueur();
        System.out.println("Stats de "+joueur.getNom()+": DMG: "+joueur.getForce()+" | PV: "+joueur.getPvActuel()+"/"+joueur.getPvMax()+" | PA: "+joueur.getProtection());
       
    }

	public static void afficherDeplacement(Personnage joueur, Donjon donjon){
		System.out.println("Votre cardinalite: ");
		System.out.println("N - nord ");
		System.out.println("S - sud ");
		System.out.println("O - ouest ");
		System.out.println("E - est ");
	
		String cardinalite = Clavier.entrerClavierString();
		System.out.println(joueur.deplacer(cardinalite));
		AffichageLab.AffichageLaby(donjon);
		System.out.println("Le joueur est dans la salle numero: " + donjon.getPositionJoueur() + ", cette salle est de type: " + donjon.getLabyrintheActuel()[donjon.getPositionJoueur()].getType());
	}
}
