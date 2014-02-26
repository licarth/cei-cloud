package VSCIFP.gens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import VSCIFP.Bin;
import VSCIFP.BinType;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import VSCIFP.algs.Item;
import VSCIFP.algs.SolutionItem;

public class MonotoneWithoutCutsVSCIFPGenerator extends VSCIFPGenerator{

	//	private final static Logger LOGGER = Logger.getLogger(LinearVSCIFPGenerator.class);
	public MonotoneWithoutCutsVSCIFPGenerator(VSCIFP problem, int maxPackingCost) {
		super(problem, maxPackingCost);
	}

	private int instanceCount = 0;
	private VSCIFPInstance ins;

	@Override
	public VSCIFPInstance generateInstance() throws Exception {
		instanceCount++;
		//USES FIRSTFIT FOR NOW !

		ins = new VSCIFPInstance(getProblem());
		ins.setOptimalSolution(new VSCIFPSolution(null, ins));

		initializeBinTypes();

//		initializeBins();

		//		System.out.println(ins);

		fillBins();
		closeNonEmptyBins();

		pasteItemsTogether();

		copyItemsList();
		
		Collections.shuffle(ins.getItems());
		//		System.out.println("Optimal solution item leaves : "+ins.getOptimalSolution().getItemLeaves());
		//		System.out.println("Instance getItems() after paste : "+ins.getItems());

		//				System.out.println(ins);
		//		ins.displayBinTypeRepartition(instanceCount);
		//		VizUtils.drawHistogramItemsRepartition(ins.getItemSizes(), getProblem().getItemMinSize(), getProblem().getItemMaxSize(), "", "-"+instanceCount);
		return ins;
	}


	private void copyItemsList() {
		//		System.out.println(ins.getItems());
		for (SolutionItem item : ins.getOptimalSolution().getItems()) {
			Item rootItem = new Item(item.getSize());
			item.setParent(rootItem);
			ins.getItems().add(rootItem);
			//			System.out.println(ins.getItems());
		}

		//		for (SolutionItem item : ins.getOptimalSolution().getItems()) {
		//			if (item.getParent() == null) System.out.println("Parent null");
		//		}
	}


	/**
	 * Paste items together in Items list.
	 * We take items 1 by 1.
	 */
	private void pasteItemsTogether() {

		int numberOfItemsToPaste = ins.getOptimalSolution().getItems().size();
		int maxNumOfTries = ins.getOptimalSolution().getItems().size() * 10;
		int numberOfItemsPasted = 0;
		int numOfTries = 0;

		while (numberOfItemsPasted < numberOfItemsToPaste && numOfTries < maxNumOfTries) {
			numOfTries++;
			int i1 = getRandom().nextInt(ins.getOptimalSolution().getItems().size());
			SolutionItem item1 = ins.getOptimalSolution().getItems().get(i1);

			if (item1.getTimesCut() < ins.getProblem().getMaxNumSplits()) {
				item1 = ins.getOptimalSolution().getItems().remove(i1);
				SolutionItem parent = null;
				SolutionItem item2 = null;

//				if (item1.getSize() > (item1.getTimesCut() + 1) * 100){
//					System.out.println(item1.getTimesCut() + " times, size = "+item1.getSize());
//				}
				
				for (SolutionItem i : ins.getOptimalSolution().getItems()) {
					item2 = i;

					if (item2.getTimesCut() + item1.getTimesCut() < ins.getProblem().getMaxNumSplits()) {

						if (item2.getSize() > (item2.getTimesCut() + 1) * 100){
							System.out.println("item2 "+item2.getTimesCut() + " times, size = "+item2.getSize());
						}
						
						//						System.out.println(item1.getTimesCut() +" et " +item2.getTimesCut());

						parent = new SolutionItem(item1.getSize()+item2.getSize());
						//First update times cut counter !
						parent.setTimesCut(item2.getTimesCut() + item1.getTimesCut() + 1);
						parent.setFilsG(item1);
						parent.setFilsD(item2);
						item1.setParent(parent);
						item2.setParent(parent);

//						if (parent.getSize() > (parent.getTimesCut() + 1) * 100){
//							System.out.println(parent.getTimesCut() + " times, size = "+parent.getSize());
//						}
						
						ins.getOptimalSolution().getItems().add(parent);
						numberOfItemsPasted++;
						
						break;
					}
				}
				if (parent == null) {
					ins.getOptimalSolution().getItems().add(item1);
					//					ins.getItems().add(item2);
				} else {
					ins.getOptimalSolution().getItems().remove(item2);
				}
			} else {
				//It item1 not chosen...
				//				ins.getItems().add(item1);
			}
		}
		//		System.out.println("Number of items pasted : "+numberOfItemsPasted);
	}


