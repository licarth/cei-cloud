package common.generator;

import common.problem.IInstance;
import common.problem.IOptimalCostAware;
import common.problem.IProblem;
import common.problem.ProblemInputDataException;


/**
 * Describes an instance we know the optimal cost of.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class OptimalGenerator<P extends IProblem, I extends IInstance<P> & IOptimalCostAware> extends AbstractRandomGenerator<P,I>{
	
	public OptimalGenerator(P problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract I generateInstance() throws ProblemInputDataException;
	
}
