package binpacking.gens;

import java.util.List;

import common.problem.InputDataException;
import common.solution.Solution;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.FFD;

public class Main {

	public static void main(String[] args) throws InputDataException {
		
		//Test BPP
		//IProblem definition
		BPP problem = new BPP(400, 1, 400);
		
		FFD ffd = new FFD();
		
		GareyJohnsonWorstGenerator gen = new GareyJohnsonWorstGenerator(problem, 10);
		List<OptimalKnownBPPInstance> l = gen.generateInstances();
		
		Solution<BPP, BPPInstance> sol = ffd.solve(l.get(0));
		
		System.out.println(sol.getCost());
	}

}
