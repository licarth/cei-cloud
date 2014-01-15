package binpacking;

import java.util.List;

import VSCIFP.algs.Item;

import common.algorithm.IAlgorithm;
import common.solution.AbstractSolution;

public class BPPSol extends AbstractSolution<BPP, BPPInstance>{
	private List<List<Item>> itemsInBins;

	BPPInstance instance;
	
	public BPPSol(List<List<Item>> itemsInBins, IAlgorithm<BPP, BPPInstance> sourceAlgorithm, BPPInstance instance) {
		super(sourceAlgorithm, instance);
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
	
	public List<List<Item>> getItemsInBins() {
		return itemsInBins;
	}
	
}
