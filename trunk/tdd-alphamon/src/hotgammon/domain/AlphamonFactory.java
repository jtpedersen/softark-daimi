package hotgammon.domain;

class AlphamonFactory implements MonFactory {
    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
    }
    public MoveStrategy createMoveStrategy() {
        return new AlphamonMoveStrategy();
    }
    public WinnerStrategy createWinnerStrategy() {
        return new SixTurnRedWinnerStrategy();
    }
}
