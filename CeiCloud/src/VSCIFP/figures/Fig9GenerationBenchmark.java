package VSCIFP.figures;

import java.io.FileWriter;

import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.algs.CDNFL;
import VSCIFP.algs.CDNFLAlmostEqual;
import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.CNFLAlmostEqual;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.RelativeVSCIFPExecution;
import VSCIFP.gens.MonotoneUnknownCostVSCIFPGenerator;

import common.algorithm.Algorithm;
import common.benchmark.RelativeExecutionStats;

public class Fig9GenerationBenchmark {

	public static void main(String[] args) throws Exception {
		FileWriter fw = new FileWriter("gnuplot/data/fig9.dat");
		
		fw.append("a \"3 bins\" c \"10 bins\" e\n");
		
		for (int D = 1; D <= 1; D++) {
			System.out.println();
			System.out.println(D);
			VSCIFP p3bins = new VSCIFP(100, 3, 1, D);
			MonotoneUnknownCostVSCIFPGenerator gen3bins = new MonotoneUnknownCostVSCIFPGenerator(p3bins, 10000);
			VSCIFP p10bins = new VSCIFP(100, 10, 1, D);
			MonotoneUnknownCostVSCIFPGenerator gen10bins = new MonotoneUnknownCostVSCIFPGenerator(p10bins, 10000);
			
			int nRuns = 1000;

			final CIFFD ciffd = new CIFFD();
			final CNFL cnfl = new CNFL();
			final CDNFLAlmostEqual cdnflAlmostEqual = new CDNFLAlmostEqual();
			final CDNFL cdnfl = new CDNFL();
			final CNFLAlmostEqual cnflAlmostEqual = new CNFLAlmostEqual();
			final NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
			final CFFf cfff = new CFFf(0.5);
			//		SavedCFFf cfffSaved = new SavedCFFf(0.5);
			
			Algorithm<VSCIFP, VSCIFPInstance>[] algs = new Algorithm[] {ciffd, cdnfl, cfff, cnfl};
			
			for (int i = 0; i < algs.length; i++) {
				gen3bins.reset();
				RelativeExecutionStats<VSCIFP, VSCIFPInstance> stats3bins = new RelativeVSCIFPExecution(p3bins, algs[i], gen3bins, nRuns).run();
				gen10bins.reset();
				RelativeExecutionStats<VSCIFP, VSCIFPInstance> stats10bins = new RelativeVSCIFPExecution(p10bins, algs[i], gen10bins, nRuns).run();
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
