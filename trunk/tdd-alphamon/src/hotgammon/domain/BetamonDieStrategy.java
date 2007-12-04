package hotgammon.domain;

import java.util.ArrayList;

public class BetamonDieStrategy implements DieStrategy {
    private DieStrategy ds;

    public BetamonDieStrategy(){
        ds = new SequenceDieStrategy(new int[] {1,2,1,2,3,4,3,4,5,6});
    }    
    public int[] throwDice() {
        return ds.throwDice();
    }
    public ArrayList<Integer> getMoves(int[] diceThrown) {
        return ds.getMoves(diceThrown);
    }
}
