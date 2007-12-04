package hotgammon.domain;

import java.util.ArrayList;

public interface DieStrategy {
    public void removeDie(ArrayList<Integer> dies, int die);
    public int[] throwDice();
    public ArrayList<Integer> getMoves(int[] thrownDice);
}