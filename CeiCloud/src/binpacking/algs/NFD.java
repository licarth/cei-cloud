package binpacking.algs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;
import common.Utils;
import common.algorithm.IAlgorithm;
import common.problem.IInstance;
import common.problem.InputDataException;
import common.solution.Solution;


/**
 * @author thomas
 *
 *	First-Fit Decreasing implementation for BPP problems.
 *
 */
public class NFD extends BPPAlgorithm {

	@Override
	public Solution<BPP, BPPInstance> solve(BPPInstance ins) throws InputDataException {
	
		List<Integer> sortedItemSizes = Utils.cloneIntList(ins.getItemSizes());
		Utils.sortDesc(sortedItemSizes);
		
		//Worst case : 1 bin per item.
		List<List<Integer>> sol = new ArrayList<>();
		//Create first bin.
		sol.add(new ArrayList<Integer>());
		List<Integer> currentBin = sol.get(0);
		
		for (int i = 0; i < sortedItemSizes.size(); i++) {
			final int itemSize = sortedItemSizes.get(i);
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
