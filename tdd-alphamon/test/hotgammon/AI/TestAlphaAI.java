package hotgammon.AI;

import static org.junit.Assert.assertEquals;
import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.Helpers;
import hotgammon.domain.RealBackgammonFactory;
import hotgammon.domain.StandardGame;

import org.junit.Before;
import org.junit.Test;

public class TestAlphaAI {

    private Game game;

    @Before
    public void setup() {
        game = new StandardGame(new RealBackgammonFactory());
        game.newGame();
        game.nextTurn();
    }

    @Test
    public void assureAIPlaysNice() {
        Color p1 = game.getPlayerInTurn();
        Color p2 = (p1 == Color.RED) ? Color.BLACK : Color.RED;
        AI ai1 = new AlphaAI(p1, game);
        AI ai2 = new AlphaAI(p2, game);
        AI ai = ai1;
        while (game.winner() == Color.NONE) {
            if (game.getNumberOfMovesLeft() > 0)
                ai.move();
            
            assertEquals(
                    (Helpers.showBoardForDebug(game) + (ai.equals(ai1) ? p1
                            : p2))
                            + Helpers.getDiceString(game.diceValuesLeft()), 0,
                    game.getNumberOfMovesLeft());
            game.nextTurn();
            ai = (ai1.equals(ai)) ? ai2 : ai1;
        }
    }

}
