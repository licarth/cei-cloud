package common.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.problem.IInstance;
import common.problem.Instance;
import common.problem.IProblem;
import common.problem.ProblemInputDataException;

/**
 * IGenerator that creates random instances from a random seed.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class AbstractRandomGenerator<P extends IProblem, I extends IInstance<? extends P>> implements RandomGenerator<P,I> {

	public static final long SEED = 4378578463L;
//	public static final long SEED = 2378578463L;

	private Random random = new Random(SEED);
	
	/**
	 * The e this generator creates instances for. e.g. Bin packing with bin capacity = 10.
	 */
	private final P e;
	
	public AbstractRandomGenerator(P problem) {
		this.e = problem;
	}
	
	public List<I> generateInstances(int n) throws ProblemInputDataException {
		List<I> l = new ArrayList<I>();
		for (int i = 0; i < n; i++) {
			l.add(generateInstance());
		}
		return l;
	};

	public Random getRandom() {
		return random;
	}
	
	public Random resetRandom() {
		random.setSeed(SEED);
		return random;
	}
	
	public long getSeed() {
		return SEED;
	}
	
	/**
	 * @param min
	 * @param max
	 * @return a uniformly distributed number in [min,max] both included.
	 */
	public int nextInt(int min, int max){
		return getRandom().nextInt(max-min+1)+min;
	}

	public P getProblem() {
		return e;
	}

}
