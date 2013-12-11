package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.OptimalGenerator;
import common.problem.IInstance;
import common.problem.IOptimalCostAware;
import common.problem.IProblem;
import common.problem.ProblemInputDataException;
import common.solution.Solution;

/**
 * 
 * A benchmark that compares the algorithm performance to the optimal cost of the problem instance.
 * The generator provided in Generics should allow us to know the optimal cost.
 * 
 * @author thomas
 *
 * @param <P>
 * @param <A>
 * @param <G>
 */
public abstract class OptimalCostBenchmark<P extends IProblem, 
I extends IInstance<P> & IOptimalCostAware, A extends IAlgorithm<P, ? super I>, G extends OptimalGenerator<P,I>>
implements IBenchmark<P,A,I,G>{
	
	private int runCount;
	
	private P problem;
	private A algorithm;
	private G generator;
	
	public BenchmarkStats<P, I> run() {
		//Create instances
		BenchmarkStats<P, I> bs = new BenchmarkStats<P,I>(this);
		for (int j = 0; j < getRunCount(); j++) {
			try {
				I i = (I) getGenerator().generateInstance();
				Solution<P, ? super I> sol = getAlgorithm().solve(i);
//				VizUtils.barChart(i., min, max);
				bs.addRatio((float)sol.getCost() / (float) i.getOptimalCost());
			} catch (ProblemInputDataException e) {
				e.printStackTrace();
			}
		}
		return bs;
	}

	public OptimalCostBenchmark(P problem, A algorithm, G generator, int runCount) {
		super();
		this.problem = problem;
		this.algorithm = algorithm;
		this.generator = generator;
		this.runCount = runCount;
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

	public int getRunCount() {
		return runCount;
	}

	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}

}
