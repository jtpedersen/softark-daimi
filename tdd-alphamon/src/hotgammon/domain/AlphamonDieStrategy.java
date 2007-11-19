package hotgammon.domain;

import java.util.ArrayList;

public class AlphamonDieStrategy implements DieStrategy {

    
    private int turn;
    private int[] sequence;
    public AlphamonDieStrategy() {
        this.turn = 0;
        sequence = new int[] {1,2,1,2,3,4,3,4,5,6,1,2}; // standard for alphamon
    }
    /**
     * sets a dice sequence and resets the turn counter
     * @param sequence
     */
    public void setSequence(int[] sequence) {
        this.sequence = sequence;
        this.turn = 0;
    }
    public void removeDie(ArrayList<Integer> dies, int die) {
	// TODO Auto-generated method stub
	dies.remove(0);
    }
    public int[] throwDice()
    {
	int[] diceThrown = new int[2];
	diceThrown[0] =sequence[(turn*2)%sequence.length];
	diceThrown[1] =sequence[(1+turn*2)%sequence.length];
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
