package common.generator;

import common.problem.IInstance;
import common.problem.IOptimalCostAwareInstance;
import common.problem.IProblem;

/**
 * @author thomas
 *
 * @param <P>
 */
public abstract class OptimalLibraryGenerator<P extends IProblem, I extends IInstance<P> & IOptimalCostAwareInstance> extends LibraryGenerator<P, I> {

	public OptimalLibraryGenerator(P problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}
	
	

}
