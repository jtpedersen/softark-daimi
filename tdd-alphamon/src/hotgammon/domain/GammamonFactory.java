package hotgammon.domain;

import Sound.EffectPlayer;
import Sound.MonSoundEffect;
import Sound.NullPlayer;

public class GammamonFactory implements MonFactory {
    
    private boolean sound = false;

    public GammamonFactory(boolean sound) {
        this.sound = sound;
    }
  
    public GammamonFactory() {
    }
    
    public DieStrategy createDieStrategy() {
        return new GammamonDieStrategy();
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
