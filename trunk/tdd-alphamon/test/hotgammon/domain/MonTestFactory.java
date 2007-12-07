package hotgammon.domain;

import Sound.MonSoundEffect;

public class MonTestFactory implements MonFactory {

    private BoardConfiguration[] config;
    private int[] dies;
    private MonFactory baseFactory;

    public MonTestFactory(MonFactory factory, BoardConfiguration[] config, int[] dies) {
        this.baseFactory = factory;
        this.config = config;
        this.dies = dies;
    }
    
    public MonTestFactory(BoardConfiguration[] config, int[] dies) {
        this(new RealBackgammonFactory(), config, dies);
    }

    public DieStrategy createDieStrategy() {
        return (dies!= null) ? new SequenceDieStrategy(dies) : baseFactory.createDieStrategy();
    }

    public Board createBoard() {
        return (config!=null) ? new FixedBoardSetup(config): baseFactory.createBoard();
    }

    public MonSoundEffect createEffectPlayer() {
        return baseFactory.createEffectPlayer();
    }

    public MoveStrategy createMoveStrategy() {
        return baseFactory.createMoveStrategy();
    }

    public WinnerStrategy createWinnerStrategy() {
        return baseFactory.createWinnerStrategy();
    }

}
