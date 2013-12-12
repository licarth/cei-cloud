package VSCIFP;

import java.util.ArrayList;
import java.util.Iterator;
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

		//USES FIRSTFIT FOR NOW !
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

		//Iterator used for openBins
		Iterator<Bin> openBinsIt = i.openBins.iterator();

		//On remplit les sacs avec des objets jusqu'à ce que le prix du packing vale getMaxPackingCost().
		fill:
			while(i.optimalCost < getMaxPackingCost()){
				//Carry on...
				//On insere des objets dans des boites online. Avec NF. On vise 100 boites utilisées.
				final int item = nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize());

				//Let's fill those bins...
				while (openBinsIt.hasNext()) {
					Bin bin = (Bin) openBinsIt.next();
					if (bin.fits(item)) {
						i.addItemToBin(bin, item);
						//If full, close it:
						if (bin.isFull())
						{
							bin.close();
							i.bins.add(bin);	 //Adds the bin to bins.
							openBinsIt.remove(); //Removes the bin.
							//Replace bin !


						}
						continue fill;
					}
				}

				//oops, no bin found for this item, then create a new bin of the best type and close the other one.
				BinType bestBinType = getSmallestBinTypeWhereItemFits(i.binTypes, item);
				//Look for open bin of that type to close it.
				openBinsIt = i.openBins.iterator();	//Reset Iterator
				closeBin:
					while (openBinsIt.hasNext()){
						Bin bin = openBinsIt.next();
						if (bin.getType().equals(bestBinType)){
							bin.close();
							i.bins.add(bin);
							Bin newBin = new Bin(bin.getType());
							openBinsIt.remove();
							i.openBins.add(newBin);	//Pas certain qu'on ait le droit... NON
							openBinsIt = i.openBins.iterator();	//Reset it.
							try {
								i.addItemToBin(newBin, item);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break closeBin;	//We added the new needed bin and put the item in it.
						}
					}
			}
		//Fill procedure terminated, close open bins.
		for (Bin bin : i.openBins) {
			Integer itSize = bin.close();
			i.itemsPut.add(itSize);
			i.optimalCost += itSize;
			i.bins.add(bin);
		}

		System.out.println(i);

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
