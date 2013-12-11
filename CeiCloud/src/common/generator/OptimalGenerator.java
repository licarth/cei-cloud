package common.generator;

import common.problem.Instance;
import common.problem.OptimalCostAware;
import common.problem.Problem;
import common.problem.ProblemInputDataException;


/**
 * Describes an instance we know the optimal cost of.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class OptimalGenerator<P extends Problem, I extends Instance<? extends P> & OptimalCostAware> extends AbstractRandomGenerator<P,I>{
	
	public OptimalGenerator(P problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract I generateInstance() throws ProblemInputDataException;
	
}
