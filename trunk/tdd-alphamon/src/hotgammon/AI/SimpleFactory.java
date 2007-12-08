package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Comparator;

public class SimpleFactory implements AIFactory {

    private Game game;
    private Color player;

    public SimpleFactory(Game game, Color player) {
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
                Info o1 = (Info) bo1;
                Info o2 = (Info) bo2;
                return o1.moves - o2.moves;
            }

        };
    }

    public BoardInformation getBoardInformation(BoardState bs) {
        return new Info(bs);
    }

}
