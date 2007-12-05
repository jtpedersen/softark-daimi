package Sound;

import Sound.MonEffects.sound;

public class EffectPlayer implements MonSoundEffect {

    public void playChecker() {
        new MonEffects(sound.CHECKER);
    }

    public void playDie() {
        new MonEffects(sound.DIE);
    }

}
