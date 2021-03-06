package VSCIFP;

import java.io.FileWriter;

import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CDNFLAlmostEqual;
import VSCIFP.algs.CNFLAlmostEqual;
import VSCIFP.algs.CDNFL;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.OptimalVSCIFPExecution;
import VSCIFP.gens.LinearVSCIFPGenerator;
import VSCIFP.gens.MonotoneVSCIFPGenerator;

import common.algorithm.Algorithm;
import common.benchmark.OptimalExecutionStats;

public class Fig4GenerationBenchmark {

	public static void main(String[] args) throws Exception {
		FileWriter fw = new FileWriter("gnuplot/data/out.dat");
		
		fw.append("a \"3 bins\" c \"10 bins\" e\n");
		
		for (int D = 1; D <= 1; D++) {
			System.out.println();
			System.out.println(D);
			VSCIFP p3bins = new VSCIFP(100, 3, 1, D);
			LinearVSCIFPGenerator gen3bins = new LinearVSCIFPGenerator(p3bins, 10000);
			VSCIFP p10bins = new VSCIFP(100, 10, 1, D);
			LinearVSCIFPGenerator gen10bins = new LinearVSCIFPGenerator(p10bins, 10000);
			
//			VSCIFP p3bins = new VSCIFP(100, 3, 1, D);
//			MonotoneVSCIFPGenerator gen3bins = new MonotoneVSCIFPGenerator(p3bins, 10000);
//			VSCIFP p10bins = new VSCIFP(100, 10, 1, D);
//			MonotoneVSCIFPGenerator gen10bins = new MonotoneVSCIFPGenerator(p10bins, 10000);
			
			int nRuns = 1000;

			final CIFFD ciffd = new CIFFD();
			final CNFL nfl = new CNFL();
			final CDNFLAlmostEqual cndfl = new CDNFLAlmostEqual();
			final CDNFL ndfl = new CDNFL();
			final CNFLAlmostEqual cnfl = new CNFLAlmostEqual();
			final NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
			final CFFf cfff = new CFFf(0.5);
			//		SavedCFFf cfffSaved = new SavedCFFf(0.5);
			
			Algorithm<VSCIFP, VSCIFPInstance>[] algs = new Algorithm[] {cfff, nfl};
			
			for (int i = 0; i < algs.length; i++) {
				gen3bins.reset();
				OptimalExecutionStats<VSCIFP, VSCIFPInstance> stats3bins = new OptimalVSCIFPExecution(p3bins, algs[i], gen3bins, nRuns).run();
				gen10bins.reset();
				OptimalExecutionStats<VSCIFP, VSCIFPInstance> stats10bins = new OptimalVSCIFPExecution(p10bins, algs[i], gen10bins, nRuns).run();
				StringBuilder sb = new StringBuilder();
				sb.append(algs[i].getClass().getSimpleName());
				sb.append(" ");
				sb.append(stats3bins.stats.getMean());
				sb.append(" ");
				sb.append(stats3bins.get2Epsilon());
				sb.append(" ");
				sb.append(stats10bins.stats.getMean());
				sb.append(" ");
				sb.append(stats10bins.get2Epsilon());
				sb.append("\n");
				System.out.println(stats3bins);
				fw.append(sb.toString());
				fw.flush();
			}
		}
		
		fw.close();
	}

}
