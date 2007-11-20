package hotgammon.domain;

import java.util.ArrayList;

class SimpleFactory implements MonFactory {
    public MoveStrategy createMoveStrategy() {
        return new MoveStrategy() {
            public int move(Game game, Board board, Location from, Location to) {
                board.move(from, to);
                return 1;
            }
            public boolean isValidMove(Game game, Location from, Location to) {
                return true;
            }
        };
    }

    public DieStrategy createDieStrategy() {
        return new DieStrategy() {
            public ArrayList<Integer> getMoves(int[] thrownDice) {
                ArrayList<Integer> movesLeft = new ArrayList<Integer>();
                for (Integer i : thrownDice)
                    movesLeft.add(i);
                return movesLeft;
            }
            public void removeDie(ArrayList<Integer> dies, int die) { }
            public int[] throwDice() {
                return new int[] {1, 2};
            }
        };
    }


    public WinnerStrategy createWinnerStrategy() {
        return new WinnerStrategy() {
            public Color winner(Game game, int turn) {
                return Color.NONE;
            }
        };
    }

}
