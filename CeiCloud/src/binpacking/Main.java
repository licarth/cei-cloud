package binpacking;
import java.util.List;

import binpacking.algs.FF;
import binpacking.algs.FFD;
import binpacking.algs.NF;
import binpacking.algs.NFD;
import binpacking.gens.UniformBPPGenerator;

import common.problem.InputDataException;

public class Main {

	public static void main(String[] args) throws InputDataException {
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
		FFD ffd = new FFD();
		FF ff = new FF();
		NF nf = new NF();
		NFD nfd = new NFD();
			
		BPP bpp = new BPP(10);		//IProblem definition
//		MartelloTothBPPLibraryGenerator gen = new MartelloTothBPPLibraryGenerator(bpp);	//IGenerator choice
//		OptimalUniformBPPGenerator gen = new OptimalUniformBPPGenerator(bpp, 200);	//IGenerator choice
		UniformBPPGenerator gen = new UniformBPPGenerator(bpp, 200);
		List<BPPInstance> l = gen.generateInstances(10);
//		System.out.println(l);
		for (BPPInstance iBpp : l) {
//			System.out.println(iBpp);
//			VizUtils.barChart(iBpp.getItemSizes(),iBpp.getProblem().getItemMinSize(), iBpp.getProblem().getItemMaxSize());
//			iBpp.checkUniformItemsRepartition();
//			VizUtils.barChart(iBpp, max);
			System.out.println(ff.solve(iBpp));
			System.out.println(nf.solve(iBpp));
//			System.out.println(nfd.solve(iBpp));
//			System.out.println(nf.solve(iBpp));
		}
//		------------------------------------
		
//		MartelloTothBPPLibraryGenerator gen = new MartelloTothBPPLibraryGenerator();
//		gen.generateInstances();
		
//		Set<BinType> binTypes = new HashSet<>();
//		binTypes.add(new BinType(10, 10));
//		binTypes.add(new BinType(100, 60));
//		binTypes.add(new BinType(15, 12));
//		VSCIFP p = new VSCIFP(binTypes, 10, 99999);
//		
//		CIFFD c = new CIFFD();
		
	}

}
