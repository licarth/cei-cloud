package binpacking.algs;
import java.util.ArrayList;
import java.util.List;

import VSCIFP.algs.Item;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;

import common.Utils;
import common.problem.InputDataException;
import common.solution.ISolution;
import static common.Utils.*;


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
		List<Item> sortedItems = Utils.cloneItemList(ins.getItems());
		Utils.sortDesc(sortedItems);
//		System.out.println(Arrays.toString(sortedItemSizes));

		//Worst case : 1 bin per item.
		List<List<Item>> sol = new ArrayList<>();

		for (int i = 0; i < sortedItems.size(); i++) {
			final Item item = sortedItems.get(i);
			boolean itemPut = false;
			if (item.getSize() > ins.getProblem().getBinSize()) throw new InputDataException("Item exceeds bin size!");
			
			for (List<Item> bin : sol) {
				if (Utils.sum(fromItemsToIntegers(bin)) + item.getSize() <= ins.getProblem().getBinSize()){
					bin.add(item);
					itemPut = true;
					break;
				}
			}
			
			if (!itemPut) {
				//Item has not been put in any bin. We need to create a new bin with it.
				sol.add(new ArrayList<Item>(){{add(item);}});
				itemPut = true;
			}
		}

		return new BPPSol(sol, this, ins);
	}

}
