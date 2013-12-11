package binpacking.tomasik;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.BPPAlgorithm;
import binpacking.algs.FF;
import binpacking.algs.FFD;
import binpacking.algs.NF;
import binpacking.algs.NFD;
import binpacking.benchmark.BPPBenchmark;
import binpacking.gens.UniformBPPGenerator;
import common.benchmark.OptimalCostBenchmark;
import common.problem.ProblemInputDataException;
import common.solution.Solution;

public class Main {

	public static void main(String[] args) {

		//Problem definition
		BPP problem = new BPP(100, 1, 99);
		
		FFD ffd = new FFD();
		FF ff = new FF();
		NF nf = new NF();
		NFD nfd = new NFD();
		
		TomasikBPPGenerator gen = new TomasikBPPGenerator(problem, 100);
		
		BPPBenchmark bench = new BPPBenchmark(problem, ffd, gen);
		
		bench.run();
		

		OptimalKnownBPPInstance i = null;
		int k = 0;
		for (int j = 0; j < 1000; j++) {
			try {
				i = gen.generateInstance();
				Solution<BPP, BPPInstance> sol = ffd.solve(i);
//				System.out.println(sol);
				if (sol.getCost() > i.getOptimalCost()){
					++k;
				}
			} catch (ProblemInputDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(k);
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
