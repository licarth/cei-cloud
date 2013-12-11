package common.solution;

import common.problem.IInstance;
import common.problem.IProblem;

public interface ISolution<P extends IProblem, I extends IInstance<? extends P>> {
	int getCost();
	I getInstance();
}