package hotgammon.domain;

import java.util.ArrayList;

/*
Gammamon is identical to Alphamon (note: it is not an elaboration of
Betamon) except for the following specification:
   • Dice rolls are random as in ordinary backgammon.
   • As a consequence of the above, double rolls like [3,3] are now permit-
     ted, and your production code must thus be augmented to handle the
     rule in backgammon which states that double rolls allow four moves
     to be made.
   • Also, as the player to start is determined by the first roll of the dice,
     you will have to test that the game acts appropriately in the situation
     where the first dice roll leads to black starting, leads to red starting,
     or leads to a draw.
*/

public class GammamonDieStrategy implements DieStrategy {

    private int turn;
    public GammamonDieStrategy() {
        
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
        diceThrown[0] = (int) ((Math.random())*6);
        diceThrown[1] = (int) ((Math.random())*6);
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
