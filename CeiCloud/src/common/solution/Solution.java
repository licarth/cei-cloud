package common.solution;

import common.problem.Instance;

public interface Solution<I extends Instance> {
	int getCost();
	I getInstance();
}