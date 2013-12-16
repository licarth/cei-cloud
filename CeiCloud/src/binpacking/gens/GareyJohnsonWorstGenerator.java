package binpacking.gens;

import java.util.ArrayList;
import java.util.List;

import binpacking.BPP;
import binpacking.OptimalKnownBPPInstance;
import common.generator.OptimalLibraryGenerator;
import common.problem.InputDataException;

/**
 * Generator based on Garey & Johnson worst case inctance for FFD alg (BPP Problem).
 * 
 * @author thomas
 *
 */
public class GareyJohnsonWorstGenerator extends OptimalLibraryGenerator<BPP, OptimalKnownBPPInstance> {

	/**
	 * @param problem
	 * @param m the parameter specified in the GareyJohnson
	 */
	public GareyJohnsonWorstGenerator(BPP problem, int m) {
		super(problem);
		this.m = m;
	}

	/**
	 * Parameter as defined in the Garey & Johnson book "Coping with NP-Complete Problems",
	 * fig.6.3 p.127
	 */
	private int m;

	private int epsilon = 1;
	
	@Override
	public List<OptimalKnownBPPInstance> generateInstances() throws InputDataException {
		List<OptimalKnownBPPInstance> l = new ArrayList<OptimalKnownBPPInstance>();
		List<Integer> itemSizes = new ArrayList<Integer>();
		checkGeneratorinput();
		
		//1 instance
		for (int j = 1; j <= 6 * m; j++) {
			itemSizes.add(getProblem().getBinSize() / 2 + epsilon);
		}
		
		for (int j = 6 * m + 1; j <= 12 * m; j++) {
			itemSizes.add(getProblem().getBinSize() / 4 + 2*epsilon);
		}
		
		for (int j = 12 * m + 1; j <= 18 * m; j++) {
			itemSizes.add(getProblem().getBinSize() / 4 + epsilon);
		}
		
		for (int j = 18 * m + 1; j <= 30 * m; j++) {
			itemSizes.add(getProblem().getBinSize() / 4 - 2*epsilon);
		}
		
		l.add(new OptimalKnownBPPInstance(getProblem(), itemSizes, 9 * m));
		return l;
	}

	private void checkGeneratorinput() throws InputDataException{
		
		//Il faut epsilon << 1/4 * getProblem().getBinSize(). On exige aussi que binSize soit divisible par 4
		if (getProblem().getBinSize() % 4 != 0) throw new InputDataException("Bin size should be a multiple of 4 for GareyJohnson instances");
		if (epsilon >= 8 * getProblem().getBinSize() / 4) throw new InputDataException("Bins should be big enough.");
		
	}
}
