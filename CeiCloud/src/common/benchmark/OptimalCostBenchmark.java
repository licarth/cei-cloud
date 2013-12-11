package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.OptimalGenerator;
import common.problem.IInstance;
import common.problem.OptimalCostAware;
import common.problem.Problem;
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
public class OptimalCostBenchmark<
P extends Problem,
A extends IAlgorithm,
I extends IInstance<P> & OptimalCostAware,
G extends OptimalGenerator<? extends P,? extends I>> implements IBenchmark{
	
	private P problem;
	private A algorithm;
	private G generator;

	/**
	 * How many instances we would like to create.
	 */
	private int instancesCount;

	@Override
	public void run() {
		//Create instances
		I i;
		int k = 0;
		for (int j = 0; j < 1000; j++) {
			try {
				i = getGenerator().generateInstance();
				Solution<P, IInstance<P>> sol = getAlgorithm().solve(i);
				//				System.out.println(sol);
				if (sol.getCost() > i.getOptimalCost()){
					++k;
				}
			} catch (ProblemInputDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public OptimalCostBenchmark(P problem, A algorithm, G generator) {
		super();
		this.problem = problem;
		this.algorithm = algorithm;
		this.generator = generator;
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
