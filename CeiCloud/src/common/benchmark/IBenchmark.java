package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.IGenerator;
import common.problem.IInstance;
import common.problem.IOptimalCostAwareInstance;
import common.problem.IProblem;

public interface IBenchmark<P extends IProblem, A extends IAlgorithm<P, ? extends IInstance<? extends P>>,
		I extends IInstance<P>, G extends IGenerator<P,? extends I>> {
	/**
	 * Runs the benchmark.
	 * @return 
	 */
	BenchmarkStats<P, I> run() throws BenchmarkRunException;
	
	P getProblem();
	A getAlgorithm();
	G getGenerator();
	
}
