package hotgammon.domain;

public class BetamonFactory implements MonFactory {
    public DieStrategy createDieStrategy() {
        return new BetamonDieStrategy();
    }
    public MoveStrategy createMoveStrategy() {
        return new BetamonMoveStrategy();
    }
    public WinnerStrategy createWinnerStrategy() {
        return new SixTurnRedWinnerStrategy();
    }

    public Board createBoard() {
        return new StandardBoard();
    }
}
