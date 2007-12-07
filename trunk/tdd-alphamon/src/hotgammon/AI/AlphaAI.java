package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.List;

public class AlphaAI implements AI {

    Game game;
    private Color player;
    private MoveSelector moveSelector;

    public AlphaAI(Color player, Game game) {
        this.game = game;
        this.player = player;
        this.moveSelector = new SimpleMoveSelector();
    }

    public void move() {
        System.out.println("move");
        BoardState bs = new SimpleBoardState(game.diceValuesLeft(), player,
                game);
        // System.out.println(bs.getValidStates().size());
        // for(BoardState b: bs.getValidStates())
        // System.out.println(b);

        List<GameMove> gm = moveSelector.getMoves(bs.getValidStates());

        for (GameMove g : gm) {
            System.out.println(g);
            game.move(g.from, g.to);
        }

    }

}
