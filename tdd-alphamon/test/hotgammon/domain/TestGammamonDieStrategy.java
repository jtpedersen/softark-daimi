package hotgammon.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Testcases for Alphamon game. Author: (c) Henrik B;rbak Christensen 2007
 */

public class TestGammamonDieStrategy {

    private DieStrategy ds;

    @Before
        public void setup() {
        ds = new GammamonDieStrategy();
    }
    
    @Test public void testfoo() {
        assertTrue(false);
    }

    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestAlphamon.class);
    }
}
