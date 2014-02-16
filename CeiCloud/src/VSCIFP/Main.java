package VSCIFP;

import java.util.List;

import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.gens.LinearVSCIFPGenerator;

import common.problem.InputDataException;
import common.solution.OptimalCostNotKnownException;

public class Main {
	public static void main(String[] args) throws InputDataException, OptimalCostNotKnownException {
		VSCIFP p = new VSCIFP(100, 3, 1, 1);
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

			CFFf cfff = new CFFf(0.5);
//			System.out.println(ins.getOptimalSolution().getCost());
			VSCIFPSolution cfffSol = cfff.solve(ins);
			System.out.println("CFFf :\t\t"+cfffSol.getCost()+" --> " +cfffSol.getErrorRatio());
			System.out.println();
			
		}
		
	}
	
	
}
