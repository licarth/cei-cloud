package common.problem;


public interface IInstance<P extends Problem> {
	
	void checkProblemInput() throws ProblemInputDataException;
	P getProblem();
	
}
