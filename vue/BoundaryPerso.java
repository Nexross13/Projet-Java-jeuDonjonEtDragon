package vue;

import controleur.ControleurDonjon;
import controleur.ControleurPerso;
import controleur.ControleurTresor;
import item.TypeArme;
import item.TypeArmure;
import item.TypePotion;


public class BoundaryPerso {

	//Attribut
	public ControleurPerso controleur;
	
		
	//Constructeur
	public BoundaryPerso(ControleurPerso controleur){
		this.controleur = controleur;
	}
	
	public void afficherStatsPersonnage() {
        controleur.majStatJoueur();
        System.out.println("Stats de "+controleur.p().getNom()+": DMG: "+controleur.p().getForce()+" | PV: "+controleur.p().getPvActuel()+"/"+controleur.p().getPvMax()+" | PA: "+controleur.p().getProtection() + " | PO: "+ controleur.p().getNbrPO());
    }
	
	public void afficherDeplacement(BoundaryDonjon boundaryDonjon, ControleurDonjon controleurDonjon, ControleurTresor controleurTresor){
		System.out.println("+---Votre cardinalite---+");
		System.out.println("|N - nord               |");
		System.out.println("|S - sud                |");
		System.out.println("|O - ouest              |");
		System.out.println("|E - est                |");
		System.out.println("+-----------------------+");
		System.out.print("Votre choix: ");
		String cardinalite = Clavier.entrerClavierString();
		if(!controleur.deplacer(cardinalite, controleurDonjon, this, controleurTresor)){
			try {
				System.out.println("Vous ne pouvez pas vous déplacer ici car il y a un mur");
				Thread.sleep(2000);
			} catch (Exception e) {
			}
		}
		boundaryDonjon.affichageLaby();
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
		}
	}
	
	public void phrasePiece(ControleurDonjon controleurDonjon){
		System.out.print("Vous entrez dans une pièce");

		try {
			Thread.sleep(200); // Pause le programme pendant 1 seconde
			System.out.print(" .");
			Thread.sleep(200); 
			System.out.print(".");
			Thread.sleep(200); 
			System.out.print(".");
			Thread.sleep(500); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(" qui contient un "+ controleurDonjon.d().getLabyrintheActuel()[controleurDonjon.d().getPositionJoueur()].getType() + "!");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
	}
	
	public void afficherInventairePersonnage() {
		System.out.println("--------- INVENTAIRE "+controleur.p().getNom()+"--------");
		System.out.println("\nArmure:");
		for (int i = 0;i<4;i++) {
			if (controleur.p().getInventaire().getArmures(i) != null) {
				System.out.println(controleur.p().getInventaire().getArmures(i).getNomArmure()+" (PV:"+controleur.p().getInventaire().getArmures(i).getPV()+" PA:"+controleur.p().getInventaire().getArmures(i).getPA()+" Durabilite: "+controleur.p().getInventaire().getArmures(i).getDurabilite()+ " / "+ controleur.p().getInventaire().getArmures(i).getDurabiliteMax() + ")");
			}
		}
		
		System.out.println("\nArme:");
		if (controleur.p().getInventaire().getArme() != null) {
			System.out.println(controleur.p().getInventaire().getArme().getNomArme()+" (DMG:"+controleur.p().getInventaire().getArme().getDMG()+ "| Durabilite: " + controleur.p().getInventaire().getArme().getDurabilite() + "/" + controleur.p().getInventaire().getArme().getDurabiliteMax()+ ")");
		}
		else {
			System.out.println("Pas d'arme equipe");
		}
		
		System.out.println("\nPotion:");
		int petitePotion = 0;
		int potionMax = 0;
		for (int i=0; i< controleur.p().getInventaire().getPotions().size();i++) {
			if (controleur.p().getInventaire().getPotions().get(i).getNomPotion() =="Petite Potion") { 
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
	
	public void boirePotion() {
		if (controleur.p().getInventaire().getPotions().size() !=0 || controleur.p().getPvActuel() != controleur.p().getPvMax()) {
			System.out.println("Quelle potion souhaitez vous boire?");
            System.out.println("0--> Annuler Boire Potion");
	        for (int j=0; j < controleur.p().getInventaire().getPotions().size() ; j++ ) {
	            System.out.println(j+1+"--> "+controleur.p().getInventaire().getPotions().get(j).getNomPotion());
	        }
	        int choix_potion = Clavier.entrerClavierInt();
	        if(choix_potion >=1 && choix_potion <= controleur.p().getInventaire().getPotions().size()) {  // Tant que le nombre entré est compris entre 1 et la longueur de la taille du stock de potion
	            double soin = ((double)controleur.p().getInventaire().getPotions().get(choix_potion-1).getPourcent_Soin()/(double)100) * (double)controleur.p().getPvMax(); // Soin en pourcentage convertit en soin fixe en fonction des PV max du Personnage
                if ((int) soin + controleur.p().getPvActuel() < controleur.p().getPvMax()) { 
                	controleur.p().setPvActuel(controleur.p().getPvActuel() + (int)soin);
                    System.out.println("Soin: +"+(int)soin+" PV");
                }
                else { // Si les soins dépasse les PV Max, le surplus de soin ne sera pas comptabilisé
                    int tempPvActuel = controleur.p().getPvActuel();
                    controleur.p().setPvActuel(controleur.p().getPvMax());
                    System.out.println("Soin: +"+(controleur.p().getPvActuel()-tempPvActuel)+" PV");
                }
                
                controleur.p().getInventaire().getPotions().remove(choix_potion-1);
                System.out.println("PV:"+controleur.p().getPvActuel()+"/"+controleur.p().getPvMax());
	        }
            else{
                System.out.println("Annulation Boire Potion");
            }
	
			
		}
		else {
			System.out.println("Impossible d'utiliser de Potion");
		}
	}
	
    public String sEquiperArmure(TypeArmure armure) {
		String nomArmure = armure.getNomArmure();
		
					
		if(nomArmure.contains("Botte")) {
			int position = 3;
			if(controleur.p().getInventaire().getArmures(position) == null) { // Si l'utilisateur de possède pas de botte
				System.out.println("Voulez-vous vous equiper de cette paire de "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Ajout effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); // On ajoute la paire de botte dans l'équipement du joueur
				}							
				
			}
			else { // Si l'utilisateur possède déjà une paire de botte, on lui demande s'il veut la remplacer
				System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+controleur.p().getInventaire().getArmures(position).getNomArmure()+" (PV:"+controleur.p().getInventaire().getArmures(position).getPV()+" PA:"+controleur.p().getInventaire().getArmures(position).getPA()+")? Oui(O) ou Non(N)"); // On demande à l'utilisateur s'il veut remplacer la nouvelle paire de botte par une autre
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Remplacement effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); // On remplace les anciennes bottes par les nouvelles
				}
			}
			return"";	
		}
			
		if(nomArmure.contains("Jambiere")) {
			int position = 2;
			if(controleur.p().getInventaire().getArmures(position) == null) {
				System.out.println("Voulez-vous vous equiper de cette "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Ajout effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); 
				}		
			}
			else {
				System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+controleur.p().getInventaire().getArmures(position).getNomArmure()+" (PV:"+controleur.p().getInventaire().getArmures(position).getPV()+" PA:"+controleur.p().getInventaire().getArmures(position).getPA()+")? Oui(O) ou Non(N)");
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Remplacement effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); 		
				}
			}
			return"";		
		}
		
		if(nomArmure.contains("Plastron")) {			
			int position = 1;
			if(controleur.p().getInventaire().getArmures(position) == null) {
				System.out.println("Voulez-vous vous equiper de ce "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Ajout effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); 
				}	
			}
			else {
				System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+controleur.p().getInventaire().getArmures(position).getNomArmure()+" (PV:"+controleur.p().getInventaire().getArmures(position).getPV()+" PA:"+controleur.p().getInventaire().getArmures(position).getPA()+")? Oui(O) ou Non(N)");
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Remplacement effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); 		
				}
			}
			return"";		
		}
					
		if(nomArmure.contains("Casque")) {
			int position = 0;
			if(controleur.p().getInventaire().getArmures(position) == null) {
				System.out.println("Voulez-vous vous equiper de ce "+armure.getNomArmure()+" ? Oui(O) ou Non(N)"); 
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Ajout effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); 
				}	
			}
			else {
				System.out.println("Remplacer "+armure.getNomArmure()+" (PV:"+armure.getTempPV()+" PA:"+armure.getTempPA()+") ==> "+controleur.p().getInventaire().getArmures(position).getNomArmure()+" (PV:"+controleur.p().getInventaire().getArmures(position).getPV()+" PA:"+controleur.p().getInventaire().getArmures(position).getPA()+")? Oui(O) ou Non(N)");
				String choix = Clavier.entrerClavierString();
				
				if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
					
					System.out.println("Remplacement effectue");
					controleur.p().getInventaire().ajouterArmure(armure,position); 		
				}
			}
			return"";		
		}
			
		else {
			System.out.println("Erreur, le joueur ne peut pas s'equiper");
			return"";	
		}
	}


	
	public String sEquiperArme(TypeArme arme) {
		
		if (controleur.p().getInventaire().getArme() == null) { 	// Si l'utilisateur ne possède pas d'épee
			System.out.println("Voulez-vous vous equiper de cette "+arme.getNomArme()+" ? Oui(O) ou Non(N)"); 
			String choix = Clavier.entrerClavierString();
			
			if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
				
				System.out.println("Ajout effectue");
				controleur.p().getInventaire().ajouterArme(arme);
			}	
			
		}
		else {
			System.out.println("Remplacer "+arme.getNomArme()+" (DMG:"+arme.getDMGTemp()+") ==> "+controleur.p().getInventaire().getArme().getNomArme()+" (DMG:"+controleur.p().getInventaire().getArme().getDMG()+")? Oui(O) ou Non(N)"); // On demande à l'utilisateur s'il veut remplacer la nouvelle Epée par une autre
		
			String choix = Clavier.entrerClavierString();		
			if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
				
				System.out.println("Remplacement effectue");
				controleur.p().getInventaire().ajouterArme(arme); // On remplace l'anciennce potion par la nouvelle		
				
			}
		}
	    return"";
	}
	
	public String sEquiperPotion(TypePotion potion) {
		if (controleur.p().getInventaire().getPotions().size() < 10) {  // Si le nombre de potion stocket est inferieur à 10, on stock directement la potion dans l'inventaire
			controleur.p().getInventaire().ajouterPotion(potion);
			System.out.println("Recu: "+potion.getNomPotion());
		}
		else {					 	// Si l'inventaire de potion est plein
			System.out.println("Votre inventaire de potion est plein, voulez-vous remplacer une potion ? Oui(O) ou Non(N)"); // On demande à l'utilisateur s'il veut remplacer la nouvelle potion par une autre
			String choix = Clavier.entrerClavierString();
			
			if(choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("O")) {
				System.out.println("Quelle potion souhaitez vous remplacer ?");
				for (int j=0; j < controleur.p().getInventaire().getPotions().size() ; j++ ) {
					System.out.println(j+1+"--> "+controleur.p().getInventaire().getPotions().get(j).getNomPotion());
				}
				int choix_potion = Clavier.entrerClavierInt();
				while (choix_potion <1 || choix_potion > controleur.p().getInventaire().getPotions().size()) {  // Tant que le nombre entré n'est pas compris entre 1 et la longueur de la taille du stock de potion
					choix_potion = Clavier.entrerClavierInt();			
				}	
				System.out.println("Vous remplacer "+controleur.p().getInventaire().getPotions().get(choix_potion-1).getNomPotion()+" par une "+potion.getNomPotion());
				controleur.p().getInventaire().getPotions().set(choix_potion-1, potion); // On remplace l'anciennce potion par la nouvelle		
			}
		}	
	    return"";
	}
}
