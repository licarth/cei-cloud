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
import common.algorithm.OfflineAlgorithm;
import common.problem.InputDataException;

/**
 * 
 * 
 * @author thomas
 *
 */
public class CNFL2 extends OfflineAlgorithm<VSCIFP, VSCIFPInstance>{

	private VSCIFPSolution sol;

	public CNFL2() {
	}

	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins)
			throws InputDataException {
		sol = new VSCIFPSolution(this, ins);


		BinType maxBinType = sol.getInstance().getBinTypeOfMaxCapacity();
		int maxBinCapacity = maxBinType.getCapacity();

		List<Bin> bins = new ArrayList<Bin>();
		// Sort desc. (OFF-LINE ALG)
		//		Utils.sortDesc(sol.getItems());


		for (SolutionItem item : sol.getItems()) {
			try {
				cutRecursively(item);
				//				System.out.println(item.getLeaves());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e1);
			}
		}

		BPP bpp = new BPP(maxBinCapacity);
		NF nfd = new NF();
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

		return sol;
	}

	private void cutRecursively(SolutionItem item) throws ItemCutException {

//		int size = item.getSize() / (sol.getInstance().getProblem().maxNumSplits+1);
//		//		System.out.println(size);
//		while (item.getSize() >= 2*size && item.getSize() > 2){
//			item = item.cut(sol.getInstance().getProblem().maxNumSplits, size).get(1);
//			//			System.out.println(item.size);
//		}
		
		int originalSize = item.getSize();
		float avg = item.getSize() / (float) (sol.getInstance().getProblem().maxNumSplits+1);
		float last = 0.0f;
		
		//		System.out.println(size);
		while (last < originalSize){
//			System.out.println(last+avg);
			int cutAt = (int) (last+avg) - (int) last;
//			System.out.println(cutAt);
			if (cutAt != 0 && cutAt < item.getSize()){
				if (item.getTimesCut()  == sol.getInstance().getProblem().maxNumSplits && cutAt == item.getSize()-1){
					//do not cut
					break;
				}
				item = item.cut(sol.getInstance().getProblem().maxNumSplits, cutAt).get(1);
			}
			last += avg;
			//			System.out.println(item.size);
		}
		
		
		//		System.out.println();

		//		item.cut(sol.getInstance().getProblem().getMaxNumSplits(), maxBinCapacity)

		//		int maxBinCapacity = sol.getInstance().getBinTypeOfMaxCapacity().getCapacity();
		//		if (item.getSize() > maxBinCapacity){
		//			cutRecursively(item.cut(sol.getInstance().getProblem().getMaxNumSplits(), maxBinCapacity).get(1));
		//		}
	}

}