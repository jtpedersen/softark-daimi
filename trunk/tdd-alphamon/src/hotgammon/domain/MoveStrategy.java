package hotgammon.domain;

public interface MoveStrategy {

	public int move(Game game, Board board, Location from, Location to);
	public boolean isValidMove(Game game, Location from, Location to);
	
}
