package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.IGenerator;
import common.problem.IInstance;
import common.problem.IProblem;

public interface IBenchmark<P extends IProblem, A extends IAlgorithm<P, ? extends IInstance<? extends P>>,
		I extends IInstance<P>, G extends IGenerator<P,? extends I>> {
	/**
	 * Runs the benchmark.
	 * @return 
	 * @throws Exception 
	 */
	BenchmarkStats<P, I> run() throws BenchmarkRunException, Exception;
	
	P getProblem();
	A getAlgorithm();
	G getGenerator();
	
}
