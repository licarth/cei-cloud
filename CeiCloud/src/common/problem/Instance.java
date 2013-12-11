package common.problem;


public abstract class Instance<P extends IProblem> implements IInstance<P> {

	/**
	 * IProblem this is an instance of.
	 */
	protected final P problem;
	
	public Instance(P problem) {
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
