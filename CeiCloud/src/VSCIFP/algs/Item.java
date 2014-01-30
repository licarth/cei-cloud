package VSCIFP.algs;


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
		if (getSize() != o.getSize())
			return getSize() - o.getSize();
		else return hashCode() - o.hashCode();
	}

	@Override
	public String toString() {
		return ""+getSize();
	}
	
}
