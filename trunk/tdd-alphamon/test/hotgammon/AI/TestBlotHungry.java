package hotgammon.AI;

import static org.junit.Assert.assertEquals;
import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.Location;
import hotgammon.domain.MonTestFactory;
import hotgammon.domain.StandardGame;

import org.junit.Before;
import org.junit.Test;

public class TestBlotHungry {

    private Game game;

    @Before
    public void setup() {
        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.R1,Color.BLACK, 2),
                new BoardConfiguration(Location.R2,Color.RED, 1),
                new BoardConfiguration(Location.R3,Color.RED, 1),
                new BoardConfiguration(Location.R5,Color.RED, 1),
                new BoardConfiguration(Location.R9,Color.RED, 1)
        };
        game = new StandardGame( new MonTestFactory(config, new int[]{1,4}));
        game.newGame();
        game.nextTurn();
    }
    
    @Test
    public void assureBlackTakesAllBlots() {
        AIFactory aif = new BlotFactory(game, Color.BLACK);
        AI ai = new BasicAI(aif);
        assertEquals(Color.BLACK, game.getPlayerInTurn());
        ai.move();
        assertEquals(1, game.getCount(Location.R2));
        assertEquals(Color.BLACK, game.getColor(Location.R2));
        
        assertEquals(1, game.getCount(Location.R5));
        assertEquals(Color.BLACK, game.getColor(Location.R5));
        
        assertEquals(Color.NONE, game.getPlayerInTurn());
        game.nextTurn();
        game.nextTurn();
        assertEquals(Color.BLACK, game.getPlayerInTurn());
        ai.move();
        assertEquals(1, game.getCount(Location.R3));
        assertEquals(Color.BLACK, game.getColor(Location.R3));
        
        assertEquals(1, game.getCount(Location.R9));
        assertEquals(Color.BLACK, game.getColor(Location.R9));
    }

}
