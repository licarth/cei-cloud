package VSCIFP.algs;

import java.util.TreeSet;

import org.junit.Test;

import binpacking.BPPInstance;
import binpacking.BPPSol;
import VSCIFP.BinType;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.gens.VSCIFPGenerator;
import common.problem.InputDataException;
import common.solution.OptimalCostNotKnownException;

public class NFLTest {

	@Test
	public void test() throws InputDataException, OptimalCostNotKnownException {

		final VSCIFP problem = new VSCIFP(10, 2, 1, 1);
		
		VSCIFPGenerator genDiv = new VSCIFPGenerator(problem, 1000) {

			@Override
			public VSCIFPInstance generateInstance() throws InputDataException {
				VSCIFPInstance instance = new VSCIFPInstance(problem);
				instance.binTypes = new TreeSet<BinType>(){{
//					add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
//					add(new BinType(20, 20));
					add(new BinType(8, 8));
					//					add(new BinType(10, 10));
					add(new BinType(4, 4));
				}};
				instance.getItems().add(new Item(16));
//				instance.getItems().add(new Item(14));
//				instance.getItems().add(new Item(10));
//				instance.getItems().add(new Item(14));
//				instance.getItems().add(new Item(14));
//				instance.getItems().add(new Item(5));
//				instance.getItems().add(new Item(6));
//				instance.getItems().add(new Item(7));
//				instance.getItems().add(new Item(8));
				instance.getItems().add(new Item(4));
				instance.getItems().add(new Item(2));
				instance.getItems().add(new Item(2));
//				instance.getItems().add(new Item(12));
//				instance.getItems().add(new Item(10));
//				instance.getItems().add(new Item(12));
//				instance.getItems().add(new Item(12));
//				instance.getItems().add(new Item(12));
//				instance.getItems().add(new Item(12));
//				instance.getItems().add(new Item(10));
//				instance.getItems().add(new Item(10));
				return instance;
			}
		};

		CNFL nfl = new CNFL();
		CIFFD ciffd = new CIFFD();
		System.out.println(ciffd.solve(genDiv.generateInstance()));
//		ciffd.solve(ins)
//		NFLWithBinTypeOptimization nflOpt = new NFLWithBinTypeOptimization();
//		System.out.println(nflOpt.solve(genDiv.generateInstance()));
		
		

	}

}
