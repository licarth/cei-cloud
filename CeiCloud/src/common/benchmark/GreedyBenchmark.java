package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.RandomGenerator;
import common.problem.IInstance;
import common.problem.IProblem;
import common.problem.InputDataException;
import common.solution.ISolution;

public abstract class GreedyBenchmark<P extends IProblem, I extends IInstance<P>, A extends IAlgorithm<P, I>, G extends RandomGenerator<P,? extends I>> implements IExecution<P,A,I,G>{

	public GreedyBenchmark(P problem, A algorithm, G generator) {
		super();
		this.problem = problem;
		this.algorithm = algorithm;
		this.generator = generator;
	}

	private P problem;
	private A algorithm;
	private G generator;
	
	@Override
	public OptimalExecutionStats<P, I> run() throws Exception {
		try {
			I i = getGenerator().generateInstance();
			ISolution<P, ? super I> sol = getAlgorithm().solve(i);
			
			System.out.println(sol);
			
		} catch (InputDataException e) {
			//TODO throw exception.
			e.printStackTrace();
		}
		return null;
	}

	public P getProblem() {
		return problem;
	}

	public void setProblem(P problem) {
		this.problem = problem;
	}

	public A getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(A algorithm) {
		this.algorithm = algorithm;
	}

	public G getGenerator() {
		return generator;
	}

	public void setGenerator(G generator) {
		this.generator = generator;
	}

}
