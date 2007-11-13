package hotgammon.domain;

public class AlphamonMoveStrategy implements MoveStrategy {

	public int move(Game game, Board board, Location from, Location to) {
		
		int[] diesLeft = game.diceValuesLeft();
		
		if (game.getNumberOfMovesLeft() == 0)
		 return -1;
		 if (board.getCount(from) == 0)
		 return -1;
		 /* This only applys for spikes.
		 if (board.getCount(to) >= 5)
		 return -1;
		 */
		 if (board.getColor(from) != game.getPlayerInTurn())
		 return -1;
		 if (board.getColor(to) != Color.NONE
		 && board.getColor(to) != game.getPlayerInTurn())
		 return -1;
		        
		 board.move(from, to);
		
		 return diesLeft[0];
	}

}
