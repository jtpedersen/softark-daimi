package hotgammon.domain;

public interface MoveStrategy {
    public boolean isValidMove(Game game, Location from, Location to);
}
