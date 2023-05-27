package controleur;

import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;

public class ControleurTresor {
	
	public ControleurTresor() {
		
	}

	// Permet de définir le drop de la rareté en fonction de l'armure
		public TypeArmure armureRareteAleatoire(int chanceCommun, int chanceRare) {
			
			int chance = (int) (Math.random() * 100);
			
			if (chance <  chanceCommun) {	// 75% Commun => trésor / 20 % => Marchant
				return armureTypeAleatoire(TypeArmure.CASQUE_COMMUN,TypeArmure.PLASTRON_COMMUN,TypeArmure.JAMBIERE_COMMUN,TypeArmure.BOTTE_COMMUN);		
			}
			else if (chance < chanceRare) { // 20% Rare => trésor / 47 % => Marchant
				return armureTypeAleatoire(TypeArmure.CASQUE_RARE,TypeArmure.PLASTRON_RARE,TypeArmure.JAMBIERE_RARE,TypeArmure.BOTTE_RARE);	
			}		
			else {					// 5% Legendaire => trésor / 33 % => Marchant
				return armureTypeAleatoire(TypeArmure.CASQUE_LEGENDAIRE,TypeArmure.PLASTRON_LEGENDAIRE,TypeArmure.JAMBIERE_LEGENDAIRE,TypeArmure.BOTTE_LEGENDAIRE);
			}	
		}
		
		// Permet de définir le taux de drop de la pièce d'armure
		private TypeArmure armureTypeAleatoire(TypeArmure casque, TypeArmure plastron, TypeArmure jambiere, TypeArmure botte) {
				
				int chance = (int) (Math.random() * 4);
				
				if (chance < 1) {	   // 25% Casque
					return casque;
				}
				else if (chance < 2) { // 25% Plastron
					return plastron;
				}
				else if (chance < 3) { // 25% Jambiere
					return jambiere;
				}
				else {   			   // 25% Botte
					return botte;
				}
			}
			
		public TypeArme armeAleatoire(int chanceCommun, int chanceRare) {
				
				int chance = (int) (Math.random() * 100);
		
				if (chance < chanceCommun) { 				// 77% Epée Commun => trésor / 20 % => Marchant
					return TypeArme.EPEE_COMMUN;
					}
				
				else if (chance < chanceRare) {			// 20% Epée Rare => trésor / 47 % => Marchant
					return TypeArme.EPEE_RARE;	
				}		
				else {							// 3% Epée Commun => trésor / 33 % => Marchant
					return TypeArme.EPEE_LEGENDAIRE;
				}	
			}
		
		public TypePotion potionAleatoire(int chancePetitPotion) {
			
			int chance = (int) (Math.random() * 100);
			
			if (chance < chancePetitPotion) {		// 75% Petite potion => Trésor / 50 % =>  Marchant
				return TypePotion.PETITE_POTION;		
			}
			else {								// 25% Potion Max => Trésor / 50 % =>  Marchant
				return TypePotion.MAX_POTION;
			}	
		}
}
