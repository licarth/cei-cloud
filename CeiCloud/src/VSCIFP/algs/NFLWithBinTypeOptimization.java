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
import binpacking.algs.NFD;
import common.Utils;
import common.algorithm.Algorithm;
import common.problem.InputDataException;

/**
 * FF-style, tries biggest bins first. NOT THREAD-SAFE: Create one new instance of CIFFD per solver.
 * 
 * @author thomas
 *
 */
public class NFLWithBinTypeOptimization extends Algorithm<VSCIFP, VSCIFPInstance>{

	private VSCIFPSolution sol;

	public NFLWithBinTypeOptimization() {
	}

	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins)
			throws InputDataException {
		sol = new VSCIFPSolution(this, ins);


		BinType maxBinType = sol.getInstance().getBinTypeOfMaxCapacity();
		int maxBinCapacity = maxBinType.getCapacity();

		List<Bin> bins = new ArrayList<Bin>();
		// Sort desc. (OFF-LINE ALG)
		Utils.sortDesc(sol.getItems());

		try {

			for (SolutionItem item : sol.getItems()) {
				cutRecursively(item);
				//				System.out.println(item.getLeaves());
			}

			BPP bpp = new BPP(maxBinCapacity);
			NFD nfd = new NFD();
			BPPInstance bppInstance = new BPPInstance(bpp, sol.getItemLeaves());

			BPPSol s = (BPPSol) nfd.solve(bppInstance);

			//Re-Use this solution and pack according to it in bins of size b1
			for (List<Item> l : s.getItemsInBins()) {
				Bin b = new Bin(maxBinType);
				for (Item item : l) {
					sol.addItemToBin(b, item);
				}
				sol.addClosedBin(b);
			}

			//Repack what can be repacked.
			int i = 0;
			for (Bin bin : sol.getBins()) {
				for (BinType newType : ins.binTypes) {
					if (bin.getFillCount() <= newType.capacity && newType.capacity < bin.getType().capacity){
						i = i+ (bin.getType().cost - newType.cost);
						sol.setTotalCost(sol.getCost() - (bin.getType().cost - newType.cost));
						bin.setType(newType);
					}
				}
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}
		return sol;
	}

	private void cutRecursively(SolutionItem item) throws ItemCutException {
		int maxBinCapacity = sol.getInstance().getBinTypeOfMaxCapacity().getCapacity();
		if (item.getSize() > maxBinCapacity){
			cutRecursively(item.cut(sol.getInstance().getProblem().getMaxNumSplits(), maxBinCapacity).get(1));
		}
	}

}