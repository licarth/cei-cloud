package common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

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
}
