package VSCIFP;

import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CDNFLAlmostEqual;
import VSCIFP.algs.CNFLAlmostEqual;
import VSCIFP.algs.CDNFL;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.OptimalVSCIFPExecution;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

public class LinearGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 3, 1, 1);
//		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p, 10000);
		MonotoneVSCIFPGenerator gen = new MonotoneVSCIFPGenerator(p, 10000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		CNFL nfl = new CNFL();
		CDNFLAlmostEqual cndfl = new CDNFLAlmostEqual();
		CDNFL ndfl = new CDNFL();
		CNFLAlmostEqual cnfl = new CNFLAlmostEqual();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		CFFf cfff = new CFFf(0.5);
//		SavedCFFf cfffSaved = new SavedCFFf(0.5);
		
		gen.reset();
		System.out.println(new OptimalVSCIFPExecution(p, ciffd, gen, nRuns).run());
//		
////		gen.reset();
////		System.out.println(new OptimalVSCIFPExecution(p, ciffd, gen, nRuns).run());
//
		gen.reset();
		System.out.println(new OptimalVSCIFPExecution(p, nfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPExecution(p, ndfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPExecution(p, cnfl, gen, nRuns).run());
//		
		gen.reset();
		System.out.println(new OptimalVSCIFPExecution(p, cndfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPExecution(p, nflOpt, gen, nRuns).run());
//		
//		gen.reset();
//		System.out.println(new OptimalVSCIFPExecution(p, cfff, gen, nRuns).run());

		gen.reset();
		System.out.println(new OptimalVSCIFPExecution(p, cfff, gen, nRuns).run());
		
//		gen.reset();
//		System.out.println(new OptimalVSCIFPExecution(p, cfffSaved, gen, nRuns).run());
		
	}

}
