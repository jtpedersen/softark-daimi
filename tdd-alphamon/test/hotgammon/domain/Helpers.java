package hotgammon.domain;

import java.util.Iterator;

public class Helpers {

    public static void showLocationCount(Game game, Color c) {
        Iterator<Location> it = game.boardIterator();
        while (it.hasNext()) {
            Location location = (Location) it.next();
            if (game.getColor(location) == c)
                System.out.println(location + " has " + game.getCount(location)
                        + " " + c + " checkers");
        }
    }

    public static String atos(int[] arr) {
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out += arr[i] + "_";
        }
        return out;
    }

    public static void showDice(int[] diceValuesLeft) {
        System.out.println("dice :");
        for (int i : diceValuesLeft)
            System.out.print(i + "\t");
        System.out.println();
    }

    public static void showLocationCount(Board board, Color player) {
        for (Location location : board)
            if (board.getColor(location) == player)
                System.out.println(location + " has "
                        + board.getCount(location) + " " + player + " checkers");
    }

    public static String showBoard(Game game) {
        String tmp = " **show board** \n";
        Iterator<Location> it = game.boardIterator();
        while (it.hasNext()) {
            Location location = (Location) it.next();
            tmp += location + " has " + game.getCount(location) + " "
                    + game.getColor(location) + " checkers \n";
        }

        return tmp;
    }

    public static String showBoardForDebug(Game game) {

        // new BoardConfiguration(Location.R1,Color.BLACK, 2),
        String tmp = " **show board** \n";
        Iterator<Location> it = game.boardIterator();
        while (it.hasNext()) {
            Location location = (Location) it.next();
            tmp += "new BoardConfiguration(Location." + location + ", Color."
                    + game.getColor(location) + ", " + game.getCount(location)
                    + "),\n";
        }

        return tmp;
    }

    public static String getDiceString(int[] diceValuesLeft) {
        String tmp = "dice :";
        for (int i : diceValuesLeft)
            tmp += i + "\t";

        return tmp;
    }

}
