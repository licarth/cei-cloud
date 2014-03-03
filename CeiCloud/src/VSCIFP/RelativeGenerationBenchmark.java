package VSCIFP;

import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.RelativeVSCIFPBenchmark;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

import common.benchmark.RelativeBenchmarkStats;

public class RelativeGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 20, 1, 1);
		MonotoneVSCIFPGenerator monoGen = new MonotoneVSCIFPGenerator(p, 1000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		CNFL nfl = new CNFL();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		
		RelativeVSCIFPBenchmark benchmarkCIFFD = new RelativeVSCIFPBenchmark(p, ciffd, monoGen, nRuns);
		RelativeBenchmarkStats<VSCIFP, VSCIFPInstance> stats = benchmarkCIFFD.run();
		RelativeVSCIFPBenchmark benchmarkNFL = new RelativeVSCIFPBenchmark(p, nfl, monoGen, nRuns);
		RelativeBenchmarkStats<VSCIFP, VSCIFPInstance> stats2 = benchmarkNFL.run();
		RelativeVSCIFPBenchmark benchmarkNFLOPT = new RelativeVSCIFPBenchmark(p, nflOpt, monoGen, nRuns);
		RelativeBenchmarkStats<VSCIFP, VSCIFPInstance> stats3 = benchmarkNFLOPT.run();
		
		System.out.println(stats);
		System.out.println(stats2);
		System.out.println(stats3);
	}

}
