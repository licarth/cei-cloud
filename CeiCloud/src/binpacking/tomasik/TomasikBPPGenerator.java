package binpacking.tomasik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import common.Utils;
import common.generator.AbstractRandomGenerator;
import common.generator.OptimalRandomGenerator;
import common.problem.IInstance;
import common.problem.InputDataException;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.gens.NumItemsFixedBPPGenerator;

public class TomasikBPPGenerator extends OptimalRandomGenerator<BPP, OptimalKnownBPPInstance> {

	/**
	 * Nombre de boites utilisées.
	 */
	private int binCount; 

	public TomasikBPPGenerator(BPP problem, int binCount) {
		super(problem);
		this.binCount = binCount;
	}

	@Override
	public OptimalKnownBPPInstance generateInstance() throws InputDataException {

		//		IInstance<BPP> instance = new BPPInstance(problem, itemSizes)

		List<Integer> itemSizes = new ArrayList<Integer>();

		List<List<Integer>> bins = createBins();

		fill:
			while (true) {
				//ON insere des objets dans des boites online. Avec NF. On vise 100 boites utilisées.
				final int itemSize = nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize());
				Iterator<List<Integer>> it = bins.iterator();
				while (it.hasNext()) {
					List<Integer> bin = it.next();
					if (itemSize > getProblem().getBinSize()) throw new InputDataException("Item exceeds bin size!");

					if (Utils.sum(bin) + itemSize <= getProblem().getBinSize()){
						bin.add(itemSize);
						itemSizes.add(itemSize);
						break;
					}
					else { //If it doesn't fit
						if (!it.hasNext()){ //We are at the last bin
							break fill; //Stops filling bins.
						}
						//					else continue;
					}

				}
			}
		
		// Fill bins that are not full
		for (List<Integer> bin : bins) {
			int spaceLeft = getProblem().getBinSize() - Utils.sum(bin);
			if (spaceLeft > 0){
				bin.add(spaceLeft);
				itemSizes.add(spaceLeft);
			}
		}
		
		//Shuffle items.
		Collections.shuffle(itemSizes, getRandom());
		
		OptimalKnownBPPInstance inst = new OptimalKnownBPPInstance(getProblem(), Utils.fromIntegersToItems(itemSizes), binCount);
		return inst;

	}

	private List<List<Integer>> createBins() {
		ArrayList<List<Integer>> bins = new ArrayList<List<Integer>>(binCount);
		for (int i = 0; i < binCount; i++) {
			bins.add(new ArrayList<Integer>());
		}
		return bins;

	}

	@Override
	public List<OptimalKnownBPPInstance> generateInstances(int n) throws InputDataException {
		List<OptimalKnownBPPInstance> instances = new ArrayList<OptimalKnownBPPInstance>(n);
		for (int i = 1; i <= n; i++) {
			instances.add((OptimalKnownBPPInstance) generateInstance());
		}
		return instances;
	}

}
