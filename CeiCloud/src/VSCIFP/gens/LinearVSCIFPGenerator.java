package VSCIFP.gens;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import VSCIFP.Bin;
import VSCIFP.BinType;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import VSCIFP.algs.Item;
import VSCIFP.algs.SolutionItem;

import common.Utils;
import common.problem.InputDataException;

public class LinearVSCIFPGenerator extends VSCIFPGenerator{

	public LinearVSCIFPGenerator(VSCIFP problem, int maxPackingCost) {
		super(problem, maxPackingCost);
	}

	private int instanceCount = 0;

	@Override
	public VSCIFPInstance generateInstance() throws InputDataException {
		instanceCount++;
		//USES FIRSTFIT FOR NOW !
		VSCIFPInstance ins = new VSCIFPInstance(getProblem());
		ins.setOptimalSolution(new VSCIFPSolution(null, ins));
		
		//For the largest bin, capacity = cost = maxBinCapacity
		ins.binTypes.add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
		//For other bins, 
		while (ins.binTypes.size() < getProblem().getTypesOfBinCount()) { // TODO Can last a while !
			int n = nextInt(1, getProblem().getMaxBinCapacity() - 1); // uniform number in [1, maxBinCapacity - 1].
			ins.binTypes.add(new BinType(n, n)); //Linear cost
		}
		
		//On ouvre une bin de chaque type.
		List<Bin> openBins = new ArrayList<>();
		for (BinType type : ins.binTypes) {
			openBins.add(new Bin(type));
		}
		ins.getOptimalSolution().setOpenBins(openBins);
		
		System.out.println(ins.binTypes);

		//Cycling bounded Iterator used for openBins
		Iterator<Bin> openBinsIt = Utils.oneCycleIt(ins.getOptimalSolution().getOpenBins(), 0);

		//On remplit les sacs avec des objets jusqu'à ce que le prix du packing vale getMaxPackingCost().
		fill:
			while(ins.getOptimalSolution().getCost() < getMaxPackingCost()){
				//Carry on...
				//On insere des objets dans des boites online. Avec NF. On vise 100 boites utilisées.
				final SolutionItem item = new SolutionItem(nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize()));

				//Let's fill those bins...
				while (openBinsIt.hasNext()) { //Always the case (cycling), but ok...
					Bin bin = (Bin) openBinsIt.next();
					if (bin.fits(item)) {
						ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, item);
						//If full, close it:
						if (bin.isFull())
						{
							ins.getOptimalSolution().addClosedBin(bin.copyToNewBin()); //Clears current bin. add a copy of it to the closed bins list.
						}
						//Reinitialize iterator to same position+1. Attention! indexOf is ok because openbins is small !
						openBinsIt = Utils.oneCycleIt(ins.getOptimalSolution().getOpenBins(), ins.getOptimalSolution().getOpenBins().indexOf(bin)+1);
						continue fill;
					}
				}

				//oops, no bin found for this item, then close & flush bin of bestBinType.
				BinType bestBinType = getBestBinForItem(ins, item);
				//Look for open bin of that type to close it.
				openBinsIt = ins.getOptimalSolution().getOpenBins().iterator();	//Reset Iterator to a normal it.
				closeBin:
					while (openBinsIt.hasNext()){
						Bin bin = openBinsIt.next();
						if (bin.getType().equals(bestBinType)){
							//Fill it and close it
							int spaceLeft = bin.getSpaceLeft();
							if (!bin.isEmpty()) {
								if (!bin.isFull()){
									//Fill it with an item.
									ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, new SolutionItem(spaceLeft));
									//Bin is full
									ins.getOptimalSolution().addClosedBin(bin.copyToNewBin()); //Clears current bin. adds a copy of it to the closed bins list.
								}
							}
							//Reinitialize iterator to same position+1. Attention! indexOf is ok because openbins is small !
							openBinsIt = Utils.oneCycleIt(ins.getOptimalSolution().getOpenBins(), ins.getOptimalSolution().getOpenBins().indexOf(bin)+1);
							continue fill;	//We added the new needed bin and put the item in it.
						}
					}
				//Throw exception
				throw new InputDataException("Code should not be reached");
			}
		//Fill procedure terminated, close (open & non-empty) bins.
		for (Bin bin : ins.getOptimalSolution().getOpenBins()) {
			if (!bin.isEmpty()) {
				if (!bin.isFull()){
					//Fill it with items.
					while (bin.fits(new Item(getProblem().getItemMaxSize()))){
						ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, new SolutionItem(nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize())));
					}
					if (!bin.isFull()){
						int spaceLeft = bin.getSpaceLeft();
						ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(bin, new SolutionItem(spaceLeft));
					}
				}
				ins.getOptimalSolution().getBins().add(bin);
				bin.close();
			}
		}

		System.out.println(ins);
		ins.displayBinTypeRepartition(instanceCount);
//		VizUtils.drawHistogramItemsRepartition(ins.getItemSizes(), getProblem().getItemMinSize(), getProblem().getItemMaxSize(), "", "-"+instanceCount);
		
		//Paste items together in Items list.
		int i1 = getRandom().nextInt(ins.getItems().size());
		SolutionItem item1 = (SolutionItem) ins.getItems().remove(i1);
		int i2 = getRandom().nextInt(ins.getItems().size());
		SolutionItem item2 = (SolutionItem) ins.getItems().remove(i2);
		
		SolutionItem parent = new SolutionItem(item1.getSize()+item2.getSize());
		parent.setFilsG(item1);
		parent.setFilsD(item2);
		
		ins.getItems().add(parent);
		
		return ins;
	}
	
	
	private BinType getSmallestBinTypeWhereItemFits(VSCIFPInstance ins, Item item){
		BinType bestBinType = null;
		for (BinType binType : ins.binTypes) {
			if (binType.fitsIfEmpty(item)) {
				if (bestBinType == null) bestBinType = binType;
				else if (binType.getCapacity() < bestBinType.getCapacity()) bestBinType = binType;
			}
		}
		return bestBinType;
	}
	
	private BinType getBestBinForItem(VSCIFPInstance ins, Item item){
		BinType bestBinType = getSmallestBinTypeWhereItemFits(ins, item);
		if (bestBinType != null) return bestBinType;
		else
			try {
				bestBinType = ins.getBinTypeOfCapacity(ins.getProblem().getMaxBinCapacity());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return bestBinType;
	}

}
