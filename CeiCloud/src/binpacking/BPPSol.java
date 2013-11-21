package binpacking;

import java.util.List;

import common.Solution;

public class BPPSol implements Solution<BPP>{
	public List<List<Integer>> itemsInBins;

	public BPPSol(List<List<Integer>> itemsInBins) {
		super();
		this.itemsInBins = itemsInBins;
	}
	
	@Override
	public String toString() {
		return String.format("Packing is : %s\nCost : %s",itemsInBins.toString(), getCost());
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return itemsInBins.size();
	}
	
}
