package hotgammon.domain;

import Sound.EffectPlayer;
import Sound.MonSoundEffect;
import Sound.NullPlayer;

public class AlphamonFactory implements MonFactory {
    
    private boolean sound = false;

    public AlphamonFactory(boolean sound) {
        this.sound = sound;
    }
    public AlphamonFactory() {
     
    }
    
    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
    }

    public MoveStrategy createMoveStrategy() {
        return new AlphamonMoveStrategy();
    }

    public WinnerStrategy createWinnerStrategy() {
        return new SixTurnRedWinnerStrategy();
    }

    public Board createBoard() {
        return new StandardBoard();
    }

    public MonSoundEffect createEffectPlayer() {
        return (sound) ? new EffectPlayer() : new NullPlayer();
    }
    
    
}
