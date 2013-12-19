package common.solution;

import common.algorithm.IAlgorithm;
import common.problem.IInstance;
import common.problem.IOptimalCostAwareInstance;
import common.problem.IProblem;

public abstract class AbstractSolution<P extends IProblem, I extends IInstance<? extends P>> implements Solution<P,I> {
	
	protected IAlgorithm<P, I> sourceAlgorithm;
	
	private I instance;
	
	public AbstractSolution(IAlgorithm<P,I> sourceAlgorithm, I instance) {
		this.sourceAlgorithm = sourceAlgorithm;
		this.instance = instance;
	}
	
	@Override
	public String toString() {
		return String.format("%s, Cost : %s", sourceAlgorithm.getClass().getSimpleName(), getCost());
	}

	@Override
	public I getInstance() {
		return instance;
	}
	
	@Override
	public float getErrorRatio() throws OptimalCostNotKnownException{
		if (instance instanceof IOptimalCostAwareInstance){
			return (float) getCost() / (float) ((IOptimalCostAwareInstance) instance).getTotalCost();
		}
		else throw new OptimalCostNotKnownException("This instance is not OptimalCost-aware.");
	}
	
}
