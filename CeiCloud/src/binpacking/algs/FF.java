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
public class FF extends BPPAlgorithm {

	@Override
	public Solution<BPP, BPPInstance> solve(BPPInstance ins) throws InputDataException {

		//Worst case : 1 bin per item.
		List<List<Integer>> sol = new ArrayList<>();

		for (int i = 0; i < ins.getItemSizes().size(); i++) {
			final int itemSize = ins.getItemSizes().get(i);
			boolean itemPut = false;
			if (itemSize > ins.getProblem().getBinSize()) throw new InputDataException("Item exceeds bin size!");
			
			for (List<Integer> bin : sol) {
				if (Utils.sum(bin) + itemSize <= ins.getProblem().getBinSize()){
					bin.add(itemSize);
					itemPut = true;
					break;
				}
			}
			if (!itemPut) {
				//Item has not been put in any bin. We need to create a new bin with it.
				sol.add(new ArrayList<Integer>(){{add(itemSize);}});
				itemPut = true;
			}
		}

		return new BPPSol(sol, this, ins);
	}

}
