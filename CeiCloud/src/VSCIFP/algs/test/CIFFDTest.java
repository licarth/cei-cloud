package VSCIFP.algs.test;

import java.util.HashSet;
import java.util.Set;
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
	final VSCIFP problem = new VSCIFP(20, 3, 1, 20, 1);
	final CIFFD ciffd = new CIFFD();
	
	/**
	 * @throws InputDataException
	 */
	@Test
	public void test() throws InputDataException {
		
		//A generator for test purposes only that generates only one type of instance, always the same.
		VSCIFPGenerator gen = new VSCIFPGenerator(problem, 1000) {
			
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
				instance.getItems().add(new Item(4));
				instance.getItems().add(new Item(12));
//				Item a = new Item(12);
//				Item b = new Item(12);
//				System.out.println(a.equals(b));
				instance.getItems().add(new Item(12));
//				Set<Item> s = new HashSet<>();
//				s.add(a);
//				s.add(b);
				return instance;
			}
		};
		
		VSCIFPGenerator genFail = new VSCIFPGenerator(problem, 1000) {
			
			@Override
			public VSCIFPInstance generateInstance() throws InputDataException {
				VSCIFPInstance instance = new VSCIFPInstance(problem);
				instance.binTypes = new TreeSet<BinType>(){{
					add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
					add(new BinType(10, 10));
					add(new BinType(3, 3));
				}};
				instance.getItems().add(new Item(14));
				return instance;
			}
		};
		
VSCIFPGenerator genDiv = new VSCIFPGenerator(problem, 1000) {
			
			@Override
			public VSCIFPInstance generateInstance() throws InputDataException {
				VSCIFPInstance instance = new VSCIFPInstance(problem);
				instance.binTypes = new TreeSet<BinType>(){{
					add(new BinType(getProblem().getMaxBinCapacity(), getProblem().getMaxBinCapacity()));
					add(new BinType(20, 20));
					add(new BinType(8, 8));
//					add(new BinType(10, 10));
					add(new BinType(5, 5));
				}};
				instance.getItems().add(new Item(14));
				instance.getItems().add(new Item(14));
				instance.getItems().add(new Item(14));
				instance.getItems().add(new Item(14));
				instance.getItems().add(new Item(5));
				instance.getItems().add(new Item(6));
				instance.getItems().add(new Item(7));
				instance.getItems().add(new Item(8));
				instance.getItems().add(new Item(4));
				instance.getItems().add(new Item(12));
				return instance;
			}
		};
		
//		VSCIFPInstance i = gen.generateInstance();
//		VSCIFPInstance i = genFail.generateInstance();
		VSCIFPInstance i = genDiv.generateInstance();
		
//		System.out.println(ciffd.solve(i));
		System.out.println(ciffd.solve(i).getBins());
		
	}

}
