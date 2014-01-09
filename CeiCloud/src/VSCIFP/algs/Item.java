package VSCIFP.algs;

import java.util.ArrayList;
import java.util.List;

import VSCIFP.ItemCutException;

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
	 * Cuts current item into 2  pieces.
	 * @return the children resulting from this cut.
	 * @throws Exception 
	 */
	public List<Item> cut(final int i) throws ItemCutException {
		if (hasBeenCut()) throw new ItemCutException("This item has children, it cannot be cut. Please cut its children.");
		if (i == 0) throw new ItemCutException("Cannot cut at zero !");
		if (i >= getSize()) throw new ItemCutException("Cannot cut over item size nor exactly at item size !");
		children = new ArrayList<>();
		Item left = new Item(i);
		Item right = new Item(getSize() - i);
		left.setParent(this);
		right.setParent(this);
		children.add(left);
		children.add(right);
		return children;
	}

	public List<Item> getChildren() {
		return children;
	}

	public void setChildren(List<Item> children) {
		this.children = children;
	} 


	public boolean hasBeenCut() {
		return (children != null && children.size() > 0);
	}

	@Override
	public int compareTo(Item o) {
		return getSize() - o.getSize();
	}

	@Override
	public String toString() {
		return ""+getSize();
	}

	/**
	 * Returns number of children of this item.
	 */
	public int timesCut() {
		if (!hasBeenCut()) return 0;
		else return getChildren().size();
	}

}
