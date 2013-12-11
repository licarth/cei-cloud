package binpacking.tomasik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import common.Utils;
import common.generator.AbstractRandomGenerator;
import common.generator.OptimalGenerator;
import common.problem.IInstance;
import common.problem.ProblemInputDataException;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.gens.NumItemsFixedBPPGenerator;

public class TomasikBPPGenerator extends OptimalGenerator<BPP, OptimalKnownBPPInstance> {

	/**
	 * Nombre de boites utilisées.
	 */
	private int binCount; 

	public TomasikBPPGenerator(BPP problem, int binCount) {
		super(problem);
		this.binCount = binCount;
	}

	@Override
	public OptimalKnownBPPInstance generateInstance() throws ProblemInputDataException {

		//		IInstance<BPP> instance = new BPPInstance(problem, itemSizes)

		List<Integer> itemSizes = new ArrayList<Integer>();

		List<List<Integer>> bins = createBins();

		fill:
			while (true) {
				//ON insere des objets dans des boites online. Avec NF. On vise 100 boites utilisées.
				final int itemSize = nextInt(getProblem().getItemMinSize(), getProblem().getItemMaxSize());
				for (List<Integer> bin : bins) {
					if (itemSize > getProblem().getBinSize()) throw new ProblemInputDataException("Item exceeds bin size!");

					if (Utils.sum(bin) + itemSize <= getProblem().getBinSize()){
						bin.add(itemSize);
						itemSizes.add(itemSize);
						break;
					}
					else { //If it doesn't fit
						if (bins.indexOf(bin) == bins.size()-1){ //We are at the last bin
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
		
//		System.out.println(itemSizes);
		//Shuffle item sizes.
		Collections.shuffle(itemSizes, getRandom());
		
		OptimalKnownBPPInstance inst = new OptimalKnownBPPInstance(getProblem(), itemSizes, binCount);
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
	public List<? extends BPPInstance> generateInstances(int n) throws ProblemInputDataException {
		List<OptimalKnownBPPInstance> instances = new ArrayList<OptimalKnownBPPInstance>(n);
		for (int i = 1; i <= n; i++) {
			instances.add((OptimalKnownBPPInstance) generateInstance());
		}
		return instances;
	}

}
