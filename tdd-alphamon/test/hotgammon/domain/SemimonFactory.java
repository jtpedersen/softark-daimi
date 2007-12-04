/**
 * 
 */
package hotgammon.domain;

/**
 * @author jacob
 * 
 * SemiMon SemiMon combines all the advanced rules from Beta-, Gamma-, and
 * DeltaMon. That is, SemiMon has:
 * 
 * Near-real backgammon move validation. (from BetaMon) Random dice rolls. (from
 * GammaMon) Realistic winning strategy. (from DeltaMon)
 * 
 * 
 */
public class SemimonFactory implements MonFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotgammon.domain.MonFactory#createDieStrategy()
	 */
	public DieStrategy createDieStrategy() {
		return new GammamonDieStrategy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotgammon.domain.MonFactory#createMoveStrategy()
	 */
	public MoveStrategy createMoveStrategy() {
		return new BetamonMoveStrategy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hotgammon.domain.MonFactory#createWinnerStrategy()
	 */
	public WinnerStrategy createWinnerStrategy() {
		return new AllInInnerFieldWinnerStrategy();
	}

}
