package binpacking;

import java.util.List;

import common.problem.IOptimalCostAwareInstance;
import common.problem.InputDataException;

public class OptimalKnownBPPInstance extends BPPInstance implements IOptimalCostAwareInstance {

	private int optimalCost;
	
	public OptimalKnownBPPInstance(BPP problem, List<Integer> itemSizes, int optimalCost)
			throws InputDataException {
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
