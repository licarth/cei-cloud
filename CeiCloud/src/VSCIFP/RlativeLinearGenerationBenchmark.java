package VSCIFP;

import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CDNFLAlmostEqual;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.OptimalVSCIFPExecution;
import VSCIFP.benchmark.RelativeVSCIFPExecution;
import VSCIFP.gens.LinearUnknownCostVSCIFPGenerator;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

public class RlativeLinearGenerationBenchmark {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		VSCIFP p = new VSCIFP(100, 3, 1, 1);
//		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p, 1000);
		LinearUnknownCostVSCIFPGenerator gen = new LinearUnknownCostVSCIFPGenerator(p, 10000);
		
		int nRuns = 1000;
		
		CIFFD ciffd = new CIFFD();
		CNFL nfl = new CNFL();
		CDNFLAlmostEqual cnfl2 = new CDNFLAlmostEqual();
		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
		CFFf cfff = new CFFf(0.5);
//		SavedCFFf cfffSaved = new SavedCFFf(0.5);
		
		gen.reset();
		System.out.println(new RelativeVSCIFPExecution(p, ciffd, gen, nRuns).run());
//		
////		gen.reset();
////		System.out.println(new RelativeVSCIFPExecution(p, ciffd, gen, nRuns).run());
//
		gen.reset();
		System.out.println(new RelativeVSCIFPExecution(p, nfl, gen, nRuns).run());

		gen.reset();
		System.out.println(new RelativeVSCIFPExecution(p, cnfl2, gen, nRuns).run());
//		
		gen.reset();
		System.out.println(new RelativeVSCIFPExecution(p, nflOpt, gen, nRuns).run());
//		
//		gen.reset();
//		System.out.println(new RelativeVSCIFPExecution(p, cfff, gen, nRuns).run());

		gen.reset();
		System.out.println(new RelativeVSCIFPExecution(p, cfff, gen, nRuns).run());
		
//		gen.reset();
//		System.out.println(new RelativeVSCIFPExecution(p, cfffSaved, gen, nRuns).run());
		
	}

}
