package VSCIFP.algs;

import java.util.ArrayList;
import java.util.List;

import VSCIFP.Bin;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;

import common.Utils;
import common.algorithm.Algorithm;
import common.problem.InputDataException;
import common.solution.Solution;

/**
 * FF-style, tries biggest bins first.
 * 
 * @author thomas
 *
 */
public class GloutonD extends Algorithm<VSCIFP, VSCIFPInstance>{

	@Override
	public Solution<VSCIFP, VSCIFPInstance> solve(VSCIFPInstance ins)
			throws InputDataException {
		
		List<Bin> bins = new ArrayList<Bin>();
		// Sort desc. (OFF-LINE ALG)
		List<Integer> sortedItemSizes = Utils.cloneIntList(ins.getItemSizes());
		Utils.sortDesc(sortedItemSizes);
		int toPut = Utils.sum(sortedItemSizes); //Counts how much we still have to put
		
		//On prend le premier item 
		for (Bin bin : bins) {
			if(bin.fits(item)){
				sol.a;
			}
		}
		
		
		return null;
	}
	
	private void split() {
		
	}


	
}