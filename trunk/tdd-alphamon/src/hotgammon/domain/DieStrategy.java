package hotgammon.domain;

import java.util.ArrayList;

public interface DieStrategy {
    public int[] throwDice();
    public ArrayList<Integer> getMoves(int[] thrownDice);
}
