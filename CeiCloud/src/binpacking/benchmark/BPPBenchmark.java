package binpacking.benchmark;

import binpacking.BPP;
import binpacking.BPPInstance;
import binpacking.algs.BPPAlgorithm;
import binpacking.tomasik.TomasikBPPGenerator;

import common.benchmark.Benchmark;

public class BPPBenchmark extends Benchmark<BPP, BPPInstance, BPPAlgorithm, TomasikBPPGenerator>{

	public BPPBenchmark(BPP problem, BPPAlgorithm algorithm,
			TomasikBPPGenerator generator) {
		super(problem, algorithm, generator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getSampleSize() {
		// TODO Auto-generated method stub
		return 10;
	}

}
