package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.IGenerator;
import common.problem.IInstance;
import common.problem.IProblem;

/**
 * A benchmark is a 
 * 
 * 
 * @author thomas
 *
 * @param <P>
 * @param <A>
 * @param <I>
 * @param <G>
 */
public interface IExecution<P extends IProblem, A extends IAlgorithm<P, ? extends IInstance<? extends P>>,
		I extends IInstance<P>, G extends IGenerator<P,? extends I>> {
	/**
	 * Runs the benchmark.
	 * @return 
	 * @throws Exception 
	 */
	ExecutionStats<P, I> run() throws AlgorithmExecutionException, Exception;
	
	P getProblem();
	A getAlgorithm();
	G getGenerator();
	
}
