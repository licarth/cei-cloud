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


/**
 * @author thomas
 *
 *	First-Fit Decreasing implementation for BPP problems.
 *
 */
public class FF extends BPPAlgorithm {

	@Override
	public ISolution<BPP, BPPInstance> solve(BPPInstance ins) throws InputDataException {

		//Worst case : 1 bin per item.
		List<List<Item>> sol = new ArrayList<>();

		for (int i = 0; i < ins.getItemSizes().size(); i++) {
			final Item item = ins.getItems().get(i);
			boolean itemPut = false;
			if (item.getSize() > ins.getProblem().getBinSize()) throw new InputDataException("Item exceeds bin size!");
			
			for (List<Item> bin : sol) {
				if (Utils.sum(Utils.fromItemsToIntegers(bin)) + item.getSize() <= ins.getProblem().getBinSize()){
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
