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
public class NFD extends BPPAlgorithm {

	@Override
	public ISolution<BPP, BPPInstance> solve(BPPInstance ins) throws InputDataException {
	
		List<Item> sortedItems = Utils.cloneItemList(ins.getItems());
		Utils.sortDesc(sortedItems);
		
		//Worst case : 1 bin per item.
		List<List<Item>> sol = new ArrayList<>();
		//Create first bin.
		sol.add(new ArrayList<Item>());
		List<Item> currentBin = sol.get(0);
		
		for (int i = 0; i < sortedItems.size(); i++) {
			final Item item = sortedItems.get(i);
			if (item.getSize() > ins.getProblem().getBinSize()) throw new InputDataException("Item exceeds bin size!");
			
			if (Utils.sum(fromItemsToIntegers(currentBin)) + item.getSize() <= ins.getProblem().getBinSize()){
				currentBin.add(item);
			}
			else {
				currentBin = new ArrayList<Item>();
				currentBin.add(item);
				sol.add(currentBin);
			}
		}

		return new BPPSol(sol, this, ins);
	}

}
