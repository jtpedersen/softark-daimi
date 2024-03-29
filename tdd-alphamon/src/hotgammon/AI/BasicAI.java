package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicAI implements AI {

    private Game game;
    private Color player;
    Map<BoardInformation, List<GameMove>> overview;
    private AIFactory factory;
    private boolean showMoves;

    public BasicAI(AIFactory factory) {
        this.game = factory.getGame();
        this.player = factory.getPlayer();
        this.factory = factory;
    }

    public void move() {
        // System.out.println("move");
        BoardState bs = new SimpleBoardState(game.diceValuesLeft(), player,
                game);

        List<BoardState> states = bs.getValidStates();
        player = states.get(0).getPlayer();
        overview = new HashMap<BoardInformation, List<GameMove>>();

        for (BoardState b : states) {
            BoardInformation info = factory.getBoardInformation(b);
            overview.put(info, b.getGameMoves());
        }
        Set<BoardInformation> infoKeys = overview.keySet();

        BoardInformation bestKey = Collections.max(infoKeys,
                factory.getComparator());
        List<GameMove> gm = overview.get(bestKey);
//        System.out.println(bestKey);
        // System.out.println(bs.getValidStates().size());
        // for(BoardState b: bs.getValidStates())
        // System.out.println(b);

        for (GameMove g : gm) {
             if(showMoves)
                 System.out.println(g);
            if (!game.move(g.from, g.to)) {
                System.out.println(g + " AAAAAAATGH");
                for (GameMove gin : gm)
                    System.out.println(gin);
            }
        }

    }

    public Color getPlayer() {
        return player;
    }

    public void setShowMoves(boolean showMoves) {
        this.showMoves = showMoves;
    }

}
