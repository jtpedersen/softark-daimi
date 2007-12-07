package hotgammon.AI;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { TestBoardState.class, TestAIvsAI.class, TestAlphaAI.class })
/**
 * Suite to run all test cases. Author: (c) Henrik B;rbak Christensen 2007
 */
public class TestAllAI {
    /**
     * This wrapper is only required for running the old JUnit 3.8 graphical
     * user interface on new JUnit 4 test cases
     */
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(TestAllAI.class);
    }

}
