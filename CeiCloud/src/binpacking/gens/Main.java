package binpacking.gens;

import java.util.List;

import common.problem.InputDataException;
import common.solution.OptimalCostNotKnownException;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.FFD;

public class Main {

	public static void main(String[] args) throws InputDataException, OptimalCostNotKnownException {
		// TODO Auto-generated method stub
		BPP problem = new BPP(100, 1, 99);

		FFD ffd = new FFD();
		
		MartelloTothBPPLibraryGenerator gen = new MartelloTothBPPLibraryGenerator(problem);
		
		List<OptimalKnownBPPInstance> instances = gen.generateInstances();
		
		for (BPPInstance bppInstance : instances) {
			System.out.println(ffd.solve(bppInstance).getErrorRatio());
			
		}
		
		
	
	}

}
