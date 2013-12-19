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
public class FFD extends BPPAlgorithm {

	@Override
	public ISolution<BPP, BPPInstance> solve(BPPInstance ins) throws InputDataException {
		// Sort desc. (OFF-LINE ALG)
		List<Integer> sortedItemSizes = Utils.cloneIntList(ins.getItemSizes());
		Utils.sortDesc(sortedItemSizes);
//		System.out.println(Arrays.toString(sortedItemSizes));

		//Worst case : 1 bin per item.
		List<List<Integer>> sol = new ArrayList<>();

		for (int i = 0; i < sortedItemSizes.size(); i++) {
			final int itemSize = sortedItemSizes.get(i);
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
