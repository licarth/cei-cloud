package VSCIFP;

import java.io.FileWriter;

import VSCIFP.algs.CFFf;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.CNDFL;
import VSCIFP.algs.CNFL;
import VSCIFP.algs.NDFL;
import VSCIFP.algs.NFL;
import VSCIFP.algs.NFLWithBinTypeOptimization;
import VSCIFP.benchmark.RelativeVSCIFPBenchmark;
import VSCIFP.gens.LinearVSCIFPGenerator;
import VSCIFP.gens.UnknownCostVSCIFPGenerator;
import common.algorithm.Algorithm;
import common.benchmark.RelativeBenchmarkStats;

public class Fig8GenerationBenchmark {

	public static void main(String[] args) throws Exception {
		FileWriter fw = new FileWriter("gnuplot/data/out.dat");
		
		fw.append("a \"3 bins\" c \"10 bins\" e\n");
		
		for (int D = 1; D <= 1; D++) {
			System.out.println();
			System.out.println(D);
			VSCIFP p3bins = new VSCIFP(100, 3, 1, D);
			UnknownCostVSCIFPGenerator gen3bins = new UnknownCostVSCIFPGenerator(p3bins, 10000);
			VSCIFP p10bins = new VSCIFP(100, 10, 1, D);
			UnknownCostVSCIFPGenerator gen10bins = new UnknownCostVSCIFPGenerator(p10bins, 10000);
			
			int nRuns = 1000;

			final CIFFD ciffd = new CIFFD();
			final NFL nfl = new NFL();
			final CNDFL cndfl = new CNDFL();
			final NDFL ndfl = new NDFL();
			final CNFL cnfl = new CNFL();
			final NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
			final CFFf cfff = new CFFf(0.5);
			//		SavedCFFf cfffSaved = new SavedCFFf(0.5);
			
			Algorithm<VSCIFP, VSCIFPInstance>[] algs = new Algorithm[] {ciffd, ndfl, cfff, nfl};
			
			for (int i = 0; i < algs.length; i++) {
				gen3bins.reset();
				RelativeBenchmarkStats<VSCIFP, VSCIFPInstance> stats3bins = new RelativeVSCIFPBenchmark(p3bins, algs[i], gen3bins, nRuns).run();
				gen10bins.reset();
				RelativeBenchmarkStats<VSCIFP, VSCIFPInstance> stats10bins = new RelativeVSCIFPBenchmark(p10bins, algs[i], gen10bins, nRuns).run();
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
