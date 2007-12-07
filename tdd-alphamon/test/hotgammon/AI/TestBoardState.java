package hotgammon.AI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hotgammon.domain.BoardConfiguration;
import hotgammon.domain.Color;
import hotgammon.domain.FixedBoardSetup;
import hotgammon.domain.Game;
import hotgammon.domain.Location;
import hotgammon.domain.MonTestFactory;
import hotgammon.domain.StandardGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestBoardState {
    
    
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
        
        Iterator<Location> loc = game.boardIterator();
        List<BoardConfiguration> bc = new ArrayList<BoardConfiguration>();
        while (loc.hasNext()) {
            Location location = (Location) loc.next();
            int count = game.getCount(location);
            if (count>0) {
                bc.add(new BoardConfiguration(location, game.getColor(location), count));
            }
        }
        
        
        BoardState bs = new SimpleBoardState(game.diceValuesLeft(), Color.BLACK, new FixedBoardSetup(bc) );
        List<BoardState> states = bs.getValidStates();
        
//        System.out.println(states.size());
        assertEquals(4, states.size());
//        for(BoardState b: states)
//            System.out.println(b);
        List<GameMove> gm = bs.getValidStates().get(0).getGameMoves();
        assertEquals(2, gm.size());
        
        for(GameMove g: gm) 
            assertTrue(game.move(g.from, g.to));
        
        
        assertEquals(1, game.getCount(Location.R2));
        assertEquals(1, game.getCount(Location.R3));
    }
    
    @Test
    public void assureAllGeneratedMovesAreLegal() {
        resetGame();
        
        BoardState bs = new SimpleBoardState(game.diceValuesLeft(), Color.BLACK, game );
        List<BoardState> states = bs.getValidStates();
        for(BoardState b: states) {
            for(GameMove gm : b.getGameMoves()) {
                System.out.println(gm);
                assertTrue(game.move(gm.from, gm.to));
            }
            System.out.println("next");
            resetGame();
        }
        
    }
    
    private void resetGame() {
        BoardConfiguration[] config = new BoardConfiguration[] {
                new BoardConfiguration(Location.R1,Color.BLACK, 2),
                new BoardConfiguration(Location.R2,Color.RED, 2),
                new BoardConfiguration(Location.R8,Color.BLACK, 2)
        };
        game = new StandardGame( new MonTestFactory(config, new int[]{1,2}));
        game.newGame();
        game.nextTurn();
    }
    
}
