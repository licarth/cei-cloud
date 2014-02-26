package VSCIFP;

import common.benchmark.OptimalBenchmarkStats;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.OptimalVSCIFPBenchmark;
import VSCIFP.gens.LinearVSCIFPGenerator;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

public class MonotoneGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 20, 1, 1);
		MonotoneVSCIFPGenerator monoGen = new MonotoneVSCIFPGenerator(p, 1000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		NFL nfl = new NFL();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		
		OptimalVSCIFPBenchmark benchmarkCIFFD = new OptimalVSCIFPBenchmark(p, ciffd, monoGen, nRuns);
		OptimalBenchmarkStats<VSCIFP, VSCIFPInstance> stats = benchmarkCIFFD.run();
		OptimalVSCIFPBenchmark benchmarkNFL = new OptimalVSCIFPBenchmark(p, nfl, monoGen, nRuns);
		OptimalBenchmarkStats<VSCIFP, VSCIFPInstance> stats2 = benchmarkNFL.run();
		OptimalVSCIFPBenchmark benchmarkNFLOPT = new OptimalVSCIFPBenchmark(p, nflOpt, monoGen, nRuns);
		OptimalBenchmarkStats<VSCIFP, VSCIFPInstance> stats3 = benchmarkNFLOPT.run();
		
		System.out.println(stats);
		System.out.println(stats2);
		System.out.println(stats3);
	}

}
