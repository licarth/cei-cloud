package common;

public abstract class AbstractProblem implements Problem{

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("[%s instance: (%s)]",this.getClass().getSimpleName(), toStringDetailed()));
		return sb.toString();
	}

	@Override
	public void checkProblemInput() throws ProblemInputDataException {
	}
	
	public abstract String toStringDetailed();
	
}
