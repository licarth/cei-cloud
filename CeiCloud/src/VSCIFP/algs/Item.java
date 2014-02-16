package VSCIFP.algs;


public class Item implements Comparable<Item>{
	protected int size;
	protected Item parent;
	protected Item filsG;
	protected Item filsD;
	
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
	
	public Item getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return ""+getSize();
	}
	
	public Item getFilsG() {
		return filsG;
	}

	public void setFilsG(Item filsG) {
		this.filsG = filsG;
	}

	public Item getFilsD() {
		return filsD;
	}

	public void setFilsD(Item filsD) {
		this.filsD = filsD;
	}
	
}
