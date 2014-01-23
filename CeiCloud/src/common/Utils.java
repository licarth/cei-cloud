package common;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import VSCIFP.Bin;
import VSCIFP.algs.Item;

import com.google.common.collect.Iterators;


/**
 * @author thomas
 *
 */
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
	 * 
	 * Sorts a in desc. order.
	 * This method edits a directly.
	 * 
	 */
	public static void sortDesc(List l){
		Collections.sort(l);
		Collections.reverse(l);
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

	public static int sumItems(List<Item> l) {
		int sum = 0;
		for (Item i : l) {
			sum = sum + i.getSize();
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
	public static BigInteger bigSum(List<Integer> a) {
		BigInteger sum = new BigInteger("0");
		for (int i = 0; i < a.size(); i++) {
			sum.add(new BigInteger(a.get(i)+""));
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

	public static List<Integer> cloneIntList(List<Integer> list) {
		List<Integer> clone = new ArrayList<Integer>(list.size());
		for(Integer item: list) clone.add(new Integer(item.intValue()));
		return clone;
	}

	public static List<Item> cloneItemList(List<? extends Item> list) {
		List<Item> clone = new ArrayList<Item>(list.size());
		for(Item item: list) clone.add(new Item(item.getSize()));
		return clone;
	}
	
//	public static <T extends CloneVisible> List<T> cloneListRecursive(List<T> list) {
//		List<T> clone = new ArrayList<T>(list.size());
//		for(T i: list) clone.add(((Object)i).clone());
//		return clone;
//	}
	public static List<Bin> cloneBinList(List<Bin> list) throws CloneNotSupportedException {
		List<Bin> clone = new ArrayList<>(list.size());
		for(Bin bin: list) clone.add(bin.clone());
		return clone;
	}
	
	public static HashSet<Bin> cloneBinHashSet(HashSet<Bin> set) throws CloneNotSupportedException {
		HashSet<Bin> clone = new HashSet<>(set.size());
		for(Bin bin: set) clone.add(bin.clone());
		return clone;
	}

	/**
	 * Executes a circular permutation described in the permutation array.
	 * 
	 * @param list
	 * @param permutation
	 * @return
	 */
	public static List<Integer> cyclicPermut(List<Integer> list, int[] permutation){
		for (int k = 0; k < permutation.length-1; k++) {
			Collections.swap(list, permutation[0], permutation[k+1]);
		}
		return list;
	}

	/**
	 * Returns a bounded iterator that starts with an offset and makes exactly one cycle in the
	 * list.
	 * 
	 * @return
	 */
	public static <T extends Object> Iterator<T> oneCycleIt(Collection<T> col, int offset) {
		Iterator<T> it = Iterators.cycle(col);
		for (int i = 0; i < offset; i++) {
			it.next();
		}
		return Iterators.limit(it, col.size());
	}

	/**
	 * Transforms a set of Items into the list of their sizes.
	 * 
	 * @param items
	 * @return
	 */
	public static List<Integer> fromItemsToIntegers(Collection<Item> items) {
		List<Integer> l = new ArrayList<Integer>();
		for (Item i : items) {
			l.add(i.getSize());
		}
		return l;
	}

	public static List<Item> fromIntegersToItems(List<Integer> itemSizes) {
		List<Item> l = new ArrayList<Item>();
		for (Integer i : itemSizes) {
			l.add(new Item(i));
		}
		return l;
	}

	/**
	 * Union of two sets
	 * 
	 * @param setA
	 * @param setB
	 * @return
	 */
	public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new HashSet<T>(setA);
		tmp.addAll(setB);
		return tmp;
	}
	
	

}
