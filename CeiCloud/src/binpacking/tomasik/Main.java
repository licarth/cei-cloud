package binpacking.tomasik;

import binpacking.BPP;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.FF;
import binpacking.algs.FFD;
import binpacking.algs.NF;
import binpacking.algs.NFD;
import binpacking.benchmark.BPPOptimalBenchmark;
import common.benchmark.BenchmarkRunException;
import common.benchmark.BenchmarkStats;
import common.problem.ProblemInputDataException;

public class Main {

	
	public static void main(String[] args) throws ProblemInputDataException, BenchmarkRunException {
		
		//IProblem definition
		BPP problem = new BPP(100, 1, 99);
		
		FFD ffd = new FFD();
		FF ff = new FF();
		NF nf = new NF();
		NFD nfd = new NFD();
		
		TomasikBPPGenerator gen = new TomasikBPPGenerator(problem, 100);
		
		System.out.println(new BPPOptimalBenchmark(problem, nf, gen, 1000).run());
		System.out.println(new BPPOptimalBenchmark(problem, nfd, gen, 1000).run());
		System.out.println(new BPPOptimalBenchmark(problem, ff, gen, 1000).run());
		System.out.println(new BPPOptimalBenchmark(problem, ffd, gen, 1000).run());

//		OptimalKnownBPPInstance i = null;
//		int k = 0;
//		for (int j = 0; j < 1000; j++) {
//			try {
//				i = gen.generateInstance();
//				Solution<BPP, BPPInstance> sol = ffd.solve(i);
////				System.out.println(sol);
//				if (sol.getCost() > i.getOptimalCost()){
//					++k;
//				}
//			} catch (ProblemInputDataException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(k);
		
		
		
		//		System.out.println(i);
		//		
		//		VizUtils.barChart(i.getItemSizes(), problem.getItemMinSize(), problem.getItemMaxSize());
		//		
		//		
		//		try {
		//			Solution<BPPInstance> sol = ffd.solve(i);
		//			System.out.println(sol);
		//		} catch (BenchmarkRunException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}



	}

}
