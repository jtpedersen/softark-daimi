package hotgammon.domain;

import Sound.EffectPlayer;
import Sound.MonSoundEffect;
import Sound.NullPlayer;

public class RealBackgammonFactory implements MonFactory {
    private boolean sound = false;

    public RealBackgammonFactory(boolean sound) {
        this.sound = sound;
    }
    
    public RealBackgammonFactory() {
    }
    
    public Board createBoard() {
        return new StandardBoard();
    }

    public DieStrategy createDieStrategy() {
        return new GammamonDieStrategy();
    }

    public MoveStrategy createMoveStrategy() {
        return new RealBackgammonMoveStrategy();
    }

    public WinnerStrategy createWinnerStrategy() {
        return new RealBackgammonWinnerStrategy();
    }
    
    public MonSoundEffect createEffectPlayer() {
        return (sound) ? new EffectPlayer() : new NullPlayer();
    }

}
