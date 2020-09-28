package minimax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class made to return the next state via the Minimax algorithm.
 * 
 * @author ivanj
 *
 */

public class MinimaxCreator {

	/**
	 * Method to get all possible moves.
	 * 
	 * @param startState starting state
	 * @return list of all possible moves
	 */

	private static List<int[]> Succ(int[] startState) {
		List<int[]> succ = new ArrayList<>();
		for (int i = 0; i < startState.length; i++) {
			for (int j = 1; j <= startState[i]; j++) {
				int[] temp = new int[startState.length];
				for (int k = 0; k < startState.length; k++) {
					temp[k] = startState[k];
				}
				temp[i] -= j;
				succ.add(temp);
			}
		}
		return succ;
	}

	/**
	 * Method that returns true if the node is a vertix. In another words, if the
	 * node is the winning node, the funciton returns true.
	 * 
	 * @param heaps current state
	 * @return true if the current state is (0, 0, 0).
	 */

	private static boolean Terminal(int[] heaps) {
		if (heaps[0] == 0 && heaps[1] == 0 && heaps[2] == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Method that returns the values of the functions. If the player character wins
	 * the method returns -1, and if the computer player wins the method returns 1.
	 * 
	 * @param isPlayer true if the current turn is the player turn
	 * @return 1 if the current turn is the computer turn, -1 if the current turn is
	 *         the computer turn
	 */

	private static int Utility(boolean isPlayer) {
		if (isPlayer) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * Method returns the next optimal move for the computer player.
	 * 
	 * @param heaps current state of the match
	 * @return next optimal state of the match
	 */
	
	public static int[] getNextMove(int[] heaps) {
		HashMap<Integer, List<int[]>> map = new HashMap<>();

		map.put(1, new ArrayList<int[]>());
		map.put(-1, new ArrayList<int[]>());

		List<int[]> listOfPossibleMoves = Succ(heaps);

		for (int[] possibleMove : listOfPossibleMoves) {
			map.get(Minimax(possibleMove, true)).add(possibleMove);
		}

		if (!map.get(1).isEmpty()) {
			return map.get(1).get(0);
		} else {
			return map.get(-1).get(0);
		}
	}

	/**
	 * Minimax function for the Nim game. MAX player is the computer player, 
	 * and the MIN player is the human player.
	 * 
	 * @param heaps current state of the match
	 * @param isPlayer true if the current player is the human player
	 * @return 1 if the next state i optimal for the computer, -1 if it's not
	 */
	
	private static int Minimax(int[] heaps, boolean isPlayer) {
		if (Terminal(heaps)) {
			return Utility(isPlayer);
		}
		if (!isPlayer) {
			int n = Integer.MIN_VALUE;
			for (int[] state : Succ(heaps)) {
				int n1 = Minimax(state, true);
				n = Math.max(n, n1);
			}
			return n;
		} else {
			int n = Integer.MAX_VALUE;
			for (int[] state : Succ(heaps)) {
				int n1 = Minimax(state, false);
				n = Math.min(n, n1);
			}
			return n;
		}
	}
}
