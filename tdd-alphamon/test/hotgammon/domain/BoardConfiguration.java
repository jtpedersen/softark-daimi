package hotgammon.domain;

public class BoardConfiguration {
    public Location place;
    public Color c;
    public int count;
    
    public BoardConfiguration(Location place, Color c, int count){
        this.place =place;
        this.c = c;
        this.count= count;
        
    }
}
