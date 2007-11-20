package hotgammon.domain;

public interface MonFactory {
    public DieStrategy createDieStrategy();
    public MoveStrategy createMoveStrategy();
    public WinnerStrategy createWinnerStrategy();
}
