package hotgammon.domain;

public class BetamonMoveStrategy implements MoveStrategy {

    public int move(Game game, Board board, Location from, Location to) {

        if (!isValidMove(game, from, to))
            return -1;
        if (board.getCount(to) == 1
                && board.getColor(to) != game.getPlayerInTurn()) {
            if (game.getPlayerInTurn() == Color.BLACK)
                board.move(to, Location.R_BAR);
            else
                board.move(to, Location.B_BAR);
        }

        board.move(from, to);

        return Math.abs(Location.distance(from, to));
    }

    public boolean isValidMove(Game game, Location from, Location to) {
        int move = Location.distance(from, to);
        
        // are there chekers in the bar ?
        if (game.getPlayerInTurn() == Color.BLACK
                && game.getCount(Location.B_BAR) > 0) {
            if (from != Location.B_BAR) // only move from bar
                return false;
            if ( !(to.getIndex()>0 && to.getIndex()<7 ) ) //only move to otherplayers home
                return false;
        } else if (game.getPlayerInTurn() == Color.RED
                && game.getCount(Location.R_BAR) > 0) {
            if (from != Location.R_BAR) // only move from bar
                return false;
            if ( !(to.getIndex()>18 && to.getIndex()<25 ) ) //only move to otherplayers home
                return false;
        }

        

        if (game.getPlayerInTurn() != game.getColor(from))
            return false;
        if (game.getCount(to) > 1
                && game.getColor(to) != game.getPlayerInTurn())
            return false;
        if (move < 0 && game.getPlayerInTurn() == Color.BLACK)
            return false;
        if (move > 0 && game.getPlayerInTurn() == Color.RED)
            return false;
        return true;
    }
}
