package hotgammon.domain;

import Sound.MonSoundEffect;

public interface MonFactory {
    public DieStrategy createDieStrategy();
    public MoveStrategy createMoveStrategy();
    public WinnerStrategy createWinnerStrategy();
    public Board createBoard();
    public MonSoundEffect createEffectPlayer();
}
