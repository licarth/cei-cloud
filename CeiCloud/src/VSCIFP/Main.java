package VSCIFP;

import java.util.List;

import VSCIFP.algs.CIFFD;
import VSCIFP.gens.LinearVSCIFPGenerator;

import common.problem.InputDataException;

public class Main {
	public static void main(String[] args) throws InputDataException {
		VSCIFP p = new VSCIFP(10, 3, 1, 30, 1);
//		MonotoneVSCIFPGenerator gen = new MonotoneVSCIFPGenerator(p);
		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p);
		List<VSCIFPInstance> instances = gen.generateInstances(1);
		
		CIFFD ciffd = new CIFFD();
		
		for (VSCIFPInstance ins : instances) {
			ciffd.solve(ins);
		}
		
	}
	
	
}
