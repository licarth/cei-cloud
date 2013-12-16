package VSCIFP.algs;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

import binpacking.BPP;

import common.Utils;
import common.algorithm.Algorithm;
import common.problem.InputDataException;
import common.solution.Solution;

public class CIFFD extends Algorithm<BPP>{

	@Override
	public Solution<BPP> solve(BPP ins) throws InputDataException {
		// Sort desc. (OFF-LINE ALG)
		int[] sortedItemSizes = ArrayUtils.clone(ins.getItemSizes());
		Utils.sortDesc(sortedItemSizes);
		System.out.println(Arrays.toString(sortedItemSizes));
		
		
		
		
		return null;

	}
	
	private void split() {
		
	}
	
}