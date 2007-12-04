package hotgammon.domain;

public interface MoveStrategy {
    public int isValidMove(Game game, Location from, Location to);
}
