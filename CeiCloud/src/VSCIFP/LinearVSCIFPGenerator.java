package VSCIFP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import VSCIFP.gens.VSCIFPGenerator;
import common.problem.ProblemInputDataException;

public class LinearVSCIFPGenerator extends VSCIFPGenerator{

	public LinearVSCIFPGenerator(VSCIFP problem) {
		super(problem);
	}

	@Override
	public List<VSCIFPInstance> generateInstances(int n)
			throws ProblemInputDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VSCIFPInstance generateInstance() throws ProblemInputDataException {

		VSCIFPInstance i = new VSCIFPInstance(getProblem());

		//For the largest bin, capacity = cost = maxBinCapacity
		i.binTypes.add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
		//For other bins, 
		while (i.binTypes.size() < getProblem().getTypesOfBinCount()) { // TODO Can last a while !
			int n = nextInt(1, getProblem().getMaxBinCapacity() - 1); // uniform number in [1, maxBinCapacity - 1].
			i.binTypes.add(new BinType(n, n)); //Linear cost
		}

		//On ouvre une bin de chaque type.
		for (BinType type : i.binTypes) {
			i.openBins.add(new Bin(type));
		}

		Bin binToCheck = null;
		Bin binToAdd = null;
		ArrayList<Bin> binsToClose = new ArrayList<Bin>();

		//On remplit les sacs avec des objets jusqu'à ce que le prix du packing vale getMaxPackingCost().
		fill:
			while(i.totalCost < getMaxPackingCost()){
				//Carry on...
				//On insere des objets dans des boites online. Avec NF. On vise 100 boites utilisées.
				final int item = nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize());

				//If a bin is full, change it.
				if (binToCheck != null){
					if (binToCheck.isFull()) {
						i.closeBin(binToCheck);
						i.openBins.add(new Bin(binToCheck.getType())); //Add another empty bin of the same type
					}
					binToCheck = null;
				}

				//Close bins marked as closed.
				for (Bin bin : binsToClose) {
						i.closeBin(bin);
				}

				//Let's fill those bins...
				for (Bin bin : i.openBins) {
					if (bin.fits(item)) {
						i.addItemToBin(bin, item);
						binToCheck = bin;
						continue fill;
					}
				}

				//oops, no bin found for this item, then create a new bin of the best type and close the other one.
				BinType bestBinType = getSmallestBinTypeWhereItemFits(i.binTypes, item);
				//Look for open bin of that type to close it.
				for (Bin bin : i.openBins) {
					if (bin.getType().equals(bestBinType)){
						binsToClose.add(bin);
						binToAdd = new Bin(bin.getType());
						try {
							i.addItemToBin(binToAdd, item);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				//Add new bin if needed
				if (binToAdd != null) {
					i.openBins.add(binToAdd);
					binToAdd = null;
				}

			}
		//Fill procedure terminated, close open bins.
		for (Bin bin : i.openBins) {
			Integer itSize = bin.close();
			i.itemsPut.add(itSize);
			i.totalCost += itSize;
			i.bins.add(bin);
		}
		System.out.println(i.bins);
		
		return i;
	}

	private BinType getSmallestBinTypeWhereItemFits(Set<BinType> binTypes, Integer item){
		BinType bestBinType = null;
		for (BinType binType : binTypes) {
			if (binType.fitsIfEmpty(item)) {
				if (bestBinType == null) bestBinType = binType;
				else if (binType.capacity < bestBinType.capacity) bestBinType = binType;
			}
		}
		return bestBinType;
	}

}
