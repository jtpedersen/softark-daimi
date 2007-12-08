package hotgammon.domain;

public class RealBackgammonFactory implements MonFactory {

    public RealBackgammonFactory() {
    }

    public Board createBoard() {
        return new StandardBoard();
    }

    public DieStrategy createDieStrategy() {
        return new GammamonDieStrategy();
    }

    public MoveStrategy createMoveStrategy() {
        return new RealBackgammonMoveStrategy();
    }

    public WinnerStrategy createWinnerStrategy() {
        return new RealBackgammonWinnerStrategy();
    }

}
