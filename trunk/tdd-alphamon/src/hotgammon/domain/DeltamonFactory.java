package hotgammon.domain;

import Sound.EffectPlayer;
import Sound.MonSoundEffect;
import Sound.NullPlayer;

public class DeltamonFactory implements MonFactory {
    private boolean sound = false;

    public DeltamonFactory(boolean sound) {
        this.sound = sound;
    }
    public DeltamonFactory() {
    }
    
    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
    }
    public MoveStrategy createMoveStrategy() {
        return new DeltamonMoveStrategy();
    }
    public WinnerStrategy createWinnerStrategy() {
        return new AllInInnerFieldWinnerStrategy();
    }

    public Board createBoard() {
        return new StandardBoard();
    }
    public MonSoundEffect createEffectPlayer() {
        return (sound) ? new EffectPlayer() : new NullPlayer();
    }
}
