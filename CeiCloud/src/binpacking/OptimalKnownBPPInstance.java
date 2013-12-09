package binpacking;

import java.util.List;

import common.problem.ProblemInputDataException;

public class OptimalKnownBPPInstance extends BPPInstance {

	private int optimalCost;
	
	public OptimalKnownBPPInstance(BPP problem, List<Integer> itemSizes, int optimalCost)
			throws ProblemInputDataException {
		super(problem, itemSizes);
		this.setOptimalCost(optimalCost);
	}


	@Override
	public String toStringDetailed() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("optimalCost=%s, ", getOptimalCost()));
		sb.append(super.toStringDetailed());
		return sb.toString();
	}

	public int getOptimalCost() {
		return optimalCost;
	}

	public void setOptimalCost(int optimalCost) {
		this.optimalCost = optimalCost;
	}
}
