package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.GameListener;

public class AITurnHandler implements GameListener {

    private Game game;
    private AI ai1, ai2;
    private boolean playing;

    public AITurnHandler(AIFactory factory) {
        this(factory, null);
    }
    
    public AITurnHandler(AIFactory factory, AIFactory factory2) {
        this.game = factory.getGame();
        this.ai1 = factory.getAI();
        if (factory2!=null)
            this.ai2 = factory2.getAI();
        playing = true;
        game.addGameListener(this);        
    }

    public void boardChange() {
        if (game.winner() != Color.NONE)
            playing = false;
        if(!playing && game.winner() == Color.NONE)
            playing = true;

    }

    public void diceRolled() {
        if (game.winner() != Color.NONE)
            playing = false;
        
        if (playing) {
            Color pl = game.getPlayerInTurn();
            System.out.println(pl + " playing");
            if (ai1.getPlayer() == pl) {
                ai1.move();
            } else if (ai2!=null && ai2.getPlayer() == pl) {
                ai2.move();
            }
        }
    }
}
