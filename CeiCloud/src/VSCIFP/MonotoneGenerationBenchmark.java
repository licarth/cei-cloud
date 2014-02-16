package VSCIFP;

import common.benchmark.BenchmarkStats;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.VSCIFPBenchmark;
import VSCIFP.gens.LinearVSCIFPGenerator;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

public class MonotoneGenerationBenchmark {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 20, 1, 1);
		MonotoneVSCIFPGenerator monoGen = new MonotoneVSCIFPGenerator(p, 1000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		NFL nfl = new NFL();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		
		VSCIFPBenchmark benchmarkCIFFD = new VSCIFPBenchmark(p, ciffd, monoGen, nRuns);
		BenchmarkStats<VSCIFP, VSCIFPInstance> stats = benchmarkCIFFD.run();
		VSCIFPBenchmark benchmarkNFL = new VSCIFPBenchmark(p, nfl, monoGen, nRuns);
		BenchmarkStats<VSCIFP, VSCIFPInstance> stats2 = benchmarkNFL.run();
		VSCIFPBenchmark benchmarkNFLOPT = new VSCIFPBenchmark(p, nflOpt, monoGen, nRuns);
		BenchmarkStats<VSCIFP, VSCIFPInstance> stats3 = benchmarkNFLOPT.run();
		
		System.out.println(stats);
		System.out.println(stats2);
		System.out.println(stats3);
	}

}
