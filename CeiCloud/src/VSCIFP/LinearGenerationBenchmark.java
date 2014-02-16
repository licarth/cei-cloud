package VSCIFP;

import common.benchmark.BenchmarkStats;
import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.VSCIFPBenchmark;
import VSCIFP.gens.LinearVSCIFPGenerator;

public class LinearGenerationBenchmark {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 3, 1, 1);
		LinearVSCIFPGenerator linGen = new LinearVSCIFPGenerator(p, 1000);
		
		int nRuns = 100;
		
		CIFFD ciffd = new CIFFD();
		NFL nfl = new NFL();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		CFFf cfff = new CFFf(0.5);
		
		VSCIFPBenchmark benchmarkCIFFD = new VSCIFPBenchmark(p, ciffd, linGen, nRuns);
		BenchmarkStats<VSCIFP, VSCIFPInstance> stats = benchmarkCIFFD.run();
		System.out.println(stats);
		
		VSCIFPBenchmark benchmarkNFL = new VSCIFPBenchmark(p, nfl, linGen, nRuns);
		BenchmarkStats<VSCIFP, VSCIFPInstance> stats2 = benchmarkNFL.run();
		System.out.println(stats2);
		
		VSCIFPBenchmark benchmarkNFLOPT = new VSCIFPBenchmark(p, nflOpt, linGen, nRuns);
		BenchmarkStats<VSCIFP, VSCIFPInstance> stats3 = benchmarkNFLOPT.run();
		System.out.println(stats3);
		
		VSCIFPBenchmark benchmarkCFFf = new VSCIFPBenchmark(p, cfff, linGen, nRuns);
		BenchmarkStats<VSCIFP, VSCIFPInstance> stats4 = benchmarkCFFf.run();
		System.out.println(stats4);
		
	}

}
