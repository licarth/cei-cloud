package binpacking.examples;
import java.util.List;

import binpacking.BPP;
import binpacking.BPPSol;
import binpacking.OptimalKnownBPPInstance;
import binpacking.algs.FF;
import binpacking.algs.FFD;
import binpacking.algs.NF;
import binpacking.algs.NFD;
import binpacking.gens.MartelloTothBPPLibraryGenerator;

import common.problem.InputDataException;
import common.solution.OptimalCostNotKnownException;

public class Optimal {

	public static void main(String[] args) throws InputDataException, OptimalCostNotKnownException {


		BPP bpp = new BPP(10);		//	Problem declaration with a max bin size parameter of 10.

		FFD ffd = new FFD();		//	Algorithms declarations
		FF ff = new FF();
		NF nf = new NF();
		NFD nfd = new NFD();

		MartelloTothBPPLibraryGenerator gen = new MartelloTothBPPLibraryGenerator(bpp);			//	Instance generator declaration

		List<OptimalKnownBPPInstance> l = gen.generateInstances();
		int i=0;
		for (OptimalKnownBPPInstance iBpp : l) {
			if (i<10) i++; else break;			//	Limits to 10 the number of instances solved.
			BPPSol ffSol = ff.solve(iBpp);		//	Solves current instance with First-Fit algorithm.
			BPPSol ffdSol = ffd.solve(iBpp);
			BPPSol nfSol = nf.solve(iBpp);
			BPPSol nfdSol = nfd.solve(iBpp);
			
			System.out.println("Instance Optimal cost : "+iBpp.getOptimalSolution().getCost());		//	Prints instance optimal cost.
			System.out.println(ffSol);																//	Prints solutions & costs.
			System.out.println(ffdSol);
			System.out.println(nfSol);
			System.out.println(nfdSol);
			System.out.println();
		}
	}

}
