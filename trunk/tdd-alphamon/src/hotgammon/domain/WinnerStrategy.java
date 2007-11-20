package hotgammon.domain;

public interface WinnerStrategy {
    public Color winner( Game game, int turn );
}
