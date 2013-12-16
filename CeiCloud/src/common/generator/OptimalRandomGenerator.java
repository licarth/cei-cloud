package common.generator;

import common.problem.IInstance;
import common.problem.IOptimalCostAware;
import common.problem.IProblem;
import common.problem.InputDataException;


/**
 * Describes an instance we know the optimal cost of.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class OptimalRandomGenerator<P extends IProblem, I extends IInstance<P> & IOptimalCostAware> extends AbstractRandomGenerator<P,I>{
	
	public OptimalRandomGenerator(P problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract I generateInstance() throws InputDataException;
	
}
