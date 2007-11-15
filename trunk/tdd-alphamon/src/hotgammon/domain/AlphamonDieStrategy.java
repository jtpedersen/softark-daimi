package hotgammon.domain;

import java.util.ArrayList;

public class AlphamonDieStrategy implements DieStrategy {

    
    private int turn;
    public AlphamonDieStrategy() {
        this.turn = 0;
    }
	public void removeDie(ArrayList<Integer> dies, int die) {
		// TODO Auto-generated method stub
		dies.remove(0);
	}
	public int[] throwDice()
	{
	    
		int[] diceThrown = null;
		switch (turn % 5) {
		case 0:
		case 1:
			diceThrown = new int[] { 1, 2 };
			break;
		case 2:
		case 3:
			diceThrown = new int[] { 3, 4 };
			break;
		case 4:
			diceThrown = new int[] { 5, 6 };
			break;
		}
		turn++;
		return diceThrown;
	}
	public ArrayList<Integer> getMoves(int[] diceThrown)
	{
		ArrayList<Integer> movesLeft = new ArrayList<Integer>();
		for (Integer i : diceThrown)
			movesLeft.add(i);
		return movesLeft;
	}

}
