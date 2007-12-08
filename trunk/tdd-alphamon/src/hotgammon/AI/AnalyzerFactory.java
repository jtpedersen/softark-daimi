package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Comparator;

public class AnalyzerFactory implements AIFactory {

    private Game game;
    private Color player;

    public AnalyzerFactory(Game game, Color player) {
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
                int tmp = o2.unsafe - o1.unsafe;
                if(tmp == 0)
                    tmp = o1.blots - o2.blots;
                if (tmp == 0)
                    tmp = o1.moves - o2.moves;
                return tmp;
            }

        };
    }

}
