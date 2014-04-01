package binpacking.examples;
import java.util.List;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.BPPSol;
import binpacking.algs.FF;
import binpacking.algs.FFD;
import binpacking.algs.NF;
import binpacking.algs.NFD;
import binpacking.gens.OptimalUniformBPPGenerator;
import binpacking.gens.UniformBPPGenerator;
import common.problem.InputDataException;
import common.solution.ISolution;

public class NonOptimal {

	public static void main(String[] args) throws InputDataException {


		BPP bpp = new BPP(10);		//	Problem declaration with a max bin size parameter of 10.

		FFD ffd = new FFD();		//	Algorithms declarations
		FF ff = new FF();
		NF nf = new NF();
		NFD nfd = new NFD();

		UniformBPPGenerator gen = new UniformBPPGenerator(bpp, 200);					//	Instance generator declaration

		List<BPPInstance> l = gen.generateInstances(3);
		for (BPPInstance iBpp : l) {
			BPPSol ffSol = ff.solve(iBpp);		//	Solves current instance with First-Fit algorithm.
			BPPSol ffdSol = ffd.solve(iBpp);
			BPPSol nfSol = nf.solve(iBpp);
			BPPSol nfdSol = nfd.solve(iBpp);

			System.out.println(ffSol);			//	Prints solutions & costs
			System.out.println(ffdSol);
			System.out.println(nfSol);
			System.out.println(nfdSol);
			System.out.println();
		}
	}

}
