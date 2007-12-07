package hotgammon.AI;
import static org.junit.Assert.assertEquals;
import hotgammon.domain.BoardConfiguration;
import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.Location;
import hotgammon.domain.MonTestFactory;
import hotgammon.domain.StandardGame;

import org.junit.Before;
import org.junit.Test;

public class TestAIvsAI {
    
    
    private Game game;

    @Before
    public void setup() {
        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.R1,Color.BLACK, 2)
        };
        game = new StandardGame( new MonTestFactory(config, new int[]{1,2}));
        game.newGame();
        game.nextTurn();
    }
    
    @Test
    public void assureBlackStartsAndMovesBothCheckers() {
        assertEquals(Color.BLACK, game.getPlayerInTurn());
        AI ai = new AlphaAI(Color.BLACK, game);
        ai.move();
        assertEquals(1, game.getCount(Location.R2));
        assertEquals(1, game.getCount(Location.R3));
    }
    
}
