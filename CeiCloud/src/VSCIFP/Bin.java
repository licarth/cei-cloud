package VSCIFP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import VSCIFP.algs.Item;

public class Bin {

	private final BinType type;
	private boolean open = true;
	private final List<Item> content;
	private int fillCount = 0;

	@Override
	public String toString() {
		return "{fillCount : "+fillCount+"/"+type.capacity+", content: "+content.toString();
	}

	public Bin(BinType type) {
		super();
		this.content = new ArrayList<>();
		this.type = type;
	}
	
	public Bin(BinType type, List<Item> content) {
		super();
		this.content = content;
		this.type = type;
	}

	public boolean isOpen() {
		return open;
	}

	public void add(Item item) throws Exception {
		if (item == null || item.getSize() == 0) throw new Exception("Null item or item size = 0!");
		if (fits(item)) {
			content.add(item);
			fillCount += item.getSize();
		} else {
			throw new Exception();
		}
	}

	public void close() {
		this.open = false;
	}
	
	public int getSpaceLeft() {
		return  type.capacity - fillCount;
	}

	public BinType getType() {
		return type;
	}

	public List<Item> getContent() {
		return Collections.unmodifiableList(content);
	}

	public boolean fits(Item item) {
		return (item.getSize() <= type.capacity - fillCount);
	}

	public int getFillCount() {
		return fillCount;
	}

	public boolean isFull() {
		return (fillCount >= type.capacity);
	}
	
	public boolean isEmpty() {
		return (fillCount == 0);
	}

	/**
	 * Copies this bin's content to a new bin.
	 * 
	 * @return
	 */
	public Bin copyToNewBin() {
		Bin clone = null;
		try {
			clone = this.clone();
			this.reset();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clone;
	}
	
	@Override
	protected Bin clone() throws CloneNotSupportedException {
		ArrayList<Item> cloneContent = new ArrayList<Item>(this.content);
		Bin clone = new Bin(this.type, cloneContent);
		clone.setFillCount(this.fillCount);
		return clone;
	}
	
	private void reset() {
		fillCount = 0;
		content.clear();
	}

	private void setOpen(boolean open) {
		this.open = open;
	}

	private void setFillCount(int fillCount) {
		this.fillCount = fillCount;
	}
	
	public void addAll(Collection<Item> items) throws Exception {
		for (Item item : items) {
			add(item);
		}
	}

}
