package VSCIFP;

import VSCIFP.gens.LinearVSCIFPGenerator;
import VSCIFP.gens.MonotoneVSCIFPGenerator;
import common.problem.InputDataException;

public class Main {
	public static void main(String[] args) throws InputDataException {
		VSCIFP p = new VSCIFP();
//		MonotoneVSCIFPGenerator gen = new MonotoneVSCIFPGenerator(p);
		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p);
		gen.generateInstances(10);
	}
}
