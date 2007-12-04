package hotgammon.domain;

public class DeltamonFactory implements MonFactory {
    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
    }
    public MoveStrategy createMoveStrategy() {
        return new DeltamonMoveStrategy();
    }
    public WinnerStrategy createWinnerStrategy() {
        return new AllInInnerFieldWinnerStrategy();
    }

    public Board createBoard() {
        return new StandardBoard();
    }
}
