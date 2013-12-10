package common.generator;

import common.problem.Problem;


/**
 * Describes an instance we know the optimal cost of.
 * 
 * @author thomas
 *
 * @param <P>
 */
public interface OptimalGenerator<P extends Problem> {
	
	long getOptimalCost();
	
}
