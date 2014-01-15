package VSCIFP.algs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.Iterators;

import VSCIFP.Bin;
import VSCIFP.BinType;
import VSCIFP.ItemCutException;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;
import binpacking.algs.NF;
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

	private List<Set<Item>> T = new LinkedList<Set<Item>>();
	private List<Set<Item>> Tp = new LinkedList<Set<Item>>();
	private List<Set<Item>> Tm = new LinkedList<Set<Item>>();

	
	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins)
			throws InputDataException {
		VSCIFPSolution sol = new VSCIFPSolution(this, ins);

		//T, Tp, Tm initialisation
		for (int i =0; i < ins.getBinTypes().size(); i++) {
			T.add(new HashSet<Item>());
			Tp.add(new HashSet<Item>());
			Tm.add(new HashSet<Item>());
		}
		
		int costMin;

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

		divide(sortedItems, ins.getProblem().getMaxBinCapacity(), 0);
		
		for (Item i : Tp.get(0)) {
			try {
				i.cut(ins.getProblem().getMaxBinCapacity());
				Bin newBin = new Bin(ins.getBinTypeOfCapacity(ins.getProblem().getMaxBinCapacity()));
//				sol.getOpenBins().add(newBin);
				sol.addItemToBin(newBin, i.getChildren().get(0));
				//Bin is full
				sol.addClosedBin(newBin);
				//Put the remainder into T1m
				Tm.get(0).add(i.getChildren().get(1));
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
		try {
			int capacity = ins.getProblem().getMaxBinCapacity();
			NF nf = new NF();
			BPP bpp = new BPP(capacity);
			BinType currentBinType;
			currentBinType = ins.getBinTypeOfCapacity(capacity);
			System.out.println(T.get(0));
			ArrayList<Item> union = new ArrayList<Item>();
			union.addAll(Utils.union(T.get(0),Tm.get(0)));
			System.out.println(union);
			BPPSol s = (BPPSol) nf.solve(new BPPInstance(bpp, union));
			System.out.println(s.getItemsInBins());

			System.out.println(sol);
			//Re-Use this solution and pack according to it in bins of size b1
			for (List<Item> l : s.getItemsInBins()) {
				Bin b = new Bin(currentBinType);
				for (Item item : l) {
					sol.addItemToBin(b, item);
				}
				sol.addClosedBin(b);
			}
			System.out.println(s);
			System.out.println(sol.getBins());
			System.out.println(sol);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int j = 1; j < ins.getProblem().getTypesOfBinCount() - 1; j++) {
			getLessFilledBinOfType(Iterators.get(ins.getBinTypes().descendingIterator(), j-1), sol.getBins());
			sol.unpack(bin);
			
			divide(items, maxBinCapacity, j);
			
			
		}

		return null;
	}

	private Bin getLessFilledBinOfType(BinType binType, Collection<Bin> bins) {
		int minFill = -1;
		Bin minFilledBin = null;
		for (Bin bin : bins) {
			//Compare bin types
			if (!bin.getType().equals(binType)) continue;
			if (minFill == -1 || bin.getFillCount() < minFill) {
				minFilledBin = bin;
			}
		}
		return minFilledBin;
	}

	/**
	 * Takes items and split them if possible (if not already splitted max number of times allowed.
	 * Put them in Tj and Tjp.
	 * @param items
	 * @param maxBinCapacity
	 */
	private void divide(Collection<Item> items, int maxBinCapacity, int j) {
		for (Item i : items) {
			if (i.getSize() <= maxBinCapacity){
				T.get(j).add(i);
			} else {
				Tp.get(j).add(i);
			}
		}
	}
}