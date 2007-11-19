package hotgammon.domain;

import java.util.ArrayList;

public class SequenceDieStrategy implements DieStrategy {
    private int[] sequence;
    private int throwAmount;

    public SequenceDieStrategy(int[] sequence) {
        this.sequence = sequence;
        throwAmount = 0;
    }

    public void removeDie(ArrayList<Integer> dies, int die) {
        for (int i = 0; i<dies.size(); i++ ) {
            if (dies.get(i) == die) {
                dies.remove(i);
                return;
            }
        }
    }
    public int[] throwDice()
    {
        int[] diceThrown = new int[2];
        diceThrown[0] = sequence[throwAmount % sequence.length];
        diceThrown[1] = sequence[(throwAmount+1) % sequence.length];
        throwAmount += 2;
        return diceThrown;
    }
    public ArrayList<Integer> getMoves(int[] diceThrown)
    {
        ArrayList<Integer> movesLeft = new ArrayList<Integer>();
        if (diceThrown[0] == diceThrown[1]) {
            for (int j = 0; j < 4; j++) {
                movesLeft.add(diceThrown[0]);
            }
        } else {
            for (Integer i : diceThrown)
                movesLeft.add(i);
        }
        return movesLeft;
    }

}
