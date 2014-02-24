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
import common.Utils;
import common.algorithm.OfflineAlgorithm;
import common.problem.InputDataException;
import common.solution.OptimalCostNotKnownException;

/**
 * FF-style, tries biggest bins first. NOT THREAD-SAFE: Create one new instance of CIFFD per solver.
 * 
 * @author thomas
 *
 */
public class NFLWithBinTypeOptimization extends OfflineAlgorithm<VSCIFP, VSCIFPInstance>{

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
		try {
			if (sol.getErrorRatio() < 1.0){
				System.out.println(sol.getErrorRatio());
				System.out.println(sol.getCost());
				System.out.println(sol.getBins());
				int total = 0;
				int size = 0;
				for (Bin bin : sol.getBins()) {
					total += bin.getCost();
					size += bin.getFillCount();
				}
				System.out.println("Total cost : " + total);
				System.out.println("Total size : " + size);

				
				VSCIFPSolution optSol = ins.getOptimalSolution();
				System.out.println(optSol.getCost());
				System.out.println(optSol.getBins());
				total = 0;
				size = 0;
				for (Bin bin : optSol.getBins()) {
					total += bin.getCost();
					size += bin.getFillCount();
				}
				System.out.println("Total opt cost : " + total);
				System.out.println("Total opt size : " + size);
				
			}
		} catch (OptimalCostNotKnownException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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