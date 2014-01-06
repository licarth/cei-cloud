package VSCIFP.algs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import VSCIFP.Bin;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;

import common.Utils;
import common.algorithm.Algorithm;
import common.problem.InputDataException;

/**
 * FF-style, tries biggest bins first.
 * 
 * @author thomas
 *
 */
public class CIFFD extends Algorithm<VSCIFP, VSCIFPInstance>{

	private Set<Item> T1 = new TreeSet<>();
	private Set<Item> T1p = new TreeSet<>();
	private Set<Item> T1m = new TreeSet<>();

	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins)
			throws InputDataException {
		VSCIFPSolution sol = new VSCIFPSolution(this, ins);
		
		List<Bin> bins = new ArrayList<Bin>();
		// Sort desc. (OFF-LINE ALG)
		List<Item> sortedItems = Utils.cloneItemList(ins.getItems());
		Utils.sortDesc(sortedItems);
//		int toPut = Utils.sumItems(sortedItems); //Counts how much we still have to put
		
		int e = 0;
		//Divide items in 2 categories : items bigger than problem's max
		//binSize (T1+) and items = or smaller (T1). Items in T1+ are cut 
		//at size b1. The remaining of each item is stored in another set
		//called T1-.
		
		for (Item i : sortedItems) {
			route(i);
		}
		return null;
	}
	
	/**
	 * Local method to test and put i in the right set.
	 * 
	 * @param i
	 */
	private void route(Item i) {
		if (i.getSize() > getInstance().getProblem().getMaxBinCapacity()){
			i.cut(getInstance().getProblem().getMaxBinCapacity());
			for (Item c : i.getChildren()) {
				route(c);
			}
		} else {
			T1.add(i);
		}
	}
	
	private void split() {
		
	}
	
}