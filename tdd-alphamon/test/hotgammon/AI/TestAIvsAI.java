package hotgammon.AI;

import static org.junit.Assert.assertEquals;
import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.Helpers;
import hotgammon.domain.RealBackgammonFactory;
import hotgammon.domain.StandardGame;

import org.junit.Before;
import org.junit.Test;

public class TestAIvsAI {

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
    public void testSimpleSelector() {
        AIFactory aifBlack = new SimpleFactory(game, Color.BLACK);
        AIFactory aifRed = new SimpleFactory(game, Color.RED);
        System.out.println("simple");
        assureAIcanPlayAndWinGame(aifBlack, aifRed);
    }

    @Test
    public void testAnalyzer() {
        AIFactory aifBlack = new AnalyzerFactory(game, Color.BLACK);
        AIFactory aifRed = new AnalyzerFactory(game, Color.RED);
        System.out.println("analyzer");
        assureAIcanPlayAndWinGame(aifBlack, aifRed);
    }
    
    @Test
    public void testSmartFactory() {
        AIFactory aifBlack = new SmartFactory(game, Color.BLACK);
        AIFactory aifRed = new SmartFactory(game, Color.RED);
        System.out.println("Smart");
        assureAIcanPlayAndWinGame(aifBlack, aifRed);
    }
    
    @Test
    public void testSmartVsAnalyzer() {
        AIFactory aifBlack = new SmartFactory(game, Color.BLACK);
        AIFactory aifRed = new AnalyzerFactory(game, Color.RED);
        System.out.println("Smart == black, Analyzer == RED");
        assureAIcanPlayAndWinGame(aifBlack, aifRed);
    }



    @Test
    public void testBlotter() {
        // assureAIPlaysNiceWithSelector(new BlotSelector()); //this can take
        // forever.... bliver let 1000+ ture
    }

    private void assureAIcanPlayAndWinGame(AIFactory aifBlack, AIFactory aifRed) {
        AI ai1, ai2;
        if (Color.RED == game.getPlayerInTurn()) {
            ai1 = aifRed.getAI();
            ai2 = aifBlack.getAI();
        } else {
            ai1 = aifBlack.getAI();
            ai2 = aifRed.getAI();
        }

        AI ai = ai1;
        int turns = 0;
        while (game.winner() == Color.NONE) {
            // String before = debugInfo(game, (ai.equals(ai1) ? p1: p2));

            if (game.getNumberOfMovesLeft() > 0)
                ai.move();

            // String after = debugInfo(game, (ai.equals(ai1) ? p1: p2));

            assertEquals(before + "  " + after, 0, game.getNumberOfMovesLeft());
            game.nextTurn();
            ai = (ai1.equals(ai)) ? ai2 : ai1;
            turns++;
        }
        System.out.println(game.winner() + " won in just " + turns + " turns");
    }

    private String debugInfo(Game g, Color player) {
        return player + " " + Helpers.showBoardForDebug(game)
                + Helpers.getDiceString(game.diceValuesLeft());
    }

}
