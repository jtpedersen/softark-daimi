/**
 * 
 */
package hotgammon.domain;

import Sound.EffectPlayer;
import Sound.MonSoundEffect;
import Sound.NullPlayer;

/**
 * @author jacob
 * 
 * SemiMon SemiMon combines all the advanced rules from Beta-, Gamma-, and
 * DeltaMon. That is, SemiMon has:
 * 
 * Near-real backgammon move validation. (from BetaMon) Random dice rolls. (from
 * GammaMon) Realistic winning strategy. (from DeltaMon)
 * 
 * 
 */
public class SemimonFactory implements MonFactory {

    private boolean sound = false;

    public SemimonFactory(boolean sound) {
        this.sound = sound;
    }

    public SemimonFactory() {
    }

    public DieStrategy createDieStrategy() {
        return new GammamonDieStrategy();
    }

    public MoveStrategy createMoveStrategy() {
        return new BetamonMoveStrategy();
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
