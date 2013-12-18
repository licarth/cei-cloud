package binpacking.test;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.FFD;
import binpacking.gens.GareyJohnsonWorstGenerator;

import common.problem.InputDataException;
import common.solution.Solution;

public class GareyJohnsonFFDTest {

	/**
	 * Tests FFD performance on GareyJohnson's worst instance. FFD cost should be 11m
	 * where optimal cost is 9m.
	 */
	@Test
	public void optimalityTest(){

		//Problem & algorithm declarations
		BPP problem = new BPP(100, 1, 100);
		FFD ffd = new FFD();

		for (int m = 1; m < 10; m++) {
			//Instance generator is Garey & Johnson's for a m parameter set to m
			GareyJohnsonWorstGenerator gen = new GareyJohnsonWorstGenerator(problem, m);
			List<OptimalKnownBPPInstance> l;
			try {
				l = gen.generateInstances();
				Solution<BPP, BPPInstance> sol = ffd.solve(l.get(0));
				assertEquals(11 * m, sol.getCost());
			} catch (InputDataException e) {
				fail(e.getMessage());
			}
		}

	}

}
