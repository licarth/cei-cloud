package common.generator;

import common.problem.IInstance;
import common.problem.Problem;

/**
 * General interface describing a generator of instances of problem P.
 * 
 * @author thomas
 *
 * @param <P>
 */
public interface Generator<P extends Problem, I extends IInstance<? extends P>> {
	
}
