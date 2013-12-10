package binpacking.tomasik;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.algs.FF;
import binpacking.algs.FFD;
import binpacking.algs.NF;
import binpacking.algs.NFD;
import common.VizUtils;
import common.problem.ProblemInputDataException;
import common.solution.Solution;

public class Main {

	public static void main(String[] args) {
		
		//Problem definition
		BPP problem = new BPP(10, 1, 10);
		
		TomasikBPPGenerator gen = new TomasikBPPGenerator(problem, 1000);
		BPPInstance i = null;
		try {
			i = gen.generateInstance();
		} catch (ProblemInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
		
		VizUtils.barChart(i.getItemSizes(), problem.getItemMinSize(), problem.getItemMaxSize());
		
		FFD ffd = new FFD();
		FF ff = new FF();
		NF nf = new NF();
		NFD nfd = new NFD();
		
		try {
			Solution<BPPInstance> sol = ffd.solve(i);
			System.out.println(sol);
		} catch (ProblemInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
