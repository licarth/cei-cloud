package common.solution;

import common.problem.IInstance;
import common.problem.IProblem;

/**
 * A solution about which the only thing we know is the cost.
 * 
 * @author thomas
 *
 * @param <P>
 * @param <I>
 */
public class FakeSolution<P extends IProblem, I extends IInstance<? extends P>> extends AbstractSolution<P, I> {

	private int cost;
	
	public FakeSolution(I ins, int cost) {
		super(null, ins);
		this.cost = cost;
	}
	
	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public I getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getErrorRatio() throws OptimalCostNotKnownException {
		// TODO Auto-generated method stub
		return 0;
	}

}
