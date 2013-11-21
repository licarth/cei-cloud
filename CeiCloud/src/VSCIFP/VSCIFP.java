package VSCIFP;

import static common.Utils.*;

import java.util.List;
import java.util.Set;

import common.Problem;
import common.ProblemInputDataException;

public class VSCIFP implements Problem{
	
	/**
	 * Number of splits allowed per item
	 */
	public int maxNumSplits;	//D
	/**
	 * Cost limit of the packing.
	 */
	public int costLimit;		//e
	
	/**
	 *	Bin Types (all different)
	 */
	private Set<BinType> binTypes;
	
	public VSCIFP(Set<BinType> binTypes, int maxNumSplits,int costLimit) throws ProblemInputDataException {
		super();
		this.binTypes = binTypes;
		this.maxNumSplits = maxNumSplits;
		this.costLimit = costLimit;
		checkProblemInput();
	}
	
	public VSCIFP(Set<BinType> binTypes, int maxNumSplits) {
		super();
		this.maxNumSplits = maxNumSplits;
		this.costLimit = it
	}

	public int[] itemSizes;	//L	
	public int[] binTypesCapacities;	//B
	public int[] binCosts;	//C

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
	
}
