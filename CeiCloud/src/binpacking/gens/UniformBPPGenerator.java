package binpacking.gens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import binpacking.BPP;
import binpacking.BPPInstance;
import common.Utils;
import common.problem.InputDataException;

public class UniformBPPGenerator extends NumItemsFixedBPPGenerator {
	
	public UniformBPPGenerator(BPP problem, int numberOfItems) {
		super(problem, numberOfItems);
	}

	Random r = reset();
	
	@Override
	public BPPInstance generateInstance() {
		BPPInstance inst = null;
		List<Integer> itemSizes = new ArrayList<Integer>(getNumberOfItems());
		for (int i = 0; i < getNumberOfItems(); i++) {
			itemSizes.add(getRandom().nextInt(getProblem().getItemMaxSize()) + 1);
		}
		
		try {
			inst = new BPPInstance(getProblem(), Utils.fromIntegersToItems(itemSizes));
		} catch (InputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inst;
	}

	@Override
	public long getSeed() {
		return SEED;
	}

	@Override
	public List<BPPInstance> generateInstances(int n) {
		List<BPPInstance> l = new ArrayList<BPPInstance>(n);
		for (int i = 0; i < n; i++) {
			l.add(generateInstance());
		}
		return l;
	}

}