	/**
	 * When the fill procedure terminated, close (open & non-empty) bins.
	 */
	private void closeNonEmptyBins() {
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
	}


	/**
	 * Core method of algorithm designed to fill the bins.
	 * 
	 * Fills big bins cutting items to make them fit. Then adapt last bin to best bin size among available bin sizes.
	 * @throws Exception 
	 */
	private void fillBins() throws Exception {

		Bin currentOpenBin = new Bin(ins.getBinTypeOfMaxCapacity());

		//On remplit les sacs avec des objets jusqu'à ce que le prix du packing vale getMaxPackingCost().
		fill:
			while(ins.getOptimalSolution().getCost() < getMaxPackingCost()){
				//On insere des objets dans des boites online. Avec NF. On vise 100 boites utilisées.
				final SolutionItem item = new SolutionItem(nextInt(getProblem().getItemMinSize(), getProblem().getMaxBinCapacity()));

				//First time an item does not fit into last bin, we stop.
				if (ins.getOptimalSolution().getCost() >= getMaxPackingCost() && currentOpenBin.getSpaceLeft() < item.getSize()){
					break fill;
				}

				if (currentOpenBin.getSpaceLeft() >= item.getSize()){
					//	Item fits.
					ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(currentOpenBin, item);
					if (currentOpenBin.isFull()){
						ins.getOptimalSolution().addClosedBin(currentOpenBin);
						currentOpenBin = new Bin(ins.getBinTypeOfMaxCapacity());
					}

				} else {
//					if ((item.getSize() - currentOpenBin.getSpaceLeft()) < (ins.getProblem().getMaxNumSplits())*currentOpenBin.getType().getCapacity()) {

					//	Then fill current bin, close it, and open new bins for remaining pieces.
//					List<SolutionItem> children = item.cut(ins.getProblem().getMaxNumSplits(), currentOpenBin.getSpaceLeft());
					ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(currentOpenBin, new SolutionItem(currentOpenBin.getSpaceLeft()));

//					if (currentOpenBin.isFull()){
						ins.getOptimalSolution().addClosedBin(currentOpenBin);
						currentOpenBin = new Bin(ins.getBinTypeOfMaxCapacity());
//					}
						
						ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(currentOpenBin, item);
						if (currentOpenBin.isFull()){
							ins.getOptimalSolution().addClosedBin(currentOpenBin);
							currentOpenBin = new Bin(ins.getBinTypeOfMaxCapacity());
						}
					
						

//				} else {
					//	Item has to be put in a brand new bin. Close this one, open a new one.
//					throw new Exception("Code should never be reached.");
				}
			}

		//Last bin has to be closed.
		ins.getOptimalSolution().addItemToBinForOptimalSolutionBuilding(currentOpenBin, new SolutionItem(currentOpenBin.getSpaceLeft()));
		ins.getOptimalSolution().addClosedBin(currentOpenBin);
	}


	/**
	 * Initializes a bin of each type.
	 */
	private void initializeBins() {
		//On ouvre une bin de chaque type.
		List<Bin> openBins = new ArrayList<>();
		for (BinType type : ins.binTypes) {
			openBins.add(new Bin(type));
		}
		ins.getOptimalSolution().setOpenBins(openBins);		
	}

	/**
	 * Declare bin types.
	 */
	private void initializeBinTypes() {
		//For the largest bin, capacity = cost = binCapacity
		ins.binTypes.add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
		//For other bins, 
		while (ins.binTypes.size() < getProblem().getTypesOfBinCount()) { // TODO Can last a while !
			int n = nextInt(1, getProblem().getMaxBinCapacity() - 1); // uniform number in [1, binCapacity - 1].
			ins.binTypes.add(new BinType(n, n)); //Linear cost
		}

		//		On fixe maintenant les coûts de chaque bin. La plus grande coute sa capacité, puis on fixe les autres coûts en descendant.
		//		A descending iterator on binTypes collection
		Iterator<BinType> descIt = ins.binTypes.descendingIterator();
		BinType prevBin = descIt.next();	// Biggest bin.
		while (descIt.hasNext()) {
			BinType currBin = descIt.next();
			//			System.out.println("["+prevBin.unitCost() * currBin.getCapacity()+","+prevBin.getCost()+"[");
			currBin.setCost((int) Math.floor(nextDouble(prevBin.unitCost() * currBin.getCapacity(), prevBin.getCost())));
			//			System.out.println(currBin);
			prevBin = currBin;
		}
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
