package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.OptimalRandomGenerator;
import common.generator.RandomGenerator;
import common.problem.IInstance;
import common.problem.IOptimalCostAwareInstance;
import common.problem.IProblem;
import common.problem.InputDataException;
import common.solution.ISolution;
import common.solution.OptimalCostNotKnownException;

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
public abstract class RelativeCostBenchmark<P extends IProblem, 
I extends IInstance<P>, A extends IAlgorithm<P, ? super I>, G extends RandomGenerator<P,I>>
implements IExecution<P,A,I,G>{
	
	private int runCount;
	
	private RelativeExecutionStats<P, I> reference;
	
	private P problem;
	private A algorithm;
	private G generator;
	
	public RelativeCostBenchmark(RelativeExecutionStats<P, I> reference) {
		this.reference = reference;
	}
	
	public RelativeExecutionStats<P, I> run() throws Exception {
		//Create instances
		RelativeExecutionStats<P, I> bs = new RelativeExecutionStats<P,I>(this);
		for (int j = 0; j < getRunCount(); j++) {
			try {
				I i = (I) getGenerator().generateInstance();
				ISolution<P, ? super I> sol = getAlgorithm().solve(i);
//				VizUtils.barChart(i., min, max);
				bs.addCost(sol.getCost());
			} catch (InputDataException | OptimalCostNotKnownException e) {
				e.printStackTrace();
			}
		}
		return bs;
	}

	public RelativeCostBenchmark(P problem, A algorithm, G generator, int runCount) {
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
