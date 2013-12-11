package binpacking;

import java.util.List;

import common.algorithm.IAlgorithm;
import common.solution.AbstractSolution;
import common.solution.Solution;

public class BPPSol extends AbstractSolution<BPP, BPPInstance>{
	public List<List<Integer>> itemsInBins;

	BPPInstance instance;
	
	public BPPSol(List<List<Integer>> itemsInBins, IAlgorithm<BPP, BPPInstance> sourceAlgorithm) {
		super(sourceAlgorithm);
		this.itemsInBins = itemsInBins;
	}
	
//	@Override
//	public String toString() {
//		return String.format("%s, Cost : %s",this.getClass(), super.toString());
//	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return itemsInBins.size();
	}

	@Override
	public BPPInstance getInstance() {
		return instance;
	}
	
}
