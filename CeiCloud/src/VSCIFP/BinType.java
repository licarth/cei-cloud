package VSCIFP;


public class BinType implements Comparable<BinType>{
	int cost;
	int capacity;
	
	public BinType(int capacity, int cost) {
		super();
		this.cost = cost;
		this.capacity = capacity;
	}
	
	public double efficiency() {
		return (double) capacity/cost;
	}
	
	@Override
	public String toString() {
		return String.format("[cap : %s, (cost : %s, eff : %.2f)]", capacity, cost, efficiency());
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
	
	public boolean fitsIfEmpty(Integer item) {
		return (item <= capacity);
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
