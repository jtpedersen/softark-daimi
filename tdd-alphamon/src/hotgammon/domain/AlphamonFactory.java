package hotgammon.domain;

public class AlphamonFactory implements MonFactory {
    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
    }

    public MoveStrategy createMoveStrategy() {
        return new AlphamonMoveStrategy();
    }

    public WinnerStrategy createWinnerStrategy() {
        return new SixTurnRedWinnerStrategy();
    }

    public Board createBoard() {
        return new StandardBoard();
    }
}
