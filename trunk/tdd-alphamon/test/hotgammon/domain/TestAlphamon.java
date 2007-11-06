package hotgammon.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for Alphamon game.
    Author: (c) Henrik B;rbak Christensen 2007
*/

public class TestAlphamon {

  private Game game;

  @Test
  public void testSomething() {
    assertTrue( true );
  }

  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestAlphamon.class);
  } 
}
