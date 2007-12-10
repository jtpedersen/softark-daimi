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
        
        
        
        Color player = game.getPlayerInTurn();
        Location bar = (player==Color.BLACK) ? Location.B_BAR : Location.R_BAR;
        Location bearOff = (player==Color.BLACK) ? Location.B_BEAR_OFF : Location.R_BEAR_OFF;
        
        // man må ikke flytte væk fra bearoff og der skal v;re noget at rykke med
        if (from == bearOff )
            return -1;
         
        if (to == bearOff && game.getCount(bar)==0) {
            return bearOffStrategy.isValidMove(game, from, to);
        } else
            return betamonMoveStrategy.isValidMove(game, from, to);
    }

}
