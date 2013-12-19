package binpacking.algs;
import java.util.ArrayList;
import java.util.List;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;

import common.Utils;
import common.problem.InputDataException;
import common.solution.ISolution;


/**
 * @author thomas
 *
 *	First-Fit Decreasing implementation for BPP problems.
 *
 */
public class NF extends BPPAlgorithm {

	@Override
	public ISolution<BPP, BPPInstance> solve(BPPInstance ins) throws InputDataException {

		//Worst case : 1 bin per item.
		List<List<Integer>> sol = new ArrayList<>();
		//Create first bin.
		sol.add(new ArrayList<Integer>());
		List<Integer> currentBin = sol.get(0);
		
		for (int i = 0; i < ins.getItemSizes().size(); i++) {
			final int itemSize = ins.getItemSizes().get(i);
			if (itemSize > ins.getProblem().getBinSize()) throw new InputDataException("Item exceeds bin size!");
			
			if (Utils.sum(currentBin) + itemSize <= ins.getProblem().getBinSize()){
				currentBin.add(itemSize);
			}
			else {
				currentBin = new ArrayList<Integer>();
				currentBin.add(itemSize);
				sol.add(currentBin);
			}
		}

		return new BPPSol(sol, this, ins);
	}

}
