package hotgammon.domain;

class GammamonFactory implements MonFactory {
    public DieStrategy createDieStrategy() {
        return new GammamonDieStrategy();
    }
    public MoveStrategy createMoveStrategy() {
        return new AlphamonMoveStrategy();
    }
    public WinnerStrategy createWinnerStrategy() {
        return new SixTurnRedWinnerStrategy();
    }
}
