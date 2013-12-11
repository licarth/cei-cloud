package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.RandomGenerator;
import common.problem.IInstance;
import common.problem.IProblem;
import common.problem.ProblemInputDataException;
import common.solution.Solution;

public abstract class Benchmark<P extends IProblem, I extends IInstance<P>, A extends IAlgorithm<P, I>, G extends RandomGenerator<P,? extends I>> implements IBenchmark<P,A,I,G>{

	public Benchmark(P problem, A algorithm, G generator) {
		super();
		this.problem = problem;
		this.algorithm = algorithm;
		this.generator = generator;
	}

	private P problem;
	private A algorithm;
	private G generator;
	
	@Override
	public BenchmarkStats<P, I> run() throws BenchmarkRunException {
		try {
			I i = getGenerator().generateInstance();
			Solution<P, ? super I> sol = getAlgorithm().solve(i);
			
			System.out.println(sol);
			
		} catch (ProblemInputDataException e) {
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
