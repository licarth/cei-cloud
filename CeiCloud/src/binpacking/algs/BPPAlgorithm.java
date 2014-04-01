package binpacking.algs;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;
import common.algorithm.OfflineAlgorithm;
import common.problem.InputDataException;

/**
 * An algorithm that solves BPPs.
 * 
 * @author thomas
 *
 */
public abstract class BPPAlgorithm extends OfflineAlgorithm<BPP, BPPInstance> {
	public abstract BPPSol solve(BPPInstance ins) throws InputDataException;
}
