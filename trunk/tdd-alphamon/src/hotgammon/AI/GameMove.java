package hotgammon.AI;

import hotgammon.domain.Location;

public class GameMove {
    public Location to, from;

    public GameMove(Location from, Location to) {
        this.from = from;
        this.to = to;
    }
    
    public String toString() {
        return "a move from " + from + " to " + to;
    }
}
