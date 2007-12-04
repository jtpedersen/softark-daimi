package hotgammon.domain;

public class RealBackgammonFactory implements MonFactory {

    public Board createBoard() {
        return new StandardBoard();
    }

    public DieStrategy createDieStrategy() {
        return new GammamonDieStrategy();
    }

    public MoveStrategy createMoveStrategy() {
        return new RealBackgammonMoveStrategy(
                new AllInInnerFieldWinnerStrategy(), new BetamonMoveStrategy());
    }

    public WinnerStrategy createWinnerStrategy() {
        return new RealBackgammonWinnerStrategy();
    }

}
