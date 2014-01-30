package VSCIFP.algs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.Iterators;

import VSCIFP.Bin;
import VSCIFP.BinType;
import VSCIFP.ItemCutException;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;
import binpacking.algs.NF;
import common.Utils;
import common.algorithm.Algorithm;
import common.problem.InputDataException;

/**
 * FF-style, tries biggest bins first. NOT THREAD-SAFE: Create one new instance of CIFFD per solver.
 * 
 * @author thomas
 *
 */
public class NFL extends Algorithm<VSCIFP, VSCIFPInstance>{

	private VSCIFPSolution sol;

	public NFL() {
	}

	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins)
			throws InputDataException {
		sol = new VSCIFPSolution(this, ins);

		List<Bin> bins = new ArrayList<Bin>();
		// Sort desc. (OFF-LINE ALG)
		Utils.sortDesc(sol.getItems());

		try {
			
			for (SolutionItem item : getI) {
				
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}
		return sol;
	}

}