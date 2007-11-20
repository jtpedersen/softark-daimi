package hotgammon.domain;

public class DeltamonMoveStrategy implements MoveStrategy {
    public boolean isValidMove(Game game, Location from, Location to) {
        //code duplication from alphamon and betamon!

        if (game.getPlayerInTurn() != game.getColor(from))
            return false;
        if (game.getCount(to) > 0
            && game.getColor(to) != game.getPlayerInTurn())
            return false;

        int move = Location.distance(from, to);
        if (move < 0 && game.getPlayerInTurn() == Color.BLACK)
            return false;
        if (move > 0 && game.getPlayerInTurn() == Color.RED)
            return false;

        return true;
    }
}
