package VSCIFP.algs;

import java.util.ArrayList;
import java.util.List;

import VSCIFP.ItemCutException;

public class Item implements Comparable<Item>{
	private int size;
	private Item parent;
//	private List<Item> children;

	public boolean comesFromCut() {
		return (parent != null);
	}

	public Item(int size) {
		this.size = size;
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
	 * Cuts current item into 2  pieces.
	 * @return the children resulting from this cut.
	 * @throws Exception 
	 */
	public List<Item> cut(final int i) throws ItemCutException {
		if (i == 0) throw new ItemCutException("Cannot cut at zero !");
		if (i >= getSize()) throw new ItemCutException("Cannot cut over item size nor exactly at item size !");
		ArrayList<Item> children = new ArrayList<>();
		Item left = new Item(i);
		Item right = new Item(getSize() - i);
		left.setParent(this);
		right.setParent(this);
		children.add(left);
		children.add(right);
		return children;
	}

	@Override
	public int compareTo(Item o) {
		return getSize() - o.getSize();
	}

	@Override
	public String toString() {
		return ""+getSize();
	}

}
