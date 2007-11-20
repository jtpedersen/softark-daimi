package hotgammon.domain;

class SixTurnRedWinnerStrategy implements WinnerStrategy {
    public Color winner( Game game, int turn ) {
        if (turn >= 6) {
            return Color.RED;
        }
        return Color.NONE;
    }
}
