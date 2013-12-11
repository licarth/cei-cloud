package common.problem;


public interface IInstance<P extends IProblem> {
	
	void checkProblemInput() throws ProblemInputDataException;
	P getProblem();
	
}
