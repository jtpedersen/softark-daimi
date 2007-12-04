package hotgammon.domain;

public class DeltamonMoveStrategy implements MoveStrategy {
    public int isValidMove(Game game, Location from, Location to) {
        //code duplication from alphamon and betamon!
//      er det et reeelt move
        if (to==from)
            return -1;
        
        if (game.getPlayerInTurn() != game.getColor(from))
            return -1;
        if (game.getCount(to) > 0
            && game.getColor(to) != game.getPlayerInTurn())
            return -1;

        int move = Location.distance(from, to);
        if (move < 0 && game.getPlayerInTurn() == Color.BLACK)
            return -1;
        if (move > 0 && game.getPlayerInTurn() == Color.RED)
            return -1;

        return game.diceValuesLeft()[0];
    }
}
