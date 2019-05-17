package codingtest;

import java.util.Random;

/**
 * Un tas de sable est repr�sent� par une grille compos�e de nombres entiers
 * allant de 0 �3, ces nombres entiers repr�sentent le nombre de grains de sable
 * par case de la grille
 * 
 * La m�thode sandpile(pile, n) ajoute n grains de sable au tas pile. pile est
 * un tableau d'entiers �2 dimensions �gales et impaires. Les n grains sont
 * ajout�s un par un au centre du tas. A chaque fois qu'un grain est ajout� �une
 * case, si le nombre de grains d'une case atteint 4, la case distribue un de
 * ses grains �chacun de ses voisins situ�s au-dessus et en dessous, ainsi qu'�
 * gauche et �droite. Si la case n'a pas 4 voisins, elle perd quand meme
 * l'ensemble de ses grains (les grains sont perdus sur les bords du tas). Les
 * cases voisines qui atteignent 4 grains redistribuent �leur tour les grains
 * dans leurs cases voisines. Les grains se propagent ainsi vers les bords du
 * tas.
 * 
 * @author mariano
 *
 */
public class SandPile {

	public static void sandpile(final int[][] pile, final int n) {
		for (int i = 0; i < n; i++) {
			int middle = pile.length / 2;
			sandpileAdd(pile, middle, middle);
		}
	}

	public static void sandpileAdd(final int[][] pile, final int x, final int y) {
		pile[x][y] += 1;
		if (pile[x][y] == 4) {
			pile[x][y] = 0;
			if (x > 0) {
				sandpileAdd(pile, x - 1, y);
			}
			if (x < pile.length - 1) {
				sandpileAdd(pile, x + 1, y);
			}
			if (y > 0) {
				sandpileAdd(pile, x, y - 1);
			}
			if (y < pile.length - 1) {
				sandpileAdd(pile, x, y + 1);
			}
		}
	}

	public static void print(final int[][] pile) {
		System.out.println("***************************");
		for (int i = 0; i < pile.length; i++) {
			for (int j = 0; j < pile.length; j++) {
				System.out.print(pile[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("***************************");
	}

	public static int[][] initRandom(final int size) {
		Random rnd = new Random(System.currentTimeMillis());
		int[][] pile = new int[size][size];
		for (int i = 0; i < pile.length; i++) {
			for (int j = 0; j < pile.length; j++) {
				pile[i][j] = rnd.nextInt(4);
			}
		}
		return pile;
	}

	public static void main(final String[] args) {
		int[][] pile = initRandom(13);

		print(pile);
		sandpile(pile, 4000);
		print(pile);
	}

}
