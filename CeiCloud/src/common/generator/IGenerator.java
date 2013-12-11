package common.generator;

import common.problem.IInstance;
import common.problem.IProblem;

/**
 * General interface describing a generator of instances of problem P.
 * 
 * @author thomas
 *
 * @param <P>
 */
public interface IGenerator<P extends IProblem, I extends IInstance<? extends P>> {
	
}
