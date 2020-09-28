package minimax;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Game of Nim with two players (human and computer) played in the console. 
 * Computer AI is made by the Minimax algorithm in the MinimaxCreator class.
 * There are three heaps of coins. Heap one has 3 coins, heap two has 4 and
 * heap 3 has 5 coins. One player can take only from one heap at a time, and
 * the number of coins the payer can take is from 1 to the number of coins in 
 * the heap. The player that takes the last coin loses.
 * 
 * Example input: 3 2. First number is the number of the heap and the second
 * number are coins taken from that heap. 
 * 
 * @author ivanj
 *
 */

public class Nim {

	private static int[] heaps = new int[] { 3, 4, 5 };
	private static boolean isPlayer = true;

	/**
	 * Checks if the game is done (all heaps are empty).
	 * 
	 * @return True if the game is done
	 */
	
	private static boolean gameDone() {
		if (heaps[0] == 0 && heaps[1] == 0 && heaps[2] == 0) {
			if (!isPlayer) {
				System.out.println("Winner is the player.");
			} else {
				System.out.println("Winner is the computer.");
			}
			return true;
		}
		return false;
	}

	/**
	 * Prints the heaps on the console.
	 */
	
	private static void printResult() {
		System.out.println(Arrays.toString(heaps));
	}

	/**
	 * Main program that starts the game. Human player starts first and after that
	 * the computer player plays. 
	 * 
	 * @param args command line arguments (not used)
	 */
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		while (true) {
			try {
				if (isPlayer) {
					System.out.print("Player input: ");

					String[] lines = in.nextLine().split("\\s+");
					if (lines.length != 2) {
						throw new IllegalArgumentException("You need to input only two numbers divided by a space.");
					}
					int heapNumber = Integer.parseInt(lines[0]) - 1;
					int numberOfCoins = Integer.parseInt(lines[1]);

					if (heaps[heapNumber] - numberOfCoins < 0 || numberOfCoins == 0) {
						throw new IllegalArgumentException("Minimum number of coins should be 1 and maximum should be the number of coins in the heap.");
					}
					heaps[heapNumber] -= numberOfCoins;
				} else {
					System.out.println("Computer");

					heaps = MinimaxCreator.getNextMove(heaps);
				}
				printResult();
				if (gameDone()) {
					break;
				}
				isPlayer ^= true;

			} catch (NumberFormatException ex) {
				System.out.println("Input is not a number.");
			} catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("There are only 3 heaps.");
			}
		}
		in.close();
	}
}
