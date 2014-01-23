package VSCIFP.algs;

import java.util.ArrayList;
import java.util.List;

import VSCIFP.ItemCutException;

public class Item implements Comparable<Item>{
	private int size;
	
	public Item(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
