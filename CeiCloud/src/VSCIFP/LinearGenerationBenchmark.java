package VSCIFP;

import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CNDFL;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NDFL;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.OptimalVSCIFPBenchmark;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

public class LinearGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 3, 1, 1);
//		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p, 10000);
		MonotoneVSCIFPGenerator gen = new MonotoneVSCIFPGenerator(p, 10000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		NFL nfl = new NFL();
		CNDFL cndfl = new CNDFL();
		NDFL ndfl = new NDFL();
		CNFL cnfl = new CNFL();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		CFFf cfff = new CFFf(0.5);
//		SavedCFFf cfffSaved = new SavedCFFf(0.5);
		
		gen.reset();
		System.out.println(new OptimalVSCIFPBenchmark(p, ciffd, gen, nRuns).run());
//		
////		gen.reset();
////		System.out.println(new OptimalVSCIFPBenchmark(p, ciffd, gen, nRuns).run());
//
		gen.reset();
		System.out.println(new OptimalVSCIFPBenchmark(p, nfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPBenchmark(p, ndfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPBenchmark(p, cnfl, gen, nRuns).run());
//		
		gen.reset();
		System.out.println(new OptimalVSCIFPBenchmark(p, cndfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPBenchmark(p, nflOpt, gen, nRuns).run());
//		
//		gen.reset();
//		System.out.println(new OptimalVSCIFPBenchmark(p, cfff, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPBenchmark(p, cfff, gen, nRuns).run());
		
//		gen.reset();
//		System.out.println(new OptimalVSCIFPBenchmark(p, cfffSaved, gen, nRuns).run());
		
	}

}
