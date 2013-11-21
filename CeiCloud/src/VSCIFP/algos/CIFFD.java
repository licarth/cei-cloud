package VSCIFP.algos;

import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;

import binpacking.BPP;
import common.Algorithm;
import common.ProblemInputDataException;
import common.Solution;
import common.Utils;

public class CIFFD implements Algorithm<BPP>{

	@Override
	public Solution<BPP> solve(BPP ins) throws ProblemInputDataException {
		// Sort desc. (OFF-LINE ALG)
		int[] sortedItemSizes = ArrayUtils.clone(ins.getItemSizes());
		Utils.sortDesc(sortedItemSizes);
		System.out.println(Arrays.toString(sortedItemSizes));
		
		
		
		
		return null;

	}
	
	private void split() {
		
	}
	
}