package VSCIFP;

import VSCIFP.gens.LinearVSCIFPGenerator;
import common.problem.InputDataException;

public class Main {
	public static void main(String[] args) throws InputDataException {
		VSCIFP p = new VSCIFP();
		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p);
		gen.generateInstances(10);
	}
}
