package hotgammon.domain;

public class AlphamonMoveStrategy implements MoveStrategy {

    public int move(Game game, Board board, Location from, Location to) {

        int[] diesLeft = game.diceValuesLeft();

        if (!isValidMove(game, from, to))
            return -1;

      

        board.move(from, to);

        return diesLeft[0];
    }

    public boolean isValidMove(Game game, Location from, Location to) {
        if (game.getNumberOfMovesLeft() == 0)
            return false;
        if (game.getCount(from) == 0)
            return false;
        if (game.getColor(from) != game.getPlayerInTurn())
            return false;
        if (game.getColor(to) != Color.NONE
                && game.getColor(to) != game.getPlayerInTurn())
            return false;

        return true;
    }

}
