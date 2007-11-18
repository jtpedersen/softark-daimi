package hotgammon.domain;

import java.util.ArrayList;

public class BetamonDieStrategy implements DieStrategy {

    private int turn;
    private int[] sequence;
    public BetamonDieStrategy(){
        this.turn =0;
        sequence = new int[] {1,2,1,2,3,4,3,4,5,6,1,2}; // standard for alphamon og betamon
    }
    
    public void removeDie(ArrayList<Integer> dies, int die) {
        for (int i = 0; i<dies.size(); i++ ) {
            if (dies.get(i) == die) {
                dies.remove(i);
                break;
            }
        }
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

    public void setSequence(int[] sequence) {
        this.sequence = sequence;
        this.turn = 0;
    }
}
