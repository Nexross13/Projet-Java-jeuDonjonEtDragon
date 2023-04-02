package test;


public class testSmall {
	
	public static void main(String[] args) {
		int[][] statsArmure = new int[4][2];
		statsArmure[3][0] = 25;
		statsArmure[3][1] = 10;
		affichageSexy(statsArmure);
		
		int[][] statsArmurePerso = new int[4][2];
		affichageSexy(statsArmurePerso);
		
		for (int i =0; i<statsArmure.length; i++ ) {
			if (statsArmure[i] != null) {
				for (int j =0; j<statsArmure[i].length; j++ ) {
					statsArmurePerso[i][j] = statsArmure[i][j];
				}
			}
		}
		affichageSexy(statsArmurePerso);
	}
	
	
	public static void affichageSexy(int[][] tab) {
		for (int i =0; i<tab.length; i++ ) {
			for (int j =0; j<tab[i].length; j++ ) {
				System.out.print(tab[i][j]+", ");
			}
		}
		System.out.println();
	}
}
