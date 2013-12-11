package common.solution;

import common.algorithm.IAlgorithm;
import common.problem.IInstance;
import common.problem.IProblem;

public abstract class AbstractSolution<P extends IProblem, I extends IInstance<P>> implements Solution<P,I> {
	
	protected IAlgorithm<P, I> sourceAlgorithm;
	
	public AbstractSolution(IAlgorithm<P,I> sourceAlgorithm) {
		this.sourceAlgorithm = sourceAlgorithm;
	}
	
	@Override
	public String toString() {
		return String.format("%s, Cost : %s", sourceAlgorithm.getClass().getSimpleName(), getCost());
	}
}
