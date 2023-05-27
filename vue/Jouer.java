package vue;


public class Jouer {
			
	public static void main(String[] args) {
		
		BoundaryJeu boundaryJeu = new BoundaryJeu();
		BoundaryMaitreDonjon boundaryMaitreDonjon = new BoundaryMaitreDonjon();
		BoundaryJouer boundaryJouer = new BoundaryJouer(boundaryJeu, boundaryMaitreDonjon);
		boundaryJouer.menu();
	}		
}
