package common;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Assume;
import org.junit.Test;

import com.google.common.collect.Iterators;

public class UtilsTest {

	@Test
	public void testCyclicPermut() {
		int[] p;
		Integer[] a = new Integer[]{10,572,7,34,1,10};
		List<Integer> l;
		
		l = Arrays.asList(Arrays.copyOf(a, a.length)); //Because changes made to the list are reflected to the array
		p = new int[]{1,2};		//Defines the permutation.
		assertArrayEquals(new Integer[]{10,7,572,34,1,10}, Utils.cyclicPermut(l, p).toArray(new Integer[0]));
		assertArrayEquals(new Integer[]{10,572,7,34,1,10}, a); //a should still be the same.
		
		l = Arrays.asList(Arrays.copyOf(a, a.length));
		p = new int[]{0,1,2};	//Defines the permutation.
		assertArrayEquals(new Integer[]{7,10,572,34,1,10}, Utils.cyclicPermut(l, p).toArray(new Integer[0]));
		assertArrayEquals(new Integer[]{10,572,7,34,1,10}, a); //a should still be the same.
		
//		int[] p = new int[]{1,2};		//Defines the permutation.
//		assertArrayEquals(new Integer[]{10,7,572,34,1,10}, Utils.cyclicPermut(l, p).toArray(new Integer[0]));
//		assertArrayEquals(new Integer[]{10,572,7,34,1,10}, a); //a should still be the same.
	}
	
	@Test
	public void oneCycleItTest() throws Exception {
		Integer[] a = new Integer[]{0,1,2,3,4,5};
		List<Integer> l = Arrays.asList(Arrays.copyOf(a, a.length));
		int offset = 2;
		int i = 0;
		Iterator<Integer> it = Utils.oneCycleIt(l, offset);
		while (it.hasNext()) {
			int elem = it.next();
			assertEquals((i+offset) % a.length, elem);
			System.out.println(elem);
			if (elem == 0) System.out.println(Iterators.size(it));
			i++;
		}
		
	}
}
