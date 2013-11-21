package binpacking;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import common.Algorithm;
import common.ProblemInputDataException;
import common.Solution;
import common.Utils;


/**
 * @author thomas
 *
 *	Next-Fit Decreasing implementation for BPP problems.
 *
 */
public class NFD implements Algorithm<BPP> {

	@Override
	public Solution<BPP> solve(BPP ins) throws ProblemInputDataException {
		// Sort desc. (OFF-LINE ALG)
		int[] sortedItemSizes = ArrayUtils.clone(ins.getItemSizes());
		Utils.sortDesc(sortedItemSizes);
		System.out.println(Arrays.toString(sortedItemSizes));

		//Worst case : 1 bin per item.
		List<List<Integer>> sol = new ArrayList<>();

		for (int i = 0; i < sortedItemSizes.length; i++) {
			final int itemSize = sortedItemSizes[i];
			boolean itemPut = false;
			if (itemSize > ins.getBinSize()) throw new ProblemInputDataException("Item exceeds bin size!");
			
			for (List<Integer> bin : sol) {
				if (Utils.sum(bin) + itemSize <= ins.getBinSize()){
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


		return new BPPSol(sol);
	}

}
