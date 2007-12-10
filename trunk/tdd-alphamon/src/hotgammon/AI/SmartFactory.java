package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Comparator;

public class SmartFactory implements AIFactory {

    private Game game;
    private Color player;
    private AI ai;

    public SmartFactory(Game game, Color player) {
        this.game = game;
        this.player = player;
    }

    public AI getAI() {
        if (ai == null)
            ai = new BasicAI(this);
        return ai;
    }

    public Game getGame() {
        return game;
    }

    public Color getPlayer() {
        return player;
    }

    public Comparator<BoardInformation> getComparator() {
        return new Comparator<BoardInformation>() {
            public int compare(BoardInformation bo1, BoardInformation bo2) {
                InfoMania o1 = (InfoMania) bo1;
                InfoMania o2 = (InfoMania) bo2;
                int tmp = 0;
                // get them to bearOff
                tmp = o1.inBearOff - o2.inBearOff;

                // play safe
                if (tmp == 0)
                    tmp = o2.unsafe - o1.unsafe;
                // try to kill
                if (tmp == 0)
                    tmp = o1.blots - o2.blots;
                // use as many eyes as possible
                if (tmp == 0)
                    tmp = o1.moves - o2.moves;
                // get them from the other end first
                if (tmp == 0)
                    tmp = o2.distancesScore - o1.distancesScore;
                // try to get home
                if (tmp == 0)
                    tmp = o1.atHome - o2.atHome;
             

                return tmp;
            }

        };
    }

    public BoardInformation getBoardInformation(BoardState bs) {
        return new InfoMania(bs);
    }

}
