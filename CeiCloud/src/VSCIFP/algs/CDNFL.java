package VSCIFP.algs;

import java.util.ArrayList;
import java.util.List;

import VSCIFP.Bin;
import VSCIFP.BinType;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;
import binpacking.algs.NFD;

import common.algorithm.OfflineAlgorithm;
import common.problem.InputDataException;

/**
 * 
 * 
 * @author thomas
 *
 */
public class CDNFL extends OfflineAlgorithm<VSCIFP, VSCIFPInstance>{

	private VSCIFPSolution sol;

	public CDNFL() {
	}

	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins)
			throws InputDataException {
		sol = new VSCIFPSolution(this, ins);


		BinType maxBinType = sol.getInstance().getBinTypeOfMaxCapacity();
		int maxBinCapacity = maxBinType.getCapacity();

		List<Bin> bins = new ArrayList<Bin>();
		// Sort desc. (OFF-LINE ALG)
//				Utils.sortDesc(sol.getItems());


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
		NFD nfd = new NFD();
		BPPInstance bppInstance = new BPPInstance(bpp, sol.getItemLeaves());

		BPPSol s = (BPPSol) nfd.solve(bppInstance);

		//Re-Use this solution and pack according to it in bins of size b1
		for (List<Item> l : s.getItemsInBins()) {
			Bin b = new Bin(maxBinType);
			for (Item item : l) {
				sol.addItemToBin(b, item);
//				System.out.println(item);
			}
			sol.addClosedBin(b);
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