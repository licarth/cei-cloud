package binpacking;

import common.ProblemFactory;

/**
 * A factory that creates BPP problems. (NOT instances of those problems !)
 * 
 * @author thomas
 *
 */
public class BPPFactory implements ProblemFactory<BPP>{
	
//	public static BPP create(int binsize) {
//		return null;
//	}

	public BPP createProblem(int binsize) {
		return new BPP(binsize);
	}
	
}
