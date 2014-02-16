package VSCIFP.algs;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import VSCIFP.Bin;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import VSCIFP.gens.LinearVSCIFPGenerator;

/**
 * Tests if CFFf returns valid solutions.
 * Uses a linear generator.
 * 
 * @author thomas
 *
 */
public class CFFfSolutionsTest {

	@BeforeClass
	public static void init() {
	}

	@Test
	public void testSolutions() throws Exception {

		VSCIFP p = new VSCIFP(100, 10, 1, 1);
		LinearVSCIFPGenerator gen = new LinearVSCIFPGenerator(p, 1000);

		VSCIFPInstance ins = gen.generateInstance();

		CFFf cfff = new CFFf(0.5);

		VSCIFPSolution sol = cfff.solve(ins);

		//Check that all items in getItems() are also in bins somewhere...

		HashMap<Item, Integer> pastedInitialItems = new HashMap<>();

		for (Item item : ins.getItems()) {
			pastedInitialItems.put(item, item.getSize());
		}

		int totalCost = 0;
		for (Bin bin : sol.getBins()) {
			//			//All items in bins have to be found in 
			for (Item item : bin.getContent()) {
				SolutionItem solItem = (SolutionItem) item;
				int counter = pastedInitialItems.get(solItem.getOriginalItem());
				pastedInitialItems.put(solItem.getOriginalItem(), counter - item.getSize());
				if (counter - item.getSize() == 0) {
					pastedInitialItems.remove(solItem.getOriginalItem());
				}
			}
			totalCost += bin.getCost();
		}

		//itemsAfterPaste must be empty if we found all items in bins ! 
		assertEquals(pastedInitialItems.isEmpty(), true);
		
		//Cost check :
		assertEquals(sol.getCost(), totalCost);
		
	}
}
