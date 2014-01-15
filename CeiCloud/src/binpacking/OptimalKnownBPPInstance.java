package binpacking;

import java.util.List;

import VSCIFP.algs.Item;

import common.problem.IOptimalCostAwareInstance;
import common.problem.InputDataException;
import common.solution.FakeSolution;
import common.solution.ISolution;

public class OptimalKnownBPPInstance extends BPPInstance implements IOptimalCostAwareInstance {

	private int optimalCost;
	
	public OptimalKnownBPPInstance(BPP problem, List<Item> items, int optimalCost)
			throws InputDataException {
		super(problem, items);
		this.setOptimalCost(optimalCost);
	}

	@Override
	public String toStringDetailed() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("totalCost=%s, ", getTotalCost()));
		sb.append(super.toStringDetailed());
		return sb.toString();
	}

	public int getTotalCost() {
		return optimalCost;
	}

	public void setOptimalCost(int optimalCost) {
		this.optimalCost = optimalCost;
	}

	@Override
	public ISolution getOptimalSolution() {
		return new FakeSolution(this, optimalCost);
	}
}
