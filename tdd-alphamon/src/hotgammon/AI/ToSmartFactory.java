package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Comparator;

public class ToSmartFactory implements AIFactory {

    private Game game;
    private Color player;
    private AI ai;

    public ToSmartFactory(Game game, Color player) {
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
                AlotInfo o1 = (AlotInfo) bo1;
                AlotInfo o2 = (AlotInfo) bo2;
                int tmp = 0;

                if (o1.takeHomeMode && o2.takeHomeMode) {
                    
                    // get them to bearOff
                    tmp = o1.inBearOff - o2.inBearOff;
                    if (tmp != 0)
                        return tmp;
                    
                    // get them all homewards
                    tmp = o1.highOwn - o2.highOwn;
                    if (tmp != 0)
                        return tmp;
                }
                
                // play safe
                tmp = o2.unsafe - o1.unsafe;
                if (tmp != 0)
                    return tmp;

                
                // get them to bearOff
                tmp = o1.inBearOff - o2.inBearOff;
                if (tmp != 0)
                    return tmp;

                // spread em out
                tmp = o1.pipCount - o2.pipCount;
                if (tmp != 0)
                    return tmp;

                // try to kill
                tmp = o1.blots - o2.blots;
                if (tmp != 0)
                    return tmp;


                



                // try to get home
                tmp = o1.atHome - o2.atHome;
                if (tmp != 0)
                    return tmp;
                
                // get them from the other end first
                tmp = o2.distancesScore - o1.distancesScore;


                if (tmp != 0)
                    return tmp;

                // use as many eyes as possible
                tmp = o1.moves - o2.moves;

                return tmp;
            }

        };
    }

    public BoardInformation getBoardInformation(BoardState bs) {
        return new AlotInfo(bs);
    }

}
