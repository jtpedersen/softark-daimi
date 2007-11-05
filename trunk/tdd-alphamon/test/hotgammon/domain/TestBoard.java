package hotgammon.domain;

import java.util.Iterator;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for Backgammon Board.
    Author: (c) Henrik Brbak Christensen 2007
*/

public class TestBoard {

  private Board board;
  
  @Before public void setUp() { 
  }

  @Test
  public void testIterator() {
    Board b = new BoardImpl();
    Iterator<Location> it = b.iterator();
    assertNotNull(it);
  }

  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestBoard.class);
  } 
}
