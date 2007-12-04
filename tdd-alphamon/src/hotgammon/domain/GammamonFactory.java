package hotgammon.domain;

public class GammamonFactory implements MonFactory {
    public DieStrategy createDieStrategy() {
        return new GammamonDieStrategy();
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
