package hotgammon.domain;


public class BetamonFactory implements MonFactory {

    public BetamonFactory() {
    }

    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
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
