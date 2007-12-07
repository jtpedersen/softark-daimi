package hotgammon.AI;


import hotgammon.domain.Board;
import hotgammon.domain.Color;
import hotgammon.domain.Game;
import hotgammon.domain.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimpleBoardState implements BoardState {

    private BoardState parent = null;
    private Color player;
    private List<BoardState> validBoardStates;
    private int[] dieValuesLeft;
    private Board board;
    private AIMoveStrategy ms;
    private int moveDie;
    private List<GameMove> sequence;

    public SimpleBoardState(int[] dieValuesLeft, List<GameMove> sequence,
            Color player, Board board, BoardState parent) {
        this.parent = parent;
        this.dieValuesLeft = dieValuesLeft;
        this.sequence = sequence;
        this.player = player;
        this.board = board;
        this.ms = new AIMoveStrategyAdapter(player);//new AIBackgammonMoveStrategy(player);
        CreateValidStates();
    }

    /**
     * use this to get "the ball roling"
     * 
     * @param dieValuesLeft
     * @param player
     * @param board
     */
    public SimpleBoardState(int[] dieValuesLeft, Color player, Board board) {
        this(dieValuesLeft, null, player, board, null);
    }

    public SimpleBoardState(int[] dieValuesLeft, Color player, Game game) {
        this(dieValuesLeft, null, player, SimpleBoardState.copyBoard(game),
                null);
    }

    public SimpleBoardState(int[] dvl, List<GameMove> sequence, Board board,
            BoardState parent) {
        this(dvl, sequence, parent.getPlayer(), board, parent);
    }

    private void CreateValidStates() {

        if (dieValuesLeft.length == 0) {
            addValidState(this);
            return;
        }

        boolean movesExhausted = true;

        for (Location from : board) {
            if (board.getColor(from) == player && board.getCount(from) > 0) {
                for (Location to : board) {
                    int move = ms.isValidMove(board, dieValuesLeft, from, to);
                    if (move > 0) {
                        createState(from, to, move);
                        movesExhausted = false;
                        // Helpers.showDice(dieValuesLeft);
                        // Helpers.showLocationCount(board, player);
                        // System.out.println(player + " can move from " + from
                        // + " to " + to + " using a " + move + " die");
                    }
                }
            }
        }
        if (movesExhausted) {
            addValidState(this);
            System.out.println("whouuops no more moves using this dicesequence");
        }
    }

    private void createState(Location from, Location to, int move) {
        // System.out.println(player + " can move from " + from + " to " + to
        // + " using a " + move + " die");
        Board b = copyBoard();
        b.move(from, to);
        int[] dvl = removeDie(move);
        List<GameMove> seq = new ArrayList<GameMove>();
        if (sequence != null)
            seq.addAll(sequence);

        seq.add(new GameMove(from, to));

        new SimpleBoardState(dvl, seq, b, this);
    }

    private int[] removeDie(int move) {
        int[] dvl = new int[dieValuesLeft.length - 1];
        int idx = 0;
        boolean removed = false;
        for (int d : dieValuesLeft) {
            if (!removed && d == move) {
                removed = true;
            } else {
                dvl[idx++] = d;
            }
        }
        return dvl;
    }

    private Board copyBoard() {
        List<BoardConfiguration> bc = new ArrayList<BoardConfiguration>();
        for (Location location : board) {
            int count = board.getCount(location);
            if (count > 0) {
                bc.add(new BoardConfiguration(location,
                        board.getColor(location), count));
            }
        }

        return new FixedBoardSetup(bc);
    }

    private static Board copyBoard(Game game) {
        List<BoardConfiguration> bc = new ArrayList<BoardConfiguration>();
        Iterator<Location> l = game.boardIterator();
        while (l.hasNext()) {
            Location location = (Location) l.next();
            int count = game.getCount(location);
            if (count > 0) {
                bc.add(new BoardConfiguration(location,
                        game.getColor(location), count));
            }
        }

        return new FixedBoardSetup(bc);
    }

    public void addValidState(BoardState bs) {

        if (parent != null) {
            parent.addValidState(bs);
        } else {
            if (validBoardStates == null)
                validBoardStates = new ArrayList<BoardState>();
            validBoardStates.add(bs);
        }

    }

    public Board getBoard() {
        return board;
    }

    public Color getPlayer() {
        return player;
    }

    public int getMove() {
        return moveDie;
    }

    public List<BoardState> getValidStates() {
        return validBoardStates;
    }

    public List<GameMove> getGameMoves() {
        return sequence;
    }

    public String toString() {
        String tmp = "";
        for (GameMove gm : sequence)
            tmp += gm + "\n";
        return getClass().getName() + " can move this way \n" + tmp;
    }

}
