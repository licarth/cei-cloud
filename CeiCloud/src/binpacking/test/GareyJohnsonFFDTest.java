package binpacking.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.FFD;
import binpacking.gens.GareyJohnsonWorstGenerator;
import common.problem.InputDataException;
import common.solution.ISolution;
import common.solution.OptimalCostNotKnownException;

public class GareyJohnsonFFDTest {

	/**
	 * Tests FFD performance on GareyJohnson's worst instance. FFD cost should be 11m
	 * where optimal cost is 9m.
	 * @throws ItemCutException 
	 */
	@Test
	public void optimalityTest() throws OptimalCostNotKnownException{

		//Problem & algorithm declarations
		BPP problem = new BPP(100, 1, 100);
		FFD ffd = new FFD();

		for (int m = 1; m < 10; m++) {
			//Instance generator is Garey & Johnson's for a m parameter set to m
			GareyJohnsonWorstGenerator gen = new GareyJohnsonWorstGenerator(problem, m);
			List<OptimalKnownBPPInstance> l;
			try {
				l = gen.generateInstances();
				ISolution<BPP, BPPInstance> sol = ffd.solve(l.get(0));
				System.out.println(sol.getCost());
				System.out.println(sol.getErrorRatio());
				assertEquals(11 * m, sol.getCost());
			} catch (InputDataException e) {
				fail(e.getMessage());
			}
		}

	}

}
