package VSCIFP;

import java.util.List;

import VSCIFP.algs.CIFFD;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
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
//			System.out.println(ins.getOptimalSolution().getCost());
			VSCIFPSolution ciffdSol = ciffd.solve(ins);
			System.out.println("CIFFD :\t\t"+ciffdSol.getCost()+" --> " +ciffdSol.getErrorRatio());
//			System.out.println();
			
			NFL nfl = new NFL();
//			System.out.println(ins.getOptimalSolution().getCost());
			VSCIFPSolution nflSol = nfl.solve(ins);
			System.out.println("NFL :\t\t"+nflSol.getCost()+" --> " +nflSol.getErrorRatio());
//			System.out.println();
			
			NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
//			System.out.println(ins.getOptimalSolution().getCost());
			VSCIFPSolution nflOptSol = nflOpt.solve(ins);
			System.out.println("NFL OPT :\t"+nflOptSol.getCost()+" --> " +nflOptSol.getErrorRatio());
			System.out.println();
			
		}
		
	}
	
	
}
