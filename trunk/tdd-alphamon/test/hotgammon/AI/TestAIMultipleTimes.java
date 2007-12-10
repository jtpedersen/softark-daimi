package hotgammon.AI;

import static org.junit.Assert.assertEquals;
import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.Helpers;
import hotgammon.domain.RealBackgammonFactory;
import hotgammon.domain.StandardGame;

import org.junit.Before;
import org.junit.Test;

public class TestAIMultipleTimes {

    private Game game;
    private String before;
    private String after;

    private int bw, rw;
    @Before
    public void setup() {

    }

    @Test
    public void testSmartVsAnalyzer() {
        System.out.println("Smart == black, ToSmart == RED");

        for (int i = 0; i < 500; i++) {
            game = new StandardGame(new RealBackgammonFactory());
            game.newGame();
            game.nextTurn();

            AIFactory aifBlack = new SmartFactory(game, Color.BLACK);
            AIFactory aifRed = new ToSmartFactory(game, Color.RED);
            assureAIcanPlayAndWinGame(aifBlack, aifRed);
        }
        
        System.out.println("Black won " + bw + "  and Red won " + rw + " ratio " + ( (bw*100.0)/((rw+bw)*1.0)));
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
        if (game.winner() ==Color.BLACK) 
            bw++;
        else
            rw ++;
//        System.out.println(game.winner() + " won in just " + turns + " turns");
    }

    private String debugInfo(Game g, Color player) {
        return player + " " + Helpers.showBoardForDebug(game)
                + Helpers.getDiceString(game.diceValuesLeft());
    }

}
