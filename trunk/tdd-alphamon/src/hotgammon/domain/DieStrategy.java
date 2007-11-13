package hotgammon.domain;

import java.util.ArrayList;

public interface DieStrategy {
	public void removeDie(ArrayList<Integer> dies, int die);
//	public int[] diceValuesLeft() {
	public int[] throwDice(int turn);
	public ArrayList<Integer> getMoves(int[] thrownDice);
}
