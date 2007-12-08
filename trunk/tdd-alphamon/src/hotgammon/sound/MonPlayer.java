package hotgammon.sound;

import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.GameListener;
import hotgammon.sound.SoundEffectPlayer.sound;

public class MonPlayer implements GameListener {

    private Game game;
    public MonPlayer(Game game) {
        this.game = game;
        game.addGameListener(this);
    }

    public void boardChange() {
        if (game.winner()==Color.NONE) {
            new SoundEffectPlayer(sound.CHECKER);    
        }
    }

    public void diceRolled() {
        new SoundEffectPlayer(sound.DIE);
    }

}
