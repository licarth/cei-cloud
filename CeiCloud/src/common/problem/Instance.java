package common.problem;


public interface Instance<P extends Problem> {
	
	void checkProblemInput() throws ProblemInputDataException;
	P getProblem();
	
}
