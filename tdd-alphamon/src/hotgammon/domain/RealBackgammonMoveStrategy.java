package hotgammon.domain;

public class RealBackgammonMoveStrategy implements MoveStrategy {

    private MoveStrategy betamonMoveStrategy;
    private MoveStrategy bearOffStrategy;

    public RealBackgammonMoveStrategy() {
        this.betamonMoveStrategy = new BetamonMoveStrategy();
        this.bearOffStrategy = new BearOffMoveStrategy();
    }

    public int isValidMove(Game game, Location from, Location to) {
        
        if(!BasicValidation.isValidMove(game, from, to))
            return -1;
        
        // man må ikke flytte væk fra bearoff og der skal v;re noget at rykke med
        if (from == Location.B_BEAR_OFF || from == Location.R_BEAR_OFF || game.getNumberOfMovesLeft() == 0)
            return -1;
         
        if (to == Location.B_BEAR_OFF || to == Location.R_BEAR_OFF)
            return bearOffStrategy.isValidMove(game, from, to);
        else
            return betamonMoveStrategy.isValidMove(game, from, to);
    }

}
