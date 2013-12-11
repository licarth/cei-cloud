package common.solution;

import common.problem.IInstance;
import common.problem.Problem;

public interface Solution<P extends Problem, I extends IInstance<? extends P>> {
	int getCost();
	I getInstance();
}