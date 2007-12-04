package hotgammon.domain;

import java.util.Iterator;

public class Helpers {

    public static void showLocationCount(Game game, Color c) {
        Iterator<Location> it = game.boardIterator();
        while (it.hasNext()) {
            Location location = (Location) it.next();
            if (game.getColor(location) == c)
                System.out.println(location + " " + game.getCount(location));
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
        for (int i: diceValuesLeft)
            System.out.print(i + "\t");
        System.out.println();
    }

}
