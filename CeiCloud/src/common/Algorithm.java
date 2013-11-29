package common;

public interface Algorithm<I extends Instance> {
	Solution<I> solve(I instance) throws ProblemInputDataException;
	I getInstance();
}
