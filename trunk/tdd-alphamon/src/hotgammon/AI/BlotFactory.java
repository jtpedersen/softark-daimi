package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Comparator;

public class BlotFactory implements AIFactory {

    private Game game;
    private Color player;

    public BlotFactory(Game game, Color player) {
        this.game = game;
        this.player = player;
    }

    public AI getAI() {
        return new BasicAI(this);
    }

    public Game getGame() {
        return game;
    }

    public Color getPlayer() {
        return player;
    }

    public Comparator<Info> getComparator() {
        return new Comparator<Info>() {
            public int compare(Info o1, Info o2) {
                return o1.blots-o2.blots;
            }

        };
    }

}
