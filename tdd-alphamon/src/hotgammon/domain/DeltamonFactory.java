package hotgammon.domain;

class DeltamonFactory implements MonFactory {
    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
    }
    public MoveStrategy createMoveStrategy() {
        return new DeltamonMoveStrategy();
    }
    public WinnerStrategy createWinnerStrategy() {
        return new AllInInnerFieldWinnerStrategy();
    }
}
