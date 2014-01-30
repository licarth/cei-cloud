package VSCIFP;

import java.util.List;

import VSCIFP.algs.CIFFD;
import VSCIFP.gens.LinearVSCIFPGenerator;
import common.problem.InputDataException;
import common.solution.OptimalCostNotKnownException;

public class Main {
	public static void main(String[] args) throws InputDataException, OptimalCostNotKnownException {
		VSCIFP p = new VSCIFP(10, 3, 1, 20, 1);
//		MonotoneVSCIFPGenerator gen = new MonotoneVSCIFPGenerator(p);
		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p, 1000);
		List<VSCIFPInstance> instances = gen.generateInstances(10);
		
		for (VSCIFPInstance ins : instances) {
			CIFFD ciffd = new CIFFD();
			System.out.println(ins.getOptimalSolution().getCost());
			VSCIFPSolution sol = ciffd.solve(ins);
			System.out.println(sol.getCost()+" --> " +sol.getErrorRatio());
			System.out.println();
		}
		
	}
	
	
}
