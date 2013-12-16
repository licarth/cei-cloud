package common.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.problem.IInstance;
import common.problem.Instance;
import common.problem.IProblem;
import common.problem.InputDataException;

/**
 * IGenerator that creates random instances from a random seed.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class AbstractRandomGenerator<P extends IProblem, I extends IInstance<? extends P>> extends RandomGenerator<P,I> {

	public AbstractRandomGenerator(P problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	public static final long SEED = 4378578463L;
//	public static final long SEED = 2378578463L;

	private Random random = new Random(SEED);
	
	public List<I> generateInstances(int n) throws InputDataException {
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

}
