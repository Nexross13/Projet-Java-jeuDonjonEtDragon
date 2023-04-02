package affichage;

import terrain.Donjon;

public class AffichageLab {

    // Methode qui permet d'afficher le Donjon dans le visuel suivant:
    /*  |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |####|    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |
        |    |    |    |    |    |    |    |    |    |    |    |    |    |    |*/

    public static void AffichageLaby(Donjon donjon){
        String cellule = "";
        int j = 0; // Compteur pour trouver les bords à l'ouest et l'est
        for (int i = 0; i < donjon.getLabyrintheActuel().length ; i++) {
            if(donjon.getLabyrintheActuel()[i].getEstDecouverte()){ // Vérifie si la pièce est découverte
                switch (donjon.getLabyrintheActuel()[i].getType()) {
                    case HOSTILE_FACILE:
                        cellule = " hf |";
                        break;
                    case HOSTILE_INTERMEDIAIRE:
                        cellule = " hi |";
                        break;
                    case HOSTILE_DIFFICILE:
                        cellule = " hd |";
                        break;
                    case HOSTILE_BOSS:
                        cellule = " hb |";
                        break;
                    case ENTREE:
                        cellule = " en |";
                        break;
                    case SORTIE:
                        cellule = " so |";
                        break;
                    case MARCHANT:
                        cellule = " ma |";
                        break;
                    case NEUTRE:
                        cellule = " ne |";
                        break;
                    case TRESOR:
                        cellule = " tr |";
                        break;
                    case FORGE:
                        cellule = " fo |";
                        break;
                }
            } else { // Si elle est as découverte on affiche une celulle vide
                cellule = "    |";
            }

            if (donjon.getPositionJoueur() == i) { //Sur n'impote quel type de piece, la celulle sera dans cette forme pour afficher le personnage
                cellule = "####|";
            }

            if (j == 13) { // Si le compteur est à 13, alors on saute une ligne
                cellule = cellule + "\n";
                j = 0;
            } 
            else if (j == 0) { // Si le compteur est à 0, alors on ajoute un mur
                cellule = "|" + cellule;
                j++;
            }
            else {
                j++;
            }

            System.out.print(cellule);
        }
    }
}
