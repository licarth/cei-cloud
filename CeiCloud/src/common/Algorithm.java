package common;

public interface Algorithm<P extends Problem> {
	Solution<P,?> solve(P instance) throws ProblemInputDataException;
}
