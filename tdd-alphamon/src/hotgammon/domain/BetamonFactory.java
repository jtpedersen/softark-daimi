package hotgammon.domain;

import Sound.EffectPlayer;
import Sound.MonSoundEffect;
import Sound.NullPlayer;

public class BetamonFactory implements MonFactory {

    private boolean sound = false;

    public BetamonFactory(boolean sound) {
        this.sound = sound;
    }

    public BetamonFactory() {
    }

    public DieStrategy createDieStrategy() {
        return new AlphamonDieStrategy();
    }

    public MoveStrategy createMoveStrategy() {
        return new BetamonMoveStrategy();
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
