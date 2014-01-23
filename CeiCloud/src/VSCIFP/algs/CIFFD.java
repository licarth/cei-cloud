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
 * FF-style, tries biggest bins first. NOT THREAD-SAFE: Create one new instance of CIFFD per solver.
 * 
 * @author thomas
 *
 */
public class CIFFD extends Algorithm<VSCIFP, VSCIFPInstance>{

	private List<VSCIFPSolution> solutions = new LinkedList<>();
	
	private List<Set<SolutionItem>> T = new LinkedList<Set<SolutionItem>>();
	private List<Set<SolutionItem>> Tp = new LinkedList<Set<SolutionItem>>();
	private List<Set<SolutionItem>> Tm = new LinkedList<Set<SolutionItem>>();

	private VSCIFPSolution sol;

	public CIFFD() {
		T = new LinkedList<Set<SolutionItem>>();
		Tp = new LinkedList<Set<SolutionItem>>();
		Tm = new LinkedList<Set<SolutionItem>>();
	}
	
	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins)
			throws InputDataException {
		sol = new VSCIFPSolution(this, ins);
		T = new LinkedList<Set<SolutionItem>>();
		Tp = new LinkedList<Set<SolutionItem>>();
		Tm = new LinkedList<Set<SolutionItem>>();
//		System.out.println(ins.getItems());

		//T, Tp, Tm initialisation
		for (int i =0; i < ins.getBinTypes().size(); i++) {
			T.add(new HashSet<SolutionItem>());
			Tp.add(new HashSet<SolutionItem>());
			Tm.add(new HashSet<SolutionItem>());
		}

		int costMin;

		List<Bin> bins = new ArrayList<Bin>();
		// Sort desc. (OFF-LINE ALG)
		Utils.sortDesc(sol.getItems());
//		List<SolutionItem> sortedItems = Utils.cloneItemList(ins.getItems());

		//		int toPut = Utils.sumItems(sortedItems); //Counts how much we still have to put

		int e = 0;
		//Divide items in 2 categories : items bigger than problem's max
		//binSize (T1+) and items = or smaller (T1). Items in T1+ are cut 
		//at size b1. The remaining of each item is stored in another set
		//called T1-.
		try {

			divide(sol.getItems(), ins.getProblem().getMaxBinCapacity(), 0);

			//Pack items in T1p, put the remainders in T1m
			packBig(ins, 0);
//			System.out.println(Tm.get(0));
			//Pack items from T1 and T1m with BPP
			packSmallToBPP(ins,0);
//			System.out.println(sol);
//			System.out.println(sol.getBins());
			solutions.add(sol.clone());
			System.out.println("j =0, " + sol);
			for (int j = 1; j < ins.getBinTypes().size(); j++) {
				//Repack less filled bin.
				unpackAndDivide(ins,j); //includes call to divide()
				packBig(ins, j);
				packSmallToBPP(ins, j);
//				System.out.println(sol);
//				System.out.println(sol.getBins());
				solutions.add(sol.clone());
				System.out.println("j ="+j +", "+ sol);
			}

		} catch (ItemCutException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(solutions);

		//TODO return best solution.
		
		return sol;
	}

	private void unpackAndDivide(VSCIFPInstance ins, int j) throws Exception {
		Bin binToUnpack = getLessFilledBinOfType(ins.getBinTypeByIndex(j-1), sol.getBins());
		List<Item> itemsToRepack = sol.unpack(binToUnpack);
		divide(itemsToRepack, ins.getBinTypeByIndex(j).getCapacity(), j);
	}

	private void packSmallToBPP(VSCIFPInstance ins, int j) throws Exception {
		int capacity = ins.getBinTypeByIndex(j).getCapacity();
		NF nf = new NF();
		BPP bpp = new BPP(capacity);
		BinType currentBinType;
		currentBinType = ins.getBinTypeOfCapacity(capacity);
		//		System.out.println(T.get(j));
		ArrayList<SolutionItem> union = new ArrayList<SolutionItem>();
		union.addAll(Utils.union(T.get(j),Tm.get(j)));
//				System.out.println(union);
		BPPSol s = (BPPSol) nf.solve(new BPPInstance(bpp, union));
		//		System.out.println(s.getItemsInBins());

		//Re-Use this solution and pack according to it in bins of size b1
		for (List<Item> l : s.getItemsInBins()) {
			Bin b = new Bin(currentBinType);
			for (Item item : l) {
				sol.addItemToBin(b, item);
			}
			sol.addClosedBin(b);
		}
	}

	private void packBig(VSCIFPInstance ins, int j) throws ItemCutException  {
		for (SolutionItem i : Tp.get(j)) {
			List<SolutionItem> cutChildren = null;
				BinType bt = ins.getBinTypeByIndex(j);
				cutChildren = i.cut(ins.getProblem().getMaxNumSplits(), bt.getCapacity());
				Bin newBin = new Bin(bt);
				sol.addItemToBin(newBin, cutChildren.get(0));
				//Bin is full
				sol.addClosedBin(newBin);
				//Put the remainder into T1m
				Tm.get(j).add(cutChildren.get(1));

		}		
	}

	private Bin getLessFilledBinOfType(BinType binType, Collection<Bin> bins) {
		int minFill = -1;
		Bin minFilledBin = null;
		for (Bin bin : bins) {
			//Compare bin types
			if (!bin.getType().equals(binType)) continue;
			if (minFill == -1 || bin.getFillCount() < minFill) {
				minFilledBin = bin;
				minFill = bin.getFillCount();
			}
		}
		return minFilledBin;
	}

	/**
	 * Takes items and split them if possible (if not already splitted max number of times allowed.
	 * Put them in Tj and Tjp.
	 * @param items
	 * @param binCapacity
	 */
	private void divide(Collection<? extends Item> items, int binCapacity, int j) {
		for (Item i : items) {
			if (i.getSize() <= binCapacity){
				T.get(j).add((SolutionItem)i);
			} else {
				Tp.get(j).add((SolutionItem)i);
			}
		}
	}

	public List<VSCIFPSolution> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<VSCIFPSolution> solutions) {
		this.solutions = solutions;
	}
}