package common.generator;

import common.problem.Problem;


public interface OptimalGenerator<P extends Problem> {
	
	long getOptimalCost();
	
}
