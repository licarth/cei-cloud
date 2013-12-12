package VSCIFP;

import static common.Utils.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.problem.IOptimalCostAware;
import common.problem.Instance;
import common.problem.ProblemInputDataException;

/**
 * @author thomas
 *
 */
public class VSCIFPInstance extends Instance<VSCIFP> implements IOptimalCostAware {

	/**
	 *	Bin Types (all different)
	 */
	public Set<BinType> binTypes = new HashSet<>();

	//Generates bin types
	int optimalCost = 0;
	/**
	 * Closed bins.
	 */
	public List<Bin> bins = new ArrayList<>();
	public List<Bin> openBins = new ArrayList<>();
	public List<Integer> itemsPut = new ArrayList<Integer>();

	public VSCIFPInstance(VSCIFP problem) throws ProblemInputDataException {
		super(problem);
		checkProblemInput();
	}

	@Override
	public void checkProblemInput() throws ProblemInputDataException {
		//TODO Check inputs.
		//Checks that a bigger bin is never less efficient than a smaller bin.
		List<BinType> sortedBinTypes = asSortedList(binTypes, false);
		System.out.println(sortedBinTypes);
		double eff = 0;
		for (BinType binType : sortedBinTypes) {
			if (eff > binType.efficiency()){
				throw new ProblemInputDataException("Bin types (A,B) were found such as cap(A) > cap (B) and effiency(A) < efficiency(B)");
			}
			else eff = binType.efficiency();
		}		
	}

	@Override
	public String toStringDetailed() {
		return getBins().toString();
	}

	@Override
	public int getOptimalCost() {
		return optimalCost;
	}

	public List<Bin> getBins() {
		return bins;
	}

	public void setBins(List<Bin> bins) {
		this.bins = bins;
	}

	public void addItemToBin(Bin bin, int item) {
		try {
			bin.add(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		itemsPut.add(item);
		optimalCost += item;
	}

	public void setOptimalCost(int optimalCost) {
		this.optimalCost = optimalCost;
	}



}
