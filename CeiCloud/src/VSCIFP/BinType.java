package VSCIFP;

import VSCIFP.algs.Item;


public class BinType implements Comparable<BinType>{
	public int cost;
	public int capacity;
	
	public BinType(int capacity, int cost) {
		super();
		this.cost = cost;
		this.capacity = capacity;
	}
	
	public double unitCost() {
		return (double) cost/capacity;
	}
	
	@Override
	public String toString() {
		return String.format("[cap : %s, (cost : %s, unitCost : %.2f)]", capacity, cost, unitCost());
	}

	@Override
	public int hashCode() {
		//Overriden because should look at equals() method.
		return capacity;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof BinType) {
			return (this.cost == ((BinType) o).cost
					&& this.capacity == ((BinType) o).capacity);
		}
		return false;
	}

	@Override
	public int compareTo(BinType o) {
		//We sort them by capacity.
		return Integer.signum(this.capacity - o.capacity);
	}
	
	public boolean fitsIfEmpty(Item item) {
		return (item.getSize() <= capacity);
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
