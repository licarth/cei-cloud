package common.generator;

import VSCIFP.algs.ItemCutException;
import common.problem.IInstance;
import common.problem.IOptimalCostAwareInstance;
import common.problem.IProblem;
import common.problem.InputDataException;


/**
 * Describes an instance we know the optimal cost of.
 * 
 * @author thomas
 *
 * @param <P>
 */
public abstract class OptimalRandomGenerator<P extends IProblem, I extends IInstance<P> & IOptimalCostAwareInstance> extends AbstractRandomGenerator<P,I>{
	
	public OptimalRandomGenerator(P problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract I generateInstance() throws Exception;
	
}
