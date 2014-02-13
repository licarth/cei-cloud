package VSCIFP.algs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import VSCIFP.ItemCutException;
import common.solution.ISolution;

public class SolutionItem extends Item{

	private int timesCut = 0;
//	private ISolution solution;
	private Item parent;
	private SolutionItem filsG;
	private SolutionItem filsD;
	
	public SolutionItem(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	/**
	 * From original item. To create the root SolutionItem
	 * 
	 * @param originalItem
	 * @param solution
	 */
	public SolutionItem(Item originalItem, ISolution solution) {
		super(originalItem.getSize());
//		this.solution = solution;
		this.parent = originalItem;
	}
	
	/**
	 * From another solution Item
	 * 
	 * @param root
	 * @param solution
	 */
	public SolutionItem(SolutionItem parent, int size) {
		super(size);
		this.parent = parent;
//		this.solution = parent.solution;
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Cuts current item into 2  pieces. If maxNumOfCuts is compatible.
	 * @return the children resulting from this cut.
	 * @throws Exception 
	 */
	public List<SolutionItem> cut(final int maxNumOfCuts, final int where) throws ItemCutException {
		if (where == 0) throw new ItemCutException("Cannot cut at zero !");
		if (where >= getSize()) throw new ItemCutException("Cannot cut over item size nor exactly at item size !");
		if (this.getTimesCut() + 1 > maxNumOfCuts) 
			throw new ItemCutException(String.format("Item can't be cut more than %s times !", maxNumOfCuts));
		ArrayList<SolutionItem> children = new ArrayList<>();
		filsG = new SolutionItem(this, where);
		filsD = new SolutionItem(this, getSize() - where);
		getRoot().countCut(); //Update times cut.
		children.add(filsG);
		children.add(filsD);
		return children;
	}
	
	/**
	 * Just used for root items.
	 * @throws Exception 
	 */
	private void countCut() {
		if (this.comesFromCut()) throw new RuntimeException("timesCut counter should be incremented only on root.");
		timesCut++;
	}

	public boolean comesFromCut() {
		return (parent != null && parent instanceof SolutionItem);
	}
	
	/**
	 * Returns the root SolutionItem
	 * 
	 * @return
	 */
	public SolutionItem getRoot() {
		if (comesFromCut()){
			//Then parent is necessarily of type SolutionItem.
			return ((SolutionItem) parent).getRoot();
		} else return this;
	}
	
	public Item getParent() {
		return parent;
	}
	
	public Item getOriginalItem(){
		return getRoot().getParent();
	}

	public void setParent(Item parent) {
		this.parent = parent;
	}
	
	public int getTimesCut() {
		if (comesFromCut()) return getRoot().getTimesCut();
		else return timesCut;
	}
	
	public Set<SolutionItem> getLeaves(){
		return this.getLeavesRecursive(new TreeSet<SolutionItem>(Collections.reverseOrder()));
	}
	
	private TreeSet<SolutionItem> getLeavesRecursive(TreeSet<SolutionItem> leaves){
		
		if (filsG != null){
			leaves = filsG.getLeavesRecursive(leaves);
		} else if (filsD == null){
			leaves.add(this);
			return leaves;
		}
		
		if (filsD != null){
			filsD.getLeavesRecursive(leaves);			
		}
		
		return leaves;
	}

	public SolutionItem getFilsG() {
		return filsG;
	}

	public void setFilsG(SolutionItem filsG) {
		this.filsG = filsG;
	}

	public SolutionItem getFilsD() {
		return filsD;
	}

	public void setFilsD(SolutionItem filsD) {
		this.filsD = filsD;
	}

	public void setTimesCut(int timesCut) {
		this.timesCut = timesCut;
	}
	
}
