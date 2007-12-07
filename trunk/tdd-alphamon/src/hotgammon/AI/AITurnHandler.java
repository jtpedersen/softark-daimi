package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.GameListener;

public class AITurnHandler implements GameListener, Runnable {

    private Game game;
    private Color player1, player2;
    private AI ai1, ai2;
    private boolean playing;

    public AITurnHandler(Game game, Color player) {
        this.game = game;
        this.player1 = player;
        game.addGameListener(this);
        this.ai1 = new AlphaAI(player, game);
        playing = true;
        
    }
    public AITurnHandler(Game game) {
        this(game, Color.BLACK);
        this.player2 = Color.RED;
        this.ai2 = new AlphaAI(player2, game);
    }


    public void boardChange() {
        if (game.winner() != Color.NONE)
            playing = false;

    }

    public void diceRolled() {
        if (game.winner() != Color.NONE)
            playing = false;
        
        if (playing) {
            Color pl = game.getPlayerInTurn();
            System.out.println(pl + " playing");
            if (player1 == pl) {
                ai1.move();
            } else if (player2!=null && player2 == pl) {
                ai2.move();
            }
        }
    }

    private void delay(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
                return;
            }
        }
    }

}
