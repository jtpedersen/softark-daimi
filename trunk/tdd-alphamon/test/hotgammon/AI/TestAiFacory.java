package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.RealBackgammonFactory;
import hotgammon.domain.StandardGame;

import org.junit.Before;
import org.junit.Test;

public class TestAiFacory {

    private Game game;
    private String before;
    private String after;

    @Before
    public void setup() {
        game = new StandardGame(new RealBackgammonFactory());
        game.newGame();
        game.nextTurn();
    }

    
    @Test
    public void simpleSetupTest() {
        AITurnHandler turnhandler = new AITurnHandler(new SimpleFactory(game, Color.BLACK));
        while(game.winner()==Color.NONE)
            game.nextTurn();
        
            
    }


}
