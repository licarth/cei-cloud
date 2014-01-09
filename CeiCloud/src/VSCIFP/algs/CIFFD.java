package VSCIFP.algs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import VSCIFP.Bin;
import VSCIFP.ItemCutException;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPItemsInstance;
import binpacking.algs.NF;
import common.Utils;
import common.algorithm.Algorithm;
import common.problem.InputDataException;
import common.solution.ISolution;

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
			if (i.getSize() <= ins.getProblem().getMaxBinCapacity()){
				T1.add(i);
			} else {
				T1p.add(i);
//				i.cut(getInstance().getProblem().getMaxBinCapacity());
			}
			//			route(i);
		}
		
		for (Item i : T1p) {
			try {
				i.cut(ins.getProblem().getMaxBinCapacity());
				Bin newBin = new Bin(ins.getBinTypeOfCapacity(ins.getProblem().getMaxBinCapacity()));
				sol.getOpenBins().add(newBin);
				sol.addItemToBin(newBin, i.getChildren().get(0));
				//Bin is full
				sol.addClosedBin(newBin);
			} catch (ItemCutException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			i.getChildren().get(0);
			
		}
		
		//Pack items from T1 and T1m with BPP
		NF nf = new NF();
		BPP bpp = new BPP(ins.getProblem().getMaxBinCapacity());
		System.out.println(T1);
		ISolution<BPP, BPPInstance> s = nf.solve(new BPPItemsInstance(bpp, Utils.fromItemsToIntegers(T1)));
		
		System.out.println(s);
		
		return null;
	}
}