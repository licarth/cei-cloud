package common;

public abstract class AbstractSolution<P extends Problem, A extends Algorithm<P>> implements Solution<P,A> {
	
	protected A sourceAlgorithm;
	
	public AbstractSolution(A sourceAlgorithm) {
		this.sourceAlgorithm = sourceAlgorithm;
	}
	
	@Override
	public String toString() {
		return String.format("%s, Cost : %s", sourceAlgorithm.getClass().getSimpleName(), getCost());
	}
}
