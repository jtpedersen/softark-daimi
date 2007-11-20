package hotgammon.domain;

public class AlphamonMoveStrategy implements MoveStrategy {
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
