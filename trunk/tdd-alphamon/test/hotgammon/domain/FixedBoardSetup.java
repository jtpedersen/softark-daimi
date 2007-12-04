package hotgammon.domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;

public class FixedBoardSetup implements Board {
    
    private TreeMap<Location,Integer> counts;
    private TreeMap<Location,Color> colors;
    private BoardConfiguration[] config;

    public FixedBoardSetup(BoardConfiguration[] config)   {
        this.config = config;
        this.reset();
    }
    /* (non-Javadoc)
     * @see hotgammon.domain.Board#getColor(hotgammon.domain.Location)
     */
    public Color getColor(Location location) {
        return colors.get(location);
    }

    /* (non-Javadoc)
     * @see hotgammon.domain.Board#getCount(hotgammon.domain.Location)
     */
    public int getCount(Location location) {

        return counts.get(location);
    }

    /* (non-Javadoc)
     * @see hotgammon.domain.Board#iterator()
     */
    public Iterator<Location> iterator() {
        return Arrays.asList(Location.values()).iterator(); // meget nemmere end at adde dem manuelt
    }

    /* (non-Javadoc)
     * @see hotgammon.domain.Board#move(hotgammon.domain.Location, hotgammon.domain.Location)
     */
    public void move(Location from, Location to) {
        counts.put(from,getCount(from)-1);
        counts.put(to,getCount(to)+1);
        colors.put(to,getColor(from));
        if(getCount(from) == 0)
            colors.put(from,Color.NONE);
    }

    /* (non-Javadoc)
     * @see hotgammon.domain.Board#reset()
     */
    public void reset() {
        
        counts = new TreeMap<Location,Integer>();
        for(Location l : this)
            counts.put(l,0);
        
        colors = new TreeMap<Location,Color>();
        for(Location l : this)
            colors.put(l,Color.NONE);
        
        for(BoardConfiguration b: config) {
            counts.put(b.place, b.count);
            colors.put(b.place, b.c);
        }

    }
}