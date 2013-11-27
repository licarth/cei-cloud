package common;

import java.util.List;
import java.util.Random;

public interface LibraryGenerator<P extends Problem> {
	
	/**
	 * Generates n instances of the problem, with one single seed.
	 * 
	 * @param n qty of instances to generate.
	 * @return
	 */
	List<P> generateInstances();
	
}
