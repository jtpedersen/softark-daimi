package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Comparator;

public class SmartFactory implements AIFactory {

    private Game game;
    private Color player;

    public SmartFactory(Game game, Color player) {
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

    public Comparator<BoardInformation> getComparator() {
        return new Comparator<BoardInformation>() {
            public int compare(BoardInformation bo1, BoardInformation bo2) {
                InfoMania o1 = (InfoMania) bo1;
                InfoMania o2 = (InfoMania) bo2;
                int tmp;
                tmp = o1.inBearOff - o2.inBearOff; // get them to bearOff
                if (tmp == 0)
                    tmp = o2.unsafe - o1.unsafe; // play safe
                if (tmp == 0)
                    tmp = o1.blots - o2.blots; // try to kill
                if (tmp == 0)
                    tmp = o1.atHome - o2.atHome; // try to get home
                if (tmp == 0)
                    tmp = o1.moves - o2.moves; // use as many eyes as possible
                
                return tmp;
            }

        };
    }

    public BoardInformation getBoardInformation(BoardState bs) {
        return new InfoMania(bs);
    }

}
