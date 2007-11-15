package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


/**
 * Testcases for {@link GammamonDieStrategy}
 * • Dice rolls are random as in ordinary backgammon.
• As a consequence of the above, double rolls like [3,3] are now permit-
  ted, and your production code must thus be augmented to handle the
  rule in backgammon which states that double rolls allow four moves
  to be made.
• Also, as the player to start is determined by the first roll of the dice,
  you will have to test that the game acts appropriately in the situation
  where the first dice roll leads to black starting, leads to red starting,
  or leads to a draw.

 */

public class TestGammamonDieStrategy {

    private DieStrategy ds;

    @Before
    public void setup() {
        ds = new GammamonDieStrategy();
    }
    
    @Test
    public void allValuesArePossible() {

        int[] val = new int[6];
        boolean done = false;
        int tries = 0;
        while(!done) {
            int[] d = ds.throwDice();
            assertNotNull(d);
            
            for(int i: d) {
                if (i<1 || i>6)
                    fail("wrong dies");
            }
            val[d[0]-1]++;
            val[d[1]-1]++;
            done = true;
            for(int i: val) 
                if (i<1) done = false;
            
            if(++tries>100) {
                for(int i: val)
                    System.out.println(i);
                fail("didn't get alle values in 100 tries");
            }
        }
    }
    
    @Test
    public void dobbeltslag() {
        boolean done = false;
        int tries = 0;
        while(!done) {
            int[] d = ds.throwDice();
         
            if (d[0] == d[1]) {
                assertEquals(4, ds.getMoves(d).size());
                done=true;
            }
            
            if(++tries>100) 
                fail("intet dobbeltslag i 100 forsoeg");
        }
        
    }
    
 

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases 
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestGammamonDieStrategy.class);
    }
}
