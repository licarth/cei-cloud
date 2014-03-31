package common.benchmark;

import common.algorithm.IAlgorithm;
import common.generator.RandomGenerator;
import common.problem.IInstance;
import common.problem.IProblem;

public abstract class Execution<P extends IProblem, I extends IInstance<P>, A extends IAlgorithm<P, I>, G extends RandomGenerator<P,? extends I>> implements IExecution<P,A,I,G>{

}
