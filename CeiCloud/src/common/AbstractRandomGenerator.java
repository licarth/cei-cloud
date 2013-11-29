package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractRandomGenerator<P extends Problem> implements RandomGenerator<P> {

	public static final long SEED = 4378578463L;
	
	/**
	 * The problem this generator creates instances for. e.g. Bin packing with bin capacity = 10.
	 */
	protected final P problem;
	
	public AbstractRandomGenerator(P problem) {
		this.problem = problem;
	}
	
	public abstract List<? extends Instance<P>> generateInstances(int n);

	public Random getRandom() {
		return new Random(getSeed());
	}
	
	public long getSeed() {
		return SEED;
	}

}
