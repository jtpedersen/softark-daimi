package hotgammon.domain;

public class RealBackgammonWinnerStrategy implements WinnerStrategy {

        public Color winner(Game game, int turn) {
            
            if (game.getCount(Location.R_BEAR_OFF)==15) 
                return Color.RED;
            
            if (game.getCount(Location.B_BEAR_OFF)==15) 
                return Color.BLACK;
                        
            return Color.NONE;
    }   

}
