package hotgammon.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for Backgammon Board.
    Author: (c) Henrik Bærbak Christensen 2007
*/

public class TestBoard {

  private Board board;
  
  @Before public void setUp() {
  }

  @Test
  public void testIterator() {
    assertTrue(false);
  }

  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestBoard.class);
  } 
}
