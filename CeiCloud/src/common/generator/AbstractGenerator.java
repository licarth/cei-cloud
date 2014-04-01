package common.generator;

import common.problem.IInstance;
import common.problem.IProblem;

public abstract class AbstractGenerator<P extends IProblem, I extends IInstance<? extends P>> implements IGenerator<P, I> {
	
	/**
	 * The e this generator creates instances for. e.g. Bin packing with bin capacity = 10.
	 */
	private final P p;
	
	public AbstractGenerator(P problem) {
		this.p = problem;
	}

	public P getProblem() {
		return p;
	}
	
}
