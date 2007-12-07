package hotgammon.domain;

public class BasicValidation {
    /**
     * basic general movement thats not allowed as a *mon move
     */
    public static boolean isValidMove(Game game, Location from, Location to) {
        if (to == from || game.getColor(from) == Color.NONE || to == Location.R_BAR
                || to == Location.B_BAR || game.getPlayerInTurn() != game.getColor(from))
            return false;

        return true;
    }
}
