package VSCIFP.algs.test;

import java.util.TreeSet;

import org.junit.Test;

import VSCIFP.BinType;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.algs.CIFFD;
import VSCIFP.algs.Item;
import VSCIFP.gens.VSCIFPGenerator;
import common.problem.InputDataException;

public class CIFFDTest {
	final VSCIFP problem = new VSCIFP(10, 3, 1, 100, 1);
	final CIFFD ciffd = new CIFFD();
	
	@Test
	public void test() throws InputDataException {
		
		//A generator for test purposes only that generates only one type of instance, always the same.
		VSCIFPGenerator gen = new VSCIFPGenerator(problem) {
			
			@Override
			public VSCIFPInstance generateInstance() throws InputDataException {
				VSCIFPInstance instance = new VSCIFPInstance(problem);
				instance.binTypes = new TreeSet<BinType>(){{
					add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
					add(new BinType(5, 5));
					add(new BinType(3, 3));
					
//					add(new BinType((int)Math.floor(getProblem().getMaxBinCapacity() / 3), (int)Math.floor(getProblem().getMaxBinCapacity() / 3)));
//					add(new BinType((int)Math.floor(getProblem().getMaxBinCapacity() / 5), (int)Math.floor(getProblem().getMaxBinCapacity() / 5)));
				}};
//				instance.getItems().add(new Item(nextInt(getProblem().getItemMinSize(),getProblem().getItemMaxSize())));
				instance.getItems().add(new Item(5));
				instance.getItems().add(new Item(3));
				instance.getItems().add(new Item(11));
				
				return instance;
			}
		};
		
		
		VSCIFPInstance i = gen.generateInstance();
		
		System.out.println(ciffd.solve(i));
		
		
		
		
	}

}
