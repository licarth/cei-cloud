package VSCIFP.gens;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import VSCIFP.Bin;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.algs.Item;
import VSCIFP.algs.SolutionItem;

public class LinearVSCIFPGeneratorTest {

	@BeforeClass
	public static void init() {
	}

	@Test
	public void testGenerateInstance() throws Exception {

		VSCIFP p = new VSCIFP(100, 10, 1, 1);
		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p, 1000);

		VSCIFPInstance ins = gen.generateInstance();

		//Check that all items in getItems() are also in bins somewhere...

		HashMap<Item, Integer> itemsAfterPaste = new HashMap<>();

		for (Item item : ins.getItems()) {
			itemsAfterPaste.put(item, item.getSize());
		}
		System.out.println("Number of cuts done : "+(ins.getOptimalSolution().getItemLeaves().size() - itemsAfterPaste.size()));

		//		assertEquals(itemsAfterPaste.si, actual);

		//To check optimal cost.
		int totalCost = 0;
		for (Bin bin : ins.getOptimalSolution().getBins()) {
			//			//All items in bins have to be found in 
			for (Item item : bin.getContent()) {
				SolutionItem solItem = (SolutionItem) item;
				//						assertEquals(true, itemsAfterPaste.remove(solItem.getOriginalItem()));
				int counter = itemsAfterPaste.get(solItem.getOriginalItem());
				itemsAfterPaste.put(solItem.getOriginalItem(), counter - item.getSize());
				if (counter - item.getSize() == 0) {
					itemsAfterPaste.remove(solItem.getOriginalItem());
				}
			}
			//					To guarantee that optimal solution packing is optimal, all bins have to be full.
			assertEquals(true, bin.isFull());
			totalCost += bin.getType().cost;
		}

		//itemsAfterPaste must be empty if we found all items in bins ! 
		assertEquals(itemsAfterPaste.isEmpty(), true);

		//Optimal cost check.
		assertEquals(ins.getOptimalSolution().getCost(), totalCost);

		for (Item item : ins.getItems()) {
			assertEquals(true, item.getSize() < ins.getProblem().getItemMaxSize());
		}
	}

	@Test
	public void testFillBins() throws Exception {

	}

}
