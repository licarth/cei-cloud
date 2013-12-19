package common.solution;

import common.problem.IInstance;
import common.problem.IProblem;

public interface Solution<P extends IProblem, I extends IInstance<? extends P>> {
	int getCost();
	I getInstance();
	float getErrorRatio() throws OptimalCostNotKnownException;
	
}