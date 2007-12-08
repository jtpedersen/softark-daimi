package hotgammon.AI;

import hotgammon.domain.Color;
import hotgammon.domain.Game;

import java.util.Comparator;

public interface AIFactory {
    public AI getAI();
    public Game getGame();
    public Color getPlayer();
    public Comparator<Info> getComparator();
}   
