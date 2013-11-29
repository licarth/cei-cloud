package common;

public abstract class AbstractSolution<I extends Instance> implements Solution<I> {
	
	protected Algorithm<I> sourceAlgorithm;
	
	public AbstractSolution(Algorithm<I> sourceAlgorithm) {
		this.sourceAlgorithm = sourceAlgorithm;
	}
	
	@Override
	public String toString() {
		return String.format("%s, Cost : %s", sourceAlgorithm.getClass().getSimpleName(), getCost());
	}
}
