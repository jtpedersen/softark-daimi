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

}
