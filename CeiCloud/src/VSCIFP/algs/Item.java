package VSCIFP.algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Collections2;

public class Item implements Comparable<Item>{
	private int size;
	private Item parent;
	private List<Item> children;
	
	public boolean comesFromCut() {
		return (parent != null);
	}
	
	public Item(int size) {
		this.size = size;
	}
	
	/**
	 * Finds the number of times an item has been cut to give current item.
	 * 
	 * @return
	 */
	public int getNumCuts() {
		int n = 0;
		Item i = this;
		while (i.comesFromCut())  {
			n++;
			i = i.getParent();
		}
		return n;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Item getParent() {
		return parent;
	}

	public void setParent(Item parent) {
		this.parent = parent;
	}
	
	/**
	 * Cuts current item into 2 or more pieces.
	 * @return the list of child items.
	 */
	public List<Item> cut(final Integer ... ints) {
		children = new ArrayList<>();
		List<Integer> l = Arrays.asList(ints);
		Collections.sort(l);
		for (Integer i : l) {
			
		}
		return children;
	}

	public List<Item> getChildren() {
		return children;
	}

	public void setChildren(List<Item> children) {
		this.children = children;
	} 
	
	
	public boolean hasBeenCut() {
		return (children != null || children.size() > 0);
	}

	@Override
	public int compareTo(Item o) {
		return getSize() - o.getSize();
	}
}
