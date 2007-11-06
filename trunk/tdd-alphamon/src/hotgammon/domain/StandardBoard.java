/**
 * 
 */
package hotgammon.domain;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author jacob
 *
 */
public class StandardBoard implements Board {


    private ArrayList<Location> fields;
    private TreeMap<Location,Integer> counts;
    private TreeMap<Location,Color> colors;

    public StandardBoard()
    {
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
        return fields.iterator();
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
        fields = new ArrayList();
        fields.add(Location.B_BAR);
        fields.add(Location.R1);
        fields.add(Location.R2);
        fields.add(Location.R3);
        fields.add(Location.R4);
        fields.add(Location.R5);
        fields.add(Location.R6);
        fields.add(Location.R7);
        fields.add(Location.R8);
        fields.add(Location.R9);
        fields.add(Location.R10);
        fields.add(Location.R11);
        fields.add(Location.R12);
        fields.add(Location.B12);
        fields.add(Location.B11);
        fields.add(Location.B10);
        fields.add(Location.B9);
        fields.add(Location.B8);
        fields.add(Location.B7);
        fields.add(Location.B6);
        fields.add(Location.B5);
        fields.add(Location.B4);
        fields.add(Location.B3);
        fields.add(Location.B2);
        fields.add(Location.B1);
        fields.add(Location.R_BAR);
        fields.add(Location.B_BEAR_OFF);
        fields.add(Location.R_BEAR_OFF);
        counts = new TreeMap<Location,Integer>();
        for(Location l : this)
            counts.put(l,0);
        counts.put(Location.B1,2);
        counts.put(Location.R1,2);
        counts.put(Location.B6,5);
        counts.put(Location.R6,5);
        counts.put(Location.B8,3);
        counts.put(Location.R8,3);
        counts.put(Location.B12,5);
        counts.put(Location.R12,5);
        colors = new TreeMap<Location,Color>();
        for(Location l : this)
            colors.put(l,Color.NONE);
        colors.put(Location.B1,Color.RED);
        colors.put(Location.R1,Color.BLACK);
        colors.put(Location.B6,Color.BLACK);
        colors.put(Location.R6,Color.RED);
        colors.put(Location.B8,Color.BLACK);
        colors.put(Location.R8,Color.RED);
        colors.put(Location.B12,Color.RED);
        colors.put(Location.R12,Color.BLACK);

    }
}
