package hotgammon.domain;

import java.util.ArrayList;

public class BetamonDieStrategy implements DieStrategy {

    public void removeDie(ArrayList<Integer> dies, int die) {
        for (int i = 0; i<dies.size(); i++ ) {
            if (dies.get(i) == die) {
                dies.remove(i);
                break;
            }
        }
    }
    public int[] throwDice(int turn)
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
