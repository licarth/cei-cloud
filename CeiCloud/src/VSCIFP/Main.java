package VSCIFP;

import VSCIFP.gens.LinearVSCIFPGenerator;
import common.problem.ProblemInputDataException;

public class Main {
	public static void main(String[] args) throws ProblemInputDataException {
		VSCIFP p = new VSCIFP();
		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p);
		gen.generateInstances(10);
	}
}
