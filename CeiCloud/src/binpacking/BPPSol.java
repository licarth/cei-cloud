package binpacking;

import java.util.List;

import common.AbstractSolution;
import common.Algorithm;
import common.Solution;

public class BPPSol<A extends Algorithm<BPP>> extends AbstractSolution<BPP, A>{
	public List<List<Integer>> itemsInBins;

	public BPPSol(List<List<Integer>> itemsInBins, A sourceAlgorithm) {
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
	
}
