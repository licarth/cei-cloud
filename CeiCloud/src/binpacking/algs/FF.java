package binpacking.algs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import binpacking.BPP;
import binpacking.BPPSol;
import common.Algorithm;
import common.ProblemInputDataException;
import common.Solution;
import common.Utils;


/**
 * @author thomas
 *
 *	First-Fit Decreasing implementation for BPP problems.
 *
 */
public class FF implements Algorithm<BPP> {

	@Override
	public Solution<BPP,FF> solve(BPP ins) throws ProblemInputDataException {

		//Worst case : 1 bin per item.
		List<List<Integer>> sol = new ArrayList<>();

		for (int i = 0; i < ins.getItemSizes().length; i++) {
			final int itemSize = ins.getItemSizes()[i];
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


		return new BPPSol(sol, this);
	}

}
