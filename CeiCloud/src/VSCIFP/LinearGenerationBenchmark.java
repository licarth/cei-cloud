package VSCIFP;

import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CNFL2;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.VSCIFPBenchmark;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

public class LinearGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 10, 1, 1);
//		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p, 1000);
		MonotoneVSCIFPGenerator gen = new MonotoneVSCIFPGenerator(p, 1000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		NFL nfl = new NFL();
		CNFL2 cnfl2 = new CNFL2();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		CFFf cfff = new CFFf(0.5);
//		SavedCFFf cfffSaved = new SavedCFFf(0.5);
		
		gen.reset();
		System.out.println(new VSCIFPBenchmark(p, ciffd, gen, nRuns).run());
//		
////		gen.reset();
////		System.out.println(new VSCIFPBenchmark(p, ciffd, gen, nRuns).run());
//
		gen.reset();
		System.out.println(new VSCIFPBenchmark(p, nfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new VSCIFPBenchmark(p, cnfl2, gen, nRuns).run());
//		
		gen.reset();
		System.out.println(new VSCIFPBenchmark(p, nflOpt, gen, nRuns).run());
//		
//		gen.reset();
//		System.out.println(new VSCIFPBenchmark(p, cfff, gen, nRuns).run());

		gen.reset();
		System.out.println(new VSCIFPBenchmark(p, cfff, gen, nRuns).run());
		
//		gen.reset();
//		System.out.println(new VSCIFPBenchmark(p, cfffSaved, gen, nRuns).run());
		
	}

}
