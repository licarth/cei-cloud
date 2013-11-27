package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractRandomGenerator<P extends Problem> implements RandomGenerator<P> {

	public static final long SEED = 4378578463L;
	
	public List<P> generateInstances(int n) {
		List<P> l = new ArrayList<P>();
		for (int i = 0; i < n; i++) {
			l.add(generateInstance());
		}
		return l;
	}

	public Random getRandom() {
		return new Random(getSeed());
	}
	
	public long getSeed() {
		return SEED;
	}

}
