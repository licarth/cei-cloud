package VSCIFP.gens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import VSCIFP.Bin;
import VSCIFP.BinType;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import VSCIFP.algs.Item;

import common.Utils;
import common.VizUtils;
import common.problem.InputDataException;

public class LinearVSCIFPGenerator extends VSCIFPGenerator{

	public LinearVSCIFPGenerator(VSCIFP problem) {
		super(problem);
	}

	private int instanceCount = 0;

	@Override
	public VSCIFPInstance generateInstance() throws InputDataException {
		instanceCount++;
		//USES FIRSTFIT FOR NOW !
		VSCIFPInstance i = new VSCIFPInstance(getProblem());
		i.setOptimalSolution(new VSCIFPSolution(null, i));
		
		
		//For the largest bin, capacity = cost = maxBinCapacity
		i.binTypes.add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
		//For other bins, 
		while (i.binTypes.size() < getProblem().getTypesOfBinCount()) { // TODO Can last a while !
			int n = nextInt(1, getProblem().getMaxBinCapacity() - 1); // uniform number in [1, maxBinCapacity - 1].
			i.binTypes.add(new BinType(n, n)); //Linear cost
		}
		
		//On ouvre une bin de chaque type.
		List<Bin> openBins = new ArrayList<>();
		for (BinType type : i.binTypes) {
			openBins.add(new Bin(type));
		}
		i.getOptimalSolution().setOpenBins(openBins);
		
		System.out.println(i.binTypes);

		//Cycling bounded Iterator used for openBins
		Iterator<Bin> openBinsIt = Utils.oneCycleIt(i.getOptimalSolution().getOpenBins(), 0);

		//On remplit les sacs avec des objets jusqu'à ce que le prix du packing vale getMaxPackingCost().
		fill:
			while(i.getOptimalSolution().getCost() < getMaxPackingCost()){
				//Carry on...
				//On insere des objets dans des boites online. Avec NF. On vise 100 boites utilisées.
				final Item item = new Item(nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize()));

				//Let's fill those bins...
				while (openBinsIt.hasNext()) { //Always the case (cycling), but ok...
					Bin bin = (Bin) openBinsIt.next();
					if (bin.fits(item)) {
						i.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, item);
						//If full, close it:
						if (bin.isFull())
						{
							i.getOptimalSolution().addClosedBin(bin.copyToNewBin()); //Clears current bin. add a copy of it to the closed bins list.
						}
						//Reinitialize iterator to same position+1. Attention! indexOf is ok because openbins is small !
						openBinsIt = Utils.oneCycleIt(i.getOptimalSolution().getOpenBins(), i.getOptimalSolution().getOpenBins().indexOf(bin)+1);
						continue fill;
					}
				}

				//oops, no bin found for this item, then close & flush bin of bestBinType.
				BinType bestBinType = getSmallestBinTypeWhereItemFits(i.binTypes, item);
				//Look for open bin of that type to close it.
				openBinsIt = i.getOptimalSolution().getOpenBins().iterator();	//Reset Iterator to a normal it.
				closeBin:
					while (openBinsIt.hasNext()){
						Bin bin = openBinsIt.next();
						if (bin.getType().equals(bestBinType)){
							//Fill it and close it
							int spaceLeft = bin.getSpaceLeft();
							if (!bin.isEmpty()) {
								if (!bin.isFull()){
									//Fill it with an item.
									i.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, new Item(spaceLeft));
									//Bin is full
									i.getOptimalSolution().addClosedBin(bin.copyToNewBin()); //Clears current bin. adds a copy of it to the closed bins list.
								}
							}
							//Reinitialize iterator to same position+1. Attention! indexOf is ok because openbins is small !
							openBinsIt = Utils.oneCycleIt(i.getOptimalSolution().getOpenBins(), i.getOptimalSolution().getOpenBins().indexOf(bin)+1);
							continue fill;	//We added the new needed bin and put the item in it.
						}
					}
				//Throw exception
				throw new InputDataException("Code should not be reached");
			}
		//Fill procedure terminated, close (open & non-empty) bins.
		for (Bin bin : i.getOptimalSolution().getOpenBins()) {
			if (!bin.isEmpty()) {
				if (!bin.isFull()){
					//Fill it with items.
					while (bin.fits(new Item(getProblem().getItemMaxSize()))){
						i.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, new Item(nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize())));
					}
					if (!bin.isFull()){
						int spaceLeft = bin.getSpaceLeft();
						i.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, new Item(spaceLeft));
					}
				}
				i.getOptimalSolution().getBins().add(bin);
				bin.close();
			}
		}

		System.out.println(i);
		i.displayBinTypeRepartition(instanceCount);
		VizUtils.drawHistogramItemsRepartition(i.getItemSizes(), getProblem().getItemMinSize(), getProblem().getItemMaxSize(), "", "-"+instanceCount);
		return i;
	}
	
	
	private BinType getSmallestBinTypeWhereItemFits(Set<BinType> binTypes, Item item){
		BinType bestBinType = null;
		for (BinType binType : binTypes) {
			if (binType.fitsIfEmpty(item)) {
				if (bestBinType == null) bestBinType = binType;
				else if (binType.getCapacity() < bestBinType.getCapacity()) bestBinType = binType;
			}
		}
		return bestBinType;
	}

}
