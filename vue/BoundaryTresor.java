package vue;

import controleur.ControleurPerso;
import controleur.ControleurTresor;
import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;

public class BoundaryTresor {

	//Attribut
	public ControleurTresor controleur;
		
	//Constructeur
	public BoundaryTresor(ControleurTresor controleur){
		this.controleur = controleur;
	}
	
	public void tresor(ControleurPerso controleurPerso) {
		
		BoundaryPerso boundaryPerso = new BoundaryPerso(controleurPerso);
		
		int chanceNbTresor =  (int) (Math.random() * 100); //Chance d'obtenir un nombre de Trésor
		int nbTresor = 0;
		
		if (chanceNbTresor < 60) {      // 60% obtenir 1 Trésor
			nbTresor = 1;
		}
		else if (chanceNbTresor < 85) { // 25% obtenir 2 Trésors
			nbTresor = 2;
		}
		else { 						    // 15% obtenir 3 Trésors
			nbTresor = 3;
		}
		
		System.out.println("Vous recevez "+nbTresor+" Tresor(s)");
		
		for (int i = 0; i < nbTresor ; i++) { // Boucle en fonction du nombre de Trésor tiré au hasard
			int chanceTypeTresor = (int) (Math.random() * 100); // Chance pour détermine le type de trésor
			
			if (chanceTypeTresor < 30) {     // 30% obtenir Trésor de type potion
				TypePotion potionTresor = controleur.potionAleatoire(75);	// Création de la potion
				System.out.println("Tresor "+(i+1)+": "+potionTresor.getNomPotion()); // i trésor : potion => nom, 2pée, nom + dmg, et armure => nom, PV et PA
				boundaryPerso.sEquiperPotion(potionTresor);	// On équipe le joueur avec la potion s'il peut
				
			}
			else if (chanceTypeTresor < 55) { // 25% obtenir Trésor de type armure
				TypeArmure armureTresor = controleur.armureRareteAleatoire(75,95).randomStats(controleurPerso.p().getDonjon().getEtage()); // Création de l'armure
				System.out.println("Tresor "+(i+1)+": "+armureTresor.getNomArmure()+" (PV: "+armureTresor.getTempPV()+" PA:"+armureTresor.getTempPA()+")");
				boundaryPerso.sEquiperArmure(armureTresor);
			}
			else if (chanceTypeTresor < 75)	{		// 20% obtenir Trésor de type arme
				TypeArme armeTresor = controleur.armeAleatoire(77,97).randomDMG(controleurPerso.p().getDonjon().getEtage());	// Création de l'arme			
				System.out.println("Tresor "+(i+1)+": "+armeTresor.getNomArme()+" (DMG: " + armeTresor.getDMGTemp()+")");				
				boundaryPerso.sEquiperArme(armeTresor); // On équipe le joueur avec l'arme s'il peut
			}
			else { // 25% obtenir Trésor vide
				System.out.println("Tresor: poussière");
			}
		}
	}
}
