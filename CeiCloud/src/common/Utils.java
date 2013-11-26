package common;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;


public class Utils {
	
	/**
	 * @param a
	 * 
	 * Sorts a in desc. order.
	 * This method edits a directly.
	 * 
	 */
	public static void sortDesc(int[] a){
		Arrays.sort(a);
		ArrayUtils.reverse(a);
	}

	/**
	 * @param a
	 * @return
	 * 
	 * Sums elements of a.
	 * 
	 */
	public static int sum(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum = sum + a[i];
		}
		return sum;
	}
	
	/**
	 * @param a
	 * @return
	 * 
	 * Sums elements of a.
	 * 
	 */
	public static int sum(List<Integer> a) {
		int sum = 0;
		for (Integer i : a) {
			sum = sum + i;
		}
		return sum;
	}
	
	
	/**
	 * @param a
	 * @return
	 * 
	 * Sums elements of a.
	 * 
	 */
	public static BigInteger bigSum(int[] a) {
		BigInteger sum = new BigInteger("0");
		for (int i = 0; i < a.length; i++) {
			sum.add(new BigInteger(a[i]+""));
		}
		return sum;
	}
	
	
	/**
	 * @param c
	 * @return
	 * 
	 * Returns a sorted list from the given set of Comparable objects.
	 * 
	 */
	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c, boolean reversed) {
	  List<T> list = new ArrayList<T>(c);
	  if (reversed) Collections.sort(list, Collections.reverseOrder()); else Collections.sort(list);
	  return list;
	}
}
