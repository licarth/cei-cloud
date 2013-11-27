package binpacking;
import binpacking.gens.MartelloTothBPPLibraryGenerator;

import common.ProblemInputDataException;

public class Main {

	public static void main(String[] args) throws ProblemInputDataException {
		// TODO Auto-generated method stub
		
//		VSCIFP pb = new VSCIFP();
//		pb.binCapacities = new int[]{20, 15, 10};
//		pb.binCosts = new int[]{100, 150, 200};
//		pb.D = 100;
//		pb.e = 9999999;
//		pb.itemSizes = new int[]{2, 5, 10, 20, 25};
		
//		BPP bpp = new BPP(8, new int[]{8,8,2,5,4,3,3,2,2,1});
		
//		VizUtils.barChart(new int[]{8,8,2,5,4,3,3,2,2,1}, 10);
		
		
		
//		------------------------------------
//		FFD ffd = new FFD();
//		FF ff = new FF();
//			
////		UniformGeneratorBPP gen = new UniformGeneratorBPP();
//		UniformBPPGenerator gen = new UniformBPPGenerator();
//		List<BPP> l = gen.generateInstances(1);
//		for (BPP bpp : l) {
//			VizUtils.barChart(bpp.getItemSizes(), bpp.getItemMaxSize());
//			bpp.checkUniformItemsRepartition();
//			System.out.println(ffd.solve(bpp));
//			System.out.println(ff.solve(bpp));
//		}
//		------------------------------------
		
		MartelloTothBPPLibraryGenerator gen = new MartelloTothBPPLibraryGenerator();
		gen.generateInstances();
		
		
		
		
		
		
		
//		Set<BinType> binTypes = new HashSet<>();
//		binTypes.add(new BinType(10, 10));
//		binTypes.add(new BinType(100, 60));
//		binTypes.add(new BinType(15, 12));
//		VSCIFP p = new VSCIFP(binTypes, 10, 99999);
//		
//		CIFFD c = new CIFFD();
		
	}

}
