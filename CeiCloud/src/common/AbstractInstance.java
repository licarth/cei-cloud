package common;

public abstract class AbstractInstance<P extends Problem> implements Instance<P> {

	/**
	 * Problem this is an instance of.
	 */
	protected final P problem;
	
	public AbstractInstance(P problem) {
		this.problem = problem;
	}
	
	@Override
	public P getProblem() {
		return problem;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("[%s instance: (%s)]", getProblem().getClass().getSimpleName(), toStringDetailed()));
		return sb.toString();
	}

	public abstract String toStringDetailed();
	
}
