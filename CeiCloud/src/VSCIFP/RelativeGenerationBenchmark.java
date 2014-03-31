package VSCIFP;

import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.RelativeVSCIFPExecution;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

import common.benchmark.RelativeExecutionStats;

public class RelativeGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 20, 1, 1);
		MonotoneVSCIFPGenerator monoGen = new MonotoneVSCIFPGenerator(p, 1000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		CNFL nfl = new CNFL();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		
		RelativeVSCIFPExecution benchmarkCIFFD = new RelativeVSCIFPExecution(p, ciffd, monoGen, nRuns);
		RelativeExecutionStats<VSCIFP, VSCIFPInstance> stats = benchmarkCIFFD.run();
		RelativeVSCIFPExecution benchmarkNFL = new RelativeVSCIFPExecution(p, nfl, monoGen, nRuns);
		RelativeExecutionStats<VSCIFP, VSCIFPInstance> stats2 = benchmarkNFL.run();
		RelativeVSCIFPExecution benchmarkNFLOPT = new RelativeVSCIFPExecution(p, nflOpt, monoGen, nRuns);
		RelativeExecutionStats<VSCIFP, VSCIFPInstance> stats3 = benchmarkNFLOPT.run();
		
		System.out.println(stats);
		System.out.println(stats2);
		System.out.println(stats3);
	}

}
