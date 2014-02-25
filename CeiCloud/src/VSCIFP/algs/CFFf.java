package VSCIFP.algs;

import java.sql.NClob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import VSCIFP.Bin;
import VSCIFP.BinType;
import VSCIFP.VSCIFP;
import VSCIFP.VSCIFPInstance;
import VSCIFP.VSCIFPSolution;
import common.algorithm.Algorithm;
import common.problem.InputDataException;

/**
 * 
 * Warning : NOT THREAD-SAFE
 * 
 * @author thomas
 *
 */
public class CFFf extends Algorithm<VSCIFP, VSCIFPInstance>{

	private double f;

	public CFFf(double f) {
		if (f<0 || f>1) throw new RuntimeException("Fill factor has to be between 0 and 1");
		this.f = f;
	}

	private LinkedList<Bin> openBins;
	private LinkedList<Bin> closedBins;
	private VSCIFPInstance ins;
	private VSCIFPSolution sol;
	/**
	 * SolutionItems who have instance items as parents.
	 */
	private List<SolutionItem> items;

	/**
	 * 
	 * Keeps a pointer for NFC.
	 * 
	 */
	private Bin currentNFCBin;

	@Override
	public VSCIFPSolution solve(VSCIFPInstance ins2)
			throws InputDataException {
		try {
			//Initialize data structures.
			init(ins2);

			iterateItems:
				for (SolutionItem item : items) {
					SolutionItem remainder = null;
					if (item.getSize() <= ins.getBinTypeOfMaxCapacity().getCapacity()){
						//Use NFC if item is smaller than bmax.
						if (currentNFCBin == null){
							currentNFCBin = openBin(ins.getBinTypeOfMaxCapacity());
						}

						SolutionItem nfcItem = item;
						nfc:
							while(true) {
								//NFC START
								if (currentNFCBin.fits(item)){
									currentNFCBin.add(item);

								} else {
									if (!nfcItem.canBeCut(ins.getProblem().getMaxNumSplits())){
										//ex : zero cuts allowed and currentNFCBin is not empty.
										//Open a new bin for nfc without closing the former currentNFCBin. 
										currentNFCBin = openBin(ins.getBinTypeOfMaxCapacity());
										currentNFCBin.add(nfcItem);
									} else {
										List<SolutionItem> children = nfcItem.cut(ins.getProblem().getMaxNumSplits(), currentNFCBin.getSpaceLeft());
										//Create new bin for nfc.
										currentNFCBin.add(children.get(0));
										closeAndRemoveBin(currentNFCBin);
										currentNFCBin = openBin(ins.getBinTypeOfMaxCapacity());
										if (children.get(1).getSize() <= currentNFCBin.getSpaceLeft()) {
											currentNFCBin.add(children.get(1));
										} else {
											nfcItem = children.get(1);
											continue nfc;
										}
									}
								}
								if (currentNFCBin.isFull()){
									//Close it and open a new one of same type.
									closeAndRemoveBin(currentNFCBin);
									currentNFCBin = null;	//We don't explicitely open a new bin of that type.
								}
								continue iterateItems;
							}
					} else {
						//item from A+
						List<SolutionItem> children;
						remainder = item;
						
						do {
							children = remainder.cut(ins.getProblem().getMaxNumSplits(), ins.getBinTypeOfMaxCapacity().capacity);
							//Create new bin of capacity bmax and close it.
							Bin newBin = new Bin(ins.getBinTypeOfMaxCapacity());
							newBin.add(children.get(0));
							//Bin is full
							closedBins.add(newBin);
							sol.addItemToBin(newBin, children.get(0));
							//Keep remainder.
							remainder = children.get(1);
						} while (remainder.getSize() > ins.getBinTypeOfMaxCapacity().capacity);
					}

					//Here remainder has to be not null
					assert remainder != null : "Remainder has to be not null";

					Bin firstOpenBinWhereItemFits = firstOpenBinWhereItemFits(remainder);
					if (firstOpenBinWhereItemFits!=null){
						//TODO Et si c'est celle de NFC ?
						firstOpenBinWhereItemFits.add(remainder);
						if (firstOpenBinWhereItemFits.isFull()) {
							if (firstOpenBinWhereItemFits == currentNFCBin) currentNFCBin = null;
							closeAndRemoveBin(firstOpenBinWhereItemFits);
						}
					} else {
						if (remainder.getSize() <= ins.getBinTypeOfMaxCapacity().capacity/2){
							//remainder is small
							//Pack into bin of capacity bmax and close it.
							Bin newBMaxBin = new Bin(ins.getBinTypeOfMaxCapacity());
							newBMaxBin.add(remainder);
							closedBins.add(newBMaxBin);
						} else {
							//remainder is big
							BinType binType = getSmallestBinTypeOfCapacityBetweenSizeAndSizeOverF(remainder);
							if (binType == null){
								//Chose bin of bmax.
								binType = ins.getBinTypeOfMaxCapacity();
							}
							Bin binChosen = new Bin(binType);
							//Pack remainder into bin binToPackItemInto
							binChosen.add(remainder);
							if (binChosen.isFull()){
								closedBins.add(binChosen);
							} else {
								openBins.add(binChosen);
							}
						}
					}

				}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		//Close open bins.
		for (Bin bin : openBins) {
			if (!bin.isEmpty()){
				closedBins.add(bin);
			}
		}
		openBins.clear();

		//Populate solution:
		sol.setItems(items);
		sol.setBins(new HashSet<>(closedBins));
		int totalCost = 0;
		for (Bin bin : closedBins) {
			totalCost += bin.getCost();
		}
		sol.setTotalCost(totalCost);

		return sol;
	}

	private BinType getSmallestBinTypeOfCapacityBetweenSizeAndSizeOverF(
			SolutionItem remainder) {
		double lowerBound = remainder.getSize();
		double upperBound = lowerBound/f;
		BinType res = null;
		for (BinType bt : ins.getBinTypes()) {
			if (bt.capacity <= upperBound && bt.capacity >= lowerBound){
				if (res == null || res.capacity > bt.capacity) res = bt;
			}
		}
		return res;
	}

	private Bin firstOpenBinWhereItemFits(SolutionItem remainder) {
		for (Bin bin : openBins) {
			if (bin.fits(remainder)) return bin;
		}
		return null;
	}

	private Bin openBin(BinType binType) {
		Bin newBin = new Bin(binType);
		openBins.add(newBin);
		return newBin;
	}

	private void closeAndRemoveBin(Bin bin) throws Exception {
		closedBins.add(bin);
		if(openBins.remove(bin) == false) throw new Exception("Tried to remove a bin that was not in collection.");
	}

	/**
	 * Initializes data structures.
	 * @param ins2
	 */
	private void init(VSCIFPInstance ins2) {
		this.ins = ins2;
		openBins = new LinkedList<>();
		closedBins = new LinkedList<>();
		sol = new VSCIFPSolution(this, ins);
		currentNFCBin = null;
		items = new ArrayList<SolutionItem>(ins.getItems().size());
		for (Item item : ins.getItems()) {
			items.add(new SolutionItem(item, sol));
		}
	}

}
