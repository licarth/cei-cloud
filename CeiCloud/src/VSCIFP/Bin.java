package VSCIFP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bin {

	private final BinType type;
	private boolean open = true;
	private final List<Integer> content = new ArrayList<>();
	private int fillCount = 0;

	@Override
	public String toString() {
		return "{capacity : "+type.capacity+", content: "+content.toString();
	}

	public Bin(BinType type) {
		super();
		this.type = type;
	}

	public boolean isOpen() {
		return open;
	}

	public void add(Integer item) throws Exception {
		if (isOpen() && fits(item)) {
			content.add(item);
			fillCount += item;
		} else {
			throw new Exception();
		}
	}

	public Integer close() {
		int itSize = 0;
		try {
			itSize = type.capacity - fillCount;
			if (itSize > 0) add(itSize);
			this.open = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itSize;
	}

	public BinType getType() {
		return type;
	}

	public List<Integer> getContent() {
		return Collections.unmodifiableList(content);
	}

	public boolean fits(Integer item) {
		return (item <= type.capacity - fillCount);
	}

	public int getFillCount() {
		return fillCount;
	}

	public boolean isFull() {
		return (fillCount >= type.capacity);
	}

}
