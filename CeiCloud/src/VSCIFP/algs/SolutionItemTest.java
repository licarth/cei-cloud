package VSCIFP.algs;

import java.util.List;

import org.junit.Test;

public class SolutionItemTest {

	@Test
	public void testGetLeaves() throws ItemCutException {
		Item root = new Item(10);
		SolutionItem solutionRoot = new SolutionItem(root, null);
		
		List<SolutionItem> children = solutionRoot.cut(10, 5);
//		children.get(1).cut(10, 1);
		
		System.out.println(solutionRoot.getLeaves());
	
	}

}
